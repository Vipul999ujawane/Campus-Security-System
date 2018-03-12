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
         SELECT * from employee WHERE username=? <sql:param value ='<%= session.getAttribute("name") %>' />
      </sql:query>
 
      <table border = "1" width = "100%">
         <tr>
            <th>Day</th>
            <th>Duty</th>
         </tr>
         
         <c:forEach var = "row" items = "${result.rows}">
            <tr>
               <td><c:out value = "Monday"/></td>
               <td><c:out value = "${row.monday}"/></td>
            </tr>
            <tr>
               <td><c:out value = "Tuesday"/></td>
               <td><c:out value = "${row.tuesday}"/></td>
            </tr>
            <tr>
               <td><c:out value = "Wednesday"/></td>
               <td><c:out value = "${row.thursday}"/></td>
            </tr>
            <tr>
               <td><c:out value = "Thursday"/></td>
               <td><c:out value = "${row.Thursday}"/></td>
            </tr>
            <tr>
               <td><c:out value = "Friday"/></td>
               <td><c:out value = "${row.friday}"/></td>
            </tr>
            <tr>
               <td><c:out value = "Saturday"/></td>
               <td><c:out value = "${row.saturday}"/></td>
            </tr>
            <tr>
               <td><c:out value = "Sunday"/></td>
               <td><c:out value = "${row.sunday}"/></td>
            </tr>
         </c:forEach>
      </table>
      <form action="Employee.jsp">
    <input type="submit" value="Back" />
</form>
   </body>
</html>