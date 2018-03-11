package cn.EMS.Servlet.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.EMS.BLL.CompulsoryCourseBLL;
import cn.EMS.BLL.CourseBLL;
import cn.EMS.BLL.NewsBLL;
import cn.EMS.BLL.PersonalCourseBLL;
import cn.EMS.BLL.StudentBLL;
import cn.EMS.Model.CompulsoryCourseModel;
import cn.EMS.Model.CourseModel;
import cn.EMS.Model.NewsModel;
import cn.EMS.Model.PersonalCourseModel;
import cn.EMS.Model.StudentModel;

public class StudentServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public StudentServlet() {
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
			String studentId = (String) session.getAttribute("userId");
			String Term = request.getParameter("term");
			request.setAttribute("Term", Term);

			if (studentId == null || studentId == "") {

				// 若StuentId为空，出错
				RequestDispatcher rd = request
						.getRequestDispatcher("Student/Course.jsp");

				try {
					rd.forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			} else {
				// 若没选择term，则为展示界面
				if (Term == null || Term == "") {

					RequestDispatcher rd = request
							.getRequestDispatcher("Student/Course.jsp");

					try {
						rd.forward(request, response);
					} catch (ServletException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}

				} else {
					int term = Integer.valueOf(Term);
					// 若选择了term，显示该学期的课程。
					ArrayList<PersonalCourseModel> personalCourseList = PersonalCourseBLL
							.GetArrayList("StudentId=? and Term=?", studentId,
									term);
					ArrayList<CompulsoryCourseModel> compulsoryCourseList = CompulsoryCourseBLL
							.GetArrayList("StudentId=? and Term=?", studentId,
									term);


					request.setAttribute("personalCourseList",
							personalCourseList);
					request.setAttribute("compulsoryCourseList",
							compulsoryCourseList);

					RequestDispatcher rd = request
							.getRequestDispatcher("Student/Course.jsp");

					try {
						rd.forward(request, response);
					} catch (ServletException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

		} else if (action.toLowerCase().equals("deletecourse")) {

			Long personalCourseId = Long.valueOf(request
					.getParameter("selectId"));

			if ((personalCourseId == null)) {

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
		} else if (action.toLowerCase().equals("scorelist")) {

			HttpSession session = request.getSession();
			String studentId = (String) session.getAttribute("userId");
			String Term = request.getParameter("term");
			request.setAttribute("Term", Term);

			if (studentId == null || studentId == "") {

				// 若StuentId为空，出错
				RequestDispatcher rd = request
						.getRequestDispatcher("Student/Score.jsp");

				try {
					rd.forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			} else {
				// 若没选择term，则为展示界面
				if (Term == null || Term == "") {

					RequestDispatcher rd = request
							.getRequestDispatcher("Student/Score.jsp");

					try {
						rd.forward(request, response);
					} catch (ServletException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}

				} else {
					int term = Integer.valueOf(Term);

					ArrayList<PersonalCourseModel> personalCourseList = PersonalCourseBLL
							.GetArrayList("StudentId=? and Term=?", studentId,term);
					ArrayList<CompulsoryCourseModel> compulsoryCourseList = CompulsoryCourseBLL
							.GetArrayList("StudentId=? and Term=?", studentId,term);

					request.setAttribute("personalCourseList",
							personalCourseList);
					request.setAttribute("compulsoryCourseList",
							compulsoryCourseList);

					RequestDispatcher rd = request
							.getRequestDispatcher("Student/Score.jsp");

					try {
						rd.forward(request, response);
					} catch (ServletException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

		} else if (action.toLowerCase().equals("selectedcourselist")) {


			ArrayList<CourseModel> courseList = CourseBLL.GetArrayList(
					"IsCompulsoryCourse=?", "0");

			request.setAttribute("courseList", courseList);

			RequestDispatcher rd = request
					.getRequestDispatcher("Student/SelectedCourse.jsp");

			try {
				rd.forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (action.toLowerCase().equals("addselectedcourse")) {
			
			String courseId = request.getParameter("courseId");
			HttpSession session = request.getSession();
			String studentId = (String) session.getAttribute("userId");

			if ((courseId == null)) {

				request.setAttribute("infoType", "false");

				RequestDispatcher rd = request
						.getRequestDispatcher("Student/SelectedCourse.jsp");

				try {
					rd.forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			} else {
                long CourseId = Long.valueOf(courseId);
				ArrayList<PersonalCourseModel> personalCourseList = PersonalCourseBLL
						.GetArrayList("StudentId=? and CourseId=?", studentId,
								CourseId);
				Iterator<PersonalCourseModel> personalCourseIterator = personalCourseList
						.iterator();

				if (personalCourseIterator.hasNext()) {
					// 不为空显示不可以添加
					request.setAttribute("infoType", "cannot");

					RequestDispatcher rd = request
							.getRequestDispatcher("Student/Course.jsp");

					try {
						rd.forward(request, response);
					} catch (ServletException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}

				} else {
					// 为空就add
					PersonalCourseModel personalCourse = new PersonalCourseModel();
					personalCourse.setCourseId(CourseId);
					personalCourse.setStudentId(studentId);
					personalCourse.setIsInUse(true);
					
					StudentModel student = StudentBLL.GetModel(studentId);
					
					Calendar cal = Calendar.getInstance();
					//将cal设置为系统当前时间
					cal.setTimeInMillis(System.currentTimeMillis());
					int currentYear = cal.get(Calendar.YEAR);
					int currentMonth = cal.get(Calendar.MONTH);
					
					//获得学生入学时间的年和月
					//将cal设置为学生的入学时间
					cal.setTimeInMillis(student.getStartTime().getTime());
					int studentStartYear = cal.get(Calendar.YEAR);
					int studentStartMonth = cal.get(Calendar.MONTH) + 1;
					
					int monthAccount = (currentYear - studentStartYear) * 12 + (currentMonth - studentStartMonth + 1);
					
					int term = monthAccount / 6 + 1;
					
					personalCourse.setTerm(term);
					personalCourse.setScore(0);

					Boolean isSuccess = PersonalCourseBLL
							.Insert(personalCourse);
          
					// 跳转到列表页
					request.setAttribute("infoType",isSuccess.toString());

					RequestDispatcher rd = request
							.getRequestDispatcher("Student/Course.jsp");

					try {
						rd.forward(request, response);
					} catch (ServletException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}
		}else if(action.toLowerCase().equals("newslist")){
			
            ArrayList<NewsModel> newsList = NewsBLL.GetArrayList();
			
			request.setAttribute("newsList", newsList);
			
			RequestDispatcher rd = request.getRequestDispatcher("Student/StudentNews.jsp");
			
			try {
				rd.forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else if (action.toLowerCase().equals("details")){
			
            Integer newsId = Integer.valueOf(request.getParameter("newsId"));
			
			NewsModel news = NewsBLL.GetModel(newsId);
			
			if(news == null){
				
				//没有取到NewsModel
				
				request.setAttribute("infoType", "false");

				RequestDispatcher rd = request.getRequestDispatcher("StudentServlet?action=newslist");
				
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
				
				RequestDispatcher rd = request.getRequestDispatcher("Student/NewsDetails.jsp");
				
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
