package cn.EMS.Servlet.Admin;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.EMS.BLL.CompulsoryCourseBLL;
import cn.EMS.BLL.CourseBLL;
import cn.EMS.BLL.PersonalCourseBLL;
import cn.EMS.BLL.StopCourseBLL;
import cn.EMS.BLL.StudentBLL;
import cn.EMS.Model.CompulsoryCourseModel;
import cn.EMS.Model.CourseModel;
import cn.EMS.Model.PersonalCourseModel;
import cn.EMS.Model.StopCourseModel;
import cn.EMS.Model.StudentModel;

public class CourseManagementServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public CourseManagementServlet() {
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

			ArrayList<CourseModel> courseList = CourseBLL.GetArrayList(
					"IsCompulsoryCourse=?", "0");

			request.setAttribute("courseList", courseList);

			// �õ�RequestDispatcher
			RequestDispatcher rd = request
					.getRequestDispatcher("Admin/SelectedCourseManagement.jsp");

			// ���д������ݵĹ���
			try {

				rd.forward(request, response);

			} catch (ServletException e) {

				e.printStackTrace();

			} catch (IOException e) {

				e.printStackTrace();

			}

		} else if (action.toLowerCase().equals("add")) {

			String save = request.getParameter("save");
			if (save == null || save == "") {

				RequestDispatcher rd = request
						.getRequestDispatcher("Admin/AddCourse.jsp");

				try {

					rd.forward(request, response);

				} catch (ServletException e) {

					e.printStackTrace();

				} catch (IOException e) {

					e.printStackTrace();

				}

			} else {

				// �Ǳ������
				// ��ȡ���е�����
				String name = request.getParameter("name");
				String description = request.getParameter("description");
				String teacherId = request.getParameter("teacherId");
				String period = request.getParameter("period");
				String classTime = request.getParameter("classTime");
				Boolean isCompulsoryCourse = true;

				if (request.getParameter("isCompulsoryCourse").equals("0")) {
					isCompulsoryCourse = false;
				}

				// ��ǰ̨��ȡ��ֵ����һ��AdminModel
				CourseModel course = new CourseModel();
				course.setName(name);
				course.setDescription(description);
				course.setTeacherId(teacherId);
				course.setPeriod(Integer.valueOf(period));
				course.setClassTime(classTime);
				course.setIsCompulsoryCourse(isCompulsoryCourse);

				// ��AdminModelд�뵽���ݿ���
				Boolean isSuccess = CourseBLL.Insert(course);

				// ������ӽ����ǰ̨
				request.setAttribute("infoType", isSuccess.toString());

				// ��ת�����ҳ�棬���û�������ӹ���Ա������ʾ�ɹ�����ʧ�ܵ���Ϣ
				RequestDispatcher rd = request
						.getRequestDispatcher("Admin/AddCourse.jsp");

				try {

					rd.forward(request, response);

				} catch (ServletException e) {

					e.printStackTrace();

				} catch (IOException e) {

					e.printStackTrace();

				}
			}

		} else if (action.toLowerCase().equals("delete")) {

			long courseId = Long.valueOf(request.getParameter("courseId"));

			Boolean isSuccess = CourseBLL.Delete(courseId);

			request.setAttribute("infoType", isSuccess.toString());

			RequestDispatcher rd = request
					.getRequestDispatcher("CourseManagementServlet?action=list");

			try {

				rd.forward(request, response);

			} catch (ServletException e) {

				e.printStackTrace();

			} catch (IOException e) {

				e.printStackTrace();

			}

		} else if (action.toLowerCase().equals("stopcourselist")) {

			ArrayList<StopCourseModel> stopCourseList = StopCourseBLL
					.GetArrayList();

			request.setAttribute("stopCourseList", stopCourseList);

			// �õ�RequestDispatcher
			RequestDispatcher rd = request
					.getRequestDispatcher("Admin/CheckStopCourse.jsp");

			// ���д������ݵĹ���
			try {

				rd.forward(request, response);

			} catch (ServletException e) {

				e.printStackTrace();

			} catch (IOException e) {

				e.printStackTrace();

			}

		} else if (action.toLowerCase().equals("passstopcourse")) {

			long stopCourseId = Long.valueOf(request
					.getParameter("stopCourseId"));
			StopCourseModel stopCourse = StopCourseBLL.GetModel(stopCourseId);
			int stopWeek = stopCourse.getStopWeek();
			int addWeek = stopCourse.getAddWeek();
			ArrayList<PersonalCourseModel> personalCourseList = PersonalCourseBLL
					.GetArrayList("CourseId=?", stopCourse.getCourseId());
			ArrayList<CompulsoryCourseModel> compulsoryCourseList = CompulsoryCourseBLL
					.GetArrayList("CourseId=?", stopCourse.getCourseId());
			ArrayList<Boolean> isSuccessList = new ArrayList<Boolean>();

			Boolean isSuccess = false;

			if (personalCourseList.isEmpty() & compulsoryCourseList.isEmpty()) {

				request.setAttribute("infoType", "false");

				RequestDispatcher rd = request
						.getRequestDispatcher("CourseManagementServlet?action=stopcourselist");

				try {

					rd.forward(request, response);

				} catch (ServletException e) {

					e.printStackTrace();

				} catch (IOException e) {

					e.printStackTrace();

				}

			} else {

				Iterator<PersonalCourseModel> personalCourseIterator = personalCourseList
						.iterator();
				
				while (personalCourseIterator.hasNext()) {

					PersonalCourseModel personalCourse = personalCourseIterator
							.next();
					CourseModel course = CourseBLL.GetModel(personalCourse.getCourseId());

					course.setStopWeek(stopWeek);
					course.setAddWeek(addWeek);

					isSuccess = CourseBLL.Update(course);
					isSuccessList.add(isSuccess);

				}

				Iterator<CompulsoryCourseModel> compulsoryCourseIterator = compulsoryCourseList
						.iterator();
				while (compulsoryCourseIterator.hasNext()) {

					CompulsoryCourseModel compulsoryCourse = compulsoryCourseIterator
							.next();
				    CourseModel course = CourseBLL.GetModel(compulsoryCourse.getCourseId());
				    
					course.setStopWeek(stopWeek);
					course.setAddWeek(addWeek);
				    
					isSuccess = CourseBLL.Update(course);
					isSuccessList.add(isSuccess);

				}			

				isSuccess = StopCourseBLL.Delete(stopCourseId);
				isSuccessList.add(isSuccess);
				
				Iterator<Boolean> isSuccessIterator = isSuccessList
						.iterator();
				while(isSuccessIterator.hasNext()){
					
					boolean isSuccess1 = isSuccessIterator.next();
					if(isSuccess1){
						
						isSuccess = true;
						
					}else{
						
						isSuccess = false;
						break;
						
					}
					
				}
				
				request.setAttribute("infoType", isSuccess.toString());

				RequestDispatcher rd = request
						.getRequestDispatcher("CourseManagementServlet?action=stopcourselist");

				try {

					rd.forward(request, response);

				} catch (ServletException e) {

					e.printStackTrace();

				} catch (IOException e) {

					e.printStackTrace();

				}
				

			}

		} else if (action.toLowerCase().equals("refusestopcourse")) {

			long stopCourseId = Long.valueOf(request
					.getParameter("stopCourseId"));

			Boolean isSuccess = StopCourseBLL.Delete(stopCourseId);

			request.setAttribute("infoType", isSuccess.toString());

			RequestDispatcher rd = request
					.getRequestDispatcher("CourseManagementServlet?action=stopcourselist");

			try {

				rd.forward(request, response);

			} catch (ServletException e) {

				e.printStackTrace();

			} catch (IOException e) {

				e.printStackTrace();

			}

		} else {

			// û�в�����������˵�������ˣ��ǾͰ���list������

			RequestDispatcher dispatcher = request
					.getRequestDispatcher("CourseManagementServlet?action=list");

			try {

				dispatcher.forward(request, response);

			} catch (ServletException e) {

				e.printStackTrace();

			} catch (IOException e) {

				e.printStackTrace();

			}
		}
	}

}
