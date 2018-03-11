package cn.EMS.Servlet.Admin;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.EMS.BLL.DepartmentBLL;
import cn.EMS.BLL.MajorBLL;
import cn.EMS.BLL.StudentBLL;
import cn.EMS.Model.DepartmentModel;
import cn.EMS.Model.MajorModel;
import cn.EMS.Model.StudentModel;

public class CollegeManagementServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public CollegeManagementServlet() {
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

			ArrayList<DepartmentModel> departmentList = DepartmentBLL
					.GetArrayList();
			ArrayList<MajorModel> majorList = MajorBLL.GetArrayList();

			request.setAttribute("departmentList", departmentList);
			request.setAttribute("majorList", majorList);

			// �õ�RequestDispatcher
			RequestDispatcher rd = request
					.getRequestDispatcher("Admin/CollegeManagement.jsp");

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

				// ���Ǳ������
				// �������ѧ����ҳ��
				RequestDispatcher rd = request
						.getRequestDispatcher("Admin/AddCollege.jsp");

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

				// ��ǰ̨��ȡ��ֵ����һ��AdminModel
				DepartmentModel department = new DepartmentModel();

				department.setName(name);

				// ��AdminModelд�뵽���ݿ���
				Boolean isSuccess = DepartmentBLL.Insert(department);

				// ������ӽ����ǰ̨
				request.setAttribute("infoType", isSuccess.toString());

				// ��ת�����ҳ�棬���û�������ӹ���Ա������ʾ�ɹ�����ʧ�ܵ���Ϣ
				RequestDispatcher rd = request
						.getRequestDispatcher("Admin/AddCollege.jsp");

