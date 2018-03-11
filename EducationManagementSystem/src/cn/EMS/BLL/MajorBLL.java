package cn.EMS.BLL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cn.EMS.DAO.MajorDAO;
import cn.EMS.Model.MajorModel;

public class MajorBLL {

	public static Boolean Exist(int majorId) {

		return MajorDAO.Exist(majorId);

	}

	public static Boolean Insert(MajorModel major) {

		return MajorDAO.Insert(major);

	}

	public static Boolean Delete(int majorId) {

		return MajorDAO.Delete(majorId);

	}

	public static Boolean Update(MajorModel major) {

		return MajorDAO.Update(major);

	}

	public static ResultSet GetList() {

		return MajorDAO.GetList();

	}

	public static ResultSet GetList(String strWhere, Object... args) {

		return MajorDAO.GetList(strWhere, args);

	}

	public static MajorModel GetModel(int majorId) {

		return MajorDAO.GetModel(majorId);

	}

	public static ArrayList<MajorModel> GetArrayList() {

		ResultSet rs = MajorBLL.GetList();

		// �����յ�arrayList
		ArrayList<MajorModel> majorList = new ArrayList<MajorModel>();

		// �����ݿ���ȡ���ݣ����ҷŵ�arrayList��
		try {
			while (rs.next()) {

				// ͨ�����ݱ��е�һ����Ŀ������adminģ��
				MajorModel major = new MajorModel();
				major.setDepartmentId(rs.getInt("MajorId"));
				major.setName(rs.getString("Name"));
				major.setDepartmentId(rs.getInt("DepartmentId"));

				// ���Ѿ��������ݵ�AdminModel���뵽ArrayList��
				majorList.add(major);

			}
		} catch (SQLException e) {

			e.printStackTrace();

		}

		return majorList;

	}

	// ���������õ�AdminList
	public static ArrayList<MajorModel> GetArrayList(String strWhere,
			Object... args) {

		ResultSet rs = MajorBLL.GetList(strWhere, args);

		// �����յ�arrayList
		ArrayList<MajorModel> majorList = new ArrayList<MajorModel>();

		// �����ݿ���ȡ���ݣ����ҷŵ�arrayList��
		try {
			while (rs.next()) {

				// ͨ�����ݱ��е�һ����Ŀ������adminģ��
				MajorModel major = new MajorModel();
				major.setMajorId(rs.getInt("MajorId"));
				major.setName(rs.getString("Name"));
				major.setDepartmentId(rs.getInt("DepartmentId"));

				// ���Ѿ��������ݵ�AdminModel���뵽ArrayList��
				majorList.add(major);
			}
		} catch (SQLException e) {

			e.printStackTrace();

		}

		return majorList;

	}

}
