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

			// 得到RequestDispatcher
			RequestDispatcher rd = request
					.getRequestDispatcher("Admin/CollegeManagement.jsp");

			// 进行传递数据的过程
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

				// 不是保存过程
				// 给出添加学生的页面
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

				// 是保存过程
				// 获取表单中的数据

				String name = request.getParameter("name");

				// 将前台获取的值构建一个AdminModel
				DepartmentModel department = new DepartmentModel();

				department.setName(name);

				// 把AdminModel写入到数据库中
				Boolean isSuccess = DepartmentBLL.Insert(department);

				// 传递添加结果给前台
				request.setAttribute("infoType", isSuccess.toString());

				// 跳转到添加页面，供用户继续添加管理员并且显示成功或者失败的信息
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

				// 不是保存过程
				// 给出添加学生的页面
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

				// 传递添加结果给前台
				request.setAttribute("infoType", isSuccess.toString());

				// 跳转到添加页面，供用户继续添加管理员并且显示成功或者失败的信息
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

				// 不是保存过程
				// 给出编辑页面

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

				// 保存过程
				// 获取更新表单数据
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

			// 删除学院

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

			// 删除学院

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

				// 不是保存过程
				// 给出编辑页面
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
					// 不是空取model
					
					
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

				// 保存过程
				// 获取更新表单数据
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

			// 没有参数传过来，说明出错了，那就按照list来处理

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
