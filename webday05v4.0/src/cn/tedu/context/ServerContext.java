package cn.tedu.context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * �����������ȡ��������ز���
 * 
 * @author sj154
 *
 */
public class ServerContext {
	//1.�����ĸ�����
	public static int port;//�������Ķ˿ں�
	public static int maxSize;//�߳���
	public static String protocol;//Э�����Ͱ汾��
	public static String webRoot;//��������Դ�ĸ�Ŀ¼
	
	//����404ҳ��
	public static String notFoundPage;
	
	//��ȡtypemappings��ֵ
	//map<ext��ֵ��type��ֵ>---map<String,String>
	public static Map<String, String> typeMap = new HashMap<String,String>();
	
	//2.�ھ�̬���������ɳ�ʼ��
	static{
		init();
	}

	//��ȡ�����ļ�����������ֵ
	private static void init() {
		try {
			//��Ϊ��ȡxml�����ļ��ĺ��Ķ���
			SAXReader reader = new SAXReader();	
			//��ȡָ��λ�õ��ļ�
			Document doc = reader.read("config/server.xml");
			
			//��ȡ��Ŀ¼�������
			Element server = doc.getRootElement();
			//���ݸ�����ȡ������µ��ӽڵ�service
			Element service = server.element("service");
			//����service��ȡconnect
			Element connect = service.element("connect");
			
			//��ȡconnect���ϵ�port������ֵ
			port = Integer.valueOf(connect.attributeValue("port"));
			//��ȡconnect���ϵ�maxSize������ֵ
			maxSize = Integer.valueOf(connect.attributeValue("maxSize"));
			//��ȡconnect���ϵ�protocol������ֵ
			protocol = connect.attributeValue("protocol");
			
			//��ȡwebRoot���ϵ�ֵ
			webRoot = service.elementText("webRoot");
			
			//��404ҳ�渳ֵ
			notFoundPage = service.elementText("not-found-page");
			
			List<Element> list = server.element("typemappings").elements();
			
			//����list����
			for (Element element : list) {
				//��ȡÿ��typemapping��ext��ֵ
				String Key = element.attributeValue("ext");
				
				//��ȡÿ��typemapping��type��ֵ
				String value = element.attributeValue("type");
				
				//��key��value����typeMap
				//{}
				typeMap.put(Key,value);
				
			}
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
