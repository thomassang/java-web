package com.tedu.response;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ��ͻ��˷�������
 * @author canyin
 * @time:2018��8��16������5:12:30
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
		out.write("������".getBytes("utf-8"));
		*/
		//2.getWrite()
		/**
		 * ���д����֪ͨ������ʹ��utf-8����������
		 * ����֪ͨ�����ʹ��utf-8��������
		 */
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("��ඹ����ð�");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
