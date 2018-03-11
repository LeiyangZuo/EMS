package cn.EMS.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import cn.EMS.Hander.DBHander;
import cn.EMS.Model.AdminModel;

public class AdminDAO {

	// ����1������ID�ж��Ƿ����
	public static Boolean Exist(String adminId) {

		// ����SQL��ѯ���
		String query = "select * from Admin where AdminId=?";

		// a.�ж������Ƿ�Ϸ�����Ҫ�ж������Ƿ�Ϊnull�����Ϊnull�ⷵ��false
		if (adminId == null) {
			return false;
		} else {
			// b.ִ�в�ѯ���
			ResultSet rs = DBHander.ExecuteQuery(query, adminId);
			try {
				if (rs.next()) {
					// c.�����ѯ���ý�����գ���˵�����ڣ�����true
					// �����Ϊ��
					return true;
				}
				// c.�����ѯ���Ľ��Ϊ�գ���˵�������ڣ�����false
				else {
					return false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return false;

	}

	// ����2������ID�õ�AdminModel
	public static AdminModel GetModel(String adminId) {
		// a.�ж�ID�ĺϷ���
		if (adminId == null) {
			return null;
		} else {
			// b.��ѯ���ݿ⣬�õ�����
			String query = "select * from Admin where AdminId=?";
			ResultSet rs = DBHander.ExecuteQuery(query, adminId);
			try {
				if (rs.next()) {
					// c.�������
					// e.����һ��AdminModel������
					AdminModel admin = new AdminModel();
					admin.setAdminId(adminId);
					admin.setIsSuper(rs.getBoolean("IsSuper"));
					admin.setName(rs.getString("Name"));
					admin.setPassword(rs.getString("Password"));
					return admin;
				} else {
					// d.���������
					return null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return null;
	}

	// ����3������IDɾ��AdminModel
	public static Boolean Delete(String adminId) {

		// a.�жϲ����Ƿ�Ϸ�
		if (adminId == null) {
			return false;
		} else {
			// b.ִ��ɾ������
			String query = "Delete from Admin where AdminId=?";
			// c.ִ�в��õ�Ӱ���˶�����
			int el = DBHander.ExecuteNonQuery(query, adminId);
			if (el > 0) {
				// d.Ӱ�쳬����һ�У���˵��ɾ���ɹ�
				return true;
			} else {
				// e.Ӱ��Ϊ0��ɾ��ʧ��
				return false;
			}
		}
	}

	// ����4����һ��AdminModel���뵽���ݿ���
	public static Boolean Insert(AdminModel admin) {

		// a.�ж������Ƿ�Ϸ�
		if (admin == null) {
			// �����Adminģ��Ϊ�գ���ִ�в�����̣�����ʧ��
			return false;
		} else {
			if (AdminDAO.Exist(admin.getAdminId())) {
				// ���ڣ����ܲ���
				return false;
			} else {
				// b.��֤�ɹ����������
				String query = "insert into Admin (AdminId,Name,Password,IsSuper) values (?,?,?,?)";
				int el = DBHander.ExecuteNonQuery(query, admin.getAdminId(),
						admin.getName(), admin.getPassword(),
						admin.getIsSuper());
				if (el > 0) {
					// c.Ӱ�쳬��һ�У���˵������ɹ�
					return true;
				} else {
					// d.Ӱ��Ϊ0������ʧ��
					return false;
				}
			}
		}

	}

	// ����5������AdminId���µ�AdminModel��������
	public static Boolean Update(AdminModel admin) {
		// a.�ж������Ƿ�Ϸ�
		if (admin == null) {
			return false;
		} else {
			if (AdminDAO.Exist(admin.getAdminId())) {
				// b.���ڣ���˵�������޸�
				String query = "Update Admin set Name=?,Password=?,IsSuper=? where AdminId=?";
				int el = DBHander.ExecuteNonQuery(query, admin.getName(),
						admin.getPassword(), admin.getIsSuper(),
						admin.getAdminId());

				if (el > 0) {
					// d.Ӱ�����������0��˵�����³ɹ�
					return true;
				} else {
					// e.Ӱ�����������0��˵������ʧ��
					return false;
				}
			} else {
				// c.�����ڣ���˵���޷�����
				return false;
			}
		}
	}
	
	//����6�����AdminList
	public static ResultSet GetList(){
		
		//a.����SQL���
		String query = "Select * from Admin";
		return DBHander.ExecuteQuery(query);
		
	}
	
	
	//����7�������������AdminList������GetList()
	public static ResultSet GetList(String strWhere, Object... args){
		
		//a.����SQL���
		String query = "Select * from Admin where " + strWhere;
		return DBHander.ExecuteQuery(query, args);
	
	}

}
