<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="cn.EMS.Model.*"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
<title>Student Detail</title>
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
				<li class="active"><a href="StudentServlet?action=studentinfo"> <i class="icon-user"></i>
						个人信息查询 </a>
				</li>
				<li><a href="StudentServlet?action=newslist"> <i class="icon-comments"></i> 消息查看
				</a>
				</li>
				<li><a href="StudentServlet?action=scorelist"> <i class="icon-font"></i> 成绩查询 </a>
				</li>
				<li><a href="StudentServlet?action=courselist"> <i
						class="icon-list-alt"></i> 课表查询 </a>
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
						<h3 class="page-title">个人信息</h3>
					</div>
				</div>


				<div class="row-fluid">
					<div class="span12">
						<%																
						StudentModel student = (StudentModel)request.getAttribute("studentModel");
						
						//得到了数据，接下来要提取并显示
						
						if(student == null){
							//为空，这显示为空信息
							%>

						<div class="alert">学生信息不存在</div>
						<%
								}

                         else{
		                         //有管理员信息，则显示这些信息
							%>
						<table class="table-bordered table table-advance">
							<thead>
								<tr>
									<th>学生ID</th>
									<th>姓名</th>
									<th>专业</th>
									<th>性别</th>
									<th>入学时间</th>
									<th>年级</th>
								</tr>
							</thead>
							<tbody>
								<%
										//得到一个用于循环显示的迭代器

                                //从迭代器中取到Model

                                //把AdminModel中得值显示出来
									%>

								<tr>
									<td><%=student.getStudentId()%></td>
									<td><%=student.getName()%></td>
									<td><%=student.getDepartmentId()%></td>
									<%
											if(student.getGender()){
										%>
									<td>女</td>
									<%
											}else{
										%>
									<td>男</td>
									<%
											}
										%>
									<td><%=student.getStartTime()%></td>
									<td>
										<%
											//获得系统当前的年和月
											Calendar cal = Calendar.getInstance();
											//将cal设置为系统当前时间
											cal.setTimeInMillis(System.currentTimeMillis());
											int currentYear = cal.get(Calendar.YEAR);
											int currentMonth = cal.get(Calendar.MONTH);
											
											//获得学生入学时间的年和月
											//将cal设置为学生的入学时间
											cal.setTimeInMillis(student.getStartTime().getTime());
											int studentStartYear = cal.get(Calendar.YEAR);
											int studentStartMonth = cal.get(Calendar.MONTH) + 1;
											
											int monthAccount = (currentYear - studentStartYear) * 12 + (currentMonth - studentStartMonth + 1);
											
											int term = monthAccount / 6 + 1;
											
											switch(term){
												case 1:
													out.print("大一上");
													break;
												case 2:
													out.print("大一下");
													break;
												case 3:
													out.print("大二上");
													break;
												case 4:
													out.print("大二下");
													break;
												case 5:
													out.print("大三上");
													break;
												case 6:
													out.print("大三下");
													break;
												case 7:
													out.print("大四上");
													break;
												case 8:
													out.print("大四下");
													break;
												default:
													out.print("毕业");
													break;
					
								
											
											
											}
											
											
											
										
										 %>
									</td>
								</tr>
								<%
									}
								%>
							</tbody>
						</table>
					</div>

				</div>
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
<script type="text/javascript" src="assets/uniform/jquery.uniform.min.js"></script>
<script type="text/javascript" src="assets/chosen-bootstrap/chosen/chosen.jquery.min.js"></script>
<!-- ie8 fixes -->
<!--[if lt IE 9]>
<script src="assets/js/excanvas.js"></script>
<script src="assets/js/respond.js"></script>
<![endif]-->
<script src="assets/js/app.js"></script>
<script>
    jQuery(document).ready(function () {
        // initiate layout and plugins
        App.setPage('calendar');
        App.init();
    });
</script>
<script type="text/javascript">
    var _gaq = _gaq || [];
    _gaq.push(['_setAccount', 'UA-37564768-1']);
    _gaq.push(['_setDomainName', 'keenthemes.com']);
    _gaq.push(['_setAllowLinker', true]);
    _gaq.push(['_trackPageview']);
    (function () {
        var ga = document.createElement('script');
        ga.type = 'text/javascript';
        ga.async = true;
        ga.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + 'stats.g.doubleclick.net/dc.js';
        var s = document.getElementsByTagName('script')[0];
        s.parentNode.insertBefore(ga, s);
    })();
</script>
</body>
</html>