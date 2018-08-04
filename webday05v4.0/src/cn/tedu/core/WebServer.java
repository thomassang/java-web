package cn.tedu.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cn.tedu.context.ServerContext;

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
			server = new ServerSocket(ServerContext.port);	
			//ii--�ڹ��캯���У���ʼ���̳߳�
			pool = Executors.newFixedThreadPool(ServerContext.maxSize);
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
