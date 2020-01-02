<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "mybean.data.DataByPage" %>
<%@ page import = "com.sun.rowset.*" %>
<jsp:useBean id = "dataBean" class = "mybean.data.DataByPage" scope = "session" />
<jsp:useBean id = "loginBean" class = "mybean.data.Login" scope = "session" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>查看加分项</title>
</head>
<body>
	<div align = "center">
	<font color = red><h3>中华女子学院学生学生综合测评系统</h3></font>
		<table cellSpacing = "1" cellPadding = "1" width = "400" align = "center" border = "0">
			<tr valign = "bottome">
				<td><a href = "addExtraCategory.jsp">申请加分项</a></td>
				<td><a href = "lookExtraPoint.jsp">查看加分项</a></td>
				<td><a href = "personalPage.jsp">个人主页</a></td>
				<td><a href = "index.jsp">首页</a></td>
				<td><a href = "exitServlet">退出</a></td>
		</table>
	</div>
	<hr size = 1 width = 1000 style="padding-bottom: 50px">
	<div align = "center">
		<br>当前显示的内容是:
			<table border = 1>
				<tr>
					<th>序号</th>
					<th>年份</th>
					<th>加分项名称</th>
					<th>申请分数</th>
					<th>审核分数</th>
					<th>审核状态</th>
					<th>是否通过</th>
					<th>删除</th>
				</tr>
				<jsp:setProperty property="pageSize" name="dataBean" param="pageSize"/>
				<jsp:setProperty property="currentPage" name="dataBean" param="currentPage"/>
				<%
					CachedRowSetImpl rowSet = dataBean.getRowSet();
					if(rowSet == null){
						out.print("没有任何查询信息，无法浏览");
						return;
					}
					rowSet.last();
					int totalRecord = rowSet.getRow();
					out.println("全部记录数" + totalRecord);
					int pageSize = dataBean.getPageSize();
					int totalPages = dataBean.getTotalPages();
					if(totalRecord % pageSize == 0){	//算页数
						totalPages = totalRecord / pageSize;
					}
					else{
						totalPages = totalRecord / pageSize + 1;
					}
					dataBean.setPageSize(pageSize);
					dataBean.setTotalPages(totalPages);
					
					if(totalPages >= 1){	//循环翻页
						if(dataBean.getCurrentPage() < 1){
							dataBean.setCurrentPage(dataBean.getTotalPages());
						}
						if(dataBean.getCurrentPage() > dataBean.getTotalPages()){
							dataBean.setCurrentPage(1);
						}
						int index = (dataBean.getCurrentPage() - 1) * pageSize + 1;
						rowSet.absolute(index);		//查询位置移动到currentPage页起始位置
						boolean boo = true;
						int m = 0;
						for(int i = 1; i <= pageSize && boo; i++){
							String id = Integer.toString(++m);
							String year = rowSet.getString(1);
							String detail = rowSet.getString(2);
							String sp = rowSet.getString(3);
							String gp = rowSet.getString(4);
							String sts = rowSet.getString(5);
							String pas = rowSet.getString(6);
							String detail_num = rowSet.getString(7);
							String extra = rowSet.getString(8);
							
							String d_extra = year+","+loginBean.getLogsno()+","+extra+","+detail_num;
						
							String delete = "<form action = 'deleteExtra' method = 'post'>"+
									"<input type = 'hidden' name = 'd_extra' value = '"+d_extra+"''>"+
									"<input type = 'submit' value = '删除' ></form>";
							
							out.print("<tr>");
							out.print("<td>"+id+"</td>");
							out.print("<td>"+year+"</td>");
							out.print("<td>"+detail+"</td>");
							out.print("<td>"+sp+"</td>");
							out.print("<td>"+gp+"</td>");
							out.print("<td>"+sts+"</td>");
							out.print("<td>"+pas+"</td>");
							out.print("<td>"+delete+"</td>");
							
							boo = rowSet.next();
						}
					}		
				%>
			</table>
				<br>每页最多显示<jsp:getProperty name = "dataBean" property = "pageSize" />条信息
				<br>当前显示第<font color = blue>
					<jsp:getProperty name = "dataBean" property = "currentPage" />
				</font>页，共有<font color = blue>
					<jsp:getProperty name = "dataBean" property = "totalPages" />
				</font>页。
				
				<table>
					<tr>
						<td><form action="" method = post>
						<input type = hidden name = "currentPage" value = "<%= dataBean.getCurrentPage()-1 %>">
						<input type = submit name = "g" value = "上一页"></form></td>
					<td><form action="" method = post>
						<input type = hidden name = "currentPage" value = "<%= dataBean.getCurrentPage()+1 %>">
						<input type = submit name = "g" value = "下一页"></form></td></tr>
			</table>
			 <tr><td> <FORM action="" method=post>
		          每页显示<Input type=text name="pageSize" value =1 size=3>
		          条记录<Input type=submit name="g" value="确定"></FORM></td>
			      <td> <FORM action="" method=post>
			           输入页码：<Input type=text name="currentPage" size=2 >
			           <Input type=submit name="g" value="提交"></FORM></td></tr>		
	</div>
</body>
</html>