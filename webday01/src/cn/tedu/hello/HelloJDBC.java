package cn.tedu.hello;
/**
 * �������������һ��jdbc��Ӧ��
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
	//��Ԫ���Է���hello
	//@Test + void
	
	/**
	 * jdbcʹ�÷���
	 * 
	 * 1.ע������
	 * 2.��ȡ��������
	 * 3.��ȡ������
	 * 4.ִ��sql
	 * 5.���������
	 * 6.�ͷ���Դ
	 * @throws SQLException 
	 */
	
	@Test
	public void hello()/* throws SQLException*/{
		ResultSet rs = null;
		Connection conn = null;
		Statement st = null;
		
		try {
			//1.ע������ �� com.mysql.jdbc.Driver��
			DriverManager.registerDriver(new Driver());
			
			//2.��ȡ��������
			String url = "jdbc:mysql://localhost:3306/jt_bd";
			String user = "root";
			String password = "root";
			/*Connection */conn = DriverManager.getConnection(url, user, password);
			
			//3.��ȡ������
			/*Statement */st = conn.createStatement();
			
			//4.ִ��sql
			String sql = "select * from account";
			/*ResultSet */rs = st.executeQuery(sql);
			
			//5.���������
			while(rs.next()){
				String id = rs.getString(1);
				
				String name = rs.getString(2);
				
				String money = rs.getString(3);
				
				System.out.println(id + name + money);
			}
/*			//6.�ͷ���Դ,���ſ������Ź�
			rs.close();
			st.close();
			conn.close();*/
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			
			//6.�ͷ���Դ,���ſ������Ź�
			if(rs != null){	//��ֹ��ָ���쳣
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
