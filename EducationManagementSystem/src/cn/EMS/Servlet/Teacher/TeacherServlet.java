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

		// 判断action参数是什么
		if (action.toLowerCase().equals("studentinfo")) {

			// 获得Session
			HttpSession session = request.getSession();
			// 获取Session中得ID信息
			String studentId = (String) session.getAttribute("userId");

			// 得到StudentModel
			StudentModel student = StudentBLL.GetModel(studentId);

			if (student == null) {

				// 学生信息不存在，登录失败，返回登录页

				// 重定向到登录页
				try {
					response.sendRedirect("Login/login.jsp");
				} catch (IOException e) {
					e.printStackTrace();
				}

			} else {

				// 在request，即http头中封装刚刚取得的数据
				request.setAttribute("studentModel", student);

				// 得到RequestDispatcher
				RequestDispatcher rd = request
						.getRequestDispatcher("Student/StudentInfo.jsp");

				// 进行传递数据的过程
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

				// 成功获取消息ID，删除消息
				Boolean isSuccess = PersonalCourseBLL.Delete(personalCourseId);

				request.setAttribute("infoType", isSuccess.toString());

				// 跳转到列表页
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
				// 不是保存页面，是编辑页面
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
					// 给出编辑界面
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
				//是保存
				//通过AJAX实现
				Long Ajax_courseId = Long.valueOf(request.getParameter("courseId"));
				Integer Ajax_score = Integer.valueOf(request.getParameter("score"));
				String Ajax_type = request.getParameter("type");
				
				if(Ajax_type.equals("compulsory")){
					
					
					CompulsoryCourseModel compulsoryCourse = CompulsoryCourseBLL.GetModel(Ajax_courseId);
					if(compulsoryCourse == null){
						
						//出错
						try {
							response.getWriter().write("error");
						} catch (IOException e) {
							e.printStackTrace();
						}
						
					}
					else{
						
						
						compulsoryCourse.setScore(Ajax_score);
						if(CompulsoryCourseBLL.Update(compulsoryCourse)){
							
							
							//更新成功
							try {
								response.getWriter().write("success");
							} catch (IOException e) {
								e.printStackTrace();
							}
							
						}
						else{
							
							//更新失败
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
						
						//出错
						try {
							response.getWriter().write("error");
						} catch (IOException e) {
							e.printStackTrace();
						}
						
					}
					else{
						
						personalCourse.setScore(Ajax_score);
						if(PersonalCourseBLL.Update(personalCourse)){
							
							//更新成功
							try {
								response.getWriter().write("success");
							} catch (IOException e) {
								e.printStackTrace();
							}
							
						}
						else{
							
							//更新失败
							try {
								response.getWriter().write("error");
							} catch (IOException e) {
								e.printStackTrace();
							}
							
						}
						
					}
					
				}
				else{
					
					
					//出错
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
					// 不为空更新最新更改时间
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

				// 不是保存过程，给出添加消息页面
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

				// 列出所有的消息
				
				//获取所有信息
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
				
				//没有取到NewsModel
				
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
				
				//取到了NewsModel
				//则在一个页面中显示
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
