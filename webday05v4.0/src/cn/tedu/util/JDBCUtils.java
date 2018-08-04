package cn.tedu.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * 这个类用来完成jdbc工具类的开发
 * @author sj154
 *
 */
public class JDBCUtils {
	
	//6.2.1 私有成员构造函数,防止外界直接创建对象
	private JDBCUtils(){};
	
	//6.2.2提供静态方法getConnection。用来对外提供数据连接对象
	public static Connection getConnection(){
		try {
			
			//读取属性文件
			ResourceBundle rb = ResourceBundle.getBundle("jdbc");
			
			//1.注册驱动
			Class.forName(rb.getString("driverClass"));
			//2.提供数据库连接
			String url = rb.getString("jdbcUrl");//设置数据库
			String user = rb.getString("user");//设置数据库的用户名
			String password = rb.getString("password");//设置用户名的密码
			Connection conn = DriverManager.getConnection(url, user, password);
			
			return conn;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	//6.2.3 提供静态方法close，用来释放资源
	public static void close(ResultSet rs, Statement st, Connection conn){
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				rs = null;
			}			
		}
		
		if(st != null){
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				st = null;
			}			
		}
		
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				conn = null;
			}			
		}
	}

}
