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
			if(request.getUri() != null && request.getUri().length() >0){
				//利用响应对象完成响应过程
				HttpResponse response = new HttpResponse(socket.getOutputStream());
				
				//判断用户是否要完成登陆或者注册的功能
				if(request.getUri().startsWith("/LoginUser") || request.getUri().startsWith("/RegistUser")){
					service(request,response);
					return;
				}
				
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
					response.setStatus(HttpContext.CODE_NOTFOUND);
				}else{
					response.setStatus(HttpContext.CODE_OK);
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
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//完成登陆或注册，操作数据库的过程
	private void service(HttpRequest request,HttpResponse response){
		if(request.getUri().startsWith("/RegistUser")){
			
			Connection conn = null;
			PreparedStatement ps = null;
			
			try {
				//注册驱动，获取数据库连接
				conn = JDBCUtils.getConnection();
				//获取传输器
				String sql = "insert into user values(null,?,?)";
				ps = conn.prepareStatement(sql);
				
				//设置参数
				//获取用户名输入的用户名的值
				String name = request.getParameter("username");
				String pwd = request.getParameter("password");
				
				ps.setString(1, name);
				ps.setString(2, pwd);
				
				//执行SQL
				int rows = ps.executeUpdate();
				
				//遍历结果集
				System.out.println(rows);
				
				//给浏览器响应注册成功页面
				response.setProtocol(ServerContext.protocol);//响应的协议和版本号
				response.setStatus(HttpContext.CODE_OK);
				
				//响应注册成功文件
				File file = new File(ServerContext.webRoot + "/reg_success.html");
				response.setContentType(getContentTypeByFile(file));//响应类型
				response.setContentLength((int) file.length());//响应长度
				
				//读文件写文件
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
				//释放资源
				JDBCUtils.close(null, ps, conn);
			}
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
