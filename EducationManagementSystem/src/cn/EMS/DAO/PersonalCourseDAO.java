package cn.EMS.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import cn.EMS.Hander.DBHander;
import cn.EMS.Model.PersonalCourseModel;

public class PersonalCourseDAO {
	public static Boolean Exist(long id) {
		String query = "select * from PersonalCourse where ID=?";
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

	public static PersonalCourseModel GetModel(long id) {
		String query = "select * from PersonalCourse where ID=?";
		ResultSet rs = DBHander.ExecuteQuery(query, id);
		try {
			if (rs.next()) {
				PersonalCourseModel personalCourse = new PersonalCourseModel();
				personalCourse.setID(id);
				personalCourse.setCourseId(rs.getLong("CourseId"));
				personalCourse.setStudentId(rs.getString("StudentId"));
				personalCourse.setIsInUse(rs.getBoolean("IsInUse"));
				personalCourse.setTerm(rs.getInt("Term"));
				personalCourse.setScore(rs.getInt("Score"));
				return personalCourse;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Boolean Delete(long id) {
		String query = "Delete from PersonalCourse where ID=?";
		int el = DBHander.ExecuteNonQuery(query, id);
		if (el > 0) {
			return true;
		} else {
			return false;
		}
	}

	public static Boolean Insert(PersonalCourseModel personalCourse) {
		if (PersonalCourseDAO.Exist(personalCourse.getID())) {
			return false;
		} else {
			String query = "insert into PersonalCourse (ID,CourseId,StudentId,IsInUse,Term,Score) values (?,?,?,?,?,?)";
			int el = DBHander.ExecuteNonQuery(query, personalCourse.getID(),
					personalCourse.getCourseId(),
					personalCourse.getStudentId(), personalCourse.isIsInUse(),
			        personalCourse.getTerm(),personalCourse.getScore());
			if (el > 0) {
				return true;
			} else {
				return false;
			}
		}
	}

	public static Boolean Update(PersonalCourseModel personalCourse) {
		if (PersonalCourseDAO.Exist(personalCourse.getID())) {
			String query = "Update PersonalCourse set CourseId=?,StudentId=?,IsInUse=?,Term=?,Score=? where ID=?";
			int el = DBHander.ExecuteNonQuery(query,
					personalCourse.getCourseId(),
					personalCourse.getStudentId(), personalCourse.isIsInUse(),
					personalCourse.getTerm(),personalCourse.getScore(),
					personalCourse.getID());
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

		String query = "Select * from PersonalCourse";
		return DBHander.ExecuteQuery(query);

	}

	public static ResultSet GetList(String strWhere, Object... args) {

		String query = "Select * from PersonalCourse where " + strWhere;
		return DBHander.ExecuteQuery(query, args);

	}
}
