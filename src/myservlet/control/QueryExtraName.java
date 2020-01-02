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
import mybean.data.Extra;
import mybean.data.ExtraName;
import mybean.data.Login;

public class QueryExtraName extends HttpServlet{
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch(Exception e){}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("gb2312");
		
		String idNumber = request.getParameter("choose_category");
		if(idNumber == null)
			idNumber = "0";
		
		HttpSession session = request.getSession(true);
		Login loginBean = null;
		Extra extraBean = null;
		try {
			extraBean = (Extra) session.getAttribute("extraBean");
			loginBean = (Login) session.getAttribute("loginBean");
			if(extraBean == null) {
				extraBean = new Extra();
				session.setAttribute("extraBean", extraBean);
			}
		}
		catch(Exception exp) {
			extraBean = new Extra();
			session.setAttribute("extraBean", extraBean);
		}
		
		extraBean.setExtra(judgeCategory(idNumber));
		extraBean.setYear("2018~2019");	
		extraBean.setS_no(loginBean.getLogsno());
		RequestDispatcher dispatcher = request.getRequestDispatcher("addExtraName.jsp");
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
	
	public String setCategory(String c) {
		String res = "null";
		if(c.equals("me_etr")) {
			res = "德育加分项";
		}
		else if(c.equals("me_mns")) {
			res = "德育减分项";
		}
		else if(c.equals("ie_etr")) {
			res = "智育加分项";
		}
		else if(c.equals("pe_etr")) {
			res = "体育加分项";
		}
		return res;
	}

}









