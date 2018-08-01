package cn.tedu.pool;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import javax.sql.DataSource;

import cn.tedu.util.JDBCUtils;

/**
 * 这个类用来完成自定义连接池
 * 
 * 1.定义MyPool类，实现DataSource接口
 */
//定义MyPool类，实现DataSource接口
public class MyPool implements DataSource{
	//创建容器，用来存放数据库连接对象
	static List<Connection> list = new LinkedList<Connection>();
	//初始化容器
	static{
		for (int i = 0; i < 3; i++) {
			Connection conn = JDBCUtils.getConnection();
			
			list.add(conn);
		}
	}
	
	//提供getConnection()方法，对外提供数据库连接
	@Override
	public Connection getConnection() throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = list.remove(0);//3--3?2
		
		System.out.println("连接被拿走一个，还剩："+ list.size());
		
		return conn;
	}
	//提供returnConnection（）换回池里
	public void returnConnection(Connection conn){
		try {
			if(conn != null && !conn.isClosed()){
				list.add(conn);
				System.out.println("连接被还回，还有：" + list.size());			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
