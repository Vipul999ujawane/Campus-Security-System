import java.io.IOException;
import java.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;
/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/campus_security","root","toor");
				PreparedStatement stmt = con.prepareStatement("SELECT * FROM campus WHERE username=?");
				stmt.setString(1, name);
				ResultSet rs=stmt.executeQuery();
				if(rs.next()==false)
				{
					out.print("User not found\n");
					RequestDispatcher rd = request.getRequestDispatcher("Index.html");
					rd.include(request, response);
				}
				rs.beforeFirst();
				while (rs.next()) {
					if(rs.getString("password").equals(password))
					{
						out.println("Login Successful");
						HttpSession session=request.getSession();
						session.setAttribute("name", name);
						if(rs.getString("type").equals("MAN"))
						{
							RequestDispatcher man=request.getRequestDispatcher("Manager.jsp");
							man.forward(request, response);
						}
						if(rs.getString("type").equals("EMP"))
						{
							RequestDispatcher emp=request.getRequestDispatcher("Employee.jsp");
							emp.forward(request, response);
						}
					}
					else
					{
						out.println("Wrong Password");
						RequestDispatcher rd = request.getRequestDispatcher("Index.html");
						rd.include(request, response);
					}
					}
		
				con.close();
		}catch(Exception e) {System.out.println(e);}

	}

}