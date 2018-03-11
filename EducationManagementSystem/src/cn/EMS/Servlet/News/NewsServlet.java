package cn.EMS.Servlet.News;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.EMS.BLL.NewsBLL;
import cn.EMS.Model.NewsModel;

public class NewsServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public NewsServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

	public void service(HttpServletRequest request, HttpServletResponse response) {

		String action = request.getParameter("action");

		if (action.toLowerCase().equals("list")) {

			// 列出所有的消息
			
			//获取所有信息
			ArrayList<NewsModel> newsList = NewsBLL.GetArrayList();
			
			request.setAttribute("newsList", newsList);
			
			RequestDispatcher rd = request.getRequestDispatcher("News/NewsManagement.jsp");
			
			try {
				rd.forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			

		} 
		else if(action.toLowerCase().equals("details")){
			
			Integer newsId = Integer.valueOf(request.getParameter("newsId"));
			
			NewsModel news = NewsBLL.GetModel(newsId);
			
			if(news == null){
				
				//没有取到NewsModel
				
				request.setAttribute("infoType", "false");

				RequestDispatcher rd = request.getRequestDispatcher("NewsServlet?action=list");
				
				try {
					rd.forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else{
				
				//取到了NewsModel
				//则在一个页面中显示
				request.setAttribute("newsModel", news);
				
				RequestDispatcher rd = request.getRequestDispatcher("News/NewsDetails.jsp");
				
				try {
					rd.forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		else if (action.toLowerCase().equals("add")) {

			// 添加消息过程
			String save = request.getParameter("save");

			if (save == null || save == "") {

				// 不是保存过程，给出添加消息页面
				RequestDispatcher rd = request
						.getRequestDispatcher("News/AddNews.jsp");

				try {
					rd.forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {

				// 是保存过程，获取表单信息并且写入数据库

				String newsTitle = request.getParameter("newsTitle");
				String newsContext = request.getParameter("newsContext");

				System.out.println(newsTitle);
				
				// 通过Session得到UserId
				HttpSession session = request.getSession();
				String userId = (String) session.getAttribute("userId");
				String userTypeString = (String) session
						.getAttribute("userType");

				int userType = 0;
				if (userTypeString.equals("admin")) {

					userType = 1;
				} else if (userTypeString.equals("teacher")) {

					userType = 2;

				}

				// 通过表单得到的数据构建NewsModel
				NewsModel news = new NewsModel();
				news.setAuthorId(userId);
				news.setAuthorType(userType);
				news.setTitle(newsTitle);
				news.setContext(newsContext);

				// 执行插入过程
				Boolean isSuccess = NewsBLL.Insert(news);

				request.setAttribute("infoType", isSuccess.toString());
				RequestDispatcher rd = request
						.getRequestDispatcher("NewsServlet?action=list");

				try {
					rd.forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		} else if (action.toLowerCase().equals("edit")) {

			// 编辑消息过程
			String save = request.getParameter("save");

			if (save == null || save == "") {

				// 不是保存过程，给出编辑页面
				Integer newsId = Integer
						.valueOf(request.getParameter("newsId"));

				if (newsId == null) {

					RequestDispatcher rd = request
							.getRequestDispatcher("NewsServlet?action=list");
					try {
						rd.forward(request, response);
					} catch (ServletException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {

					NewsModel news = NewsBLL.GetModel(newsId);

					if (news == null) {

						RequestDispatcher rd = request
								.getRequestDispatcher("NewsServlet?action=list");
						try {
							rd.forward(request, response);
						} catch (ServletException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}

					} else {

						// 得到了消息的Model
						request.setAttribute("newsModel", news);

						RequestDispatcher rd = request
								.getRequestDispatcher("News/EditNews.jsp");

						try {
							rd.forward(request, response);
						} catch (ServletException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			} else {

				// 是保存过程，获取表单信息并且更新数据库
				Integer newsId = Integer
						.valueOf(request.getParameter("newsId"));

				String newsTitle = request.getParameter("newsTitle");
				String newsContext = request.getParameter("newsContext");

				// 通过Session得到UserId
				HttpSession session = request.getSession();
				String userId = (String) session.getAttribute("userId");
				String userTypeString = (String) session
						.getAttribute("userType");

				int userType = 0;
				if (userTypeString.equals("admin")) {

					userType = 1;
				} else if (userTypeString.equals("teacher")) {

					userType = 2;

				}

				// 通过表单得到的数据构建NewsModel
				NewsModel news = new NewsModel();
				news.setAuthorId(userId);
				news.setAuthorType(userType);
				news.setTitle(newsTitle);
				news.setContext(newsContext);
				news.setNewsId(newsId);

				Boolean isSuccess = NewsBLL.Update(news);

				request.setAttribute("infoType", isSuccess.toString());

				RequestDispatcher rd = request
						.getRequestDispatcher("NewsServlet?action=list");

				try {
					rd.forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else if (action.toLowerCase().equals("delete")) {

			// 删除消息过程
			Integer newsId = Integer.valueOf(request.getParameter("newsId"));
			if (newsId == null) {

				request.setAttribute("infoType", "false");

				RequestDispatcher rd = request
						.getRequestDispatcher("NewsServlet?action=list");

				try {
					rd.forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
			else{
				
				//成功获取消息ID，删除消息
				Boolean isSuccess = NewsBLL.Delete(newsId);
				
				request.setAttribute("infoType", isSuccess.toString());
				
				//跳转到列表页
				RequestDispatcher rd = request
						.getRequestDispatcher("NewsServlet?action=list");

				try {
					rd.forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		}

	}

}
