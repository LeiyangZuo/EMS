package cn.EMS.Servlet.Admin;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.sun.tools.internal.ws.processor.model.Request;

import cn.EMS.BLL.AdminBLL;
import cn.EMS.BLL.DepartmentBLL;
import cn.EMS.BLL.MajorBLL;
import cn.EMS.BLL.PersonalCourseBLL;
import cn.EMS.BLL.StudentBLL;
import cn.EMS.BLL.TeacherBLL;
import cn.EMS.Model.AdminModel;
import cn.EMS.Model.DepartmentModel;
import cn.EMS.Model.MajorModel;
import cn.EMS.Model.PersonalCourseModel;
import cn.EMS.Model.StudentModel;
import cn.EMS.Model.TeacherModel;

public class StudentManagementServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public StudentManagementServlet() {
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

			ArrayList<StudentModel> studentList = StudentBLL.GetArrayList();

			request.setAttribute("studentList", studentList);

			// �õ�RequestDispatcher
			RequestDispatcher rd = request
					.getRequestDispatcher("Admin/StudentManagement.jsp");

			// ���д������ݵĹ���
			try {

				rd.forward(request, response);

			} catch (ServletException e) {

				e.printStackTrace();

			} catch (IOException e) {

				e.printStackTrace();

			}

		} 
		else if (action.toLowerCase().equals("add")) {

			String save = request.getParameter("save");
			if(save == null || save == ""){
				//���Ǳ������
				//�������ѧ����ҳ��
				ArrayList<DepartmentModel> departmentList = DepartmentBLL
						.GetArrayList();
				request.setAttribute("departmentList", departmentList);
				
				String DepartmentId = request.getParameter("departmentId");
				request.setAttribute("DepartmentId", DepartmentId);

				if(DepartmentId == null || DepartmentId == ""){
					
					RequestDispatcher rd = request.getRequestDispatcher("Admin/AddStudent.jsp");
					
					try {
						
						rd.forward(request, response);
					
					} catch (ServletException e) {
					
						e.printStackTrace();
					
					} catch (IOException e) {

						e.printStackTrace();
					
					}
					
					
				}else{
					
					int department = Integer.valueOf(DepartmentId);
					ArrayList<MajorModel> majorList = MajorBLL.GetArrayList("DepartmentId=?",department);
					request.setAttribute("majorList",majorList);
					RequestDispatcher rd = request
							.getRequestDispatcher("Student/AddStudent.jsp");

					try {
						rd.forward(request, response);
					} catch (ServletException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}		
				
			}
			else{
				//�Ǳ������
				// ��ȡ���е�����
				String studentId = request.getParameter("studentId");
				String studentName = request.getParameter("studentName");
				String password = request.getParameter("password");
				String startTime = request.getParameter("startTime");
				String departmentId = request.getParameter("departmentId");
				String majorId = request.getParameter("majorId");
				Boolean gender = true;
				if (request.getParameter("sex").equals("0")) {
					gender = false;
				}
				
				// ��ǰ̨��ȡ��ֵ����һ��AdminModel
				StudentModel student = new StudentModel();
				student.setStudentId(studentId);
				student.setName(studentName);
				student.setPassword(password);
				student.setDepartmentId(Integer.valueOf(departmentId));
				student.setMajorId(Integer.valueOf(majorId));
				student.setGender(gender);
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				try {
					student.setStartTime(df.parse(startTime));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				
				// ��AdminModelд�뵽���ݿ���
				Boolean isSuccess = StudentBLL.Insert(student);
				
				// ��ת�����ҳ�棬���û�������ӹ���Ա������ʾ�ɹ�����ʧ�ܵ���Ϣ
				if(isSuccess){
				try {
					response.sendRedirect("StudentManagementServlet?action=add&infoType=true");
				} catch (IOException e) {
					e.printStackTrace();
				}
				}else{
					try {
						response.sendRedirect("StudentManagementServlet?action=add&infoType=false");
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
			}
			
			

		} else if (action.toLowerCase().equals("edit")) {

			// �༭ѧ��
			String save = request.getParameter("save");
			
			if(save == null || save == ""){
				
				//���Ǳ������
				//�����༭ҳ��
				String studentId = request.getParameter("studentId");
				
					if(studentId == null){
						
						request.setAttribute("infoType", "false");
						
						RequestDispatcher rd = request.getRequestDispatcher("StudentManagementServlet?action=list");
						
						try {
							rd.forward(request, response);
						} catch (ServletException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}else{
						
						StudentModel student = StudentBLL.GetModel(studentId);
						
						request.setAttribute("studentModel", student);
						
						ArrayList<DepartmentModel> departmentList = DepartmentBLL
								.GetArrayList();
						
						request.setAttribute("departmentList", departmentList);
						
						String DepartmentId = request.getParameter("departmentId");
						request.setAttribute("DepartmentId", DepartmentId);

						if(DepartmentId == null || DepartmentId == ""){
							
							RequestDispatcher rd = request.getRequestDispatcher("Admin/EditStudent.jsp");
							
							try {
								
								rd.forward(request, response);
							
							} catch (ServletException e) {
							
								e.printStackTrace();
							
							} catch (IOException e) {

								e.printStackTrace();
							
							}
							
							
						}else{
							
							int department = Integer.valueOf(DepartmentId);
							ArrayList<MajorModel> majorList = MajorBLL.GetArrayList("DepartmentId=?",department);
							request.setAttribute("majorList",majorList);
							RequestDispatcher rd = request
									.getRequestDispatcher("Admin/EditStudent.jsp");

							try {
								rd.forward(request, response);
							} catch (ServletException e) {
								e.printStackTrace();
							} catch (IOException e) {
								e.printStackTrace();
							}
							
						}
					}
				}else{
				//�Ǳ������
				// ��ȡ���е�����
				String studentId = request.getParameter("studentId");
				String studentName = request.getParameter("studentName");
				String password = request.getParameter("password");
				String startTime = request.getParameter("startTime");
				String departmentId = request.getParameter("departmentId");
				String majorId = request.getParameter("majorId");
				Boolean gender = true;
				if (request.getParameter("sex").equals("0")) {
					gender = false;
				}
				
				// ��ǰ̨��ȡ��ֵ����һ��AdminModel
				StudentModel student = new StudentModel();
				student.setStudentId(studentId);
				student.setName(studentName);
				student.setPassword(password);
				student.setDepartmentId(Integer.valueOf(departmentId));
				student.setMajorId(Integer.valueOf(majorId));
				student.setGender(gender);
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				try {
					student.setStartTime(df.parse(startTime));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				
				// ��AdminModelд�뵽���ݿ���
				Boolean isSuccess = StudentBLL.Update(student);
				
				// ��ת�����ҳ�棬���û�������ӹ���Ա������ʾ�ɹ�����ʧ�ܵ���Ϣ
				if(isSuccess){
				try {
					response.sendRedirect("StudentManagementServlet?action=list&infoType=true");
				} catch (IOException e) {
					e.printStackTrace();
				}
				}else{
					try {
						response.sendRedirect("StudentManagementServlet?action=list&infoType=false");
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
			}

		} else if (action.toLowerCase().equals("delete")) {

			// ɾ������Ա

			String studentId = request.getParameter("studentId");

			Boolean isSuccess = StudentBLL.Delete(studentId);

			request.setAttribute("infoType", isSuccess.toString());

			RequestDispatcher rd = request
					.getRequestDispatcher("StudentManagementServlet?action=list");

			try {

				rd.forward(request, response);

			} catch (ServletException e) {

				e.printStackTrace();

			} catch (IOException e) {

				e.printStackTrace();

			}

		}else if(action.toLowerCase().equals("getmajor")){
			
			//ͨ��ajax����̨ȡ�õ�רҵ���ݴ��ݵ�ǰ̨
			String departmentIdStr = request.getParameter("departmentId");
			JSONObject obj = new JSONObject();
			if(departmentIdStr == null || departmentIdStr == ""){
				
				//û�еõ�����
				obj.accumulate("info", false);
				
			}
			else{
				
				//�õ����ݣ�ͨ��JSON��ʽ������ת�����ַ��������ݵ�ǰ̨
				Integer departmentId = Integer.valueOf(departmentIdStr);
				ArrayList<MajorModel> majorList = MajorBLL.GetArrayList("DepartmentId=?",departmentId);
				obj.accumulate("majorList", majorList);
				obj.accumulate("info", true);
				
			}
			//ֵ����
			try {
				System.out.println(obj.toString());
				response.getWriter().write(obj.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
		}
		else {

			// û�в�����������˵�������ˣ��ǾͰ���list������

			RequestDispatcher dispatcher = request
					.getRequestDispatcher("StudentManagementServlet?action=list");

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
