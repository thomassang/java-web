package com.tedu.request;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author canyin
 * @time:2018年8月16日上午10:13:30
 *
 */

//Request获取客户机的基本信息，获取请求头信息
public class ResquestDemo1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.getRemoteAddr()-- 获取ip地址
		String ip = request.getRemoteAddr();
		System.out.println("ip: " + ip);
		
		//2.getContextPath()--获取web应用的虚拟路径
		String path = request.getContextPath();
		System.out.println("path: " + path);
		
		//3.getHeader() --获取请求头信息
		String host = request.getHeader("host");
		System.out.println(host);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
