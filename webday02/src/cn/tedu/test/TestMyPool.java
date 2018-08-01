package cn.tedu.test;
/**
 * 这个类用来完成自定义连接池的测试
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Test;

import cn.tedu.pool.MyPool;
import cn.tedu.util.JDBCUtils;

public class TestMyPool {
	
	@Test
	public void mytest(){
		//删除的dept表中的id为1的数据
		Connection conn = null;
		PreparedStatement ps = null;
		MyPool pool = new MyPool();
		try {
			//1.注册驱动，2.获取数据库连接
			conn = pool.getConnection();
			
			//3.获取传输器
			String sql = "delete from dept where id between ? and ?";
			ps = conn.prepareStatement(sql);
			
			//设置参数
			//第一个1是
			ps.setString(1, "3997");
			ps.setString(2, "102025");
			
			int rows = ps.executeUpdate();
			
			System.out.println(rows);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//释放资源
			JDBCUtils.close(null, ps, null);
			
			//还回池中
			pool.returnConnection(conn);
		}
	}
}
