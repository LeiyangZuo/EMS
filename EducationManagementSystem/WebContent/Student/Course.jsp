<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="cn.EMS.Model.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="cn.EMS.BLL.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
.makecorrect {
	margin: left;
}

.make {
	width: 400px;
}
</style>
<script src="Style/js/jquery-2.0.3.min.js"></script>
<link href="assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
<link href="assets/css/metro.css" rel="stylesheet" />
<link href="assets/bootstrap/css/bootstrap-responsive.min.css"
	rel="stylesheet" />
<link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
<link href="assets/css/style.css" rel="stylesheet" />
<link href="assets/css/style_responsive.css" rel="stylesheet" />
<link href="assets/css/style_default.css" rel="stylesheet"
	id="style_color" />
<link rel="stylesheet" type="text/css"
	href="assets/gritter/css/jquery.gritter.css" />
<link rel="stylesheet" type="text/css"
	href="assets/uniform/css/uniform.default.css" />
<link rel="stylesheet" type="text/css"
	href="assets/bootstrap-daterangepicker/daterangepicker.css" />
<title>Student Course</title>
</head>
<body class="fixed-top">
	<!-- BEGIN HEADER -->
	<div class="header navbar navbar-inverse navbar-fixed-top">
		<!-- BEGIN TOP NAVIGATION BAR -->
		<div class="navbar-inner">
			<div class="container-fluid">
				<!-- BEGIN LOGO -->
				<h2>
					教务管理系统 <small>By Zuoleiyang</small>
				</h2>
				<!-- END LOGO -->
				<!-- BEGIN RESPONSIVE MENU TOGGLER -->
				<a href="javascript:;" class="btn-navbar collapsed"
					data-toggle="collapse" data-target=".nav-collapse"> <img
					src="assets/img/menu-toggler.png" alt="" /> </a>
				<!-- END RESPONSIVE MENU TOGGLER -->

			</div>
		</div>
		<!-- END TOP NAVIGATION BAR -->
	</div>
	<!-- END HEADER -->

	<!-- BEGIN CONTAINER -->
	<div class="page-container row-fluid">
		<!-- BEGIN SIDEBAR！！！！ -->
		<div class="page-sidebar nav-collapse collapse">
			<!-- BEGIN SIDEBAR MENU -->
			<ul>
				<li><a href="StudentServlet?action=studentinfo"> <i
						class="icon-user"></i> 个人信息查询 </a>
				</li>
				<li><a href="StudentServlet?action=newslist"> <i
						class="icon-comments"></i> 消息查看 </a>
				</li>
				<li><a href="StudentServlet?action=scorelist"> <i
						class="icon-font"></i> 成绩查询 </a>
				</li>
				<li class="active"><a href="StudentServlet?action=courselist">
						<i class="icon-list-alt"></i> 课表查询 </a>
				</li>
				<li><a href="StudentServlet?action=selectedcourselist"> <i
						class="icon-table"></i> 网上选课 </a>
				</li>

				<li><a href="LoginServlet"><i class="icon-home"></i> 退出登录</a>
				</li>
			</ul>
			<!-- END SIDEBAR MENU -->
		</div>
		<!-- END SIDEBAR -->
		<!-- BEGIN PAGE -->
		<div class="page-content">

			<!-- BEGIN PAGE CONTAINER-->
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<h3 class="page-title make">学生本学期个人课表</h3>
						<div class="control-group make">
							<label class="control-label" for="term">学期</label>
							<div class="controls">
								<select class="small m-wrap" tabindex="1" name="term" id="term"
									onchange="SelectTerm(this)">
									<option>请选择</option>
									<option value="1">大一上</option>
									<option value="2">大一下</option>
									<option value="3">大二上</option>
									<option value="4">大二下</option>
									<option value="5">大三上</option>
									<option value="6">大三下</option>
									<option value="7">大四上</option>
									<option value="8">大四下</option>
								</select>
							</div>
						</div>
					</div>
				</div>



				<div class="row-fluid">
					<div class="span12">
						<%
							String infoType = (String) request.getAttribute("infoType");
							if (infoType != null) {
								if (infoType.equals("true")) {
									out.println("<div class='alert alert-success'>操作成功</div>");
								} if(infoType.equals("flase")) {
									out.println("<div class='alert alert-danger'>操作失败</div>");
								}if (infoType.equals("cannot")){
								out.println("<div class='alert alert-success'>以添加过该课程</div>");
								}

							}
						%>
					</div>
				</div>

				<%
					String Term = (String)request.getAttribute("Term");
						if(Term == null || Term == ""){
						out.println("<div class='alert alert-info'>请选择学期</div>");
						}else{
				%>

				<div class="row-fluid">
					<div class="span12">

						<%
							//把后台传递过来的数据提取出来，并且显示												
							ArrayList<PersonalCourseModel> personalCourseList = (ArrayList<PersonalCourseModel>) request
									.getAttribute("personalCourseList");
							ArrayList<CompulsoryCourseModel> compulsoryCourseList = (ArrayList<CompulsoryCourseModel>) request
									.getAttribute("compulsoryCourseList");
							if (personalCourseList.isEmpty() & compulsoryCourseList.isEmpty()) {
						%>
						<div class="alert">没有课程</div>
						<%
							} else {
						%>

						<table
							class="table-bordered table table-advance table-striped table-hover">
							<thead>
								<tr>
									<th>课程代码</th>
									<th>课程名称</th>
									<th>教师姓名</th>
									<th>课程周期</th>
									<th>上课时间</th>
									<th>删除</th>
								</tr>
							</thead>
							<tbody>

								<%
									//得到一个用于循环显示的迭代器
									Iterator<PersonalCourseModel> personalCourseIterator = personalCourseList
											.iterator();

									while (personalCourseIterator.hasNext()) {
										//从迭代器中取到Model
										PersonalCourseModel personalCourse = personalCourseIterator
												.next();
								%>

								<tr>
									<td><%=personalCourse.getCourseId()%></td>
									<%
										CourseModel course = CourseBLL.GetModel(personalCourse
											.getCourseId());
							TeacherModel teacher = TeacherBLL.GetModel(course.getTeacherId());
									%>
									<td><%=course.getName()%></td>
									<td><%=teacher.getName()%></td>
									<td><%=course.getPeriod()%></td>
									<td><%=course.getClassTime()%></td>
									<td><a class="btn mini red"
										href="StudentServlet?action=deletecourse&selectId=<%=personalCourse.getID()%>">
											<i class="icon-trash"></i> 删除</a>
									</td>
								</tr>
								<%
								   if(course.getStopWeek() != 0){%>
								   
								   <tr>
								      <td></td>
								      <td>调停课信息</td>
								      <td>停课周数</td>
								      <td><%=course.getStopWeek()%></td>
								      <td>补课周数</td>
								      <td><%=course.getAddWeek() %></td>
								   </tr>
								   
								   <% 
								   }
									}
								
									//得到一个用于循环显示的迭代器
									Iterator<CompulsoryCourseModel> compulsoryCourseIterator = compulsoryCourseList
											.iterator();

									while (compulsoryCourseIterator.hasNext()) {
										//从迭代器中取到Model
										CompulsoryCourseModel compulsoryCourse = compulsoryCourseIterator
												.next();
								%>

								<tr>
									<td><%=compulsoryCourse.getCourseId()%></td>
									<%
										CourseModel course = CourseBLL.GetModel(compulsoryCourse
													.getCourseId());
									TeacherModel teacher = TeacherBLL.GetModel(course.getTeacherId());
									%>
									<td><%=course.getName()%></td>
									<td><%=teacher.getName()%></td>
									<td><%=course.getPeriod()%></td>
									<td><%=course.getClassTime()%></td>
									<td><span class="label label-danger label-mini"> <i
											class="icon-check"></i> 必选课</span>
									</td>

								</tr>
								<%
								   if(course.getStopWeek() != 0){%>
								   
								   <tr>
								      <td></td>
								      <td>调停课信息</td> 
								      <td>停课周数</td>
								      <td><%=course.getStopWeek()%></td>
								      <td>补课周数</td>
								      <td><%=course.getAddWeek() %></td>
								   </tr>
								   
								   <% 
								     }
									}
								%>
							</tbody>
						</table>
						<%
							}
						%>

					</div>

				</div>
				<%
					}
				%>
				<!-- END PAGE CONTAINER-->
			</div>
			<!-- END PAGE -->
		</div>
	</div>
	<!-- END CONTAINER -->

	<script src="assets/js/jquery-1.8.3.min.js"></script>
	<script src="assets/breakpoints/breakpoints.js"></script>
	<script src="assets/jquery-slimscroll/jquery-ui-1.9.2.custom.min.js"></script>
	<script src="assets/bootstrap/js/bootstrap.min.js"></script>
	<script src="assets/js/jquery.blockui.js"></script>
	<script src="assets/fullcalendar/fullcalendar/fullcalendar.min.js"></script>
	<script type="text/javascript"
		src="assets/uniform/jquery.uniform.min.js"></script>
	<script type="text/javascript"
		src="assets/chosen-bootstrap/chosen/chosen.jquery.min.js"></script>
	<!-- ie8 fixes -->
	<!--[if lt IE 9]>
<script src="assets/js/excanvas.js"></script>
<script src="assets/js/respond.js"></script>
<![endif]-->
	<script src="assets/js/app.js"></script>
	<script>
		jQuery(document).ready(function() {
			// initiate layout and plugins
			App.setPage('calendar');
			App.init();
		});
	</script>
	<script type="text/javascript">
		var _gaq = _gaq || [];
		_gaq.push([ '_setAccount', 'UA-37564768-1' ]);
		_gaq.push([ '_setDomainName', 'keenthemes.com' ]);
		_gaq.push([ '_setAllowLinker', true ]);
		_gaq.push([ '_trackPageview' ]);
		(function() {
			var ga = document.createElement('script');
			ga.type = 'text/javascript';
			ga.async = true;
			ga.src = ('https:' == document.location.protocol ? 'https://'
					: 'http://')
					+ 'stats.g.doubleclick.net/dc.js';
			var s = document.getElementsByTagName('script')[0];
			s.parentNode.insertBefore(ga, s);
		})();
	</script>

	<script>
		function SelectTerm(oSelect) {

			var term = oSelect.value;
			if (term != null && term != 0) {

				var address = "StudentServlet?action=courselist&term=" + term;
				window.location.href = address;

			}

		}
	</script>

</body>
</html>