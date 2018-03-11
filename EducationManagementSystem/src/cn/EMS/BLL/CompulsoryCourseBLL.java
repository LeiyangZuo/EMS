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

		// �����յ�arrayList
		ArrayList<CompulsoryCourseModel> compulsoryCourseList = new ArrayList<CompulsoryCourseModel>();

		// �����ݿ���ȡ���ݣ����ҷŵ�arrayList��
		try {
			while (rs.next()) {

				// ͨ�����ݱ��е�һ����Ŀ������adminģ��
				CompulsoryCourseModel compulsoryCourse = new CompulsoryCourseModel();
				compulsoryCourse.setID(rs.getLong("ID"));
				compulsoryCourse.setCourseId(rs.getLong("CourseId"));
				compulsoryCourse.setDepartmentId(rs.getInt("DepartmentId"));
				compulsoryCourse.setIsInUse(rs.getBoolean("IsInUse"));
				compulsoryCourse.setTerm(rs.getInt("Term"));
				compulsoryCourse.setStudentId(rs.getString("StudentId"));
				compulsoryCourse.setScore(rs.getInt("Score"));

				// ���Ѿ��������ݵ�AdminModel���뵽ArrayList��
				compulsoryCourseList.add(compulsoryCourse);

			}
		} catch (SQLException e) {

			e.printStackTrace();

		}

		return compulsoryCourseList;

	}

	// ���������õ�AdminList
	public static ArrayList<CompulsoryCourseModel> GetArrayList(
			String strWhere, Object... args) {

		ResultSet rs = CompulsoryCourseBLL.GetList(strWhere, args);

		// �����յ�arrayList
		ArrayList<CompulsoryCourseModel> compulsoryCourseList = new ArrayList<CompulsoryCourseModel>();

		// �����ݿ���ȡ���ݣ����ҷŵ�arrayList��
		try {
			while (rs.next()) {

				// ͨ�����ݱ��е�һ����Ŀ������adminģ��
				CompulsoryCourseModel compulsoryCourse = new CompulsoryCourseModel();
				compulsoryCourse.setID(rs.getLong("ID"));
				compulsoryCourse.setCourseId(rs.getLong("CourseId"));
				compulsoryCourse.setDepartmentId(rs.getInt("DepartmentId"));
				compulsoryCourse.setIsInUse(rs.getBoolean("IsInUse"));
				compulsoryCourse.setTerm(rs.getInt("Term"));
				compulsoryCourse.setStudentId(rs.getString("StudentId"));
				compulsoryCourse.setScore(rs.getInt("Score"));

				// ���Ѿ��������ݵ�AdminModel���뵽ArrayList��
				compulsoryCourseList.add(compulsoryCourse);
			}
		} catch (SQLException e) {

			e.printStackTrace();

		}

		return compulsoryCourseList;

	}

}
