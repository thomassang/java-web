package cn.tedu.core;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * �������������������˵ĳ���
 * @author sj154
 *
 */
public class WebServer {
	//1.����ServerSocket�������������
	private ServerSocket server;
	
	//i--�����̳߳ض���
	private ExecutorService pool;
	
	//2.�ڹ��캯���г�ʼ��ServerSocket����
	public WebServer(){
		try {
			server = new ServerSocket(8080);	
			//ii--�ڹ��캯���У���ʼ���̳߳�
			pool = Executors.newFixedThreadPool(100);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//3.����start����
	//�����������󣬴���ҵ����Ӧ
	public void start(){
		try {
			while(true){
				//������������
				Socket socket = server.accept();
				
				//iii--����start����
				pool.execute(new ClientHandler(socket));
				//�����Ӧ��Ϣ
				/*������http��ʽ����Ӧģʽ
				OutputStream out = socket.getOutputStream();
				out.write("hello server!".getBytes());
				
				out.flush();*/
				
				//״̬��  content-length	content-Type
				
				/*��ȡ����ClientHandr���У�
				 * 
				 *  
				PrintStream ps = new PrintStream(socket.getOutputStream());
				
				ps.println("HTTP/1.1 200 OK");//״̬��
				ps.println("Content-Type:text/html");//Content-Type
				
				String data = "hello server~~~";
				ps.println("Content-Length:"+ data.length());//content-length
				ps.println();//�հ��С���	
				//��Ӧʵ������
				ps.write(data.getBytes());
				ps.flush();
				socket.close();*/
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//4.����main����������������
	public static void main(String[] args) {
		WebServer server = new WebServer();
		server.start();
	}
}
