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

			// 得到RequestDispatcher
			RequestDispatcher rd = request
					.getRequestDispatcher("Admin/StudentManagement.jsp");

			// 进行传递数据的过程
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
				//不是保存过程
				//给出添加学生的页面
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
				//是保存过程
				// 获取表单中的数据
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
				
				// 将前台获取的值构建一个AdminModel
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
				
				// 把AdminModel写入到数据库中
				Boolean isSuccess = StudentBLL.Insert(student);
				
				// 跳转到添加页面，供用户继续添加管理员并且显示成功或者失败的信息
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

			// 编辑学生
			String save = request.getParameter("save");
			
			if(save == null || save == ""){
				
				//不是保存过程
				//给出编辑页面
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
				//是保存过程
				// 获取表单中的数据
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
				
				// 将前台获取的值构建一个AdminModel
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
				
				// 把AdminModel写入到数据库中
				Boolean isSuccess = StudentBLL.Update(student);
				
				// 跳转到添加页面，供用户继续添加管理员并且显示成功或者失败的信息
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

			// 删除管理员

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
			
			//通过ajax将后台取得的专业数据传递到前台
			String departmentIdStr = request.getParameter("departmentId");
			JSONObject obj = new JSONObject();
			if(departmentIdStr == null || departmentIdStr == ""){
				
				//没有得到数据
				obj.accumulate("info", false);
				
			}
			else{
				
				//得到数据，通过JSON格式将数据转换成字符串，传递到前台
				Integer departmentId = Integer.valueOf(departmentIdStr);
				ArrayList<MajorModel> majorList = MajorBLL.GetArrayList("DepartmentId=?",departmentId);
				obj.accumulate("majorList", majorList);
				obj.accumulate("info", true);
				
			}
			//值传递
			try {
				System.out.println(obj.toString());
				response.getWriter().write(obj.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
		}
		else {

			// 没有参数传过来，说明出错了，那就按照list来处理

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
