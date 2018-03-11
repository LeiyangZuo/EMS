package cn.EMS.DAO;


import java.sql.ResultSet;
import java.sql.SQLException;

import cn.EMS.Hander.DBHander;
import cn.EMS.Model.NewsModel;

public class NewsDAO {
	public static Boolean Exist(long newsId) {

		String query = "select * from News where NewsId=?";

		ResultSet rs = DBHander.ExecuteQuery(query, newsId);
		try {
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static NewsModel GetModel(long newsId) {

		String query = "select * from News where NewsId=?";
		ResultSet rs = DBHander.ExecuteQuery(query, newsId);
		try {
			if (rs.next()) {
				NewsModel news = new NewsModel();
				news.setNewsId(newsId);
				news.setTitle(rs.getString("Title"));
				news.setContext(rs.getString("Context"));
				news.setAuthorId(rs.getString("AuthorId"));
				news.setAuthorType(rs.getInt("AuthorType"));

				return news;
			} else {

				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static Boolean Delete(long newsId) {

		String query = "Delete from News where NewsId=?";

		int el = DBHander.ExecuteNonQuery(query, newsId);
		if (el > 0) {
			return true;
		} else {
			return false;
		}
	}

	public static Boolean Insert(NewsModel news) {

		if (NewsDAO.Exist(news.getNewsId())) {
			return false;
		} else {
			String query = "insert into News (NewsId,Title,Context,AuthorId,AuthorType) values (?,?,?,?,?)";
			int el = DBHander.ExecuteNonQuery(query, news.getNewsId(),
					news.getTitle(), news.getContext(), news.getAuthorId(),
					news.getAuthorType());
			if (el > 0) {
				return true;
			} else {
				return false;
			}
		}
	}

	public static Boolean Update(NewsModel news) {
		if (NewsDAO.Exist(news.getNewsId())) {
			String query = "Update News set Title=?,Context=?,AuthorId=?,AuthorType=? where NewsId=?";
			int el = DBHander
					.ExecuteNonQuery(query, news.getTitle(), news.getContext(),
							news.getAuthorId(), news.getAuthorType(), news.getNewsId());
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

		String query = "Select * from News";
		return DBHander.ExecuteQuery(query);

	}

	public static ResultSet GetList(String strWhere, Object... args) {

		String query = "Select * from News where " + strWhere;
		return DBHander.ExecuteQuery(query, args);

	}
	
	//获取所有Title
	//public static Boolean NewsTitle(){}
}
