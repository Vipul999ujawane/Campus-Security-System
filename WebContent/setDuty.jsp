<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome 
<% session = request.getSession(false);
String name = (String)session.getAttribute("name"); 
out.print(name);%>></title>
</head>
<body>
<h1>Enter Shift number</h1>
<sql:setDataSource var = "snapshot" driver = "com.mysql.jdbc.Driver"
         url = "jdbc:mysql://localhost/campus_security"
         user = "root"  password = "toor"/>
 
      <sql:query dataSource = "${snapshot}" var = "result">
         SELECT * from employee;
      </sql:query>
      <table border = "1" width = "100%">
         <tr>
            <th>username</th>
            <th>Monday</th>
            <th>Tuesday</th>
            <th>Wednesday</th>
            <th>Thursday</th>
            <th>Friday</th>
            <th>Saturday</th>
            <th>Sunday</th>
         </tr>
         <form action="ConfirmDuty" method="post">
         <c:forEach var = "row" items = "${result.rows}">
            <tr>
               <td><c:out value = "${row.username}"/></td>
               <td><input type="text" name="${row.username}+monday" value = "${row.monday}" required/></td>
               <td><input type="text" name="${row.username}+tuesday" value = "${row.tuesday}" required/></td>
               <td><input type="text" name="${row.username}+wednesday" value = "${row.wednesday}" required/></td>
               <td><input type="text" name="${row.username}+thursday" value = "${row.thursday}" required/></td>
               <td><input type="text" name="${row.username}+friday" value = "${row.friday}" required/></td>
               <td><input type="text" name="${row.username}+saturday" value = "${row.saturday}" required/></td>
               <td><input type="text" name="${row.username}+sunday" value = "${row.sunday}" required/></td>
            </tr>
         </c:forEach>
         <input type="submit" value="update"/>
         </form>
      </table>

</body>
</html>