package com.tedu.request;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 通过request获取请求参数
 * @author canyin
 * @time:2018年8月16日上午10:33:31
 *
 */
public class ResquestDemo2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		//1.获取用户名
		String username = request.getParameter("username");
		System.out.println("解决之前的username = " + username);
		
		//通用的解决方式：不管是get还是post都会起作用
		byte[] bs = username.getBytes("iso8859-1");
		username = new String( bs, "utf-8");
		
		//测试分别使用get和post提交中文用户名，查看解决前和解决后的用户名
		System.out.println("解决之后的username = " + username);
		*/
		/**
		 * 在提交传输中文的昵称，并获得昵称打印在控制台
		 * 解决昵称中文乱码问题
		 * 如果是get提交怎么办
		 * 如果是post提交怎么办
		 */
		
		String nickname = request.getParameter("nickname");
		System.out.println("解决之前的nickname = " + nickname);
		
		request.setCharacterEncoding("utf-8");
		System.out.println("解决之后的nickname = " + nickname);
		
		//2.获取爱好
		String[] likes = request.getParameterValues("like");
		System.out.println("likes:" + Arrays.toString(likes));
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
