<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="net.javaguides.registration.dao.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Success Root</title>
</head>
<body>
<center>
<h2> Root Login Successfully</h2>
 <form class="noclass" action="<%=request.getContextPath()%>/initialize" method="post">
 <button type="submit">Initialize Database</button>
 </form>
 </center>
</body>
</html>