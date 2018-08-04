package cn.tedu.core;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
			server = new ServerSocket(8080);	
			//ii--在构造函数中，初始化线程池
			pool = Executors.newFixedThreadPool(100);
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
				//输出响应信息
				/*不符合http格式的相应模式
				OutputStream out = socket.getOutputStream();
				out.write("hello server!".getBytes());
				
				out.flush();*/
				
				//状态行  content-length	content-Type
				
				/*提取到了ClientHandr类中：
				 * 
				 *  
				PrintStream ps = new PrintStream(socket.getOutputStream());
				
				ps.println("HTTP/1.1 200 OK");//状态行
				ps.println("Content-Type:text/html");//Content-Type
				
				String data = "hello server~~~";
				ps.println("Content-Length:"+ data.length());//content-length
				ps.println();//空白行··	
				//响应实体内容
				ps.write(data.getBytes());
				ps.flush();
				socket.close();*/
				
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
