package cn.tedu.core;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * ������������webServer����Ż�����ȡ���롣
 * 1.�����߳���
 * 2.��������ͻ��˵�Socket����
 * 3.�������캯��������Socket��������������
 * 4.��дrun����
 */
//		1.�����߳���
public class ClientHandler implements Runnable{
	//2.��������ͻ��˵�Socket����
	private Socket socket;
	
	//3.�������캯��������Socket���󲢱���������
	public ClientHandler(Socket socket) {
		this.socket = socket;	
	}
	
	//4.��дrun����
	@Override
	public void run() {
		try {
			//��ȡ������
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(
							socket.getInputStream()));
			//��ȡ������
			//str ---> GET /index.html HTTP/1.1
			String str = reader.readLine();
			/*System.out.println(str);*/
			
			//��ȡ���ʵ���Դ·��
			//���տո��и��ַ���strs->[GET,/index.html,HTTP/1.1]
			if(str != null && str.length() > 0){//��ֹ��ָ���쳣
				String[] strs = str.split(" ");
				String uri = strs[1];
				//������վ��Ĭ����ҳ
				if(uri.equals("/")){
					uri = "/index.html";
				}
				
				
				//����PrintStream����
				PrintStream ps = new PrintStream(
						socket.getOutputStream());
				//ƴ����Ӧͷ��״̬��
				ps.println("HTTP/1.1 200 OK");
				ps.println("Content-Type:text/html");
				//	String data1 = "hello Server!!!";
				//	ps.println("Content-Length:" + data.length());
				/*
			String data = "��춼���Ļ��Ƿ�������ظտ�������ҷ���";
			ps.println("Content-Length:" + data.getBytes().length);
			ps.println();
			��Ӧͷֻ�ܳ���һ��
		//	ps.write(data1.getBytes());
			ps.write(data.getBytes());
				 */
				//������������ҳ�ļ�
				/*File file = new File("WebContent/index.html");//��ȡָ��λ�õ��ļ�*/
				File file = new File("WebContent" + uri);
				ps.println("Content-Length:" + file.length());
				ps.println();
				
				//��Ӧ��ҳ�ļ�
				BufferedInputStream bis = new BufferedInputStream(
						new FileInputStream(file));
				
				byte[] bs = new byte[(int) file.length()];
				bis.read(bs);
				
				//��Ӧ
				ps.write(bs);
				ps.flush();
				socket.close();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
