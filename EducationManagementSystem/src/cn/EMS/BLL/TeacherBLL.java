package cn.EMS.BLL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cn.EMS.DAO.TeacherDAO;
import cn.EMS.Model.TeacherModel;

public class TeacherBLL {

	public static Boolean Exist(String teacherId) {

		return TeacherDAO.Exist(teacherId);

	}

	public static Boolean Insert(TeacherModel teacher) {

		return TeacherDAO.Insert(teacher);

	}

	public static Boolean Delete(String teacherId) {

		return TeacherDAO.Delete(teacherId);

	}

	public static Boolean Update(TeacherModel teacher) {

		return TeacherDAO.Update(teacher);

	}

	public static ResultSet GetList() {

		return TeacherDAO.GetList();

	}

	public static ResultSet GetList(String strWhere, Object... args) {

		return TeacherDAO.GetList(strWhere, args);

	}

	public static TeacherModel GetModel(String teacherId){
		
		return TeacherDAO.GetModel(teacherId);
		
	}
	
	public static ArrayList<TeacherModel> GetArrayList() {

		ResultSet rs = TeacherBLL.GetList();

		// 构建空的arrayList
		ArrayList<TeacherModel> teacherList = new ArrayList<TeacherModel>();

		// 从数据库中取数据，并且放到arrayList中
		try {
			while (rs.next()) {

				TeacherModel teacher = new TeacherModel();
				teacher.setTeacherId(rs.getString("TeacherId"));
				teacher.setName(rs.getString("Name"));
				teacher.setPassword(rs.getString("Password"));
				teacher.setDepartmentId(rs.getInt("DepartmentId"));

				// 把已经存入数据的AdminModel放入到ArrayList中
				teacherList.add(teacher);

			}
		} catch (SQLException e) {

			e.printStackTrace();

		}

		return teacherList;

	}

	// 根据条件得到AdminList
	public static ArrayList<TeacherModel> GetArrayList(String strWhere,
			Object... args) {

		ResultSet rs = TeacherBLL.GetList(strWhere, args);

		// 构建空的arrayList
		ArrayList<TeacherModel> teacherList = new ArrayList<TeacherModel>();

		// 从数据库中取数据，并且放到arrayList中
		try {
			while (rs.next()) {

				// 通过数据表中的一个条目来创建admin模型
				TeacherModel teacher = new TeacherModel();
				teacher.setTeacherId(rs.getString("TeacherId"));
				teacher.setName(rs.getString("Name"));
				teacher.setPassword(rs.getString("Password"));
				teacher.setDepartmentId(rs.getInt("DepartmentId"));
				// 把已经存入数据的AdminModel放入到ArrayList中
				teacherList.add(teacher);

			}
		} catch (SQLException e) {

			e.printStackTrace();

		}

		return teacherList;

	}

}
