package myservlet.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mybean.data.Extra;
import mybean.data.Login;
import mybean.data.Register;

public class HandleAddExtra extends HttpServlet{
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch(Exception e){}
	}
	public String handleString(String s)
	{
		try {
			byte bb[] = s.getBytes("UTF-8");
			s = new String(bb);
		}
		catch(Exception ee) {}
		return s;
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("gb2312");
		HttpSession session = request.getSession(true);
		Extra extraBean = null;
		
		String spoint = request.getParameter("s_point").trim();
		String sremarks = request.getParameter("remarks").trim();
		
		try {
			extraBean = (Extra) session.getAttribute("extraBean");
			if(extraBean == null) {
				extraBean = new Extra();
				session.setAttribute("extraBean", extraBean);
			}
		}
		catch(Exception exp) {
			extraBean = new Extra();
			session.setAttribute("extraBean", extraBean);
		}
		
		extraBean.setS_point(spoint);
		extraBean.setG_point(spoint);
		extraBean.setRemarks(sremarks);
		extraBean.setPass("0");
		extraBean.setStatus("0");
		
		
		String uri = "jdbc:mysql://localhost:3306/ScoringSysForJSP?"+"user=root&password=Reborn22&characterEncoding=gb2312";
		Connection con;
		PreparedStatement sql;
		
		if(spoint == null) {
			spoint = "";
		}
		if(sremarks == null) {
			sremarks = "null";
		}
		boolean rrr = true;
		if(Integer.parseInt(spoint) > Integer.parseInt(extraBean.getBackMax()) || Integer.parseInt(spoint) < Integer.parseInt(extraBean.getBackMin())) {
			rrr = false;
		}
		
		boolean boo = spoint.length() > 0 && sremarks.length() > 0 && rrr; 
		String backNews = "";
		try {
			con = DriverManager.getConnection(uri);
//			insert into extra values(null,year,sno,etr,dtl,rmrks,sp,gp,sts,pas);
			String insertCondition = "insert into extra values(null,?,?,?,?,?,?,?,?,?);";
			sql = con.prepareStatement(insertCondition);
			if(boo) {
				sql.setString(1, extraBean.getYear());
				sql.setString(2, extraBean.getS_no());
				sql.setString(3, extraBean.getExtra());
				sql.setString(4, extraBean.getDetail());
				sql.setString(5, extraBean.getRemarks());
				sql.setString(6, extraBean.getS_point());
				sql.setString(7, extraBean.getG_point());
				sql.setString(8, extraBean.getStatus());
				sql.setString(9, extraBean.getPass());
				int m = sql.executeUpdate();
				if(m != 0) {
					extraBean.setBackNews("申报成功！");
					RequestDispatcher dispatcher = request.getRequestDispatcher("addExtraResult.jsp");
					dispatcher.forward(request, response);
				}
			}
			else {
				extraBean.setBackNews("申报失败！信息填写不合法！");
				RequestDispatcher dispatcher = request.getRequestDispatcher("addExtraResult.jsp");
				dispatcher.forward(request, response);
			}
			con.close();
		}
		catch(SQLException e) {
			extraBean.setBackNews("申报失败！请检查您的登录状态");
			RequestDispatcher dispatcher = request.getRequestDispatcher("addExtraResult.jsp");
			dispatcher.forward(request, response);
		}
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request, response);
	}
}
