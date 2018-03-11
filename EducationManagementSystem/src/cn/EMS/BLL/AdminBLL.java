package cn.EMS.BLL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cn.EMS.DAO.AdminDAO;
import cn.EMS.Model.AdminModel;

public class AdminBLL {

	public static Boolean Exist(String adminId){
		
		return AdminDAO.Exist(adminId);
		
	}
	
	public static Boolean Insert(AdminModel admin){
		
		return AdminDAO.Insert(admin);
		
	}
	
	public static Boolean Delete(String adminId){
		
		return AdminDAO.Delete(adminId);
		
	}
	
	public static Boolean Update(AdminModel admin){
		
		return AdminDAO.Update(admin);
		
	}
	
	public static ResultSet GetList(){
		
		return AdminDAO.GetList();
		
	}
	
	public static ResultSet GetList(String strWhere, Object... args){
		
		return AdminDAO.GetList(strWhere, args);
		
	}
	
	//根据管理员信息得到AdminModel
	public static AdminModel GetModel(String adminId){
		
		
		return AdminDAO.GetModel(adminId);
		
	}
	
	
	
	
	//判断管理员是否为超级管理员
	public static Boolean IsSuperAdmin(String adminId){
		
		AdminModel admin = AdminDAO.GetModel(adminId);
		if(admin == null){
			//管理员不存在
			return false;
		}
		else{
			//管理员存在
			//判断是否是超级管理员
			return admin.getIsSuper();
		}
		
	}
	
	//得到所有管理员的信息
	public static ArrayList<AdminModel> GetArrayList(){
		
		ResultSet rs = AdminBLL.GetList();
		
		//构建空的arrayList
		ArrayList<AdminModel> adminList = new ArrayList<AdminModel>();
		
		//从数据库中取数据，并且放到arrayList中
		try {
			while(rs.next()){
				
				//通过数据表中的一个条目来创建admin模型
				AdminModel admin = new AdminModel();
				admin.setAdminId(rs.getString("AdminId"));
				admin.setName(rs.getString("Name"));
				admin.setPassword(rs.getString("Password"));
				admin.setIsSuper(rs.getBoolean("IsSuper"));
				
				//把已经存入数据的AdminModel放入到ArrayList中
				adminList.add(admin);
				
			}
		} catch (SQLException e) {

			e.printStackTrace();
		
		}
		
		return adminList;
		
	}
	
	
	//根据条件得到AdminList
	public static ArrayList<AdminModel> GetArrayList(String strWhere, Object... args){
		
		ResultSet rs = AdminBLL.GetList(strWhere, args);

		//构建空的arrayList
		ArrayList<AdminModel> adminList = new ArrayList<AdminModel>();
		
		//从数据库中取数据，并且放到arrayList中
		try {
			while(rs.next()){
				
				//通过数据表中的一个条目来创建admin模型
				AdminModel admin = new AdminModel();
				admin.setAdminId(rs.getString("AdminId"));
				admin.setName(rs.getString("Name"));
				admin.setPassword(rs.getString("Password"));
				admin.setIsSuper(rs.getBoolean("IsSuper"));
				
				//把已经存入数据的AdminModel放入到ArrayList中
				adminList.add(admin);
				
			}
		} catch (SQLException e) {

			e.printStackTrace();
		
		}
		
		return adminList;
		
	}
	
}
