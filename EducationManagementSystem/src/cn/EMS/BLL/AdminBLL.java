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
	
	//���ݹ���Ա��Ϣ�õ�AdminModel
	public static AdminModel GetModel(String adminId){
		
		
		return AdminDAO.GetModel(adminId);
		
	}
	
	
	
	
	//�жϹ���Ա�Ƿ�Ϊ��������Ա
	public static Boolean IsSuperAdmin(String adminId){
		
		AdminModel admin = AdminDAO.GetModel(adminId);
		if(admin == null){
			//����Ա������
			return false;
		}
		else{
			//����Ա����
			//�ж��Ƿ��ǳ�������Ա
			return admin.getIsSuper();
		}
		
	}
	
	//�õ����й���Ա����Ϣ
	public static ArrayList<AdminModel> GetArrayList(){
		
		ResultSet rs = AdminBLL.GetList();
		
		//�����յ�arrayList
		ArrayList<AdminModel> adminList = new ArrayList<AdminModel>();
		
		//�����ݿ���ȡ���ݣ����ҷŵ�arrayList��
		try {
			while(rs.next()){
				
				//ͨ�����ݱ��е�һ����Ŀ������adminģ��
				AdminModel admin = new AdminModel();
				admin.setAdminId(rs.getString("AdminId"));
				admin.setName(rs.getString("Name"));
				admin.setPassword(rs.getString("Password"));
				admin.setIsSuper(rs.getBoolean("IsSuper"));
				
				//���Ѿ��������ݵ�AdminModel���뵽ArrayList��
				adminList.add(admin);
				
			}
		} catch (SQLException e) {

			e.printStackTrace();
		
		}
		
		return adminList;
		
	}
	
	
	//���������õ�AdminList
	public static ArrayList<AdminModel> GetArrayList(String strWhere, Object... args){
		
		ResultSet rs = AdminBLL.GetList(strWhere, args);

		//�����յ�arrayList
		ArrayList<AdminModel> adminList = new ArrayList<AdminModel>();
		
		//�����ݿ���ȡ���ݣ����ҷŵ�arrayList��
		try {
			while(rs.next()){
				
				//ͨ�����ݱ��е�һ����Ŀ������adminģ��
				AdminModel admin = new AdminModel();
				admin.setAdminId(rs.getString("AdminId"));
				admin.setName(rs.getString("Name"));
				admin.setPassword(rs.getString("Password"));
				admin.setIsSuper(rs.getBoolean("IsSuper"));
				
				//���Ѿ��������ݵ�AdminModel���뵽ArrayList��
				adminList.add(admin);
				
			}
		} catch (SQLException e) {

			e.printStackTrace();
		
		}
		
		return adminList;
		
	}
	
}
