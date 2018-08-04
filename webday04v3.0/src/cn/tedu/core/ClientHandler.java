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
			//利用请求对象完成请求过程
			HttpRequest request = new HttpRequest(socket.getInputStream());

			//利用响应对象完成响应过程
			HttpResponse response = new HttpResponse(socket.getOutputStream());
			//设置参数
			response.setProtocol(ServerContext.protocol);
			//	response.setStatus(200);
			//response.setContentType("text/html");

			File file = new File(ServerContext.webRoot + request.getUri());

			response.setContentType(getContentTypeByFile(file));
			
			//判断访问的文件是否存在
			if(! file.exists()){
				//跳转到404.html
				file = new File(ServerContext.webRoot + "/" + ServerContext.notFoundPage);

				//设置404状态码
				response.setStatus(404);
			}else{
				response.setStatus(200);
			}

			response.setContentLength((int) file.length());

			//响应网页文件
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));

			byte[] bs = new byte[(int) file.length()];
			bis.read(bs);

			//响应
			response.getOut().write(bs);
			response.getOut().flush();

			socket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	//根据访问文件的后缀名作为key，去map中找value
	private String getContentTypeByFile(File file) {
		//fileName = index.html
		String fileName = file.getName();
		//按最后一个点进行切割
		//根据文件名获取文件的后缀名
		String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
		
		//拿着名字后缀名，去map中找value
		//{html,text/html  jpg,image/jpeg}
		String val = ServerContext.typeMap.get(ext);
		
		return val;
	}
}
