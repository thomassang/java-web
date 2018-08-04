package cn.tedu.http;

import java.io.OutputStream;
import java.io.PrintStream;

import cn.tedu.context.HttpContext;

/**
 * �����������װ��Ӧ��Ϣ
 * 
 * @author sj154
 * 1.�����ĸ���Ӧ������set��get����
 * 2.�ڹ��캯���д���OutputStream����
 * 3.����getOut��������������
 */
public class HttpResponse {
	//1.�����ĸ���Ӧ������set��get����
	/**
	 * HTTP/1.1 200 OK
	 * Content-Type:text/html
	 * Content-Length:294
	 */
	private String protocol;//Э�����Ͱ汾��
	private int status;//״̬�� 404 500 403������
	private String contentType;//��Ӧ����
	private int contentLength;//��Ӧ����
	
	//2.�ڹ��캯���д���OutputStream����
	private OutputStream out;
	
	public HttpResponse(OutputStream out){
		this.out = out;
	}
	
	//3.����getOut��������������
	//��ֵ֤��Ӧͷֻ������һ��
	
	boolean isSend;//Ĭ����false
	public OutputStream getOut(){
		
		if(! isSend){
			//ƴ����Ӧͷ�Ĺ���
			PrintStream ps = new PrintStream(out);
			
			//ƴ��״̬��
			ps.println(protocol + " " + status + " " + HttpContext.map.get(status));
			//��Ӧ����
			ps.println("Content-Type:" + contentType);
			//��Ӧ����
			ps.println("Content-Length:" + contentLength);
			
			//�հ���
			ps.println();
			
			isSend = true;
		}
		return out;
	}
	
	//get��set����
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public int getContentLength() {
		return contentLength;
	}
	public void setContentLength(int contentLength) {
		this.contentLength = contentLength;
	}

}
