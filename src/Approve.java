

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
/**
 * Servlet implementation class Approve
 */
@WebServlet("/approve")
public class Approve extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String employee=request.getParameter("username");
		String day=request.getParameter("day");
		String action=request.getParameter("action");
		PrintWriter out=response.getWriter();
		out.print(employee+" "+day+" "+action);
		Connection con=null;
		String sql="UPDATE request SET stat=? WHERE (employee=?) AND (day=?) ";
		String stat="";
		if(action.equals("Approve"))
		{
			stat="APP";
		}
		if(action.equals("Reject"))
		{
			stat="REJ";
		}
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/campus_security","root","toor");
			PreparedStatement stm=con.prepareStatement(sql);
			stm.setString(1, stat);
			stm.setString(2, employee);
			stm.setString(3, day);
			stm.executeUpdate();
			con.close();
		}catch(Exception e) {System.out.println(e);}
		RequestDispatcher rd= request.getRequestDispatcher("getRequests.jsp");
		rd.include(request, response);
		
	}

}
