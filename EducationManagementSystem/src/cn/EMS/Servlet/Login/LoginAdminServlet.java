package cn.EMS.Servlet.Login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.EMS.BLL.AdminBLL;
import cn.EMS.DAO.AdminDAO;
import cn.EMS.Model.AdminModel;

public class LoginAdminServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LoginAdminServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void service(HttpServletRequest request, HttpServletResponse response) {
		
		String submit = request.getParameter("submit");
		if(submit == null || submit == ""){
			
			RequestDispatcher rd = request.getRequestDispatcher("Login/loginAdmin.jsp");
			
			try {
				rd.forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else{
			
			String userId = request.getParameter("id");
			String password = request.getParameter("password");
			
			Boolean isPermit = false;
			String info = "";
			RequestDispatcher dispatcher = null;
			if (userId == null || userId == "") {
				info = "请输入用户名";
			} else if (password == null || password == "") {
				info = "请输入密码";
			} else {
				
				if (AdminBLL.Exist(userId)) {
					
					AdminModel admin = AdminDAO.GetModel(userId);
					
					if (admin.getPassword().equals(password)) {
						
						isPermit = true;
						info = "Welcome";
						
					} else {
						info = "密码错误";
					}
					
				} else {
					
					info = "用户不存在";
				}
				
				System.out.println(info);
				
				if (isPermit) {
					System.out.println("登陆成功");
					
					//将登录信息写入Session
					HttpSession session = request.getSession();
					
					session.setAttribute("userId", userId);
					session.setAttribute("userType", "admin");
					
					dispatcher = request
							.getRequestDispatcher("AdminServlet?action=add");
					
					try {
						dispatcher.forward(request, response);
					} catch (ServletException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					
					dispatcher = request.getRequestDispatcher("Login/loginAdmin.jsp");
					
					request.setAttribute("info", info);
					System.out.println("登录失败");
					
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

}
