<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome <% session = request.getSession(false);
String name = (String)session.getAttribute("name"); 
out.print(name);%></title>
</head>
<body>
<h1>Welcome <%out.print(name);%></h1>
<form action="showallempl.jsp" method="post">
    <input type="submit" value="Show All Employees">
</form>
<form action="setDuty.jsp" method="post">
    <input type="submit" value="Set Duty">
</form>
<form action="generatePayslip.jsp" method="post">
    <input type="submit" value="Generate Payslip">
</form>
<form action="getRequests.jsp" method="post">
	<input type="submit" value="Get Requests">
</form>
<form action="Logout" method="post">
	<input type="submit" value="Logout">
</form>
</body>
</html>