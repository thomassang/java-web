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
 * �������������Զ������ӳ�
 * 
 * 1.����MyPool�࣬ʵ��DataSource�ӿ�
 */
//����MyPool�࣬ʵ��DataSource�ӿ�
public class MyPool implements DataSource{
	//��������������������ݿ����Ӷ���
	static List<Connection> list = new LinkedList<Connection>();
	//��ʼ������
	static{
		for (int i = 0; i < 3; i++) {
			Connection conn = JDBCUtils.getConnection();
			
			list.add(conn);
		}
	}
	
	//�ṩgetConnection()�����������ṩ���ݿ�����
	@Override
	public Connection getConnection() throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = list.remove(0);//3--3?2
		
		System.out.println("���ӱ�����һ������ʣ��"+ list.size());
		
		return conn;
	}
	//�ṩreturnConnection�������س���
	public void returnConnection(Connection conn){
		try {
			if(conn != null && !conn.isClosed()){
				list.add(conn);
				System.out.println("���ӱ����أ����У�" + list.size());			
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
