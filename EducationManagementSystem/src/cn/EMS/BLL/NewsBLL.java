package cn.EMS.BLL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cn.EMS.DAO.NewsDAO;
import cn.EMS.Model.NewsModel;

public class NewsBLL {

	public static Boolean Exist(long newsId) {

		return NewsDAO.Exist(newsId);

	}

	public static Boolean Insert(NewsModel news) {

		return NewsDAO.Insert(news);

	}

	public static Boolean Delete(long newsId) {

		return NewsDAO.Delete(newsId);

	}

	public static Boolean Update(NewsModel news) {

		return NewsDAO.Update(news);

	}

	public static ResultSet GetList() {

		return NewsDAO.GetList();

	}

	public static ResultSet GetList(String strWhere, Object... args) {

		return NewsDAO.GetList(strWhere, args);

	}

	public static NewsModel GetModel(int newsId){
		
		return NewsDAO.GetModel(newsId);
		
	}
	
	public static ArrayList<NewsModel> GetArrayList() {

		ResultSet rs = NewsBLL.GetList();

		// 构建空的arrayList
		ArrayList<NewsModel> newsList = new ArrayList<NewsModel>();

		// 从数据库中取数据，并且放到arrayList中
		try {
			while (rs.next()) {

				NewsModel news = new NewsModel();
				news.setNewsId(rs.getLong("NewsId"));
				news.setTitle(rs.getString("Title"));
				news.setContext(rs.getString("Context"));
				news.setAuthorId(rs.getString("AuthorId"));
				news.setAuthorType(rs.getInt("AuthorType"));

				newsList.add(news);

			}
		} catch (SQLException e) {

			e.printStackTrace();

		}

		return newsList;

	}

	// 获得所有title?????

}