				try {

					rd.forward(request, response);

				} catch (ServletException e) {

					e.printStackTrace();

				} catch (IOException e) {

					e.printStackTrace();

				}
			}
		} else if (action.toLowerCase().equals("addmajor")) {

			String save = request.getParameter("save");

			if (save == null || save == "") {

				ArrayList<DepartmentModel> departmentList = DepartmentBLL
						.GetArrayList();

				request.setAttribute("departmentList", departmentList);

				// ���Ǳ������
				// �������ѧ����ҳ��
				RequestDispatcher rd = request
						.getRequestDispatcher("Admin/AddMajor.jsp");

				try {

					rd.forward(request, response);

				} catch (ServletException e) {

					e.printStackTrace();

				} catch (IOException e) {

					e.printStackTrace();

				}

			} else {

				String name = request.getParameter("name");
				int departmentId = Integer.valueOf(request
						.getParameter("departmentId"));

				MajorModel major = new MajorModel();

				major.setName(name);
				major.setDepartmentId(departmentId);

				Boolean isSuccess = MajorBLL.Insert(major);

				// ������ӽ����ǰ̨
				request.setAttribute("infoType", isSuccess.toString());

				// ��ת�����ҳ�棬���û�������ӹ���Ա������ʾ�ɹ�����ʧ�ܵ���Ϣ
				RequestDispatcher rd = request
						.getRequestDispatcher("CollegeManagementServlet?action=list");

				try {

					rd.forward(request, response);

				} catch (ServletException e) {

					e.printStackTrace();

				} catch (IOException e) {

					e.printStackTrace();

				}
			}
		} else if (action.toLowerCase().equals("editdepartment")) {

			String save = request.getParameter("save");

			if (save == null || save == "") {

				// ���Ǳ������
				// �����༭ҳ��

				String departmentId = request.getParameter("departmentId");

				if (departmentId == null || departmentId == "") {

					RequestDispatcher rd = request
							.getRequestDispatcher("CollegeManagementServlet?action=list");

					try {
						rd.forward(request, response);
					} catch (ServletException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}

				} else {
					int DepartmentId = Integer.parseInt(departmentId);

					DepartmentModel department = DepartmentBLL
							.GetModel(DepartmentId);

					if (department == null) {

						RequestDispatcher rd = request
								.getRequestDispatcher("CollegeManagementServlet?action=list");

						try {
							rd.forward(request, response);
						} catch (ServletException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}

					} else {

						request.setAttribute("departmentModel", department);

						RequestDispatcher rd = request
								.getRequestDispatcher("Admin/EditCollege.jsp");

						try {
							rd.forward(request, response);
						} catch (ServletException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}

					}
				}
			} else {

				// �������
				// ��ȡ���±�����
				int DepartmentId = Integer.parseInt(request
						.getParameter("departmentId"));
				String Name = request.getParameter("name");

				DepartmentModel department = new DepartmentModel();
				department.setDepartmentId(DepartmentId);
				department.setName(Name);

				Boolean isSuccess = DepartmentBLL.Update(department);

				request.setAttribute("infoType", isSuccess.toString());

				RequestDispatcher rd = request
						.getRequestDispatcher("CollegeManagementServlet?action=list");

				try {
					rd.forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		} else if (action.toLowerCase().equals("deletedepartment")) {

			// ɾ��ѧԺ

			int departmentId = Integer.parseInt(request
					.getParameter("departmentId"));

			Boolean isSuccess = DepartmentBLL.Delete(departmentId);

			request.setAttribute("infoType", isSuccess.toString());

			RequestDispatcher rd = request
					.getRequestDispatcher("CollegeManagementServlet?action=list");

			try {

				rd.forward(request, response);

			} catch (ServletException e) {

				e.printStackTrace();

			} catch (IOException e) {

				e.printStackTrace();

			}

		} else if (action.toLowerCase().equals("deletemajor")) {

			// ɾ��ѧԺ

			int majorId = Integer.parseInt(request.getParameter("majorId"));

			Boolean isSuccess = MajorBLL.Delete(majorId);

			request.setAttribute("infoType", isSuccess.toString());

			RequestDispatcher rd = request
					.getRequestDispatcher("CollegeManagementServlet?action=list");

			try {

				rd.forward(request, response);

			} catch (ServletException e) {

				e.printStackTrace();

			} catch (IOException e) {

				e.printStackTrace();

			}
		} else if (action.toLowerCase().equals("editmajor")) {

			String save = request.getParameter("save");

			if (save == null || save == "") {

				// ���Ǳ������
				// �����༭ҳ��
				ArrayList<DepartmentModel> departmentList = DepartmentBLL
						.GetArrayList();

				request.setAttribute("departmentList", departmentList);

				String majorId = request.getParameter("majorId");

				if (majorId == null || majorId == "") {
					
					RequestDispatcher rd = request
							.getRequestDispatcher("CollegeManagementServlet?action=list");

					try {
						rd.forward(request, response);
					} catch (ServletException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}

				} else {
					// ���ǿ�ȡmodel
					
					
					int MajorId = Integer.valueOf(majorId);

					MajorModel major = MajorBLL.GetModel(MajorId);

					if (major == null) {

						RequestDispatcher rd = request
								.getRequestDispatcher("CollegeManagementServlet?action=list");

						try {
							rd.forward(request, response);
						} catch (ServletException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}

					} else {

						request.setAttribute("majorModel", major);

						RequestDispatcher rd = request
								.getRequestDispatcher("Admin/EditMajor.jsp");

						try {
							rd.forward(request, response);
						} catch (ServletException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}

					}
				}
			} else {

				// �������
				// ��ȡ���±�����
				String majorId = request.getParameter("majorId");
				String name = request.getParameter("name");
				String departmentId = request.getParameter("departmentId");
				
				MajorModel major = new MajorModel();
				major.setMajorId(Integer.parseInt(majorId));
				major.setName(name);
				major.setDepartmentId(Integer.parseInt(departmentId));
			
				Boolean isSuccess = MajorBLL.Update(major);

				request.setAttribute("infoType", isSuccess.toString());

				RequestDispatcher rd = request
						.getRequestDispatcher("CollegeManagementServlet?action=list");

				try {
					rd.forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		} else {

			// û�в�����������˵�������ˣ��ǾͰ���list������

			RequestDispatcher dispatcher = request
					.getRequestDispatcher("CollegeManegementServlet?action=list");

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
