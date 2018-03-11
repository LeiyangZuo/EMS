package cn.EMS.BLL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cn.EMS.DAO.StopCourseDAO;
import cn.EMS.Model.StopCourseModel;

public class StopCourseBLL {

	public static Boolean Exist(long StopCourseId) {

		return StopCourseDAO.Exist(StopCourseId);

	}

	public static Boolean Insert(StopCourseModel StopCourse) {

		return StopCourseDAO.Insert(StopCourse);

	}

	public static Boolean Delete(long StopCourseId) {

		return StopCourseDAO.Delete(StopCourseId);

	}

	public static Boolean Update(StopCourseModel StopCourse) {

		return StopCourseDAO.Update(StopCourse);

	}

	public static ResultSet GetList() {

		return StopCourseDAO.GetList();

	}

	public static ResultSet GetList(String strWhere, Object... args) {

		return StopCourseDAO.GetList(strWhere, args);

	}

	public static StopCourseModel GetModel(long StopCourseId) {

		return StopCourseDAO.GetModel(StopCourseId);

	}

	public static ArrayList<StopCourseModel> GetArrayList() {

		ResultSet rs = StopCourseBLL.GetList();

		// 构建空的arrayList
		ArrayList<StopCourseModel> StopCourseList = new ArrayList<StopCourseModel>();

		// 从数据库中取数据，并且放到arrayList中
		try {
			while (rs.next()) {

				StopCourseModel StopCourse = new StopCourseModel();
				StopCourse.setStopCourseId(rs
						.getLong("StopCourseId"));
				StopCourse.setCourseId(rs.getLong("CourseId"));
				StopCourse.setStopWeek(rs.getInt("StopWeek"));
				StopCourse.setAddWeek(rs.getInt("AddWeek"));

				StopCourseList.add(StopCourse);

			}
		} catch (SQLException e) {

			e.printStackTrace();

		}

		return StopCourseList;

	}

	public static ArrayList<StopCourseModel> GetArrayList(String strWhere,
			Object... args) {

		ResultSet rs = StopCourseBLL.GetList(strWhere, args);

		// 构建空的arrayList
		ArrayList<StopCourseModel> StopCourseList = new ArrayList<StopCourseModel>();

		// 从数据库中取数据，并且放到arrayList中
		try {
			while (rs.next()) {

				StopCourseModel StopCourse = new StopCourseModel();
				StopCourse.setStopCourseId(rs
						.getLong("StopCourseId"));
				StopCourse.setCourseId(rs.getLong("CourseId"));
				StopCourse.setStopWeek(rs.getInt("StopWeek"));
				StopCourse.setAddWeek(rs.getInt("AddWeek"));

				StopCourseList.add(StopCourse);
			}
		} catch (SQLException e) {

			e.printStackTrace();

		}

		return StopCourseList;

	}

}
