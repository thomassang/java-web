package cn.tedu.test;
/**
 * �������������jdbc������
 * @author sj154
 *
 */

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import cn.tedu.util.JDBCUtils;

public class UtilsTest {
	//��ѯuser�����������
	@Test
	public void utils(){
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			//1.ע������
			//2.��ȡ��������
			conn = JDBCUtils.getConnection();
			//3.��ȡ������
			st = conn.createStatement();
			//4.ִ��sql
			String sql = "select * from user";
			rs = st.executeQuery(sql);
			
			//5.����
			while(rs.next()){
				//��ȡid�е�ֵ
				String id = rs.getString(1);
				//��ȡusername�е�ֵ
				String username = rs.getString(2);
				//��ȡmoney�е�ֵ
				String money = rs.getString(3);
				
				System.out.println(id + ", " + username + ", " + money);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//6.�ͷ���Դ
			JDBCUtils.close(rs, st, conn);
		}
		
		
	}
}
