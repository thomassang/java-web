package com.tedu.response;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 向客户端发送数据
 * @author canyin
 * @time:2018年8月16日下午5:12:30
 *
 */
public class ResponseDemo1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		//1.getOutputStream()
		response.setContentType("text/html;charset=utf-8");
		ServletOutputStream out = response.getOutputStream();
		out.write("hello".getBytes("utf-8"));
		out.write("你好你好".getBytes("utf-8"));
		*/
		//2.getWrite()
		/**
		 * 这行代码会通知服务器使用utf-8来发送数据
		 * 还会通知浏览器使用utf-8接受数据
		 */
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("哈喽哈喽你好啊");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
