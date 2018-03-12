

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;
/**
 * Servlet implementation class GenerateRequest
 */
@WebServlet("/GenerateRequest")
public class GenerateRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession(false);
		String name=(String) session.getAttribute("name");
		String day = request.getParameter("day");
		PrintWriter out=response.getWriter();
		Connection con=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/campus_security","root","toor");
			PreparedStatement ps=con.prepareStatement("INSERT IGNORE INTO request (employee,day,stat) VALUES ((SELECT username FROM employee WHERE username=?),?,'REQ')");
			ps.setString(1, name);
			ps.setString(2, day);
			ps.executeUpdate();
			con.close();
		}catch(Exception e) {System.out.println(e);}
		out.println("Request Sent");
		RequestDispatcher rd=request.getRequestDispatcher("Employee.jsp");
		rd.include(request, response);
		
	}

}
