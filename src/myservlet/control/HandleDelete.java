package myservlet.control;
import mybean.data.DataByPage;
import mybean.data.Extra;
import mybean.data.Login;
import java.util.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;
public class HandleDelete extends HttpServlet {
   public void init(ServletConfig config) throws ServletException { 
      super.init(config);
   }
   public  void  doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
      request.setCharacterEncoding("gb2312");
      String delete = request.getParameter("d_extra");
      String[] delete_arr = delete.split(",");
      
      String year = delete_arr[0];
      String sno = delete_arr[1];
      String extra_name = delete_arr[2];
      String extra_detail = delete_arr[3];
   
      DataByPage dataBean = null;
      HttpSession session=request.getSession(true);
      String uri = "jdbc:mysql://localhost:3306/ScoringSysForJSP?"+"user=root&password=Reborn22&characterEncoding=gb2312";
      Connection con;
      PreparedStatement sql;
      
      try{  
    	  con = DriverManager.getConnection(uri);
//			insert into extra values(null,year,sno,etr,dtl,rmrks,sp,gp,sts,pas);
			String insertCondition = "delete from extra where year = ? and s_no = ? and extra = ? and detail = ?;";
			sql = con.prepareStatement(insertCondition);
			sql.setString(1, year);
			sql.setString(2, sno);
			sql.setString(3, extra_name);
			sql.setString(4, extra_detail);
			int m = sql.executeUpdate();
			if(m != 0) {
				RequestDispatcher dispatcher= request.getRequestDispatcher("lookExtraPoint.jsp");
				dispatcher.forward(request, response);
			}
			else {
				RequestDispatcher dispatcher= request.getRequestDispatcher("lookExtraPoint.jsp");
				dispatcher.forward(request, response);
			}
			con.close();
		}
		catch(SQLException e) {
			RequestDispatcher dispatcher= request.getRequestDispatcher("lookExtraPoint.jsp");
			dispatcher.forward(request, response);
		}
   }
   
   public  void  doGet(HttpServletRequest request,HttpServletResponse response)
                        throws ServletException,IOException {
      doPost(request,response);
   }
}
