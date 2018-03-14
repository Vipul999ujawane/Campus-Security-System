

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;
/**
 * Servlet implementation class DeleteRequest
 */
@WebServlet("/DeleteRequest")
public class DeleteRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession(false);
		String employee=(String) session.getAttribute("name");
		String day=request.getParameter("day");
		PrintWriter out=response.getWriter();
		out.println(employee+" "+day);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/campus_security","root","toor");
			PreparedStatement stm=con.prepareStatement("DELETE FROM request WHERE employee=? AND day=?");
			stm.setString(1, employee);
			stm.setString(2, day);
			stm.executeUpdate();
		}catch(Exception e) {System.out.print(e);}
		out.print("Request Deleted");
		RequestDispatcher rs = request.getRequestDispatcher("getRequestStatus.jsp");
		rs.include(request,response);

	}

}
