package cn.tedu.bath;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import cn.tedu.util.JDBCUtils;

/**
 * ������������jdbc���������ܡ�
 * @author sj154
 *
 */
public class BathTest {
	//��ʽһ
	@Test
	public void statementTest(){
		Connection conn = null;
		Statement st = null;
		try {
			//ע����������ȡ���ݿ�����
			conn = JDBCUtils.getConnection();
			//a````�ر�jdbc���������
			conn.setAutoCommit(false);
			
			long ct = System.currentTimeMillis();
			//��ȡ������
			st = conn.createStatement();
			//ִ��sql
			for (int i = 1; i <= 100; i++) {
				String sql = "insert into dept values(null,'"+i+"')";
				//��sql���һ������
				st.addBatch(sql);
			}
			//ͳһ�������ݿ������ 
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
	//��ʽ��
	@Test
	public void PSTest(){
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			//ע����������ȡ���ݿ�����
			conn = JDBCUtils.getConnection();
			//a````�ر�jdbc���������
			conn.setAutoCommit(false);
			
			long ct = System.currentTimeMillis();
			//��ȡ������
			String sql = "insert into dept values(null,?)";
			ps = conn.prepareStatement(sql);
			//���ò���
			for (int i = 1; i <= 100; i++) {
				ps.setString(1, "PS"+i);
				//��SQL���һ������
				ps.addBatch();
			}
			//ͳһ�������ݿ������ 
			ps.executeBatch();
			
			//b`````�ֶ��ύ����
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
