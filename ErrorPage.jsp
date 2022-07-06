<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Error Page</title>
</head>
<body>

 
    <center>
       
        <h2>
            <a href="views/Login.jsp">Login</a>
             
        </h2>
        <h2>
            <a href="views/SignUp.jsp">New User</a>
             
        </h2>
    </center>
	
    <div align="center">
       <h3 align="center"> Error Page </h3>

	<p> ${message }
</p></div>  
</body>
</html>
</body>
</html>