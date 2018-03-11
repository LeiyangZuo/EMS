package cn.EMS.BLL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cn.EMS.DAO.DepartmentDAO;
import cn.EMS.Model.DepartmentModel;

public class DepartmentBLL {

	public static Boolean Exist(int departmentId) {

		return DepartmentDAO.Exist(departmentId);

	}

	public static Boolean Insert(DepartmentModel department) {

		return DepartmentDAO.Insert(department);

	}

	public static Boolean Delete(int departmentId) {

		return DepartmentDAO.Delete(departmentId);

	}

	public static Boolean Update(DepartmentModel department) {

		return DepartmentDAO.Update(department);

	}

	public static ResultSet GetList() {

		return DepartmentDAO.GetList();

	}

	public static ResultSet GetList(String strWhere, Object... args) {

		return DepartmentDAO.GetList(strWhere, args);

	}

	public static DepartmentModel GetModel(int departmentId) {

		return DepartmentDAO.GetModel(departmentId);

	}

	public static ArrayList<DepartmentModel> GetArrayList() {

		ResultSet rs = DepartmentBLL.GetList();

		// �����յ�arrayList
		ArrayList<DepartmentModel> departmentList = new ArrayList<DepartmentModel>();

		// �����ݿ���ȡ���ݣ����ҷŵ�arrayList��
		try {
			while (rs.next()) {

				DepartmentModel department = new DepartmentModel();
				department.setDepartmentId(rs.getInt("DepartmentId"));
				department.setName(rs.getString("Name"));

				departmentList.add(department);

			}
		} catch (SQLException e) {

			e.printStackTrace();

		}

		return departmentList;

	}

	// ���������õ�AdminList
	public static ArrayList<DepartmentModel> GetArrayList(String strWhere,
			Object... args) {

		ResultSet rs = DepartmentBLL.GetList(strWhere, args);

		// �����յ�arrayList
		ArrayList<DepartmentModel> departmentList = new ArrayList<DepartmentModel>();

		// �����ݿ���ȡ���ݣ����ҷŵ�arrayList��
		try {
			while (rs.next()) {

				DepartmentModel department = new DepartmentModel();
				department.setDepartmentId(rs.getInt("DepartmentId"));
				department.setName(rs.getString("Name"));

				departmentList.add(department);
			}
		} catch (SQLException e) {

			e.printStackTrace();

		}

		return departmentList;

	}

}
