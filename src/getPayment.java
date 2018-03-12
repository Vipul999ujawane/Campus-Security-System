

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;
/**
 * Servlet implementation class getPayment
 */
@WebServlet("/getPayment")
public class getPayment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getPayment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession sess=request.getSession(false);
		Connection con=null;
		String username=request.getParameter("username");
		PrintWriter out=response.getWriter();
		out.println("<H1> Payslip for "+username+"</H1><br>");
		int job=0;
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/campus_security","root","toor");
			PreparedStatement stm=con.prepareStatement("SELECT monday,tuesday,wednesday,thursday,friday,saturday,sunday FROM employee WHERE username=?");
			stm.setString(1, username);
			ResultSet rs=stm.executeQuery();
			int days[]=new int[7];
			while(rs.next())
			{
				days[0]=rs.getInt("monday");
				days[1]=rs.getInt("tuesday");
				days[2]=rs.getInt("wednesday");
				days[3]=rs.getInt("thursday");
				days[4]=rs.getInt("friday");
				days[5]=rs.getInt("saturday");
				days[6]=rs.getInt("sunday");
			}
			for(int i : days)
			{
				if(i>0)
				{
					job++;
				}
			}
			out.println("<H2>Days Worked :" + job+"</H2><br>");
			con.close();
		}catch(Exception e) {System.out.print(e);}
		
	}

}
