package com.tedu.request;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author canyin
 * @time:2018年8月16日下午2:39:05
 *
 */
public class ResquestDemo3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("ResquestDemo3.doGet()");
		
		//请求转发给ResquestDemo4处理
		/**
		 * 请求转发，只能是同一个web应用内部资源之间的跳转，
		 * 不能是不同的web应用
		 * 或者是不用的虚拟主机之间的资源跳转
		 * 因此，转发时，转发的资源只需要写资源的路径，
		 * 不需要写主机名称和web主机的虚拟路径。
		 */
		request.getRequestDispatcher("/ResquestDemo4").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

