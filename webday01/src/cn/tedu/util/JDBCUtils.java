package cn.tedu.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

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
			
			//��ȡ�����ļ�
			ResourceBundle rb = ResourceBundle.getBundle("jdbc");
			
			//1.ע������
			Class.forName(rb.getString("driverClass"));
			//2.�ṩ���ݿ�����
			String url = rb.getString("jdbcUrl");//�������ݿ�
			String user = rb.getString("user");//�������ݿ���û���
			String password = rb.getString("password");//�����û���������
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
