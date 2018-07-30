package cn.tedu.hello;
/**
 * 这个类用来做第一段jdbc的应用
 * @author sj154
 *
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import com.mysql.jdbc.Driver;

public class HelloJDBC {
	//单元测试方法hello
	//@Test + void
	
	/**
	 * jdbc使用方法
	 * 
	 * 1.注册驱动
	 * 2.获取数据连接
	 * 3.获取传输器
	 * 4.执行sql
	 * 5.遍历结果集
	 * 6.释放资源
	 * @throws SQLException 
	 */
	
	@Test
	public void hello()/* throws SQLException*/{
		ResultSet rs = null;
		Connection conn = null;
		Statement st = null;
		
		try {
			//1.注册驱动 导 com.mysql.jdbc.Driver包
			DriverManager.registerDriver(new Driver());
			
			//2.获取数据连接
			String url = "jdbc:mysql://localhost:3306/jt_bd";
			String user = "root";
			String password = "root";
			/*Connection */conn = DriverManager.getConnection(url, user, password);
			
			//3.获取传输器
			/*Statement */st = conn.createStatement();
			
			//4.执行sql
			String sql = "select * from account";
			/*ResultSet */rs = st.executeQuery(sql);
			
			//5.遍历结果集
			while(rs.next()){
				String id = rs.getString(1);
				
				String name = rs.getString(2);
				
				String money = rs.getString(3);
				
				System.out.println(id + name + money);
			}
/*			//6.释放资源,正着开，倒着关
			rs.close();
			st.close();
			conn.close();*/
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			
			//6.释放资源,正着开，倒着关
			if(rs != null){	//防止空指针异常
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally{
					rs = null;
				}
			}
			
			if(st != null){
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					 st = null;
				}
			}
			
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally{
					conn = null;
				}
				
			}
		}
	}
}
