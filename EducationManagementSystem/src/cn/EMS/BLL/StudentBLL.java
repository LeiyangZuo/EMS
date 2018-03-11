package cn.EMS.BLL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cn.EMS.DAO.StudentDAO;
import cn.EMS.Model.StudentModel;

public class StudentBLL {

	public static Boolean Exist(String studentId) {

		return StudentDAO.Exist(studentId);

	}

	public static Boolean Insert(StudentModel student) {

		return StudentDAO.Insert(student);

	}

	public static Boolean Delete(String studentId) {

		return StudentDAO.Delete(studentId);

	}

	public static Boolean Update(StudentModel student) {

		return StudentDAO.Update(student);

	}

	public static ResultSet GetList() {

		return StudentDAO.GetList();

	}

	public static ResultSet GetList(String strWhere, Object... args) {

		return StudentDAO.GetList(strWhere, args);

	}

	
	public static StudentModel GetModel(String studentId){
		
		return StudentDAO.GetModel(studentId);
		
	}
	
	// �õ����й���Ա����Ϣ
	public static ArrayList<StudentModel> GetArrayList() {

		ResultSet rs = StudentBLL.GetList();

		// �����յ�arrayList
		ArrayList<StudentModel> studentList = new ArrayList<StudentModel>();

		// �����ݿ���ȡ���ݣ����ҷŵ�arrayList��
		try {
			while (rs.next()) {

				StudentModel student = new StudentModel();
				student.setStudentId(rs.getString("StudentId"));
				student.setName(rs.getString("Name"));
				student.setDepartmentId(rs.getInt("DepartmentId"));
				student.setGender(rs.getBoolean("Gender"));
				student.setStartTime(rs.getDate("StartTime"));
				student.setGender(rs.getBoolean("Gender"));
				student.setPassword(rs.getString("Password"));
				student.setMajorId(rs.getInt("MajorId"));

				studentList.add(student);

			}
		} catch (SQLException e) {

			e.printStackTrace();

		}

		return studentList;

	}

	// ���������õ�AdminList
	public static ArrayList<StudentModel> GetArrayList(String strWhere,
			Object... args) {

		ResultSet rs = StudentBLL.GetList(strWhere, args);

		// �����յ�arrayList
		ArrayList<StudentModel> studentList = new ArrayList<StudentModel>();

		// �����ݿ���ȡ���ݣ����ҷŵ�arrayList��
		try {
			while (rs.next()) {

				StudentModel student = new StudentModel();
				student.setStudentId(rs.getString("StudentId"));
				student.setName(rs.getString("Name"));
				student.setDepartmentId(rs.getInt("DepartmentId"));
				student.setGender(rs.getBoolean("Gender"));
				student.setStartTime(rs.getDate("StartTime"));
				student.setGender(rs.getBoolean("Gender"));
				student.setPassword(rs.getString("Password"));
				student.setMajorId(rs.getInt("MajorId"));

				// ���Ѿ��������ݵ�AdminModel���뵽ArrayList��
				studentList.add(student);

			}
		} catch (SQLException e) {

			e.printStackTrace();

		}

		return studentList;

	}

}
