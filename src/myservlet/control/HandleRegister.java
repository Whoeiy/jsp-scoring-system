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

import mybean.data.Register;

public class HandleRegister extends HttpServlet{
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
		String uri = "jdbc:mysql://localhost:3306/ScoringSysForJSP?"+"user=root&password=Reborn22&characterEncoding=gb2312";
		Connection con;
		PreparedStatement sql;
		Register userBean = new Register();
		String logname = request.getParameter("logname").trim();
		String logsno = request.getParameter("logsno").trim();
		String password = request.getParameter("password").trim();
		String again_password = request.getParameter("again_password").trim();
		request.setAttribute("userBean", userBean);
		
		if(logname == null) {
			logname = "";
		}
		if(password == null) {
			password = "";
		}
		if(!password.equals(again_password)) {
			userBean.setBackNews("两次密码不同，注册失败！");
			RequestDispatcher dispatcher = request.getRequestDispatcher("inputRegisterMess.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		boolean boo = logname.length() >= 0 && password.length() >= 6 && password.length() <= 12; 
		String backNews = "";
		try {
			con = DriverManager.getConnection(uri);
			String updateCondition = "UPDATE student SET s_pswd = ? where s_no = ? ";
			sql = con.prepareStatement(updateCondition);
			if(boo) {
				sql.setString(1, handleString(password));
				sql.setString(2, handleString(logsno));
				int m = sql.executeUpdate();
				if(m != 0) {
					userBean.setBackNews("激活成功！");		
				}
			}
			else {
				userBean.setBackNews("信息填写不合法！");
			}
			con.close();
		}
		catch(SQLException e) {
			userBean.setBackNews("您的信息尚未入库，无法激活！");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("inputRegisterMess.jsp");
		dispatcher.forward(request, response);
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request, response);
	}
}
