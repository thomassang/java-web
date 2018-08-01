package cn.tedu.test;
/**
 * 这个类用来完成c3p0连接池的测试
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Test;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import cn.tedu.pool.MyPool;
import cn.tedu.util.JDBCUtils;

public class TestPool {
	
	@Test
	public void mytest(){
		//删除的dept表中的id为1的数据
		Connection conn = null;
		PreparedStatement ps = null;
		//c3p0连接池
		ComboPooledDataSource pool = new ComboPooledDataSource();
		
		try {
			//设置数据库连接参数
		/*	pool.setDriverClass("com.mysql.jdbc.Driver");
			pool.setJdbcUrl("jdbc:mysql:///jt_db");
			pool.setUser("root");
			pool.setPassword("root");*/
			//1.注册驱动，2.获取数据库连接
			conn = pool.getConnection();
			
			//3.获取传输器
			String sql = "delete from dept where id between ? and ?";
			ps = conn.prepareStatement(sql);
			
			//设置参数
			//第一个1是
			ps.setString(1, "102143");
			ps.setString(2, "102155");
			
			int rows = ps.executeUpdate();
			
			System.out.println(rows);
			
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//释放资源
			JDBCUtils.close(null, ps, conn);
			//还回池中
//			pool.returnConnection(conn);
		}
	}
}
