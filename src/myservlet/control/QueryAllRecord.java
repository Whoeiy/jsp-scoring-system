package myservlet.control;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.rowset.CachedRowSetImpl;

import mybean.data.DataByPage;
import mybean.data.Login;

public class QueryAllRecord extends HttpServlet{
	CachedRowSetImpl rowSet = null;
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch(Exception e){}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("gb2312");
		String idNumber = request.getParameter("category");
		if(idNumber == null)
			idNumber = "0";
		HttpSession session = request.getSession(true);
		Connection con = null;
		DataByPage dataBean = null;
		Login loginBean = null;
		ResultSet rs = null;
		try {
			dataBean = (DataByPage) session.getAttribute("dataBean");
			loginBean = (Login) session.getAttribute("loginBean");
			if(dataBean == null) {
				dataBean = new DataByPage();
				session.setAttribute("dataBean", dataBean);
			}
		}
		catch(Exception exp) {
			dataBean = new DataByPage();
			session.setAttribute("dataBean", dataBean);
		}
		String uri = "jdbc:mysql://localhost:3306/ScoringSysForJSP?"+"user=root&password=Reborn22&characterEncoding=gb2312";
		try {
			con = DriverManager.getConnection(uri);
			if(idNumber.equals("0")) {
				Statement sql = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				rs = sql.executeQuery("select year, extra_name.name as name, s_point, g_point, status, pass, extra.detail, extra.extra from extra inner join extra_name on extra.extra = extra_name.extra and extra.detail = extra_name.detail "
						+ "where s_no = "+loginBean.getLogsno()+" order by cast(extra.detail as signed);");
			}
			else {
				Statement sql = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				rs = sql.executeQuery("select year, extra_name.name as name, s_point, g_point, status, pass, detail, extra from extra inner join extra_name on extra.extra = extra_name.extra and extra.detail = extra_name.detail "
						+ "where s_no = "+loginBean.getLogsno()+" and extra.extra = '"+judgeCategory(idNumber)+"' order by cast(extra.detail as signed);");
			}
			rowSet = new CachedRowSetImpl();
			rowSet.populate(rs);
			dataBean.setRowSet(rowSet);
			con.close();
		}catch(SQLException exp) {}
		RequestDispatcher dispatcher = request.getRequestDispatcher("byPageShow.jsp");
		dispatcher.forward(request, response);
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doPost(request, response);
	}
	public String judgeCategory(String c) {
		String res = "0";
		if(c.equals("1"))
			res = "me_etr";
		else if(c.equals("2"))
			res = "me_mns";
		else if(c.equals("3"))
			res = "ie_etr";
		else if(c.equals("4"))
			res = "pe_etr";
		
		return res;
	}

}









