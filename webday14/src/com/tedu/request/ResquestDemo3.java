package com.tedu.request;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author canyin
 * @time:2018��8��16������2:39:05
 *
 */
public class ResquestDemo3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("ResquestDemo3.doGet()");
		
		//����ת����ResquestDemo4����
		/**
		 * ����ת����ֻ����ͬһ��webӦ���ڲ���Դ֮�����ת��
		 * �����ǲ�ͬ��webӦ��
		 * �����ǲ��õ���������֮�����Դ��ת
		 * ��ˣ�ת��ʱ��ת������Դֻ��Ҫд��Դ��·����
		 * ����Ҫд�������ƺ�web����������·����
		 */
		request.getRequestDispatcher("/ResquestDemo4").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

