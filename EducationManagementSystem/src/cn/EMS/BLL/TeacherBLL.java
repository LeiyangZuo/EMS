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

		// �����յ�arrayList
		ArrayList<TeacherModel> teacherList = new ArrayList<TeacherModel>();

		// �����ݿ���ȡ���ݣ����ҷŵ�arrayList��
		try {
			while (rs.next()) {

				TeacherModel teacher = new TeacherModel();
				teacher.setTeacherId(rs.getString("TeacherId"));
				teacher.setName(rs.getString("Name"));
				teacher.setPassword(rs.getString("Password"));
				teacher.setDepartmentId(rs.getInt("DepartmentId"));

				// ���Ѿ��������ݵ�AdminModel���뵽ArrayList��
				teacherList.add(teacher);

			}
		} catch (SQLException e) {

			e.printStackTrace();

		}

		return teacherList;

	}

	// ���������õ�AdminList
	public static ArrayList<TeacherModel> GetArrayList(String strWhere,
			Object... args) {

		ResultSet rs = TeacherBLL.GetList(strWhere, args);

		// �����յ�arrayList
		ArrayList<TeacherModel> teacherList = new ArrayList<TeacherModel>();

		// �����ݿ���ȡ���ݣ����ҷŵ�arrayList��
		try {
			while (rs.next()) {

				// ͨ�����ݱ��е�һ����Ŀ������adminģ��
				TeacherModel teacher = new TeacherModel();
				teacher.setTeacherId(rs.getString("TeacherId"));
				teacher.setName(rs.getString("Name"));
				teacher.setPassword(rs.getString("Password"));
				teacher.setDepartmentId(rs.getInt("DepartmentId"));
				// ���Ѿ��������ݵ�AdminModel���뵽ArrayList��
				teacherList.add(teacher);

			}
		} catch (SQLException e) {

			e.printStackTrace();

		}

		return teacherList;

	}

}
