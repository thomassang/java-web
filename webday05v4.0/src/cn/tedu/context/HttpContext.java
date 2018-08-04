package cn.tedu.context;

import java.util.HashMap;
import java.util.Map;

/**
 * 这个类用来封装http协议相关的信息
 * @author sj154
 *
 */
public class HttpContext {
	//1.声明响应的状态码，状态码的描述信息
	public static final int CODE_OK = 200;
	public static final int CODE_ERROR = 500;
	public static final int CODE_NOTFOUND = 404;
	
	//状态码的描述信息
	public static final String DESC_OK = "OK";
	public static final String DESC_ERROR = "ERROR";
	public static final String DESC_NOTFOUND = "NOT FOUND";
	
	//封装成map的结构
	//key存状态码，value存描述
	public static Map<Integer, String> map = new HashMap<Integer, String>();
	
	//在静态代码块中设置map的值
	static{
		map.put(CODE_OK, DESC_OK);
		map.put(CODE_ERROR, DESC_ERROR);
		map.put(CODE_NOTFOUND, DESC_NOTFOUND);
	}
	
}
