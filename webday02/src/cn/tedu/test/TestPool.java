package cn.tedu.test;
/**
 * ������������c3p0���ӳصĲ���
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
		//ɾ����dept���е�idΪ1������
		Connection conn = null;
		PreparedStatement ps = null;
		//c3p0���ӳ�
		ComboPooledDataSource pool = new ComboPooledDataSource();
		
		try {
			//�������ݿ����Ӳ���
		/*	pool.setDriverClass("com.mysql.jdbc.Driver");
			pool.setJdbcUrl("jdbc:mysql:///jt_db");
			pool.setUser("root");
			pool.setPassword("root");*/
			//1.ע��������2.��ȡ���ݿ�����
			conn = pool.getConnection();
			
			//3.��ȡ������
			String sql = "delete from dept where id between ? and ?";
			ps = conn.prepareStatement(sql);
			
			//���ò���
			//��һ��1��
			ps.setString(1, "102143");
			ps.setString(2, "102155");
			
			int rows = ps.executeUpdate();
			
			System.out.println(rows);
			
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//�ͷ���Դ
			JDBCUtils.close(null, ps, conn);
			//���س���
//			pool.returnConnection(conn);
		}
	}
}
