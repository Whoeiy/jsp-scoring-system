<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="loginBean" class="mybean.data.Login" scope="session" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body background = image/back.jpg><font size = 2>
<div align = "center" style = "padding-bottom: 20px">
		<font color = red><h3>中华女子学院学生学生综合测评系统</h3></font>
		<table cellSpacing = "1" cellPadding = "1" width = "400" align = "center" border = "0">
			<tr valign = "bottom">
				<td><a href = "inputRegisterMess.jsp">激活</a></td>
				<td><a href = "login.jsp">登录</a></td>
				<td><a href = "exitServlet">退出</a></td>
				<td><a href = "index.jsp">首页</a></td>
		</table>
	</div>
	<hr size = 1 width = 1000 style="padding-bottom: 50px">
	<div align = "center">
		<table border = 2>
			<tr><th>登录</th></tr>
			<form action = "loginServlet" method = "post">
				<tr><td>学号: <input type = text name = "logsno"></td></tr>
				<tr><td>密码: <input type = password name = "password"></td></tr>
		</table>
		<input type = submit name = "g" value = "提交">
		</form>
	</div>
	<div align = "center">
		登录反馈信息: <jsp:getProperty property = "backNews" name = "loginBean" />
		<br>登录学号: <jsp:getProperty property="logsno" name="loginBean"/>
		<% if(!loginBean.getLogsno().isEmpty()){ 
			out.print("<br><a href = 'personalPage.jsp'>进入系统</a>");
		}
		%>
	</div>
</font>

</body>
</html>