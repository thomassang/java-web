package com.tedu.request;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author canyin
 * @time:2018��8��16������10:13:30
 *
 */

//Request��ȡ�ͻ����Ļ�����Ϣ����ȡ����ͷ��Ϣ
public class ResquestDemo1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.getRemoteAddr()-- ��ȡip��ַ
		String ip = request.getRemoteAddr();
		System.out.println("ip: " + ip);
		
		//2.getContextPath()--��ȡwebӦ�õ�����·��
		String path = request.getContextPath();
		System.out.println("path: " + path);
		
		//3.getHeader() --��ȡ����ͷ��Ϣ
		String host = request.getHeader("host");
		System.out.println(host);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
