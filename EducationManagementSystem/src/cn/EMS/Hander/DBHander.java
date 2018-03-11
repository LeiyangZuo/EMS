package cn.EMS.Hander;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBHander {

	private static String DB_URL = "jdbc:mysql://localhost:3306/DB_EMS?useUnicode=true&characterEncoding=UTF8";
	
	public static Connection GetConnection(){
	
		//获取连接
		Connection connection = null;
		
		//注册数据库类
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			//通过DriverManager尝试获取连接
			connection = DriverManager.getConnection(DB_URL, "root", "");
			
			return connection;
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//如果没有成功获取，这返回null
		return connection;
		
	}
	
	
	
	//执行没有返回值的SQL语句
	//但是这里还是返回了一个整数，表示影响了数据库中的多少行
	public static int ExecuteNonQuery(String query, Object... args){
		
		//获取数据库连接
		Connection connection = GetConnection();
		
		try {
			
			PreparedStatement pstmt = connection.prepareStatement(query);
			
			//将参数放入Statement中
			for(int i = 0; i < args.length; i++){
				
				pstmt.setObject(i + 1, args[i]);
			
			}
			
			int affectRows = pstmt.executeUpdate();
			
			//关闭Statement
			pstmt.close();
			
			//关闭数据库连接
			connection.close();
			
			return affectRows;
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	//返回有返回值的SQL语句
	public static ResultSet ExecuteQuery(String query, Object... args){
		
		//获取数据库连接
		Connection connection = GetConnection();
		
		try {
			
			PreparedStatement pstmt = connection.prepareStatement(query);
		
			//将参数放入Statement中
			for(int i = 0; i < args.length; i++){
				
				pstmt.setObject(i + 1, args[i]);
			
			}
			
			//返回ResultSet
			ResultSet rs = pstmt.executeQuery();
			
			return rs;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
}
