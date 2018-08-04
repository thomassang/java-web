package cn.tedu.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cn.tedu.context.ServerContext;

/**
 * 这个类用来代表服务器端的程序
 * @author sj154
 *
 */
public class WebServer {
	//1.声明ServerSocket，代表服务器端
	private ServerSocket server;
	
	//i--声明线程池对象
	private ExecutorService pool;
	
	//2.在构造函数中初始化ServerSocket对象
	public WebServer(){
		try {
			server = new ServerSocket(ServerContext.port);	
			//ii--在构造函数中，初始化线程池
			pool = Executors.newFixedThreadPool(ServerContext.maxSize);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//3.创建start方法
	//用来接收请求，处理业务，响应
	public void start(){
		try {
			while(true){
				//用来接收请求
				Socket socket = server.accept();
				
				//iii--改造start方法
				pool.execute(new ClientHandler(socket));	
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//4.创建main方法，启动服务器
	public static void main(String[] args) {
		WebServer server = new WebServer();
		server.start();
	}
}
