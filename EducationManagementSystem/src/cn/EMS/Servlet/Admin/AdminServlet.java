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
		
		//判断action参数是什么
		if(action.toLowerCase().equals("list")){
			
			//如果action的值为list，则列出所有的管理员
			
			//获取所有管理员的数据
			ArrayList<AdminModel> adminList = AdminBLL.GetArrayList();
			
			//在request，即http头中封装刚刚取得的数据 
			request.setAttribute("adminList", adminList);
			
			//得到RequestDispatcher
			RequestDispatcher rd = request.getRequestDispatcher("Admin/AdminManagement.jsp");
			
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
			//添加管理员
		
			
			String save = request.getParameter("save");
			if(save == null || save == ""){
				
				//不是保存过程，给出保存的页面
				
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
				
				//保存的过程
				
				//获取表单中的数据
				String adminId = request.getParameter("adminId");
				String password = request.getParameter("password");
				String name = request.getParameter("name");
				Boolean isSuper = false;
				if(request.getParameter("isSuper") != null){
					
					isSuper = true;
					
				}
				
				//将前台获取的值构建一个AdminModel
				AdminModel admin = new AdminModel();
				admin.setAdminId(adminId);
				admin.setName(name);
				admin.setPassword(password);
				admin.setIsSuper(isSuper);
				
				//把AdminModel写入到数据库中
				Boolean isSuccess = AdminBLL.Insert(admin);
				
				//传递添加结果给前台
				request.setAttribute("infoType", isSuccess.toString());
				
				//跳转到添加页面，供用户继续添加管理员并且显示成功或者失败的信息
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
		
			
			//编辑管理员
			
			//判断接下来是得到编辑页面的过程还是编辑完毕需要保存的过程
			String save = request.getParameter("save");
			
			if(save == null){
				//如果save 为 null，说明没有取到save这个变量，则进一步说明现在不是保存编辑内容的过程
				//则给出需要编辑的页面
				
				String adminId = request.getParameter("adminId");
				if(adminId == null || adminId == ""){
					
					//说明没有得到adminId
					//跳转到Admin的列表页面，让用户重新选择
					RequestDispatcher rd = request.getRequestDispatcher("AdminServlet?action=list");
					//TODO 需要添加错误信息
					
					try {
						rd.forward(request, response);
					} catch (ServletException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
				else{
					
					//得到了adminID
					//取出这个管理员现有的信息
					AdminModel admin = AdminBLL.GetModel(adminId);
					
					if(admin == null){
						
						//没有取到AdminModel
						//跳转到Admin的列表页面，让用户重新选择
						RequestDispatcher rd = request.getRequestDispatcher("AdminServlet?action=list");
						//TODO 需要添加错误信息
						
						try {
							rd.forward(request, response);
						} catch (ServletException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					else{
						
						//取到了AdminModel的信息，则开始构建编辑页面
						//传递值给EditAdmin.jsp
						request.setAttribute("adminModel", admin);

						//跳转到编辑页面
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
				
				//是保存过程，说明用户修改的信息已经修改完毕了，需要更新到数据库中
				
				//获取表单中的数据
				String adminId = request.getParameter("adminId");
				String password = request.getParameter("password");
				String name = request.getParameter("name");
				Boolean isSuper = false;
				if(request.getParameter("isSuper") != null){
					
					isSuper = true;
					
				}
				
				//将前台获取的数据构建一个AdminModel
				AdminModel admin = new AdminModel();
				
				admin.setAdminId(adminId);
				admin.setName(name);
				admin.setPassword(password);
				admin.setIsSuper(isSuper);
				
				Boolean isSuccess = AdminBLL.Update(admin);
				
				
				//传递添加结果给前台
				request.setAttribute("infoType", isSuccess.toString());
				
				//跳转到添加页面，供用户继续添加管理员并且显示成功或者失败的信息
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
			
			//删除管理员
			
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
			
			//没有参数传过来，说明出错了，那就按照list来处理
			
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
