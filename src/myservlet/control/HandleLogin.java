package myservlet.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

import mybean.data.Login;

public class HandleLogin extends HttpServlet{
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
		Statement sql;
		
		String logsno = request.getParameter("logsno").trim();
		String password = request.getParameter("password").trim();
		logsno = handleString(logsno);
		password = handleString(password);
		
		boolean boo = (logsno.length() == 9) && (password.length() >= 6 && password.length() <= 12);
		try {
			con = DriverManager.getConnection(uri);
			String condition = "select * from student where s_no = '" + logsno +
					"' and s_pswd = '" + password + "'";
			sql = con.createStatement();
			if(boo) {
				ResultSet rs = sql.executeQuery(condition);
				boolean m = rs.next();
				if(m == true) {
					success(request, response, logsno, password);
					RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
					dispatcher.forward(request, response);
				}
				else {
					String backNews = "您输入的用户名不存在，或密码不匹配";
					fail(request, response, logsno, backNews);
				}
				con.close();
			}
			else {
				String backNews = "请输入合法的用户名和密码";
				fail(request, response, logsno, backNews);
			}
		}catch(SQLException exp){
			String backNews = "" + exp;
			fail(request, response, logsno, backNews);
		}
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request, response);
	}
	
	public void success(HttpServletRequest request, HttpServletResponse response, String logsno, String password) throws IOException, ServletException {
		Login loginBean = null;
		HttpSession session = request.getSession(true);
		try {
			loginBean = (Login) session.getAttribute("loginBean");
			if(loginBean == null) {
				loginBean = new Login();
				session.setAttribute("loginBean", loginBean);
				loginBean = (Login) session.getAttribute("loginBean");
			}
			String name = loginBean.getLogsno();
			if(name.equals(logsno)) {
				loginBean.setBackNews(logsno + "已经登录了");
				loginBean.setLogsno(logsno);
			}
			else {
				loginBean.setBackNews(logsno + "登录成功");
				loginBean.setLogsno(logsno);
//				RequestDispatcher dispatcher = request.getRequestDispatcher("personalPage.jsp");
//				dispatcher.forward(request, response);
//				response.sendRedirect("personalPage.jsp");
				}
		}catch(Exception ee) {
			loginBean = new Login();
			session.setAttribute("loginBean", loginBean);
			loginBean.setBackNews(logsno + "登录成功");
			loginBean.setLogsno(logsno);
//			RequestDispatcher dispatcher = request.getRequestDispatcher("personalPage.jsp");
//			dispatcher.forward(request, response);
//			return;
		}		
	}
	
	public void fail(HttpServletRequest request, HttpServletResponse response, String logsno, String backNews) {
		response.setContentType("text/html;charset=UTF-8");
		try {
			PrintWriter out = response.getWriter();
			out.println("<html><body>");
			out.println("<h2>"+logsno+"登录反馈结果<br>"+backNews+"</h2>");
			out.println("返回登录页面或主页<br>");
			out.println("<a href = login.jsp>登录页面</a>");
			out.println("<br><a href = index.jsp>主页</a>");
			out.println("</body></html>");
		}
		catch(IOException exp) {}
	}
	
}
