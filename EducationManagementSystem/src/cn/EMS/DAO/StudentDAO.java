package cn.EMS.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import cn.EMS.Hander.DBHander;
import cn.EMS.Model.StudentModel;

public class StudentDAO {

	// 学生DAO

	private static String TABLE_NAME = "Student";

	// 判断是否存在
	public static Boolean Exist(String studentId) {

		if (studentId == null || studentId == "") {

			return false;

		} else {
			// 数据库查询语句
			String query = "select * from " + TABLE_NAME + " where StudentId=?";

			// 执行
			ResultSet rs = DBHander.ExecuteQuery(query, studentId);

			try {
				if (rs.next()) {

					// 不为空，表示存在
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			return false;
		}

	}

	// 根据ID得到一个StudentModel
	public static StudentModel GetModel(String studentId) {

		if (studentId == null || studentId == "") {

			return null;
		} else {

			// query语句
			String query = "select * from " + TABLE_NAME + " where studentId=?";

			// 得到结果集合
			ResultSet rs = DBHander.ExecuteQuery(query, studentId);

			StudentModel student = new StudentModel();

			try {
				if (rs.next()) {

					// 得到一行
					// 把从数据库中取到的字段写入到StudentModel中
					student.setStudentId(studentId);
					student.setName(rs.getString("Name"));
					student.setGender(rs.getBoolean("Gender"));
					student.setDepartmentId(rs.getInt("DepartmentId"));
					student.setStartTime(rs.getDate("StartTime"));
					student.setPassword(rs.getString("Password"));
					student.setMajorId(rs.getInt("MajorId"));

				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			return student;

		}

	}

	public static Boolean Insert(StudentModel student) {

		if (StudentDAO.Exist(student.getStudentId())) {
			return false;
		} else {
			String query = "insert into Student (StudentId,Name,DepartmentId,Gender,StartTime,Password,MajorId) values (?,?,?,?,?,?,?)";
			int el = DBHander.ExecuteNonQuery(query, student.getStudentId(),
					student.getName(), student.getDepartmentId(),
					student.getGender(), student.getStartTime(),
					student.getPassword(),student.getMajorId());
			if (el > 0) {
				return true;
			} else {
				return false;
			}
		}
	}

	public static Boolean Delete(String studentId) {

		String query = "Delete from " + TABLE_NAME + " where StudentId=?";

		int el = DBHander.ExecuteNonQuery(query, studentId);
		if (el > 0) {
			return true;
		} else {
			return false;
		}
	}

	public static Boolean Update(StudentModel student) {
		if (StudentDAO.Exist(student.getStudentId())) {
			String query = "Update Student set Name=?,DepartmentId=?,Gender=?,StartTime=?,Password=?,MajorId=? where StudentId=?";
			int el = DBHander.ExecuteNonQuery(query, student.getName(),
					student.getDepartmentId(), student.getGender(),
					student.getStartTime(), student.getPassword(),
					student.getMajorId(),student.getStudentId());
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

		String query = "Select * from Student";
		return DBHander.ExecuteQuery(query);

	}

	public static ResultSet GetList(String strWhere, Object... args) {

		String query = "Select * from Student where " + strWhere;
		return DBHander.ExecuteQuery(query, args);

	}
}
