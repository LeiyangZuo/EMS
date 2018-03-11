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
			String studentId = (String) session.getAttribute("userId");
			String Term = request.getParameter("term");
			request.setAttribute("Term", Term);

			if (studentId == null || studentId == "") {

				// ��StuentIdΪ�գ�����
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
				// ��ûѡ��term����Ϊչʾ����
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
					// ��ѡ����term����ʾ��ѧ�ڵĿγ̡�
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
		} else if (action.toLowerCase().equals("scorelist")) {

			HttpSession session = request.getSession();
			String studentId = (String) session.getAttribute("userId");
			String Term = request.getParameter("term");
			request.setAttribute("Term", Term);

			if (studentId == null || studentId == "") {

				// ��StuentIdΪ�գ�����
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
				// ��ûѡ��term����Ϊչʾ����
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
					// ��Ϊ����ʾ���������
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
					// Ϊ�վ�add
					PersonalCourseModel personalCourse = new PersonalCourseModel();
					personalCourse.setCourseId(CourseId);
					personalCourse.setStudentId(studentId);
					personalCourse.setIsInUse(true);
					
					StudentModel student = StudentBLL.GetModel(studentId);
					
					Calendar cal = Calendar.getInstance();
					//��cal����Ϊϵͳ��ǰʱ��
					cal.setTimeInMillis(System.currentTimeMillis());
					int currentYear = cal.get(Calendar.YEAR);
					int currentMonth = cal.get(Calendar.MONTH);
					
					//���ѧ����ѧʱ��������
					//��cal����Ϊѧ������ѧʱ��
					cal.setTimeInMillis(student.getStartTime().getTime());
					int studentStartYear = cal.get(Calendar.YEAR);
					int studentStartMonth = cal.get(Calendar.MONTH) + 1;
					
					int monthAccount = (currentYear - studentStartYear) * 12 + (currentMonth - studentStartMonth + 1);
					
					int term = monthAccount / 6 + 1;
					
					personalCourse.setTerm(term);
					personalCourse.setScore(0);

					Boolean isSuccess = PersonalCourseBLL
							.Insert(personalCourse);
          
					// ��ת���б�ҳ
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
				
				//û��ȡ��NewsModel
				
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
				
				//ȡ����NewsModel
				//����һ��ҳ������ʾ
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
