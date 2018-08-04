package cn.tedu.core;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * 这个类用来完成webServer类的优化，提取代码。
 * 1.创建线程类
 * 2.创建代表客户端的Socket对象
 * 3.创建构造函数，传入Socket独享并保存在类中
 * 4.重写run方法
 */
//		1.创建线程类
public class ClientHandler implements Runnable{
	//2.创建代表客户端的Socket对象
	private Socket socket;
	
	//3.创建构造函数，传入Socket对象并保存在类中
	public ClientHandler(Socket socket) {
		this.socket = socket;	
	}
	
	//4.重写run方法
	@Override
	public void run() {
		try {
			//获取输入流
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(
							socket.getInputStream()));
			//读取请求行
			//str ---> GET /index.html HTTP/1.1
			String str = reader.readLine();
			/*System.out.println(str);*/
			
			//获取访问的资源路径
			//按照空格切割字符串strs->[GET,/index.html,HTTP/1.1]
			if(str != null && str.length() > 0){//防止空指针异常
				String[] strs = str.split(" ");
				String uri = strs[1];
				//设置网站的默认主页
				if(uri.equals("/")){
					uri = "/index.html";
				}
				
				
				//创建PrintStream对象
				PrintStream ps = new PrintStream(
						socket.getOutputStream());
				//拼接响应头，状态行
				ps.println("HTTP/1.1 200 OK");
				ps.println("Content-Type:text/html");
				//	String data1 = "hello Server!!!";
				//	ps.println("Content-Length:" + data.length());
				/*
			String data = "大红都啊的话是发快递呢呢刚看到进大家符合";
			ps.println("Content-Length:" + data.getBytes().length);
			ps.println();
			响应头只能出现一次
		//	ps.write(data1.getBytes());
			ps.write(data.getBytes());
				 */
				//向浏览器输出网页文件
				/*File file = new File("WebContent/index.html");//读取指定位置的文件*/
				File file = new File("WebContent" + uri);
				ps.println("Content-Length:" + file.length());
				ps.println();
				
				//响应网页文件
				BufferedInputStream bis = new BufferedInputStream(
						new FileInputStream(file));
				
				byte[] bs = new byte[(int) file.length()];
				bis.read(bs);
				
				//响应
				ps.write(bs);
				ps.flush();
				socket.close();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
