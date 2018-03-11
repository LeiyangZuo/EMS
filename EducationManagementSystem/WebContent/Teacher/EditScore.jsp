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
<script src="Style/js/jquery-2.0.3.min.js"></script>
<script src="Style/js/ajax.js"></script>
<script src="Style/js/json2.js"></script>
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
<title>Edit Score</title>
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
				<li><a href="TeacherServlet?action=courselist"> <i
						class="icon-list-alt"></i> 课表查询 </a>
				</li>
				<li class="has-sub"><a href="javascript:;" class=""> <i
						class="icon-comments"></i> 消息管理 <span class="arrow"></span> </a>
					<ul class="sub">
					<li><a href="TeacherServlet?action=addnews">添加消息</a></li>
                    <li><a href="TeacherServlet?action=newslist">查看消息</a></li>

					</ul></li>
				<li class="active"><a href="TeacherServlet?action=releasescore">
						<i class="icon-font"></i> 成绩发布 </a>
				</li>
				<li><a href="TeacherServlet?action=stopcourselist"> <i
						class="icon-random"></i> 停课申请</a>
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
						<h3 class="page-title">学生成绩编辑</h3>
					</div>
				</div>

				<div class="row-fluid">
					<div class="span12">
						<%
							String infoType = (String) request.getAttribute("infoType");
							if (infoType != null) {
								if (infoType.equals("true")) {
									out.println("<div class='alert alert-success'>操作成功</div>");
								} else {
									out.println("<div class='alert alert-danger'>操作失败</div>");
								}

							}
						%>
					</div>
				</div>

				<div class="row-fluid">
					<div class="span12">

						<%
							//把后台传递过来的数据提取出来，并且显示												
											ArrayList<CompulsoryCourseModel> CompulsoryCourseList = (ArrayList<CompulsoryCourseModel>) request
													.getAttribute("compulsoryCourseList");
											ArrayList<PersonalCourseModel> PersonalCourseList = (ArrayList<PersonalCourseModel>) request
													.getAttribute("personalCourseList");

											if (CompulsoryCourseList.isEmpty() & PersonalCourseList.isEmpty()) {
						%>
						<div class="alert">没有学生选择该课程</div>
						<%
							} else {
						%>

						<table class="table-bordered table table-advance">
							<thead>
								<tr>
									<th>学生ID</th>
									<th>学生姓名</th>
									<th>学生成绩</th>
								</tr>
							</thead>
							<tbody>

								<%
									//得到一个用于循环显示的迭代器
								Iterator<CompulsoryCourseModel> CompulsoryCourseIterator = CompulsoryCourseList
										.iterator();

								while (CompulsoryCourseIterator.hasNext()) {
									//从迭代器中取到Model
									CompulsoryCourseModel CompulsoryCourse = CompulsoryCourseIterator
											.next();
								%>

								<tr>

									<td><%=CompulsoryCourse.getStudentId()%></td>

									<%
										StudentModel student = StudentBLL.GetModel(CompulsoryCourse
																					.getStudentId());
									%>
									<td><%=student.getName()%></td>
									<td><input class="m-wrap small" type="text" name="score"
										id="compulsoryScore<%=CompulsoryCourse.getID()%>"
										placeholder="Score" value='<%=CompulsoryCourse.getScore()%>'
										Onchange="EditScore(<%=CompulsoryCourse.getID()%>,'compulsory')" />
									</td>
								</tr>
								<%
									}
								%>

								<%
							//得到一个用于循环显示的迭代器
								Iterator<PersonalCourseModel> PersonalCourseIterator = PersonalCourseList
										.iterator();

								while (PersonalCourseIterator.hasNext()) {
									//从迭代器中取到Model
									PersonalCourseModel PersonalCourse = PersonalCourseIterator
											.next();
								%>

								<tr>

									<td><%=PersonalCourse.getStudentId()%></td>

									<%
										StudentModel student = StudentBLL.GetModel(PersonalCourse
																					.getStudentId());
									%>
									<td><%=student.getName()%></td>
									<td><input class="m-wrap small" type="text" name="score"
										id="personalScore<%=PersonalCourse.getID()%>"
										placeholder="Score" value='<%=PersonalCourse.getScore()%>'
										Onchange="EditScore(<%=PersonalCourse.getID()%>,'personal')" />
									</td>

								</tr>
								<%
									}
								%>
							</tbody>
						</table>

						<%
							}
						%>

					</div>

				</div>
				<div class="row-fluid makecenter">
				<div class="span12">
					<a class="btn red" href="TeacherServlet?action=releasescore"><i
						class="icon-share-alt"></i>返回</a>
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


	<script type="text/javascript">

	function EditScore(courseId, type){
	
		var score;
	
		if(type == "compulsory"){
		
			score = $("#compulsoryScore" + courseId).attr("value");
			
		
		}else if(type == "personal"){
		
			score = $("#personalScore" + courseId).attr("value");
		
		};
		
		var address = "TeacherServlet?action=editscore&save=save&score="+score+"&type="+type+"&courseId="+courseId;
		
		ajax(address, function(resText){
		
			if(resText == "success"){
				
				alert("更新成功");
			
			}else{
			
				alert("更新失败");
			
			}
		
		});
	
	};

</script>
</body>
</html>