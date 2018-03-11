package cn.EMS.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import cn.EMS.Hander.DBHander;
import cn.EMS.Model.MajorModel;

public class MajorDAO {

	public static Boolean Exist(int majorId) {
		String query = "select * from Major where MajorId=?";
		ResultSet rs = DBHander.ExecuteQuery(query, majorId);
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

	public static MajorModel GetModel(int majorId) {
		String query = "select * from Major where MajorId=?";
		ResultSet rs = DBHander.ExecuteQuery(query, majorId);
		try {
			if (rs.next()) {
				MajorModel major = new MajorModel();
				major.setMajorId(majorId);
				major.setName(rs.getString("Name"));
				major.setDepartmentId(rs.getInt("DepartmentId"));
				return major;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Boolean Delete(int majorId) {

		String query = "Delete from Major where MajorId=?";

		int el = DBHander.ExecuteNonQuery(query, majorId);
		if (el > 0) {
			return true;
		} else {
			return false;
		}
	}

	public static Boolean Insert(MajorModel major) {

		if (MajorDAO.Exist(major.getMajorId())) {
			return false;
		} else {
			String query = "insert into Major (MajorId,Name,DepartmentId) values (?,?,?)";
			int el = DBHander.ExecuteNonQuery(query,
					major.getMajorId(),
					major.getName(), major.getDepartmentId());
			if (el > 0) {
				return true;
			} else {
				return false;
			}
		}
	}

	public static Boolean Update(MajorModel major) {
		if (MajorDAO.Exist(major.getMajorId())) {
			String query = "Update Major set Name=?,DepartmentId=? where MajorId=?";
			int el = DBHander.ExecuteNonQuery(query, major.getName(),
					major.getDepartmentId(),major.getMajorId());
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

		String query = "Select * from Major";
		return DBHander.ExecuteQuery(query);

	}

	public static ResultSet GetList(String strWhere, Object... args) {

		String query = "Select * from Major where " + strWhere;
		return DBHander.ExecuteQuery(query, args);

	}

}
