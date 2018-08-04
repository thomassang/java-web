package cn.tedu.core;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

import cn.tedu.context.ServerContext;
import cn.tedu.http.HttpRequest;
import cn.tedu.http.HttpResponse;

/**
 * ������������webServer����Ż�����ȡ���롣
 * 1.�����߳���
 * 2.��������ͻ��˵�Socket����
 * 3.�������캯��������Socket��������������
 * 4.��дrun����
 */
//		1.�����߳���
public class ClientHandler implements Runnable{
	//2.��������ͻ��˵�Socket����
	private Socket socket;

	//3.�������캯��������Socket���󲢱���������
	public ClientHandler(Socket socket) {
		this.socket = socket;	
	}

	//4.��дrun����
	@Override
	public void run() {
		try {
			//���������������������
			HttpRequest request = new HttpRequest(socket.getInputStream());

			//������Ӧ���������Ӧ����
			HttpResponse response = new HttpResponse(socket.getOutputStream());
			//���ò���
			response.setProtocol(ServerContext.protocol);
			//	response.setStatus(200);
			//response.setContentType("text/html");

			File file = new File(ServerContext.webRoot + request.getUri());

			response.setContentType(getContentTypeByFile(file));
			
			//�жϷ��ʵ��ļ��Ƿ����
			if(! file.exists()){
				//��ת��404.html
				file = new File(ServerContext.webRoot + "/" + ServerContext.notFoundPage);

				//����404״̬��
				response.setStatus(404);
			}else{
				response.setStatus(200);
			}

			response.setContentLength((int) file.length());

			//��Ӧ��ҳ�ļ�
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));

			byte[] bs = new byte[(int) file.length()];
			bis.read(bs);

			//��Ӧ
			response.getOut().write(bs);
			response.getOut().flush();

			socket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	//���ݷ����ļ��ĺ�׺����Ϊkey��ȥmap����value
	private String getContentTypeByFile(File file) {
		//fileName = index.html
		String fileName = file.getName();
		//�����һ��������и�
		//�����ļ�����ȡ�ļ��ĺ�׺��
		String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
		
		//�������ֺ�׺����ȥmap����value
		//{html,text/html  jpg,image/jpeg}
		String val = ServerContext.typeMap.get(ext);
		
		return val;
	}
}
