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
<h1>Choose Employee</h1>
<form action="getPayment" method="get">
      <sql:setDataSource var = "snapshot" driver = "com.mysql.jdbc.Driver"
         url = "jdbc:mysql://localhost/campus_security"
         user = "root"  password = "toor"/>
      <sql:query dataSource = "${snapshot}" var = "result">
         SELECT username from employee;
      </sql:query>
      <select name="username">
      <c:forEach var = "row" items = "${result.rows}">
      <option value='${row.username}' >${row.username}</option>
      </c:forEach>
      </select>
<input type="submit" value="Get Payment">
</form>
</body>
</html>