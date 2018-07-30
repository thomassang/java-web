package cn.tedu.crud;
/**
 * ������������jdbc����ɾ�Ĳ�ҵ��
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
	//��user���в���һ����¼
	@Test
	public void add(){
		Connection conn = null;
		Statement st = null;
		int rows;
				
		try {
			//1.ע������
			Class.forName("com.mysql.jdbc.Driver");
			//2.��ȡ��������
			String url = "jdbc:mysql:///jt_bd";
			String user = "root";
			String password = "root";
			
			conn = DriverManager.getConnection(url, user, password);
			//3.��ȡ������
			st = conn.createStatement();
			
			//4.ִ��sql,����
			String sql = "insert into user values(null,'liupeixa','123456')";
			rows = st.executeUpdate(sql);
						
			//5.���������
			System.out.println(rows);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//6.�ͷ���Դ
			if(st != null){
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally{
					st = null;//�ֶ��ÿ�
				}
			}
			//��ֹ��ָ��
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
	
	//�޸�account����idΪ1�ļ�¼��moneyΪ200
	@Test
	public void update(){
		Connection conn = null;
		Statement st = null;
		try {
			//1.ע������
			Class.forName("com.mysql.jdbc.Driver");
			//2.��ȡ��������
			String url = "jdbc:mysql:///jt_bd";
			String user = "root";
			String password = "root";
			conn = DriverManager.getConnection(url, user, password);
			
			//3.��ȡ������
			st = conn.createStatement();
			//4.ִ��sql
			String sql = "update account set money=200 where id=1";
			int rows = st.executeUpdate(sql);
			//5.���������
			System.out.println(rows);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//6.�ͷ���Դ
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
			//1.ע������
			Class.forName("com.mysql.jdbc.Driver");
			
			//2.��ȡ���ݿ�����
			String url = "jdbc:mysql:///jt_bd";
			String user = "root";
			String password = "root";
			conn = DriverManager.getConnection(url, user, password);
			
			//3.��ȡ������
			st = conn.createStatement();
			
			//4.ִ��sql���,ɾ��
			String sql = "delete from user where id=4";
			int rows = st.executeUpdate(sql);
			
			//5.����
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
