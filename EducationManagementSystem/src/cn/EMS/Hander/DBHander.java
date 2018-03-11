package cn.EMS.Hander;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBHander {

	private static String DB_URL = "jdbc:mysql://localhost:3306/DB_EMS?useUnicode=true&characterEncoding=UTF8";
	
	public static Connection GetConnection(){
	
		//��ȡ����
		Connection connection = null;
		
		//ע�����ݿ���
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			//ͨ��DriverManager���Ի�ȡ����
			connection = DriverManager.getConnection(DB_URL, "root", "");
			
			return connection;
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//���û�гɹ���ȡ���ⷵ��null
		return connection;
		
	}
	
	
	
	//ִ��û�з���ֵ��SQL���
	//�������ﻹ�Ƿ�����һ����������ʾӰ�������ݿ��еĶ�����
	public static int ExecuteNonQuery(String query, Object... args){
		
		//��ȡ���ݿ�����
		Connection connection = GetConnection();
		
		try {
			
			PreparedStatement pstmt = connection.prepareStatement(query);
			
			//����������Statement��
			for(int i = 0; i < args.length; i++){
				
				pstmt.setObject(i + 1, args[i]);
			
			}
			
			int affectRows = pstmt.executeUpdate();
			
			//�ر�Statement
			pstmt.close();
			
			//�ر����ݿ�����
			connection.close();
			
			return affectRows;
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	//�����з���ֵ��SQL���
	public static ResultSet ExecuteQuery(String query, Object... args){
		
		//��ȡ���ݿ�����
		Connection connection = GetConnection();
		
		try {
			
			PreparedStatement pstmt = connection.prepareStatement(query);
		
			//����������Statement��
			for(int i = 0; i < args.length; i++){
				
				pstmt.setObject(i + 1, args[i]);
			
			}
			
			//����ResultSet
			ResultSet rs = pstmt.executeQuery();
			
			return rs;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
}
