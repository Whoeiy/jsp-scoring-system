<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id = "extraBean" class = "mybean.data.Extra" scope = "session" /> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align = "center"><font size = 2>
		<font color = red><h3>中华女子学院学生学生综合测评系统</h3></font>
		<table cellSpacing = "1" cellPadding = "1" width = "400" align = "center" border = "0">
			<tr valign = "bottom">
				<td><a href = "lookExtraPoint.jsp">查看加分项</a></td>
				<td><a href = "personalPage.jsp">个人主页</a></td>
				<td><a href = "index.jsp">首页</a></td>
				<td><a href = "exitServlet">退出</a></td>
		</table>
		<hr size = 1 width = 1000 style="padding-bottom: 50px">
		
		<p>申报加分项</p>
		<table>
			<tr><td>分值范围: <jsp:getProperty property = "backMin" name = "extraBean" />~<jsp:getProperty property = "backMax" name = "extraBean" /></td></tr>
			<form action = 'addExtraServlet' method = 'post' >
				<tr><td>分数: <input type = text name = 's_point'/></td></tr>
				<tr><td>备注: <input type = text name = 'remarks' /></td></tr>
				<input type = submit name = 'g' value = '提交'>
			</form>		
		</table>
	</div>
	</font>
</body>
</html>