package cn.EMS.Servlet.Teacher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.EMS.BLL.CompulsoryCourseBLL;
import cn.EMS.BLL.CourseBLL;
import cn.EMS.BLL.DepartmentBLL;
import cn.EMS.BLL.NewsBLL;
import cn.EMS.BLL.PersonalCourseBLL;
import cn.EMS.BLL.StopCourseBLL;
import cn.EMS.BLL.StudentBLL;
import cn.EMS.Model.CompulsoryCourseModel;
import cn.EMS.Model.CourseModel;
import cn.EMS.Model.DepartmentModel;
import cn.EMS.Model.NewsModel;
import cn.EMS.Model.PersonalCourseModel;
import cn.EMS.Model.StopCourseModel;
import cn.EMS.Model.StudentModel;

public class TeacherServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public TeacherServlet() {
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

		// �ж�action������ʲô
		if (action.toLowerCase().equals("studentinfo")) {

			// ���Session
			HttpSession session = request.getSession();
			// ��ȡSession�е�ID��Ϣ
			String studentId = (String) session.getAttribute("userId");

			// �õ�StudentModel
			StudentModel student = StudentBLL.GetModel(studentId);

			if (student == null) {

				// ѧ����Ϣ�����ڣ���¼ʧ�ܣ����ص�¼ҳ

				// �ض��򵽵�¼ҳ
				try {
					response.sendRedirect("Login/login.jsp");
				} catch (IOException e) {
					e.printStackTrace();
				}

			} else {

				// ��request����httpͷ�з�װ�ո�ȡ�õ�����
				request.setAttribute("studentModel", student);

				// �õ�RequestDispatcher
				RequestDispatcher rd = request
						.getRequestDispatcher("Student/StudentInfo.jsp");

				// ���д������ݵĹ���
				try {
					rd.forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		} else if (action.toLowerCase().equals("courselist")) {

			HttpSession session = request.getSession();
			String teacherId = (String) session.getAttribute("userId");

			ArrayList<CourseModel> CourseList = CourseBLL.GetArrayList(
					"TeacherId=?", teacherId);

			request.setAttribute("CourseList", CourseList);
			RequestDispatcher rd = request
					.getRequestDispatcher("Teacher/TeacherCourse.jsp");

			try {
				rd.forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (action.toLowerCase().equals("deletecourse")) {

			long personalCourseId = Long.parseLong(request
					.getParameter("personalCourseId"));

			if ((personalCourseId == ' ')) {

				request.setAttribute("infoType", "false");

				RequestDispatcher rd = request
						.getRequestDispatcher("StudentServlet?action=courselist");

				try {
					rd.forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			} else {

				// �ɹ���ȡ��ϢID��ɾ����Ϣ
				Boolean isSuccess = PersonalCourseBLL.Delete(personalCourseId);

				request.setAttribute("infoType", isSuccess.toString());

				// ��ת���б�ҳ
				RequestDispatcher rd = request
						.getRequestDispatcher("StudentServlet?action=courselist");

				try {
					rd.forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		} else if (action.toLowerCase().equals("releasescore")) {

			HttpSession session = request.getSession();
			String teacherId = (String) session.getAttribute("userId");

			ArrayList<CourseModel> CourseList = CourseBLL.GetArrayList(
					"TeacherId=?", teacherId);

			request.setAttribute("CourseList", CourseList);
			RequestDispatcher rd = request
					.getRequestDispatcher("Teacher/ReleaseScore.jsp");

			try {
				rd.forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (action.toLowerCase().equals("editscore")) {

			String save = request.getParameter("save");

			if (save == null || save == "") {
				// ���Ǳ���ҳ�棬�Ǳ༭ҳ��
				Integer teacherId = Integer.valueOf(request
						.getParameter("teacherId"));
				Integer courseId = Integer
						.valueOf(request.getParameter("courseId"));

				if (teacherId == null & courseId == null) {

					RequestDispatcher rd = request
							.getRequestDispatcher("TeacherServlet?action=releasescore");
					try {
						rd.forward(request, response);
					} catch (ServletException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}

				} else {
					// �����༭����
					ArrayList<CompulsoryCourseModel> compulsoryCourseList = CompulsoryCourseBLL
							.GetArrayList("CourseId=?", courseId);
					ArrayList<PersonalCourseModel> personalCourseList = PersonalCourseBLL
							.GetArrayList("CourseId=?", courseId);

					request.setAttribute("personalCourseList",
							personalCourseList);
					request.setAttribute("compulsoryCourseList",
							compulsoryCourseList);

					RequestDispatcher rd = request
							.getRequestDispatcher("Teacher/EditScore.jsp");
					try {
						rd.forward(request, response);
					} catch (ServletException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			} else {
				//�Ǳ���
				//ͨ��AJAXʵ��
				Long Ajax_courseId = Long.valueOf(request.getParameter("courseId"));
				Integer Ajax_score = Integer.valueOf(request.getParameter("score"));
				String Ajax_type = request.getParameter("type");
				
				if(Ajax_type.equals("compulsory")){
					
					
					CompulsoryCourseModel compulsoryCourse = CompulsoryCourseBLL.GetModel(Ajax_courseId);
					if(compulsoryCourse == null){
						
						//����
						try {
							response.getWriter().write("error");
						} catch (IOException e) {
							e.printStackTrace();
						}
						
					}
					else{
						
						
						compulsoryCourse.setScore(Ajax_score);
						if(CompulsoryCourseBLL.Update(compulsoryCourse)){
							
							
							//���³ɹ�
							try {
								response.getWriter().write("success");
							} catch (IOException e) {
								e.printStackTrace();
							}
							
						}
						else{
							
							//����ʧ��
							try {
								response.getWriter().write("error");
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						
					}
					
				}
				else if(Ajax_type.equals("personal")){
					
					PersonalCourseModel personalCourse = PersonalCourseBLL.GetModel(Ajax_courseId);
					if(personalCourse == null){
						
						//����
						try {
							response.getWriter().write("error");
						} catch (IOException e) {
							e.printStackTrace();
						}
						
					}
					else{
						
						personalCourse.setScore(Ajax_score);
						if(PersonalCourseBLL.Update(personalCourse)){
							
							//���³ɹ�
							try {
								response.getWriter().write("success");
							} catch (IOException e) {
								e.printStackTrace();
							}
							
						}
						else{
							
							//����ʧ��
							try {
								response.getWriter().write("error");
							} catch (IOException e) {
								e.printStackTrace();
							}
							
						}
						
					}
					
				}
				else{
					
					
					//����
					try {
						response.getWriter().write("error");
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
				//

			}

		} else if (action.toLowerCase().equals("studentdetail")) {

			Integer courseId = Integer
					.valueOf(request.getParameter("courseId"));

			ArrayList<CompulsoryCourseModel> compulsoryCourseList = CompulsoryCourseBLL
					.GetArrayList("CourseId=?", courseId);
			ArrayList<PersonalCourseModel> personalCourseList = PersonalCourseBLL
					.GetArrayList("CourseId=?", courseId);

			request.setAttribute("personalCourseList", personalCourseList);
			request.setAttribute("compulsoryCourseList", compulsoryCourseList);

			if (courseId == null) {

				RequestDispatcher rd = request
						.getRequestDispatcher("TeacherServlet?action=courselist");
				try {
					rd.forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			} else {

				RequestDispatcher rd = request
						.getRequestDispatcher("Teacher/StudentDetail.jsp");
				try {
					rd.forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		} else if (action.toLowerCase().equals("stopcourselist")) {

			HttpSession session = request.getSession();
			String teacherId = (String) session.getAttribute("userId");

			ArrayList<CourseModel> CourseList = CourseBLL.GetArrayList(
					"TeacherId=?", teacherId);

			request.setAttribute("CourseList", CourseList);
			RequestDispatcher rd = request
					.getRequestDispatcher("Teacher/StopCourse.jsp");

			try {
				rd.forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (action.toLowerCase().equals("stopcourse")) {

			Long courseId = Long.valueOf(request.getParameter("courseId"));
			String stopWeek = request.getParameter("stopWeek");
			String addWeek = request.getParameter("addWeek");

			if ((courseId == null) || (stopWeek == null) || (addWeek == null)) {

				RequestDispatcher rd = request
						.getRequestDispatcher("Teacher/StopCourse.jsp");
				try {
					rd.forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			} else {
				ArrayList<StopCourseModel> stopCourseList = StopCourseBLL
						.GetArrayList("CourseId=?", courseId);
				Iterator<StopCourseModel> stopCourseIterator = stopCourseList
						.iterator();

				if (stopCourseIterator.hasNext()) {
					// ��Ϊ�ո������¸���ʱ��
					StopCourseModel StopCourse = stopCourseIterator.next();
					long StopCourseId = StopCourse.getStopCourseId();
					int StopWeek = Integer.valueOf(stopWeek);
					int AddWeek = Integer.valueOf(addWeek);
					StopCourseModel stopCourse = new StopCourseModel();

					stopCourse.setStopCourseId(StopCourseId);
					stopCourse.setCourseId(courseId);
					stopCourse.setStopWeek(StopWeek);
					stopCourse.setAddWeek(AddWeek);
					
					Boolean isSuccess = StopCourseBLL.Update(stopCourse);
					
					request.setAttribute("infoType", isSuccess.toString());

					RequestDispatcher rd = request
							.getRequestDispatcher("TeacherServlet?action=stopcourselist");

					try {
						rd.forward(request, response);
					} catch (ServletException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}

				} else {
					int StopWeek = Integer.valueOf(stopWeek);
					int AddWeek = Integer.valueOf(addWeek);
					StopCourseModel stopCourse = new StopCourseModel();

					stopCourse.setCourseId(courseId);
					stopCourse.setStopWeek(StopWeek);
					stopCourse.setAddWeek(AddWeek);

					Boolean isSuccess = StopCourseBLL.Insert(stopCourse);

					request.setAttribute("infoType", isSuccess.toString());

					RequestDispatcher rd = request
							.getRequestDispatcher("TeacherServlet?action=stopcourselist");

					try {

						rd.forward(request, response);

					} catch (ServletException e) {

						e.printStackTrace();

					} catch (IOException e) {

						e.printStackTrace();

					}
				}
			}
		}else if (action.toLowerCase().equals("addnews")){
			
			String save = request.getParameter("save");

			if (save == null || save == "") {

				// ���Ǳ�����̣����������Ϣҳ��
				RequestDispatcher rd = request
						.getRequestDispatcher("Teacher/AddNews.jsp");

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
						.getRequestDispatcher("TeacherServlet?action=newslist");

				try {
					rd.forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
			
		}else if (action.toLowerCase().equals("newslist")){

				// �г����е���Ϣ
				
				//��ȡ������Ϣ
				ArrayList<NewsModel> newsList = NewsBLL.GetArrayList();
				
				request.setAttribute("newsList", newsList);
				
				RequestDispatcher rd = request.getRequestDispatcher("Teacher/TeacherNews.jsp");
				
				try {
					rd.forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			
		} else if (action.toLowerCase().equals("details")){
			
            Integer newsId = Integer.valueOf(request.getParameter("newsId"));
			
			NewsModel news = NewsBLL.GetModel(newsId);
			
			if(news == null){
				
				//û��ȡ��NewsModel
				
				request.setAttribute("infoType", "false");

				RequestDispatcher rd = request.getRequestDispatcher("TeacherServlet?action=newslist");
				
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
				
				RequestDispatcher rd = request.getRequestDispatcher("Teacher/NewsDetails.jsp");
				
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
