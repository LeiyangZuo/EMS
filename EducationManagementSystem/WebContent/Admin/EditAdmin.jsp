<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="cn.EMS.Model.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="Style/js/jquery-2.0.3.min.js"></script>
    <link href="assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
    <link href="assets/css/metro.css" rel="stylesheet" />
    <link href="assets/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" />
    <link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <link href="assets/css/style.css" rel="stylesheet" />
    <link href="assets/css/style_responsive.css" rel="stylesheet" />
    <link href="assets/css/style_default.css" rel="stylesheet" id="style_color" />
    <link rel="stylesheet" type="text/css" href="assets/gritter/css/jquery.gritter.css" />
    <link rel="stylesheet" type="text/css" href="assets/uniform/css/uniform.default.css" />
    <link rel="stylesheet" type="text/css" href="assets/bootstrap-daterangepicker/daterangepicker.css" />
    <title>Edit an administrator</title>
</head>
<body class="fixed-top">
<!-- BEGIN HEADER -->
<div class="header navbar navbar-inverse navbar-fixed-top">
    <!-- BEGIN TOP NAVIGATION BAR -->
    <div class="navbar-inner">
        <div class="container-fluid">
            <!-- BEGIN LOGO -->
            <h2>教务管理系统 <small>By Zuoleiyang</small></h2>
            <!-- END LOGO -->
            <!-- BEGIN RESPONSIVE MENU TOGGLER -->
            <a href="javascript:;" class="btn-navbar collapsed" data-toggle="collapse" data-target=".nav-collapse">
                <img src="assets/img/menu-toggler.png" alt="" />
            </a>
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
            <li class="active has-sub">
                <a href="javascript:;" class="">
                    <i class="icon-group"></i> 管理员管理
                    <span class="arrow open"></span>
                     <span class="selected"></span>
                </a>
                
                <ul class="sub">
                    <li><a href="AdminServlet?action=add">添加管理员</a></li>
                    <li class="active"><a href="AdminServlet?action=list">管理管理员</a></li>

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

            <li class="has-sub">
                <a href="javascript:;" class="">
                    <i class="icon-user-md"></i> 教师管理
                    <span class="arrow"></span>
                </a>
                <ul class="sub">
                    <li><a href="TeacherManagementServlet?action=add">添加教师</a></li>
                    <li><a href="TeacherManagementServlet?action=list">管理教师</a></li>

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

            <li><a href="LoginAdminServlet"><i class="icon-home"></i> 退出登录</a></li>
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
                    <h3 class="page-title">编辑管理员</h3>
                </div>
            </div>
            <div class="row-fluid">
                <!-- error container -->
                <div style="display:none" class="alert alert-error" id="errorContainer">errorContainer</div>

                <div class="span12">
                
                <%
                	if(request.getAttribute("infoType") != null){
                	
	                    if(request.getAttribute("infoType").equals("true")){
	                    	out.println("<div class='alert alert-success'>更新成功</div>");
	                    }
	                    else{
	                    	out.println("<div class='alert alert-alert'>更新失败</div>");
	                    }
                	}

                    %>

                </div>
                    <!-- main content start -->
                    <div class="row-fluid">
                        <div class="span12">
                            <div class="portlet box red tabbable">
                                <div class="portlet-title">
                                    <h4>编辑管理员</h4>
                                </div>
                            </div>

							<%
							
								AdminModel admin = (AdminModel)request.getAttribute("adminModel");
								
							%>

                            <div class="portlet-body form">
                                <form action="AdminServlet?action=edit" method="post" class="form-horizontal">
                                    <div class="control-group">
                                        <label class="control-label" for="adminId">管理员ID</label>
                                        <div class="controls">
                                            <input class="m-wrap medium" type = "text" name ="adminId" id="adminId" placeholder="Admin ID" value='<%=admin.getAdminId() %>' readonly/>
                                        </div>
                                    </div>
                                    
                                    <div class="control-group">
                                        <label class="control-label" for="name">姓名</label>
                                        <div class="controls">
                                            <input class="m-wrap medium" type = "text" name ="name" id="name" placeholder="Admin Name" value='<%=admin.getName() %>' />
                                        </div>
                                    </div>
                                    
                                    <div class="control-group">
                                        <label class="control-label" for="password">密码</label>
                                        <div class="controls">
                                            <input class="m-wrap medium" type = "password" name = "password" id="password" placeholder="Password" />
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label" for="password2">确认密码</label>
                                        <div class="controls">
                                            <input class="m-wrap medium" type = "password" id="password2" placeholder="Please confirm the password" />
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label" for="isSuper">是否是超级管理员</label>
                                        <div class="controls"><label class="checkbox line">
                                            <div class="checker">
                                                <span><input style="opacity: 0;" type = "checkbox" id = "isSuper" name = "isSuper" 
                                                <%
                                                	if(admin.getIsSuper()){
                                                		out.print("checked");
                                                	}
                                                
                                                 %>
                                                /></span>
                                            </div>
                                            是超级管理员</label>
                                        </div>
                                    </div>
                                    <div class="form-actions">
                                        <button class="btn  red" type = "submit" name="save" value="save"><i class="icon-ok"></i>提交</button>
                                    </div>
                                </form>
                            </div>
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