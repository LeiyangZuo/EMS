package cn.EMS.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import cn.EMS.Hander.DBHander;
import cn.EMS.Model.AdminModel;

public class AdminDAO {

	// 方法1，根据ID判断是否存在
	public static Boolean Exist(String adminId) {

		// 构建SQL查询语句
		String query = "select * from Admin where AdminId=?";

		// a.判断输入是否合法，主要判断输入是否为null，如果为null这返回false
		if (adminId == null) {
			return false;
		} else {
			// b.执行查询语句
			ResultSet rs = DBHander.ExecuteQuery(query, adminId);
			try {
				if (rs.next()) {
					// c.如果查询到得结果不空，这说明存在，返回true
					// 结果不为空
					return true;
				}
				// c.如果查询到的结果为空，这说明不存在，返回false
				else {
					return false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return false;

	}

	// 方法2，根据ID得到AdminModel
	public static AdminModel GetModel(String adminId) {
		// a.判断ID的合法性
		if (adminId == null) {
			return null;
		} else {
			// b.查询数据库，得到数据
			String query = "select * from Admin where AdminId=?";
			ResultSet rs = DBHander.ExecuteQuery(query, adminId);
			try {
				if (rs.next()) {
					// c.结果存在
					// e.构建一个AdminModel并返回
					AdminModel admin = new AdminModel();
					admin.setAdminId(adminId);
					admin.setIsSuper(rs.getBoolean("IsSuper"));
					admin.setName(rs.getString("Name"));
					admin.setPassword(rs.getString("Password"));
					return admin;
				} else {
					// d.结果不存在
					return null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return null;
	}

	// 方法3，根据ID删除AdminModel
	public static Boolean Delete(String adminId) {

		// a.判断参数是否合法
		if (adminId == null) {
			return false;
		} else {
			// b.执行删除命令
			String query = "Delete from Admin where AdminId=?";
			// c.执行并得到影响了多少行
			int el = DBHander.ExecuteNonQuery(query, adminId);
			if (el > 0) {
				// d.影响超过了一行，这说明删除成功
				return true;
			} else {
				// e.影响为0，删除失败
				return false;
			}
		}
	}

	// 方法4，将一个AdminModel插入到数据库中
	public static Boolean Insert(AdminModel admin) {

		// a.判断输入是否合法
		if (admin == null) {
			// 输入的Admin模型为空，不执行插入过程，返回失败
			return false;
		} else {
			if (AdminDAO.Exist(admin.getAdminId())) {
				// 存在，不能插入
				return false;
			} else {
				// b.验证成功，允许插入
				String query = "insert into Admin (AdminId,Name,Password,IsSuper) values (?,?,?,?)";
				int el = DBHander.ExecuteNonQuery(query, admin.getAdminId(),
						admin.getName(), admin.getPassword(),
						admin.getIsSuper());
				if (el > 0) {
					// c.影响超过一行，这说明插入成功
					return true;
				} else {
					// d.影响为0，插入失败
					return false;
				}
			}
		}

	}

	// 方法5，根据AdminId及新的AdminModel更新数据
	public static Boolean Update(AdminModel admin) {
		// a.判断输入是否合法
		if (admin == null) {
			return false;
		} else {
			if (AdminDAO.Exist(admin.getAdminId())) {
				// b.存在，这说明允许修改
				String query = "Update Admin set Name=?,Password=?,IsSuper=? where AdminId=?";
				int el = DBHander.ExecuteNonQuery(query, admin.getName(),
						admin.getPassword(), admin.getIsSuper(),
						admin.getAdminId());

				if (el > 0) {
					// d.影响的行数大于0，说明更新成功
					return true;
				} else {
					// e.影响的行数等于0，说明更新失败
					return false;
				}
			} else {
				// c.不存在，这说明无法更新
				return false;
			}
		}
	}
	
	//方法6，获得AdminList
	public static ResultSet GetList(){
		
		//a.生成SQL语句
		String query = "Select * from Admin";
		return DBHander.ExecuteQuery(query);
		
	}
	
	
	//方法7，根据条件获得AdminList，重载GetList()
	public static ResultSet GetList(String strWhere, Object... args){
		
		//a.生成SQL语句
		String query = "Select * from Admin where " + strWhere;
		return DBHander.ExecuteQuery(query, args);
	
	}

}
