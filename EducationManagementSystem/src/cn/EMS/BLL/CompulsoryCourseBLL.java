package cn.EMS.BLL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cn.EMS.DAO.CompulsoryCourseDAO;
import cn.EMS.Model.CompulsoryCourseModel;

public class CompulsoryCourseBLL {

	public static Boolean Exist(long id) {

		return CompulsoryCourseDAO.Exist(id);

	}

	public static Boolean Insert(CompulsoryCourseModel compulsoryCourse) {

		return CompulsoryCourseDAO.Insert(compulsoryCourse);

	}

	public static Boolean Delete(long id) {

		return CompulsoryCourseDAO.Delete(id);

	}

	public static Boolean Update(CompulsoryCourseModel compulsoryCourse) {

		return CompulsoryCourseDAO.Update(compulsoryCourse);

	}

	public static ResultSet GetList() {

		return CompulsoryCourseDAO.GetList();

	}

	public static CompulsoryCourseModel GetModel(long id) {

		return CompulsoryCourseDAO.GetModel(id);
	}

	public static ResultSet GetList(String strWhere, Object... args) {

		return CompulsoryCourseDAO.GetList(strWhere, args);

	}

	public static ArrayList<CompulsoryCourseModel> GetArrayList() {

		ResultSet rs = CompulsoryCourseBLL.GetList();

		// 构建空的arrayList
		ArrayList<CompulsoryCourseModel> compulsoryCourseList = new ArrayList<CompulsoryCourseModel>();

		// 从数据库中取数据，并且放到arrayList中
		try {
			while (rs.next()) {

				// 通过数据表中的一个条目来创建admin模型
				CompulsoryCourseModel compulsoryCourse = new CompulsoryCourseModel();
				compulsoryCourse.setID(rs.getLong("ID"));
				compulsoryCourse.setCourseId(rs.getLong("CourseId"));
				compulsoryCourse.setDepartmentId(rs.getInt("DepartmentId"));
				compulsoryCourse.setIsInUse(rs.getBoolean("IsInUse"));
				compulsoryCourse.setTerm(rs.getInt("Term"));
				compulsoryCourse.setStudentId(rs.getString("StudentId"));
				compulsoryCourse.setScore(rs.getInt("Score"));

				// 把已经存入数据的AdminModel放入到ArrayList中
				compulsoryCourseList.add(compulsoryCourse);

			}
		} catch (SQLException e) {

			e.printStackTrace();

		}

		return compulsoryCourseList;

	}

	// 根据条件得到AdminList
	public static ArrayList<CompulsoryCourseModel> GetArrayList(
			String strWhere, Object... args) {

		ResultSet rs = CompulsoryCourseBLL.GetList(strWhere, args);

		// 构建空的arrayList
		ArrayList<CompulsoryCourseModel> compulsoryCourseList = new ArrayList<CompulsoryCourseModel>();

		// 从数据库中取数据，并且放到arrayList中
		try {
			while (rs.next()) {

				// 通过数据表中的一个条目来创建admin模型
				CompulsoryCourseModel compulsoryCourse = new CompulsoryCourseModel();
				compulsoryCourse.setID(rs.getLong("ID"));
				compulsoryCourse.setCourseId(rs.getLong("CourseId"));
				compulsoryCourse.setDepartmentId(rs.getInt("DepartmentId"));
				compulsoryCourse.setIsInUse(rs.getBoolean("IsInUse"));
				compulsoryCourse.setTerm(rs.getInt("Term"));
				compulsoryCourse.setStudentId(rs.getString("StudentId"));
				compulsoryCourse.setScore(rs.getInt("Score"));

				// 把已经存入数据的AdminModel放入到ArrayList中
				compulsoryCourseList.add(compulsoryCourse);
			}
		} catch (SQLException e) {

			e.printStackTrace();

		}

		return compulsoryCourseList;

	}

}
