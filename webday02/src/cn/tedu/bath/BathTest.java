package cn.tedu.bath;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import cn.tedu.util.JDBCUtils;

/**
 * 这个类用来完成jdbc的批处理功能。
 * @author sj154
 *
 */
public class BathTest {
	//方式一
	@Test
	public void statementTest(){
		Connection conn = null;
		Statement st = null;
		try {
			//注册驱动，获取数据库连接
			conn = JDBCUtils.getConnection();
			//a````关闭jdbc的事务管理
			conn.setAutoCommit(false);
			
			long ct = System.currentTimeMillis();
			//获取传输器
			st = conn.createStatement();
			//执行sql
			for (int i = 1; i <= 100; i++) {
				String sql = "insert into dept values(null,'"+i+"')";
				//吧sql达成一个批次
				st.addBatch(sql);
			}
			//统一发给数据库服务器 
			st.executeBatch();
			
			conn.commit();
			long end = System.currentTimeMillis()-ct;
			System.out.println(end);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCUtils.close(null, st, conn);
		}
	}
	//方式二
	@Test
	public void PSTest(){
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			//注册驱动，获取数据库连接
			conn = JDBCUtils.getConnection();
			//a````关闭jdbc的事务管理
			conn.setAutoCommit(false);
			
			long ct = System.currentTimeMillis();
			//获取传输器
			String sql = "insert into dept values(null,?)";
			ps = conn.prepareStatement(sql);
			//设置参数
			for (int i = 1; i <= 100; i++) {
				ps.setString(1, "PS"+i);
				//把SQL打成一个批，
				ps.addBatch();
			}
			//统一发给数据库服务器 
			ps.executeBatch();
			
			//b`````手动提交事务
			conn.commit();
			
			long end = System.currentTimeMillis()-ct;
			System.out.println(end);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCUtils.close(null, ps, conn);
		}
	}
}
