package cn.EMS.Servlet.Login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.EMS.BLL.StudentBLL;
import cn.EMS.BLL.TeacherBLL;
import cn.EMS.DAO.StudentDAO;
import cn.EMS.Model.StudentModel;
import cn.EMS.Model.TeacherModel;

public class LoginServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LoginServlet() {
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
			
			RequestDispatcher rd = request.getRequestDispatcher("Login/login.jsp");
			
			try {
				rd.forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else{
			
			// 获取表单信息
			String userId = request.getParameter("userId");
			String password = request.getParameter("password");
			String isTeacherString = request.getParameter("isTeacher");
			Boolean isTeacher = true;
			if(isTeacherString == null || isTeacherString == ""){
				isTeacher = false;
			}
			
			Boolean isPermit = false;
			String info = "";
			RequestDispatcher dispatcher = null;
			// 对表单信息进行验证
			if (userId == null || userId == "") {
				// 没有输入用户名
				info = "请输入用户名";
			} else if (password == null || password == "") {
				// 没有输入密码
				info = "请输入密码";
			} else {
				// 真实性验证，判断用户是否存在以及密码是否正确
				if(!isTeacher){
					
					//不是老师，是学生
					if (StudentBLL.Exist(userId)) {
						// 用户存在
						// 判断密码
						// 存在，取出这个学生
						StudentModel student = StudentDAO.GetModel(userId);
						
						if (student.getPassword().equals(password)) {
							
							// 密码相同
							
							isPermit = true;
							info = "Welcome";
							
							// 此处要进行页面跳转，调到学生的功能页，登录成功
						} else {
							info = "密码错误";
						}
						
					} else {
						
						// 用户不存在
						info = "用户不存在";
					}
					
					System.out.println(info);
					
					if (isPermit) {
						// 登录成功
						System.out.println("登陆成功");
						//将登录信息写入Session
						
						HttpSession session = request.getSession();
						session.setAttribute("userId", userId);
						session.setAttribute("userType", "student");
						
						
						dispatcher = request.getRequestDispatcher("StudentServlet?action=studentInfo");
						
						try {
							
							dispatcher.forward(request, response);
							
						} catch (ServletException e) {
							
							e.printStackTrace();
							
						} catch (IOException e) {
							
							e.printStackTrace();
							
						}
					} else {
						// 登录失败
						// 返回登录页
						
						// 构造RequestDispatcher，确定要接下来要跳转的页面为login.jsp
						dispatcher = request.getRequestDispatcher("Login/login.jsp");
						
						// 确定需要传输的参数
						request.setAttribute("info", info);
						System.out.println("登录失败");
						// 跳转
						try {
							dispatcher.forward(request, response);
						} catch (ServletException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
						
					}
					
				}
				else{
					//是老师
					if (TeacherBLL.Exist(userId)) {
						// 用户存在
						// 判断密码
						// 存在，取出这个学生
						TeacherModel teacher = TeacherBLL.GetModel(userId);
						
						if (teacher.getPassword().equals(password)) {
							
							// 密码相同
							
							isPermit = true;
							info = "Welcome";
							
							// 此处要进行页面跳转，调到学生的功能页，登录成功
						} else {
							info = "密码错误";
						}
						
					} else {
						
						// 用户不存在
						info = "用户不存在";
					}
					
					System.out.println(info);
					
					if (isPermit) {
						// 登录成功
						System.out.println("登陆成功");
						//将登录信息写入Session
						
						HttpSession session = request.getSession();
						session.setAttribute("userId", userId);
						session.setAttribute("userType", "teacher");
						
						
						dispatcher = request.getRequestDispatcher("TeacherServlet?action=courselist");
						
						try {
							
							dispatcher.forward(request, response);
							
						} catch (ServletException e) {
							
							e.printStackTrace();
							
						} catch (IOException e) {
							
							e.printStackTrace();
							
						}
					} else {
						// 登录失败
						// 返回登录页
						
						// 构造RequestDispatcher，确定要接下来要跳转的页面为login.jsp
						dispatcher = request.getRequestDispatcher("Login/login.jsp");
						
						// 确定需要传输的参数
						request.setAttribute("info", info);
						System.out.println("登录失败");
						// 跳转
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
