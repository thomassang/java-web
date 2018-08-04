package cn.tedu.core;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

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
			response.setProtocol("HTTP/1.1");
			response.setStatus(200);
			response.setContentType("text/html");
			
			File file = new File("WebContent" + request.getUri());
			response.setContentLength((int) file.length());

			//��Ӧ��ҳ�ļ�
			BufferedInputStream bis = new BufferedInputStream(
					new FileInputStream(file));

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
}
