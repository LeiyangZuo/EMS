package cn.EMS.Servlet.Admin;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.EMS.BLL.AdminBLL;
import cn.EMS.BLL.DepartmentBLL;
import cn.EMS.BLL.TeacherBLL;
import cn.EMS.Model.AdminModel;
import cn.EMS.Model.DepartmentModel;
import cn.EMS.Model.TeacherModel;

public class TeacherManagementServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public TeacherManagementServlet() {
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
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}
	
public void service(HttpServletRequest request, HttpServletResponse response){
		
		String action = request.getParameter("action");
		
		//判断action参数是什么
		if(action.toLowerCase().equals("list")){
			
			//如果action的值为list，则列出所有的管理员
			//获取所有管理员的数据
			ArrayList<TeacherModel> teacherList = TeacherBLL.GetArrayList();
			
			//在request，即http头中封装刚刚取得的数据 
			request.setAttribute("teacherList", teacherList);
			
			//得到RequestDispatcher
			RequestDispatcher rd = request.getRequestDispatcher("Admin/TeacherManagement.jsp");
			
			//进行传递数据的过程
			try {
				rd.forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
		}
		else if(action.toLowerCase().equals("add")){
			//添加教师
			String save = request.getParameter("save");
			
			if(save == null || save == ""){
				ArrayList<DepartmentModel> departmentList = DepartmentBLL
						.GetArrayList();
				request.setAttribute("departmentList", departmentList);
				//不是保存过程，给出添加页面
				
				RequestDispatcher rd = request.getRequestDispatcher("Admin/AddTeacher.jsp");
				
				try {
					rd.forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			else{
				
				//获取表单中的数据
				String teacherId = request.getParameter("teacherId");
				String password = request.getParameter("password");
				String name = request.getParameter("teacherName");
				String departmentId = request.getParameter("departmentId");
				
				//将前台获取的值构建一个AdminModel
				TeacherModel teacher = new TeacherModel();
				teacher.setTeacherId(teacherId);
				teacher.setName(name);
				teacher.setPassword(password);
				teacher.setDepartmentId(Integer.parseInt(departmentId));
				
				//把AdminModel写入到数据库中
				Boolean isSuccess = TeacherBLL.Insert(teacher);
				
				//传递添加结果给前台
				
				//跳转到添加页面，供用户继续添加管理员并且显示成功或者失败的信息
				
				if(isSuccess){
					
					try {
						response.sendRedirect("TeacherManagementServlet?action=add&infoType=true");
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					
				}
				else{
					
					try {
						response.sendRedirect("TeacherManagementServlet?action=add&infoType=false");
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
				
				
			}		
		}
		else if(action.toLowerCase().equals("edit")){
		
			
			//编辑管理员
			
			String save = request.getParameter("save");
			
			if(save == null || save == ""){
				
				//不是保存过程
				//得到教师信息并且跳转到编辑页面
				
				String teacherId = request.getParameter("teacherId");
				ArrayList<DepartmentModel> departmentList = DepartmentBLL
						.GetArrayList();
				
				request.setAttribute("departmentList", departmentList);
				
				if(teacherId == null || teacherId == ""){
					
					request.setAttribute("infoType", "false");
					
					RequestDispatcher rd = request.getRequestDispatcher("TeacherManagementServlet?action=list");
					
					try {
						rd.forward(request, response);
					} catch (ServletException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else{
					
					TeacherModel teacher = TeacherBLL.GetModel(teacherId);
		           
					
					if(teacher == null){
						
						//没有得到教师信息
						
						request.setAttribute("infoType", "false");
						
						RequestDispatcher rd = request.getRequestDispatcher("TeacherManagementServlet?action=list");
						
						try {
							rd.forward(request, response);
						} catch (ServletException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					else{
						
						//得到教师信息
						
						request.setAttribute("teacherModel", teacher);
						
						RequestDispatcher rd = request.getRequestDispatcher("Admin/EditTeacher.jsp");
						
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
			else{
				
				//是保存过程
				//获取表单信息，并进行更新
				
				String teacherId = request.getParameter("teacherId");
				String password = request.getParameter("password");
				String name = request.getParameter("teacherName");
				String departmentId = request.getParameter("departmentId");
				
				//将前台获取的值构建一个AdminModel
				TeacherModel teacher = new TeacherModel();
				teacher.setTeacherId(teacherId);
				teacher.setName(name);
				teacher.setPassword(password);
				teacher.setDepartmentId(Integer.parseInt(departmentId));
				
				//更新操作
				Boolean isSuccess = TeacherBLL.Update(teacher);
				
				request.setAttribute("infoType", isSuccess.toString());
				
				RequestDispatcher rd = request.getRequestDispatcher("TeacherManagementServlet?action=list");
				
				try {
					rd.forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		else if(action.toLowerCase().equals("delete")){
			
			//删除管理员
			
			String teacherId = request.getParameter("teacherId");
			
			Boolean isSuccess = TeacherBLL.Delete(teacherId);
			
			request.setAttribute("infoType", isSuccess.toString());
			
			RequestDispatcher rd = request.getRequestDispatcher("TeacherManagementServlet?action=list");
			
			try {
				rd.forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		else{
			
			//没有参数传过来，说明出错了，那就按照list来处理
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("TeacherManagementServlet?action=list");
			
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
