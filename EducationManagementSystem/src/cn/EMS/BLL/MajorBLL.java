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

		// 构建空的arrayList
		ArrayList<MajorModel> majorList = new ArrayList<MajorModel>();

		// 从数据库中取数据，并且放到arrayList中
		try {
			while (rs.next()) {

				// 通过数据表中的一个条目来创建admin模型
				MajorModel major = new MajorModel();
				major.setDepartmentId(rs.getInt("MajorId"));
				major.setName(rs.getString("Name"));
				major.setDepartmentId(rs.getInt("DepartmentId"));

				// 把已经存入数据的AdminModel放入到ArrayList中
				majorList.add(major);

			}
		} catch (SQLException e) {

			e.printStackTrace();

		}

		return majorList;

	}

	// 根据条件得到AdminList
	public static ArrayList<MajorModel> GetArrayList(String strWhere,
			Object... args) {

		ResultSet rs = MajorBLL.GetList(strWhere, args);

		// 构建空的arrayList
		ArrayList<MajorModel> majorList = new ArrayList<MajorModel>();

		// 从数据库中取数据，并且放到arrayList中
		try {
			while (rs.next()) {

				// 通过数据表中的一个条目来创建admin模型
				MajorModel major = new MajorModel();
				major.setMajorId(rs.getInt("MajorId"));
				major.setName(rs.getString("Name"));
				major.setDepartmentId(rs.getInt("DepartmentId"));

				// 把已经存入数据的AdminModel放入到ArrayList中
				majorList.add(major);
			}
		} catch (SQLException e) {

			e.printStackTrace();

		}

		return majorList;

	}

}
