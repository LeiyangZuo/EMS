package cn.EMS.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import cn.EMS.Hander.DBHander;
import cn.EMS.Model.StopCourseModel;

public class StopCourseDAO {

	public static Boolean Exist(long stopCourseId) {
		String query = "select * from StopCourse where StopCourseId=?";
		ResultSet rs = DBHander.ExecuteQuery(query, stopCourseId);
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

	public static StopCourseModel GetModel(long stopCourseId) {
		String query = "select * from StopCourse where StopCourseId=?";
		ResultSet rs = DBHander.ExecuteQuery(query, stopCourseId);
		try {
			if (rs.next()) {
				StopCourseModel stopCourse = new StopCourseModel();
				stopCourse.setStopCourseId(stopCourseId);
				stopCourse.setCourseId(rs.getLong("CourseId"));
				stopCourse.setStopWeek(rs.getInt("StopWeek"));
				stopCourse.setAddWeek(rs.getInt("AddWeek"));
				return stopCourse;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Boolean Delete(long stopCourseId) {

		String query = "Delete from StopCourse where StopCourseId=?";

		int el = DBHander.ExecuteNonQuery(query, stopCourseId);
		if (el > 0) {
			return true;
		} else {
			return false;
		}
	}

	public static Boolean Insert(StopCourseModel stopCourse) {

		if (StopCourseDAO.Exist(stopCourse.getStopCourseId())) {
			return false;
		} else {
			String query = "insert into StopCourse (StopCourseId,CourseId,StopWeek,AddWeek) values (?,?,?,?)";
			int el = DBHander.ExecuteNonQuery(query,
					stopCourse.getStopCourseId(),
					stopCourse.getCourseId(),
					stopCourse.getStopWeek(),
					stopCourse.getAddWeek());
			if (el > 0) {
				return true;
			} else {
				return false;
			}
		}
	}

	public static Boolean Update(StopCourseModel stopCourse) {
		if (StopCourseDAO.Exist(stopCourse.getStopCourseId())) {
			String query = "Update StopCourse set CourseId=?,StopWeek=?,AddWeek=? where StopCourseId=?";
			int el = DBHander.ExecuteNonQuery(query,
					stopCourse.getCourseId(),
					stopCourse.getStopWeek(),
					stopCourse.getAddWeek(),
					stopCourse.getStopCourseId());
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

		String query = "Select * from StopCourse";
		return DBHander.ExecuteQuery(query);

	}

	public static ResultSet GetList(String strWhere, Object... args) {

		String query = "Select * from StopCourse where " + strWhere;
		return DBHander.ExecuteQuery(query, args);

	}

}
