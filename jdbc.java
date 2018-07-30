package cn.tedu.test;
/**
 * 这个类用来测试jdbc工具类
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
	//查询user表的所有数据
	@Test
	public void utils(){
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			//1.注册驱动
			//2.获取数据连接
			conn = JDBCUtils.getConnection();
			//3.获取传输器
			st = conn.createStatement();
			//4.执行sql
			String sql = "select * from user";
			rs = st.executeQuery(sql);
			
			//5.遍历
			while(rs.next()){
				//获取id列的值
				String id = rs.getString(1);
				//获取username列的值
				String username = rs.getString(2);
				//获取money列的值
				String money = rs.getString(3);
				
				System.out.println(id + ", " + username + ", " + money);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//6.释放资源
			JDBCUtils.close(rs, st, conn);
		}
		
		
	}
}
