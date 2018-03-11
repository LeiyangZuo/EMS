package cn.EMS.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import cn.EMS.Hander.DBHander;
import cn.EMS.Model.CourseModel;

public class CourseDAO {

	public static Boolean Exist(long courseId) {
		String query = "select * from Course where CourseId=?";
		ResultSet rs = DBHander.ExecuteQuery(query, courseId);
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

	public static CourseModel GetModel(long courseId) {
		String query = "select * from Course where CourseId=?";
		ResultSet rs = DBHander.ExecuteQuery(query, courseId);
		try {
			if (rs.next()) {
				CourseModel course = new CourseModel();
				course.setCourseId(courseId);
				course.setName(rs.getString("Name"));
				course.setDescription(rs.getString("Description"));
				course.setTeacherId(rs.getString("TeacherId"));
				course.setPeriod(rs.getInt("Period"));
				course.setClassTime("ClassTime");
				course.setIsCompulsoryCourse(rs.getBoolean("IsCompulsoryCourse"));
				course.setStopWeek(rs.getInt("StopWeek"));
				course.setAddWeek(rs.getInt("AddWeek"));

				return course;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Boolean Delete(long courseId) {

		String query = "Delete from Course where CourseId=?";

		int el = DBHander.ExecuteNonQuery(query, courseId);
		if (el > 0) {
			return true;
		} else {
			return false;
		}
	}

	public static Boolean Insert(CourseModel course) {
		if (CourseDAO.Exist(course.getCourseId())) {
			return false;
		} else {
			String query = "insert into Course (CourseId,Name,Description,TeacherId,Period,ClassTime,IsCompulsoryCourse,StopWeek,AddWeek) values (?,?,?,?,?,?,?,?,?)";
			int el = DBHander.ExecuteNonQuery(query, course.getCourseId(),
					course.getName(), course.getDescription(),
					course.getTeacherId(),
					course.getPeriod(), course.getClassTime(),
					course.isIsCompulsoryCourse(),
					course.getStopWeek(),course.getAddWeek());
			if (el > 0) {
				return true;
			} else {
				return false;
			}
		}
	}

	public static Boolean Update(CourseModel course) {
		if (CourseDAO.Exist(course.getCourseId())) {
			String query = "Update Course set Name=?,Description=?,TeacherId=?,Period=?,ClassTime=?,IsCompulsoryCourse=?,StopWeek=?,AddWeek=? where CourseId=?";
			int el = DBHander.ExecuteNonQuery(query, course.getName(),
					course.getDescription(), course.getTeacherId(),
					 course.getPeriod(),
					course.getClassTime(), 
					course.isIsCompulsoryCourse(),course.getStopWeek(),
					course.getAddWeek(),course.getCourseId());
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

		String query = "Select * from Course";
		return DBHander.ExecuteQuery(query);

	}

	public static ResultSet GetList(String strWhere, Object... args) {

		String query = "Select * from Course where " + strWhere;
		return DBHander.ExecuteQuery(query, args);

	}
}
