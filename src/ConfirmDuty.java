import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

/**
 * Servlet implementation class ConfirmDuty
 */
@WebServlet("/ConfirmDuty")
public class ConfirmDuty extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		request.getSession(false);
		Connection con=null;
		List<String> empList = new ArrayList<String>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con= DriverManager.getConnection("jdbc:mysql://localhost:3306/campus_security","root","toor");
			PreparedStatement ps=con.prepareStatement("SELECT username from campus where type = 'EMP'");
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				
				empList.add(rs.getString("username"));
			}
		}catch(Exception e) {System.out.print(e);}
	for(String oneEmp :empList)
	{
		try {
			String sql="UPDATE employee SET monday=?, tuesday=?, wednesday=?, thursday=?, friday=?, saturday=?, sunday=? WHERE username=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, request.getParameter(oneEmp+"+monday"));
			stmt.setString(2, request.getParameter(oneEmp+"+tuesday"));
			stmt.setString(3, request.getParameter(oneEmp+"+wednesday"));
			stmt.setString(4, request.getParameter(oneEmp+"+thursday"));
			stmt.setString(5, request.getParameter(oneEmp+"+friday"));
			stmt.setString(6, request.getParameter(oneEmp+"+saturday"));
			stmt.setString(7, request.getParameter(oneEmp+"+sunday"));
			stmt.setString(8, oneEmp);
			stmt.executeUpdate();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	out.println("Records Updated");
	RequestDispatcher man=request.getRequestDispatcher("Manager.jsp");
	man.include(request, response);
}
}
