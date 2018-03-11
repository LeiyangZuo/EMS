package cn.EMS.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import cn.EMS.Hander.DBHander;
import cn.EMS.Model.DepartmentModel;

public class DepartmentDAO {
	public static Boolean Exist(int departmentId) {
		String query = "select * from Department where DepartmentId=?";
		ResultSet rs = DBHander.ExecuteQuery(query, departmentId);
		try {
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static DepartmentModel GetModel(int departmentId) {
		String query = "select * from Department where DepartmentId=?";
		ResultSet rs = DBHander.ExecuteQuery(query, departmentId);
		try {
			if (rs.next()) {
				DepartmentModel department = new DepartmentModel();
				department.setDepartmentId(departmentId);
				department.setName(rs.getString("Name"));
				return department;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Boolean Delete(int departmentId) {

		String query = "Delete from Department where DepartmentId=?";

		int el = DBHander.ExecuteNonQuery(query, departmentId);
		if (el > 0) {
			return true;
		} else {
			return false;
		}
	}

	public static Boolean Insert(DepartmentModel department) {

		if (DepartmentDAO.Exist(department.getDepartmentId())) {
			return false;
		} else {
			String query = "insert into Department (DepartmentId,Name) values (?,?)";
			int el = DBHander.ExecuteNonQuery(query,
					department.getDepartmentId(), department.getName());
			if (el > 0) {
				return true;
			} else {
				return false;
			}
		}
	}

	public static Boolean Update(DepartmentModel department) {
		if (DepartmentDAO.Exist(department.getDepartmentId())) {
			String query = "Update Department set Name=? where DepartmentId=?";
			int el = DBHander.ExecuteNonQuery(query, department.getName(), department.getDepartmentId());
			if (el > 0) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public static ResultSet GetList() {

		String query = "Select * from Department";
		return DBHander.ExecuteQuery(query);

	}

	public static ResultSet GetList(String strWhere, Object... args) {

		String query = "Select * from Department where " + strWhere;
		return DBHander.ExecuteQuery(query, args);

	}
}
