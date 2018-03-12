<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome 
<% session = request.getSession(false);
String name = (String)session.getAttribute("name"); 
out.print(name);%></title>
</head>

   <body>
      <sql:setDataSource var = "snapshot" driver = "com.mysql.jdbc.Driver"
         url = "jdbc:mysql://localhost/campus_security"
         user = "root"  password = "toor"/>
 
      <sql:query dataSource = "${snapshot}" var = "result">
         SELECT * from request;
      </sql:query>
 
      <table border = "1" width = "100%">
         <tr>
            <th>Employee</th>
            <th>Day</th>
            <th>Status</th>
            <th>Action</th>
         </tr>
         
         <c:forEach var = "row" items = "${result.rows}">
        	 <tr>
        	   <form action="approve" method="post">
               <td><input type="hidden" name="username" value="${row.employee}"/>${row.employee}</td>
               <td><input type="hidden" name="day" value="${row.day}"/>${row.day}</td>
               <td><input type="hidden" name="status" value="${row.stat}"/>${row.stat}</td>
               <td><input type="submit" name="action" value="Approve"/><input type="submit" name="action" value="Reject"/></td>
               </form>
            </tr>
         </c:forEach>
      </table>
      <form action="Manager.jsp">
    <input type="submit" value="Back" />
</form>
   </body>
</html>