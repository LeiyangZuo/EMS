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

		// 构建空的arrayList
		ArrayList<PersonalCourseModel> personalCourseList = new ArrayList<PersonalCourseModel>();

		// 从数据库中取数据，并且放到arrayList中
		try {
			while (rs.next()) {

				// 通过数据表中的一个条目来创建admin模型
				PersonalCourseModel personalCourse = new PersonalCourseModel();
				personalCourse.setID(rs.getLong("ID"));
				personalCourse.setCourseId(rs.getLong("CourseId"));
				personalCourse.setStudentId(rs.getString("StudentId"));
				personalCourse.setIsInUse(rs.getBoolean("IsInUse"));
				personalCourse.setTerm(rs.getInt("Term"));
				personalCourse.setScore(rs.getInt("Score"));

				// 把已经存入数据的AdminModel放入到ArrayList中
				personalCourseList.add(personalCourse);

			}
		} catch (SQLException e) {

			e.printStackTrace();

		}

		return personalCourseList;

	}

	// 根据条件得到AdminList
	public static ArrayList<PersonalCourseModel> GetArrayList(String strWhere,
			Object... args) {

		ResultSet rs = PersonalCourseBLL.GetList(strWhere, args);

		// 构建空的arrayList
		ArrayList<PersonalCourseModel> personalCourseList = new ArrayList<PersonalCourseModel>();

		// 从数据库中取数据，并且放到arrayList中
		try {
			while (rs.next()) {

				// 通过数据表中的一个条目来创建admin模型
				PersonalCourseModel personalCourse = new PersonalCourseModel();
				personalCourse.setID(rs.getLong("ID"));
				personalCourse.setCourseId(rs.getLong("CourseId"));
				personalCourse.setStudentId(rs.getString("StudentId"));
				personalCourse.setIsInUse(rs.getBoolean("IsInUse"));
				personalCourse.setTerm(rs.getInt("Term"));
				personalCourse.setScore(rs.getInt("Score"));

				// 把已经存入数据的AdminModel放入到ArrayList中
				personalCourseList.add(personalCourse);
			}
		} catch (SQLException e) {

			e.printStackTrace();

		}

		return personalCourseList;

	}

}
