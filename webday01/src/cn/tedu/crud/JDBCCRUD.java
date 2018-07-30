package cn.tedu.crud;
/**
 * 这个类用来完成jdbc的增删改查业务
 * 
 * @author sj154
 *
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class JDBCCRUD {
	//向user表中插入一条记录
	@Test
	public void add(){
		Connection conn = null;
		Statement st = null;
		int rows;
				
		try {
			//1.注册驱动
			Class.forName("com.mysql.jdbc.Driver");
			//2.获取数据连接
			String url = "jdbc:mysql:///jt_bd";
			String user = "root";
			String password = "root";
			
			conn = DriverManager.getConnection(url, user, password);
			//3.获取传输器
			st = conn.createStatement();
			
			//4.执行sql,插入
			String sql = "insert into user values(null,'liupeixa','123456')";
			rows = st.executeUpdate(sql);
						
			//5.遍历结果集
			System.out.println(rows);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//6.释放资源
			if(st != null){
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally{
					st = null;//手动置空
				}
			}
			//防止空指针
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
	
	//修改account表中id为1的记录：money为200
	@Test
	public void update(){
		Connection conn = null;
		Statement st = null;
		try {
			//1.注册驱动
			Class.forName("com.mysql.jdbc.Driver");
			//2.获取数据连接
			String url = "jdbc:mysql:///jt_bd";
			String user = "root";
			String password = "root";
			conn = DriverManager.getConnection(url, user, password);
			
			//3.获取传输器
			st = conn.createStatement();
			//4.执行sql
			String sql = "update account set money=200 where id=1";
			int rows = st.executeUpdate(sql);
			//5.遍历结果集
			System.out.println(rows);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//6.释放资源
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
				} finally {
					conn = null;
				}				
			}
		}

	}
	
	@Test
	public void del(){
		Connection conn = null;
		Statement st = null;
		try {
			//1.注册驱动
			Class.forName("com.mysql.jdbc.Driver");
			
			//2.获取数据库连接
			String url = "jdbc:mysql:///jt_bd";
			String user = "root";
			String password = "root";
			conn = DriverManager.getConnection(url, user, password);
			
			//3.获取传输器
			st = conn.createStatement();
			
			//4.执行sql语句,删除
			String sql = "delete from user where id=4";
			int rows = st.executeUpdate(sql);
			
			//5.遍历
			System.out.println(rows);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
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

}
