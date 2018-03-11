<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Administrator login</title>

<!-- Loading Bootstrap -->
<link href="Style/bootstrap/css/bootstrap.css" rel="stylesheet">

<!-- Loading Flat UI -->
<link href="Style/css/flat-ui.css" rel="stylesheet">
<link href="Style/css/demo.css" rel="stylesheet">
<link href="Style/css/common.css" rel="stylesheet">

<!-- HTML5 shim, for IE6-8 support of HTML5 elements. All other JS at the end of file. -->
<!--[if lt IE 9]>
      <script src="js/html5shiv.js"></script>
    <![endif]-->
</head>
<body style="background: #3498DB">
	<div class="container">

		<div class="title">
			<h2>
				教务信息管理系统
			</h2>
		</div>
		<div class="login">
			<div class="login-screen">
				<div class="login-icon">
					<img src="Style/images/icons/png/Infinity-Loop.png"
						alt="Welcome to Mail App" />
					<h4>管理员登录</h4>
				</div>

				<div class="login-form">
					<form method="post" action="LoginAdminServlet">
						<div class="form-group">
							<input type="text" class="form-control login-field"
								placeholder="Enter your id" id="login-name" name="id" /> <label
								class="login-field-icon fui-user" for="login-name"></label>
						</div>

						<div class="form-group">
							<input type="password" class="form-control login-field"
								placeholder="Password" id="login-pass" name="password" /> <label
								class="login-field-icon fui-lock" for="login-pass"></label>
						</div>

						<div class="mb20">
							<button class="btn btn-info btn-lg btn-block" type="submit" name="submit" value="submit">登录</button>
						</div>
						<div class="share mrl">
							<div class="fr">
								<input type="checkbox" name="isSuper" data-toggle="switch" />
							</div>
							<label class="share-label w90 fr" for="share-toggle4">超级管理员</label>
							<div class="clf"></div>
						</div>
					</form>

					<a class="login-link" href="LoginServlet">回到普通用户登录<a>
				</div>

				<!-- if there is some error, print here -->
				<%
					if (request.getAttribute("noExist") != null) {
				%>
				<div class="col-md-3">
					<div class="demo-tooltips">
						<p align="center" data-toggle="tooltip" data-placement="bottom"
							title="The user doesn't exist"></p>
					</div>
				</div>

				<%
					} else if (request.getAttribute("wrongpassword") != null) {
				%>
				<div class="col-md-3">
					<div class="demo-tooltips">
						<p align="center" data-toggle="tooltip" data-placement="bottom"
							title="Wrong password"></p>
					</div>
				</div>

				<%
					}
				%>



			</div>
		</div>

	</div>
	<!-- /container -->



	<!-- Load JS here for greater good =============================-->
	<script src="Style/js/jquery-1.8.3.min.js"></script>
	<script src="Style/js/jquery-ui-1.10.3.custom.min.js"></script>
	<script src="Style/js/jquery.ui.touch-punch.min.js"></script>
	<script src="Style/js/bootstrap.min.js"></script>
	<script src="Style/js/bootstrap-select.js"></script>
	<script src="Style/js/bootstrap-switch.js"></script>
	<script src="Style/js/flatui-checkbox.js"></script>
	<script src="Style/js/flatui-radio.js"></script>
	<script src="Style/js/jquery.tagsinput.js"></script>
	<script src="Style/js/jquery.placeholder.js"></script>
	<script src="Style/js/jquery.stacktable.js"></script>
	<script src="Style/js/application.js"></script>
</body>

</html>