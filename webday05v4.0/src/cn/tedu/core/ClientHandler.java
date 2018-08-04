package cn.tedu.core;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import cn.tedu.context.HttpContext;
import cn.tedu.context.ServerContext;
import cn.tedu.http.HttpRequest;
import cn.tedu.http.HttpResponse;
import cn.tedu.util.JDBCUtils;

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
			if(request.getUri() != null && request.getUri().length() >0){
				//������Ӧ���������Ӧ����
				HttpResponse response = new HttpResponse(socket.getOutputStream());
				
				//�ж��û��Ƿ�Ҫ��ɵ�½����ע��Ĺ���
				if(request.getUri().startsWith("/LoginUser") || request.getUri().startsWith("/RegistUser")){
					service(request,response);
					return;
				}
				
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
					response.setStatus(HttpContext.CODE_NOTFOUND);
				}else{
					response.setStatus(HttpContext.CODE_OK);
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
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//��ɵ�½��ע�ᣬ�������ݿ�Ĺ���
	private void service(HttpRequest request,HttpResponse response){
		if(request.getUri().startsWith("/RegistUser")){
			
			Connection conn = null;
			PreparedStatement ps = null;
			
			try {
				//ע����������ȡ���ݿ�����
				conn = JDBCUtils.getConnection();
				//��ȡ������
				String sql = "insert into user values(null,?,?)";
				ps = conn.prepareStatement(sql);
				
				//���ò���
				//��ȡ�û���������û�����ֵ
				String name = request.getParameter("username");
				String pwd = request.getParameter("password");
				
				ps.setString(1, name);
				ps.setString(2, pwd);
				
				//ִ��SQL
				int rows = ps.executeUpdate();
				
				//���������
				System.out.println(rows);
				
				//���������Ӧע��ɹ�ҳ��
				response.setProtocol(ServerContext.protocol);//��Ӧ��Э��Ͱ汾��
				response.setStatus(HttpContext.CODE_OK);
				
				//��Ӧע��ɹ��ļ�
				File file = new File(ServerContext.webRoot + "/reg_success.html");
				response.setContentType(getContentTypeByFile(file));//��Ӧ����
				response.setContentLength((int) file.length());//��Ӧ����
				
				//���ļ�д�ļ�
				BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
				
				byte[] b = new byte[(int) file.length()];
				bis.read(b);
				
				response.getOut().write(b);
				response.getOut().flush();
				
				socket.close();
								
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				//�ͷ���Դ
				JDBCUtils.close(null, ps, conn);
			}
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
