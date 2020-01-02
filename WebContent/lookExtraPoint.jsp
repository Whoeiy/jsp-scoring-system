<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body><font size = 2>
<div align = "center" style = "padding-bottom: 20px">
		<font color = red><h3>中华女子学院学生学生综合测评系统</h3></font>
		<table cellSpacing = "1" cellPadding = "1" width = "400" align = "center" border = "0">
			<tr valign = "bottom">
				<td><a href = "addExtraCategory.jsp">申请加分项</a></td>
				<td><a href = "lookExtraPoint.jsp">查看加分项</a></td>
				<td><a href = "personalPage.jsp">个人主页</a></td>
				<td><a href = "index.jsp">首页</a></td>
				<td><a href = "exitServlet">退出</a></td>
		</table>
	</div>
	<hr size = 1 width = 1000 style="padding-bottom: 50px">
	<div align = "center">
		按类别查看已申请的加分项: 
		<form action = "queryServlet" method = "post">
			<select name = "category">
				<option value = "0">全部</option>
				<option value = "1">德育加分项</option>
				<option value = "2">德育减分项</option>
				<option value = "3">智育学术成果加分项</option>
				<option value = "4">体育加分项</option> 
			</select>
			<input type = submit value = 提交>
		</form>
	</div>
</font>
</body>
</html>