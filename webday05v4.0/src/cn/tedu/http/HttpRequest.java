package cn.tedu.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * �����������װ������Ϣ
 * @author sj154
 * 1.���������������
 * 2.�ڹ��캯���г�ʼ���������
 * 3��
 */
public class HttpRequest {
	//1.���������������
	//GET /index.html HTTP/1.1
	private String method;//����ʽ
	private String uri;//������Դ��·��
	private String protocol;//Э�����汾��
	
	//����map����������û�������û�������
	private Map<String ,String> map = new HashMap<String, String>();
	
	//2.�ڹ��캯���г�ʼ���������
	public HttpRequest(InputStream in){
		try {
			//��ȡ������
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			//��ȡ������
			//GET /index.html HTTP/1.1
			//datas = [GET,/index.html,HTTP/1.1]
			String line = reader.readLine();
			
			if(line != null && line.length() >0){
				//����
				String[] datas = line.split(" ");
				
				method = datas[0];
				uri = datas[1];
				//������վ��Ĭ����ҳ
				if(uri.equals("/")){
					uri = "/index.html";
				}
				
				//��ȡ�û���������������ֵ
				//Ŀ�����õ����ݣ��������ݿ���
				if(uri != null && uri.contains("?")){
					//���� �ʺ� �и�
					//RegistUser?username=canyin&password=123456
					String xiaochuan = uri.split("\\?")[1];
					//xiaohcuan = username=canyin&password=123456
					//��&�������и�
					//strs = [username=canyin, password=123456]
					String[] strs = xiaochuan.split("&");
					//��������
					for (String string : strs) {
						String key = string.split("=")[0];
						String val = string.split("=")[1];
						
						//��װ��map
						map.put(key,val);
					}
				}
				protocol = datas[2];
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	//�ṩ�����ģ���������key����ѯvalue.��ѯusername�����ֵ
	public String getParameter(String key){
		return map.get(key);
	}
	
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

}
