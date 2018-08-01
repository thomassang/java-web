package cn.tedu.test;
/**
 * �������������Զ������ӳصĲ���
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
		//ɾ����dept���е�idΪ1������
		Connection conn = null;
		PreparedStatement ps = null;
		MyPool pool = new MyPool();
		try {
			//1.ע��������2.��ȡ���ݿ�����
			conn = pool.getConnection();
			
			//3.��ȡ������
			String sql = "delete from dept where id between ? and ?";
			ps = conn.prepareStatement(sql);
			
			//���ò���
			//��һ��1��
			ps.setString(1, "3997");
			ps.setString(2, "102025");
			
			int rows = ps.executeUpdate();
			
			System.out.println(rows);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//�ͷ���Դ
			JDBCUtils.close(null, ps, null);
			
			//���س���
			pool.returnConnection(conn);
		}
	}
}
