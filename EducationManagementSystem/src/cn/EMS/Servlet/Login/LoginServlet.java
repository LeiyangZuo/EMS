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
			
			// ��ȡ����Ϣ
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
			// �Ա���Ϣ������֤
			if (userId == null || userId == "") {
				// û�������û���
				info = "�������û���";
			} else if (password == null || password == "") {
				// û����������
				info = "����������";
			} else {
				// ��ʵ����֤���ж��û��Ƿ�����Լ������Ƿ���ȷ
				if(!isTeacher){
					
					//������ʦ����ѧ��
					if (StudentBLL.Exist(userId)) {
						// �û�����
						// �ж�����
						// ���ڣ�ȡ�����ѧ��
						StudentModel student = StudentDAO.GetModel(userId);
						
						if (student.getPassword().equals(password)) {
							
							// ������ͬ
							
							isPermit = true;
							info = "Welcome";
							
							// �˴�Ҫ����ҳ����ת������ѧ���Ĺ���ҳ����¼�ɹ�
						} else {
							info = "�������";
						}
						
					} else {
						
						// �û�������
						info = "�û�������";
					}
					
					System.out.println(info);
					
					if (isPermit) {
						// ��¼�ɹ�
						System.out.println("��½�ɹ�");
						//����¼��Ϣд��Session
						
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
						// ��¼ʧ��
						// ���ص�¼ҳ
						
						// ����RequestDispatcher��ȷ��Ҫ������Ҫ��ת��ҳ��Ϊlogin.jsp
						dispatcher = request.getRequestDispatcher("Login/login.jsp");
						
						// ȷ����Ҫ����Ĳ���
						request.setAttribute("info", info);
						System.out.println("��¼ʧ��");
						// ��ת
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
					//����ʦ
					if (TeacherBLL.Exist(userId)) {
						// �û�����
						// �ж�����
						// ���ڣ�ȡ�����ѧ��
						TeacherModel teacher = TeacherBLL.GetModel(userId);
						
						if (teacher.getPassword().equals(password)) {
							
							// ������ͬ
							
							isPermit = true;
							info = "Welcome";
							
							// �˴�Ҫ����ҳ����ת������ѧ���Ĺ���ҳ����¼�ɹ�
						} else {
							info = "�������";
						}
						
					} else {
						
						// �û�������
						info = "�û�������";
					}
					
					System.out.println(info);
					
					if (isPermit) {
						// ��¼�ɹ�
						System.out.println("��½�ɹ�");
						//����¼��Ϣд��Session
						
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
						// ��¼ʧ��
						// ���ص�¼ҳ
						
						// ����RequestDispatcher��ȷ��Ҫ������Ҫ��ת��ҳ��Ϊlogin.jsp
						dispatcher = request.getRequestDispatcher("Login/login.jsp");
						
						// ȷ����Ҫ����Ĳ���
						request.setAttribute("info", info);
						System.out.println("��¼ʧ��");
						// ��ת
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
