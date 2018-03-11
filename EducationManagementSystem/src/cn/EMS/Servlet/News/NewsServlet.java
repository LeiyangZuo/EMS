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

			// �г����е���Ϣ
			
			//��ȡ������Ϣ
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
				
				//û��ȡ��NewsModel
				
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
				
				//ȡ����NewsModel
				//����һ��ҳ������ʾ
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

			// �����Ϣ����
			String save = request.getParameter("save");

			if (save == null || save == "") {

				// ���Ǳ�����̣����������Ϣҳ��
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

				// �Ǳ�����̣���ȡ����Ϣ����д�����ݿ�

				String newsTitle = request.getParameter("newsTitle");
				String newsContext = request.getParameter("newsContext");

				System.out.println(newsTitle);
				
				// ͨ��Session�õ�UserId
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

				// ͨ�����õ������ݹ���NewsModel
				NewsModel news = new NewsModel();
				news.setAuthorId(userId);
				news.setAuthorType(userType);
				news.setTitle(newsTitle);
				news.setContext(newsContext);

				// ִ�в������
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

			// �༭��Ϣ����
			String save = request.getParameter("save");

			if (save == null || save == "") {

				// ���Ǳ�����̣������༭ҳ��
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

						// �õ�����Ϣ��Model
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

				// �Ǳ�����̣���ȡ����Ϣ���Ҹ������ݿ�
				Integer newsId = Integer
						.valueOf(request.getParameter("newsId"));

				String newsTitle = request.getParameter("newsTitle");
				String newsContext = request.getParameter("newsContext");

				// ͨ��Session�õ�UserId
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

				// ͨ�����õ������ݹ���NewsModel
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

			// ɾ����Ϣ����
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
				
				//�ɹ���ȡ��ϢID��ɾ����Ϣ
				Boolean isSuccess = NewsBLL.Delete(newsId);
				
				request.setAttribute("infoType", isSuccess.toString());
				
				//��ת���б�ҳ
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
