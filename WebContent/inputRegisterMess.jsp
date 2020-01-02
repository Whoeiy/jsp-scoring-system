<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id = "userBean" class = "mybean.data.Register" scope = "request" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>激活账户页面</title>
</head>
<body background = image/back.jpg><font size = 2>
	<div align = "center" style = "padding-bottom: 20px">
		<font color = red><h3>中华女子学院学生学生综合测评系统</h3></font>
		<table cellSpacing = "1" cellPadding = "1" width = "400" align = "center" border = "0">
			<tr valign = "bottom">
				<td><a href = "inputRegisterMess.jsp">激活</a></td>
				<td><a href = "login.jsp">登录</a></td>
				<td><a href = "index.jsp">首页</a></td>
				<td><a href = "exitServlet">退出</a></td>		
		</table>
	</div>
	<hr size = 1 width = 1000 style="padding-bottom: 50px">
	<div align = "center">
	<p style="font:bold">请输入您的基本信息，并设置密码（长度为6~12个字符）。*为必填项。</p>
		<form action = "registerServlet" method = "post" name = form>
			<table>
				<tr>
					<td>*姓名: </td><td><input type = text name = "logname" ></td>
					<td>*学号: </td><td><input type = text name = "logsno" ></td>
				</tr>
				<tr>
					<td>*设置新密码: </td><td><input type = password name = "password"></td>
					<td>*重复新密码: </td><td><input type = password name = "again_password"></td>
				</tr>
				<tr><td><input type = submit name = "g" value = "提交"></td></tr>
			</table>
		</form>
	 </div>
	 <div align = "center">
	 	<p> <jsp:getProperty name = "userBean" property = "backNews" /></p>
	 </div>
</font>
</body>
</html>