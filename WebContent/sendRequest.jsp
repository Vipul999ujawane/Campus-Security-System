<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<H1>Choose Day of Leave</H1>
<form action="GenerateRequest" method="post">
<select name="day">
<option value="monday">Monday</option>
<option value="tuesday">Tuesday</option>
<option value="wednesday">Wednesday</option>
<option value="thursday">Thursday</option>
<option value="friday">Friday</option>
<option value="saturday">Saturday</option>
<option value="sunday">Sunday</option>
</select>
<input type="submit" value="Send Request"/>
</form>
</body>
</html>