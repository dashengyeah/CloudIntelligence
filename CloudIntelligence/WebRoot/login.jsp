<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8"/>
	<title>欢迎使用云智教育</title>
	<script type="text/javascript" src="./js/login.js"></script>
</head>
<body>
	<div id="title_box">
		欢迎使用云智教育平台
	</div>
	
	<div id="login_box">
		<h3>管理员登录</h3>
		<form action="login" method="post">
			用户名:<input type="text" id="username"/> <br />
			密码:<input type="password" id="password"/> <br />
			<input type="submit" value="登录"/>
		</form>
	</div>
	
</body>
</html>