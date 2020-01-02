<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<%@ page import = "java.sql.*" %> 
<%@ page import = "java.util.*" %>
<jsp:useBean id = "extraBean" class = "mybean.data.Extra" scope = "session" /> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align = "center" style = "padding-bottom: 20px">
		<font color = red><h3>中华女子学院学生学生综合测评系统</h3></font>
		<table cellSpacing = "1" cellPadding = "1" width = "400" align = "center" border = "0">
			<tr valign = "bottom">
				<td><a href = "lookExtraPoint.jsp">查看加分项</a></td>
				<td><a href = "personalPage.jsp">个人主页</a></td>
				<td><a href = "index.jsp">首页</a></td>
				<td><a href = "exitServlet">退出</a></td>
		</table>
	</div>
	<hr size = 1 width = 1000 style="padding-bottom: 50px">
	<div align = "center">
		选择要申请的加分项的名称: 
		<%
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch(Exception e){}
		String uri = "jdbc:mysql://localhost:3306/ScoringSysForJSP?"+"user=root&password=Reborn22&characterEncoding=gb2312";
		Connection con;
		Statement sql;
		ResultSet rs;
		try{
			con = DriverManager.getConnection(uri);
			sql = con.createStatement();
			
			rs = sql.executeQuery("select detail,name,max,min from extra_name where extra = '"+extraBean.getExtra()+"' order by cast(detail as signed);");
			out.print("<form action = 'queryExtraInfoServlet' method = 'post'>");
			out.print("<select name = 'choose_extra_name'>");
			while(rs.next()){
				String detail = rs.getString("detail");
				String name = rs.getString("name");
				String max = rs.getString("max");
				String min = rs.getString("min");
				out.print("<option value = '"+detail+","+max+","+min+"'>"+name+"</option>");
			}
			out.print("</select>");
			out.print("<input type = 'submit' value = '选择'>");
			out.print("</form>");
			con.close();
		}
		catch(SQLException e){
			out.print(e);
		}
		
	%>
	</div>
</body>
</html>