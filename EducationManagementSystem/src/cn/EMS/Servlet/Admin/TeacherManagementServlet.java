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
		
		//�ж�action������ʲô
		if(action.toLowerCase().equals("list")){
			
			//���action��ֵΪlist�����г����еĹ���Ա
			//��ȡ���й���Ա������
			ArrayList<TeacherModel> teacherList = TeacherBLL.GetArrayList();
			
			//��request����httpͷ�з�װ�ո�ȡ�õ����� 
			request.setAttribute("teacherList", teacherList);
			
			//�õ�RequestDispatcher
			RequestDispatcher rd = request.getRequestDispatcher("Admin/TeacherManagement.jsp");
			
			//���д������ݵĹ���
			try {
				rd.forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
		}
		else if(action.toLowerCase().equals("add")){
			//��ӽ�ʦ
			String save = request.getParameter("save");
			
			if(save == null || save == ""){
				ArrayList<DepartmentModel> departmentList = DepartmentBLL
						.GetArrayList();
				request.setAttribute("departmentList", departmentList);
				//���Ǳ�����̣��������ҳ��
				
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
				
				//��ȡ���е�����
				String teacherId = request.getParameter("teacherId");
				String password = request.getParameter("password");
				String name = request.getParameter("teacherName");
				String departmentId = request.getParameter("departmentId");
				
				//��ǰ̨��ȡ��ֵ����һ��AdminModel
				TeacherModel teacher = new TeacherModel();
				teacher.setTeacherId(teacherId);
				teacher.setName(name);
				teacher.setPassword(password);
				teacher.setDepartmentId(Integer.parseInt(departmentId));
				
				//��AdminModelд�뵽���ݿ���
				Boolean isSuccess = TeacherBLL.Insert(teacher);
				
				//������ӽ����ǰ̨
				
				//��ת�����ҳ�棬���û�������ӹ���Ա������ʾ�ɹ�����ʧ�ܵ���Ϣ
				
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
		
			
			//�༭����Ա
			
			String save = request.getParameter("save");
			
			if(save == null || save == ""){
				
				//���Ǳ������
				//�õ���ʦ��Ϣ������ת���༭ҳ��
				
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
						
						//û�еõ���ʦ��Ϣ
						
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
						
						//�õ���ʦ��Ϣ
						
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
				
				//�Ǳ������
				//��ȡ����Ϣ�������и���
				
				String teacherId = request.getParameter("teacherId");
				String password = request.getParameter("password");
				String name = request.getParameter("teacherName");
				String departmentId = request.getParameter("departmentId");
				
				//��ǰ̨��ȡ��ֵ����һ��AdminModel
				TeacherModel teacher = new TeacherModel();
				teacher.setTeacherId(teacherId);
				teacher.setName(name);
				teacher.setPassword(password);
				teacher.setDepartmentId(Integer.parseInt(departmentId));
				
				//���²���
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
			
			//ɾ������Ա
			
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
			
			//û�в�����������˵�������ˣ��ǾͰ���list������
			
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
