package cn.EMS.Servlet.Admin;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.EMS.BLL.AdminBLL;
import cn.EMS.Model.AdminModel;

public class AdminServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AdminServlet() {
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
			ArrayList<AdminModel> adminList = AdminBLL.GetArrayList();
			
			//��request����httpͷ�з�װ�ո�ȡ�õ����� 
			request.setAttribute("adminList", adminList);
			
			//�õ�RequestDispatcher
			RequestDispatcher rd = request.getRequestDispatcher("Admin/AdminManagement.jsp");
			
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
			//��ӹ���Ա
		
			
			String save = request.getParameter("save");
			if(save == null || save == ""){
				
				//���Ǳ�����̣����������ҳ��
				
				RequestDispatcher rd = request.getRequestDispatcher("Admin/AddAdmin.jsp");
				
				try {
				
					rd.forward(request, response);
				
				} catch (ServletException e) {
				
					e.printStackTrace();
				
				} catch (IOException e) {

					e.printStackTrace();
				
				}
				
			}
			else{
				
				//����Ĺ���
				
				//��ȡ���е�����
				String adminId = request.getParameter("adminId");
				String password = request.getParameter("password");
				String name = request.getParameter("name");
				Boolean isSuper = false;
				if(request.getParameter("isSuper") != null){
					
					isSuper = true;
					
				}
				
				//��ǰ̨��ȡ��ֵ����һ��AdminModel
				AdminModel admin = new AdminModel();
				admin.setAdminId(adminId);
				admin.setName(name);
				admin.setPassword(password);
				admin.setIsSuper(isSuper);
				
				//��AdminModelд�뵽���ݿ���
				Boolean isSuccess = AdminBLL.Insert(admin);
				
				//������ӽ����ǰ̨
				request.setAttribute("infoType", isSuccess.toString());
				
				//��ת�����ҳ�棬���û�������ӹ���Ա������ʾ�ɹ�����ʧ�ܵ���Ϣ
				RequestDispatcher rd = request.getRequestDispatcher("Admin/AddAdmin.jsp");
				
				try {
					
					rd.forward(request, response);
					
				} catch (ServletException e) {
					
					e.printStackTrace();
					
				} catch (IOException e) {
					
					e.printStackTrace();
					
				}
			}
			
			
		}
		else if(action.toLowerCase().equals("edit")){
		
			
			//�༭����Ա
			
			//�жϽ������ǵõ��༭ҳ��Ĺ��̻��Ǳ༭�����Ҫ����Ĺ���
			String save = request.getParameter("save");
			
			if(save == null){
				//���save Ϊ null��˵��û��ȡ��save������������һ��˵�����ڲ��Ǳ���༭���ݵĹ���
				//�������Ҫ�༭��ҳ��
				
				String adminId = request.getParameter("adminId");
				if(adminId == null || adminId == ""){
					
					//˵��û�еõ�adminId
					//��ת��Admin���б�ҳ�棬���û�����ѡ��
					RequestDispatcher rd = request.getRequestDispatcher("AdminServlet?action=list");
					//TODO ��Ҫ��Ӵ�����Ϣ
					
					try {
						rd.forward(request, response);
					} catch (ServletException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
				else{
					
					//�õ���adminID
					//ȡ���������Ա���е���Ϣ
					AdminModel admin = AdminBLL.GetModel(adminId);
					
					if(admin == null){
						
						//û��ȡ��AdminModel
						//��ת��Admin���б�ҳ�棬���û�����ѡ��
						RequestDispatcher rd = request.getRequestDispatcher("AdminServlet?action=list");
						//TODO ��Ҫ��Ӵ�����Ϣ
						
						try {
							rd.forward(request, response);
						} catch (ServletException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					else{
						
						//ȡ����AdminModel����Ϣ����ʼ�����༭ҳ��
						//����ֵ��EditAdmin.jsp
						request.setAttribute("adminModel", admin);

						//��ת���༭ҳ��
						RequestDispatcher rd = request.getRequestDispatcher("Admin/EditAdmin.jsp");

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
				
				//�Ǳ�����̣�˵���û��޸ĵ���Ϣ�Ѿ��޸�����ˣ���Ҫ���µ����ݿ���
				
				//��ȡ���е�����
				String adminId = request.getParameter("adminId");
				String password = request.getParameter("password");
				String name = request.getParameter("name");
				Boolean isSuper = false;
				if(request.getParameter("isSuper") != null){
					
					isSuper = true;
					
				}
				
				//��ǰ̨��ȡ�����ݹ���һ��AdminModel
				AdminModel admin = new AdminModel();
				
				admin.setAdminId(adminId);
				admin.setName(name);
				admin.setPassword(password);
				admin.setIsSuper(isSuper);
				
				Boolean isSuccess = AdminBLL.Update(admin);
				
				
				//������ӽ����ǰ̨
				request.setAttribute("infoType", isSuccess.toString());
				
				//��ת�����ҳ�棬���û�������ӹ���Ա������ʾ�ɹ�����ʧ�ܵ���Ϣ
				RequestDispatcher rd = request.getRequestDispatcher("AdminServlet?action=list");
				
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
			
			String adminId = request.getParameter("adminId");
			
			Boolean isSuccess = AdminBLL.Delete(adminId);
			
			request.setAttribute("infoType", isSuccess.toString());
			
			RequestDispatcher rd = request.getRequestDispatcher("AdminServlet?action=list");
			
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
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("AdminServlet?action=list");
			
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
