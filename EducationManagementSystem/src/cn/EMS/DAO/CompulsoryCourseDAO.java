package cn.EMS.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import cn.EMS.Hander.DBHander;
import cn.EMS.Model.CompulsoryCourseModel;

public class CompulsoryCourseDAO {
	public static Boolean Exist(long id) {
		String query = "select * from CompulsoryCourse where ID=?";
		ResultSet rs = DBHander.ExecuteQuery(query, id);
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

	public static CompulsoryCourseModel GetModel(long id) {
		String query = "select * from CompulsoryCourse where ID=?";
		ResultSet rs = DBHander.ExecuteQuery(query, id);
		try {
			if (rs.next()) {
				CompulsoryCourseModel compulsoryCourse = new CompulsoryCourseModel();
				compulsoryCourse.setID(id);
				compulsoryCourse.setCourseId(rs.getLong("CourseId"));
				compulsoryCourse.setDepartmentId(rs.getInt("DepartmentId"));
				compulsoryCourse.setIsInUse(rs.getBoolean("IsInUse"));
				compulsoryCourse.setTerm(rs.getInt("Term"));
				compulsoryCourse.setStudentId(rs.getString("StudentId"));
				compulsoryCourse.setScore(rs.getInt("Score"));
				return compulsoryCourse;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Boolean Delete(long id) {

		String query = "Delete from CompulsoryCourse where ID=?";

		int el = DBHander.ExecuteNonQuery(query, id);
		if (el > 0) {
			return true;
		} else {
			return false;
		}
	}

	public static Boolean Insert(CompulsoryCourseModel compulsoryCourse) {

		if (CompulsoryCourseDAO.Exist(compulsoryCourse.getID())) {
			return false;
		} else {
			String query = "insert into CompulsoryCourse (ID,CourseId,DepartmentId,IsInUse,Term,StudentId,Score) values (?,?,?,?,?,?,?)";
			int el = DBHander.ExecuteNonQuery(query, compulsoryCourse.getID(),
					compulsoryCourse.getCourseId(),
					compulsoryCourse.getDepartmentId(),
					compulsoryCourse.isIsInUse(),
					compulsoryCourse.getTerm(),
					compulsoryCourse.getStudentId(),
					compulsoryCourse.getScore()
					);
			if (el > 0) {
				return true;
			} else {
				return false;
			}
		}
	}

	public static Boolean Update(CompulsoryCourseModel compulsoryCourse) {
		if (CompulsoryCourseDAO.Exist(compulsoryCourse.getID())) {
			String query = "Update CompulsoryCourse set CourseId=?,DepartmentId=?,IsInUse=?,Term=?,StudentId=?,Score=? where ID=?";
			int el = DBHander.ExecuteNonQuery(query,
					compulsoryCourse.getCourseId(),
					compulsoryCourse.getDepartmentId(),
					compulsoryCourse.isIsInUse(),
					compulsoryCourse.getTerm(),
					compulsoryCourse.getStudentId(),
					compulsoryCourse.getScore(),
					compulsoryCourse.getID()
					);

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

		String query = "Select * from CompulsoryCourse";
		return DBHander.ExecuteQuery(query);

	}

	public static ResultSet GetList(String strWhere, Object... args) {

		String query = "Select * from CompulsoryCourse where " + strWhere;
		return DBHander.ExecuteQuery(query, args);

	}
}
