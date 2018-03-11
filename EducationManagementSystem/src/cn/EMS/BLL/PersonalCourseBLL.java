package cn.EMS.BLL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cn.EMS.DAO.PersonalCourseDAO;
import cn.EMS.Model.PersonalCourseModel;

public class PersonalCourseBLL {

	public static Boolean Exist(long id) {

		return PersonalCourseDAO.Exist(id);

	}

	public static Boolean Insert(PersonalCourseModel personalCourse) {

		return PersonalCourseDAO.Insert(personalCourse);

	}

	public static Boolean Delete(long id) {

		return PersonalCourseDAO.Delete(id);

	}

	public static Boolean Update(PersonalCourseModel personalCourse) {

		return PersonalCourseDAO.Update(personalCourse);

	}

	public static ResultSet GetList() {

		return PersonalCourseDAO.GetList();

	}

	public static ResultSet GetList(String strWhere, Object... args) {

		return PersonalCourseDAO.GetList(strWhere, args);

	}
	
	public static PersonalCourseModel GetModel(long ID){
		
		return PersonalCourseDAO.GetModel(ID);
	}

	public static ArrayList<PersonalCourseModel> GetArrayList() {

		ResultSet rs = PersonalCourseBLL.GetList();

		// �����յ�arrayList
		ArrayList<PersonalCourseModel> personalCourseList = new ArrayList<PersonalCourseModel>();

		// �����ݿ���ȡ���ݣ����ҷŵ�arrayList��
		try {
			while (rs.next()) {

				// ͨ�����ݱ��е�һ����Ŀ������adminģ��
				PersonalCourseModel personalCourse = new PersonalCourseModel();
				personalCourse.setID(rs.getLong("ID"));
				personalCourse.setCourseId(rs.getLong("CourseId"));
				personalCourse.setStudentId(rs.getString("StudentId"));
				personalCourse.setIsInUse(rs.getBoolean("IsInUse"));
				personalCourse.setTerm(rs.getInt("Term"));
				personalCourse.setScore(rs.getInt("Score"));

				// ���Ѿ��������ݵ�AdminModel���뵽ArrayList��
				personalCourseList.add(personalCourse);

			}
		} catch (SQLException e) {

			e.printStackTrace();

		}

		return personalCourseList;

	}

	// ���������õ�AdminList
	public static ArrayList<PersonalCourseModel> GetArrayList(String strWhere,
			Object... args) {

		ResultSet rs = PersonalCourseBLL.GetList(strWhere, args);

		// �����յ�arrayList
		ArrayList<PersonalCourseModel> personalCourseList = new ArrayList<PersonalCourseModel>();

		// �����ݿ���ȡ���ݣ����ҷŵ�arrayList��
		try {
			while (rs.next()) {

				// ͨ�����ݱ��е�һ����Ŀ������adminģ��
				PersonalCourseModel personalCourse = new PersonalCourseModel();
				personalCourse.setID(rs.getLong("ID"));
				personalCourse.setCourseId(rs.getLong("CourseId"));
				personalCourse.setStudentId(rs.getString("StudentId"));
				personalCourse.setIsInUse(rs.getBoolean("IsInUse"));
				personalCourse.setTerm(rs.getInt("Term"));
				personalCourse.setScore(rs.getInt("Score"));

				// ���Ѿ��������ݵ�AdminModel���뵽ArrayList��
				personalCourseList.add(personalCourse);
			}
		} catch (SQLException e) {

			e.printStackTrace();

		}

		return personalCourseList;

	}

}
