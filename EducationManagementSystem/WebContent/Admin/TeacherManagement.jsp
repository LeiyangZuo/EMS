<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="cn.EMS.Model.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="cn.EMS.BLL.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
<title>Manage teachers</title>
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
				<li class="has-sub">
                <a href="javascript:;" class="">
                    <i class="icon-group"></i> 管理员管理
                    <span class="arrow"></span>
                </a>
                
                <ul class="sub">
                    <li><a href="AdminServlet?action=add">添加管理员</a></li>
                    <li><a href="AdminServlet?action=list">管理管理员</a></li>

                </ul>
            </li>
            <li class="has-sub">
                <a href="javascript:;" class="">
                    <i class="icon-user"></i>学生管理
                    <span class="arrow"></span>
                </a>

                <ul class="sub">
                    <li><a href="StudentManagementServlet?action=add">添加学生</a></li>
                    <li><a href="StudentManagementServlet?action=list">管理学生</a></li>
                </ul>

            <li class="active has-sub">
                <a href="javascript:;" class="">
                    <i class="icon-user-md"></i> 教师管理
                    <span class="arrow open"></span>
                     <span class="selected"></span>
                </a>
                <ul class="sub">
                    <li><a href="TeacherManagementServlet?action=add">添加教师</a></li>
                    <li class="active"><a href="TeacherManagementServlet?action=list">管理教师</a></li>

                </ul>
            </li>
            <li class="has-sub">
                <a href="javascript:;" class="">
                    <i class="icon-sitemap"></i> 院系管理
                    <span class="arrow"></span>
                </a>
                <ul class="sub">
                    <li><a href="CollegeManagementServlet?action=add">添加学院</a></li>
                    <li><a href="CollegeManagementServlet?action=addmajor">添加专业</a></li>
                    <li><a href="CollegeManagementServlet?action=list">管理院系</a></li>
                </ul>
            </li>
            <li class="has-sub">
                <a href="javascript:;" class="">
                    <i class="icon-comments"></i> 消息管理
                    <span class="arrow"></span>
                </a>
                <ul class="sub">
                    <li><a href="NewsServlet?action=add">添加消息</a></li>
                    <li><a href="NewsServlet?action=list">管理消息</a></li>

                </ul>
            </li>
            <li class="has-sub"><a href="javascript:;" class=""> <i
				class="icon-list-alt"></i> 课程管理 <span class="arrow"></span> </a>
			<ul class="sub">
				<li><a href="CourseManagementServlet?action=add">添加课程</a>
				</li>
				<li><a href="CourseManagementServlet?action=stopcourselist">管理教师停课申请</a>
				</li>
				<li><a href="CourseManagementServlet?action=list">管理学生可选课程</a>
				</li>

			</ul></li>

				<li><a href="LoginAdminServlet"><i class="icon-home"></i> 退出登录</a>
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
						<h3 class="page-title">教师管理</h3>
					</div>
				</div>
				
				<div class="row-fluid">
					<div class="span12">
					  <label>筛选</label>
                      <input type="text" class="filter" id="searchInput"/>
					</div>
				</div>
					<!-- main content start -->

					<div class="row-fluid">
						<div class="span12">
							<%
								String infoType = (String)request.getAttribute("infoType");
								if(infoType != null){
								 	if(request.getAttribute("infoType").equals("true")){
				                    	out.println("<div class='alert alert-success'>操作成功</div>");
				                    }
				                    else{
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
																					
								ArrayList<TeacherModel> teacherList = (ArrayList<TeacherModel>)request.getAttribute("teacherList");
																						
								//得到了数据，接下来要提取并显示
								
								if(teacherList.isEmpty()){
									//为空，这显示为空信息
							%>
							<div class="alert">没有教师信息</div>
							<%
								}
																						
								else{
									//有管理员信息，则显示这些信息
							%>
							<table class="table-bordered table table-advance table-striped table-hover">
								<thead>
									<tr>
										<th>教师ID</th>
										<th>姓名</th>
										<th>学院</th>
										<th>编辑</th>
										<th>删除</th>
									</tr>
								</thead>
								<tbody>
									<%
										//得到一个用于循环显示的迭代器
										Iterator<TeacherModel> teacherIterator = teacherList.iterator();
									 
									 	while(teacherIterator.hasNext()){
									 		//从迭代器中取到Model
									 		TeacherModel teacher = teacherIterator.next();
									 		
									 		//把AdminModel中得值显示出来
									%>

									<tr>
										<td><%=teacher.getTeacherId()%></td>
										<td><%=teacher.getName()%></td>
										<%
										DepartmentModel Department  = DepartmentBLL.GetModel(teacher.getDepartmentId());
										 %>
										<td><%=Department.getName()%></td>
										<td><a class="btn mini purple"
											href="TeacherManagementServlet?action=edit&teacherId=<%=teacher.getTeacherId()%>">
											<i class="icon-edit"></i> 编辑</a>
										</td>
										<td><a class="btn mini black"
											href="TeacherManagementServlet?action=delete&teacherId=<%=teacher.getTeacherId()%>">
											<i class="icon-trash"></i> 删除</a>
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
					<!-- main content stop -->
				</div>

			</div>
			<!-- END PAGE CONTAINER-->
		</div>
		<!-- END PAGE -->
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
	
<script type="text/javascript">
    $(function(){
        $("#searchInput").keyup(function(){
            var $filterInfo = $("#searchInput").val();
            if($filterInfo == null || $filterInfo == ""){
            	$("table tbody tr").show();
            }
            else{
	            $("table tbody tr").hide()
	                    .filter(":contains('"+$filterInfo+"')").show();
            
            }
        }).keyup();//DOM加载完毕时，绑定事件完成之后立即触发

    })
</script>

</body>
</html>