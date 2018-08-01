package cn.tedu.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import cn.tedu.util.JDBCUtils;

/**
 * ������д���ģ���û���¼����
 * 
 * 1.����һ��main������ ������ʾ�û���¼��Ϣ
 * 
 * 2.
 */
public class loginUser {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		//��ʾ�û������û���
		System.out.println("�������û�����");
		String name = sc.nextLine();
		System.out.println("�������û����룺");
		String pwd = sc.nextLine();
		//����login��������ɲ�ѯ
		login(name,pwd);
	}

	private static void login(String name, String pwd) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		//Statement st = null;
		ResultSet rs = null;
		try {
			//1.ע����������2.��ȡ���ݿ�����
			conn = JDBCUtils.getConnection();
			//3.��ȡ������
			//Ԥ����Ч�����Ȱ�sql�Ǽܷ��͸����ݿ������
			//�� ����ռλ��
			//st = conn.createStatement();
			
			String sql = "select * from user where username =? and password =?";
			ps = conn.prepareStatement(sql);
			//���ò�������һ��������ֵ��Ҫ�ͣ��ŵ�˳�򱣳�һ��
			ps.setString(1, name);
			ps.setString(2, pwd);
			
			//4.ִ��SQL��䣬�����û����������ѯuser��
			rs = ps.executeQuery();
			//rs = st.executeQuery(sql);
			
			//5.����
			if(rs.next()){
				System.out.println("��ϲ����¼�ɹ�");
			}else{
				System.out.println("������û��������벻��ȷ");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.close(rs, ps, conn);
		}
	}
}
