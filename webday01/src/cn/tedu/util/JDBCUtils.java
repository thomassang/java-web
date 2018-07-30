package cn.tedu.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ������������jdbc������Ŀ���
 * @author sj154
 *
 */
public class JDBCUtils {
	
	//6.2.1 ˽�г�Ա���캯��,��ֹ���ֱ�Ӵ�������
	private JDBCUtils(){};
	
	//6.2.2�ṩ��̬����getConnection�����������ṩ�������Ӷ���
	public static Connection getConnection(){
		try {
			//1.ע������
			Class.forName("com.mysql.jdbc.Driver");
			//2.�ṩ���ݿ�����
			String url = "jdbc:mysql:///jt_bd";//�������ݿ�
			String user = "root";//�������ݿ���û���
			String password = "root";//�����û���������
			Connection conn = DriverManager.getConnection(url, user, password);
			
			return conn;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	//6.2.3 �ṩ��̬����close�������ͷ���Դ
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
