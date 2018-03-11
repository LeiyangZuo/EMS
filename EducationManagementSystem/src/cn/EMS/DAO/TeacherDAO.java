package cn.EMS.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import cn.EMS.Hander.DBHander;
import cn.EMS.Model.TeacherModel;

public class TeacherDAO {
	
	public static Boolean Exist(String teacherId) {
		String query = "select * from Teacher where TeacherId=?";
		ResultSet rs = DBHander.ExecuteQuery(query, teacherId);
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

	public static TeacherModel GetModel(String teacherId) {
		String query = "select * from Teacher where TeacherId=?";
		ResultSet rs = DBHander.ExecuteQuery(query, teacherId);
		try {
			if (rs.next()) {
				TeacherModel teacher = new TeacherModel();
				teacher.setTeacherId(teacherId);
				teacher.setName(rs.getString("Name"));
				teacher.setPassword(rs.getString("Password"));
				teacher.setDepartmentId(rs.getInt("DepartmentId"));			
				return teacher;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Boolean Delete(String teacherId) {

		String query = "Delete from Teacher where TeacherId=?";

		int el = DBHander.ExecuteNonQuery(query, teacherId);
		if (el > 0) {
			return true;
		} else {
			return false;
		}
	}

	public static Boolean Insert(TeacherModel teacher) {
		if (TeacherDAO.Exist(teacher.getTeacherId())) {
			return false;
		} else {
			String query = "insert into Teacher (TeacherId,Name,Password,DepartmentId) values (?,?,?,?)";
			int el = DBHander.ExecuteNonQuery(query, teacher.getTeacherId(),
					teacher.getName(), teacher.getPassword(),
					teacher.getDepartmentId());
			if (el > 0) {
				return true;
			} else {
				return false;
			}
		}
	}

	public static Boolean Update(TeacherModel teacher) {
		if (TeacherDAO.Exist(teacher.getTeacherId())) {
			String query = "Update Teacher set Name=?,Password=?,DepartmentId=? where TeacherId=?";
			int el = DBHander.ExecuteNonQuery(query, teacher.getName(),
					teacher.getPassword(), teacher.getDepartmentId(),teacher.getTeacherId());
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

		String query = "Select * from Teacher";
		return DBHander.ExecuteQuery(query);

	}

	public static ResultSet GetList(String strWhere, Object... args) {

		String query = "Select * from Teacher where " + strWhere;
		return DBHander.ExecuteQuery(query, args);

	}

}
