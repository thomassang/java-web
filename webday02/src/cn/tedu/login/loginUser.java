package cn.tedu.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import cn.tedu.util.JDBCUtils;

/**
 * 这个类有从来模拟用户登录过程
 * 
 * 1.创建一个main方法， 用来提示用户登录信息
 * 
 * 2.
 */
public class loginUser {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		//提示用户输入用户名
		System.out.println("请输入用户名：");
		String name = sc.nextLine();
		System.out.println("请输入用户密码：");
		String pwd = sc.nextLine();
		//调用login方法，完成查询
		login(name,pwd);
	}

	private static void login(String name, String pwd) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		//Statement st = null;
		ResultSet rs = null;
		try {
			//1.注册驱动器，2.获取数据库连接
			conn = JDBCUtils.getConnection();
			//3.获取传输器
			//预编译效果，先把sql骨架发送给数据库服务器
			//？ 叫做占位符
			//st = conn.createStatement();
			
			String sql = "select * from user where username =? and password =?";
			ps = conn.prepareStatement(sql);
			//设置参数，第一个参数的值，要和？号的顺序保持一致
			ps.setString(1, name);
			ps.setString(2, pwd);
			
			//4.执行SQL语句，根据用户名和密码查询user表
			rs = ps.executeQuery();
			//rs = st.executeQuery(sql);
			
			//5.遍历
			if(rs.next()){
				System.out.println("恭喜，登录成功");
			}else{
				System.out.println("输入的用户名或密码不正确");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.close(rs, ps, conn);
		}
	}
}
