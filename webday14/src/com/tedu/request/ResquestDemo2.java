package com.tedu.request;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ͨ��request��ȡ�������
 * @author canyin
 * @time:2018��8��16������10:33:31
 *
 */
public class ResquestDemo2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		//1.��ȡ�û���
		String username = request.getParameter("username");
		System.out.println("���֮ǰ��username = " + username);
		
		//ͨ�õĽ����ʽ��������get����post����������
		byte[] bs = username.getBytes("iso8859-1");
		username = new String( bs, "utf-8");
		
		//���Էֱ�ʹ��get��post�ύ�����û������鿴���ǰ�ͽ������û���
		System.out.println("���֮���username = " + username);
		*/
		/**
		 * ���ύ�������ĵ��ǳƣ�������ǳƴ�ӡ�ڿ���̨
		 * ����ǳ�������������
		 * �����get�ύ��ô��
		 * �����post�ύ��ô��
		 */
		
		String nickname = request.getParameter("nickname");
		System.out.println("���֮ǰ��nickname = " + nickname);
		
		request.setCharacterEncoding("utf-8");
		System.out.println("���֮���nickname = " + nickname);
		
		//2.��ȡ����
		String[] likes = request.getParameterValues("like");
		System.out.println("likes:" + Arrays.toString(likes));
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
