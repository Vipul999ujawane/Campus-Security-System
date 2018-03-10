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
out.print(name);%>></title>
</head>

   <body>
      <sql:setDataSource var = "snapshot" driver = "com.mysql.jdbc.Driver"
         url = "jdbc:mysql://localhost/campus_security"
         user = "root"  password = "toor"/>
 
      <sql:query dataSource = "${snapshot}" var = "result">
         SELECT id,username,type from campus;
      </sql:query>
 
      <table border = "1" width = "100%">
         <tr>
            <th>ID</th>
            <th>Username</th>
            <th>Type</th>
         </tr>
         
         <c:forEach var = "row" items = "${result.rows}">
            <tr>
               <td><c:out value = "${row.id}"/></td>
               <td><c:out value = "${row.username}"/></td>
               <td><c:out value = "${row.type}"/></td>
            </tr>
         </c:forEach>
      </table>
      <form action="Manager.jsp">
    <input type="submit" value="Back" />
</form>
   </body>
</html>