package cn.EMS.BLL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cn.EMS.DAO.CourseDAO;
import cn.EMS.Model.CourseModel;

public class CourseBLL {

	public static Boolean Exist(long courseId) {

		return CourseDAO.Exist(courseId);

	}

	public static Boolean Insert(CourseModel course) {

		return CourseDAO.Insert(course);

	}

	public static Boolean Delete(long courseId) {

		return CourseDAO.Delete(courseId);

	}

	public static Boolean Update(CourseModel course) {

		return CourseDAO.Update(course);

	}

	public static CourseModel GetModel(long courseId) {

		return CourseDAO.GetModel(courseId);

	}

	public static ResultSet GetList() {

		return CourseDAO.GetList();

	}

	public static ResultSet GetList(String strWhere, Object... args) {

		return CourseDAO.GetList(strWhere, args);

	}

	public static ArrayList<CourseModel> GetArrayList() {

		ResultSet rs = CourseBLL.GetList();

		// 构建空的arrayList
		ArrayList<CourseModel> courseList = new ArrayList<CourseModel>();

		// 从数据库中取数据，并且放到arrayList中
		try {
			while (rs.next()) {

				CourseModel course = new CourseModel();
				course.setCourseId(rs.getLong("CourseId"));
				course.setName(rs.getString("Name"));
				course.setDescription(rs.getString("Description"));
				course.setTeacherId(rs.getString("TeacherId"));
				course.setPeriod(rs.getInt("Period"));
				course.setClassTime(rs.getString("ClassTime"));
				course.setIsCompulsoryCourse(rs
						.getBoolean("IsCompulsoryCourse"));

				courseList.add(course);

			}
		} catch (SQLException e) {

			e.printStackTrace();

		}

		return courseList;

	}

	// 根据条件得到AdminList
	public static ArrayList<CourseModel> GetArrayList(String strWhere,
			Object... args) {

		ResultSet rs = CourseBLL.GetList(strWhere, args);

		// 构建空的arrayList
		ArrayList<CourseModel> courseList = new ArrayList<CourseModel>();

		// 从数据库中取数据，并且放到arrayList中
		try {
			while (rs.next()) {

				CourseModel course = new CourseModel();
				course.setCourseId(rs.getLong("CourseId"));
				course.setName(rs.getString("Name"));
				course.setDescription(rs.getString("Description"));
				course.setTeacherId(rs.getString("TeacherId"));
				course.setPeriod(rs.getInt("Period"));
				course.setClassTime(rs.getString("ClassTime"));
				course.setIsCompulsoryCourse(rs
						.getBoolean("IsCompulsoryCourse"));

				courseList.add(course);
			}
		} catch (SQLException e) {

			e.printStackTrace();

		}

		return courseList;

	}

}
