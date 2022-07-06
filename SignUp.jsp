<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- <link href="css/bootstrap.min.css" rel="stylesheet"> -->
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <title>Registration Form </title>
  
   <script>
          
            // Function to check Whether both passwords
            // is same or not.
            function checkPassword(form) {
                password1 = form.password.value;
                password2 = form.cnmPassword.value;
  
            if (password1 != password2) {
                    alert ("\nPassword did not match: Please try again...")
                   return false;
                }
              
            }
        </script>
</head>

<body class="bg-light">
<div class="container">
  <div class="py-5 text-center">
    <h2>Registration Form </h2>
  </div>
</div>
<div class="container">
  <div class="row">
    <div class="col-md-12">
      <form onSubmit = "return checkPassword(this)" id="js-userRegFrm" action="<%=request.getContextPath()%>/register" method="POST">
        <c:if test="${iserror}">

          <div class="p-3 mb-2 bg-danger text-white">${error}</div>

        </c:if>
        <c:if test="${!iserror && !isadd}">
          <div class="p-3 mb-2 bg-success text-white">${msg}</div>

        </c:if>
        <div class="row">
          <div class="col-md-5 mb-3">
            <label for="firstName">First Name <span class="text-danger">*</span> </label>
            <input type="text" class="form-control" name="firstName" id="firstName" minlength="3" placeholder="First Name"   required>
            <div class="invalid-feedback"> First Name required. </div>
          </div>
          <div class="col-md-5 mb-3">
            <label for="lastName">Last Name <span class="text-danger">*</span> </label>
            <input type="text" class="form-control" name="lastName" id="lastName" minlength="2" placeholder="Last Name"  required>
            <div class="invalid-feedback"> Last Name required. </div>
          </div>
          <div class="col-md-2 mb-3">
            <label for="age">Age</label>
            <input type="number" min="0" class="form-control" id="age"  name="age" placeholder="Age"  >
          </div>
        </div>

        <h4 class="mb-3">Login Information</h4>
        <div class="form-group">
          <label for="userId">Email address/User Id <span class="text-danger">*</span></label>
          <input type="email" class="form-control" name="username" id="userId" placeholder="Enter Your Email" aria-describedby="emailHelp" required >
          <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
          <div class="invalid-feedback"> Email required. </div>
        </div>

        <div class="row">
          <div class="col-md-6 mb-3">
            <label for="password">Password <span class="text-danger">*</span></label>
            <input type="password" class="form-control"  minlength="6" maxlength="12" id="password" name="password"  placeholder="" required>
            <div class="invalid-feedback"> Password required. </div>
          </div>
          <div class="col-md-6 mb-3">
            <label for="cnmPassword">Confirm Password <span class="text-danger">*</span></label>
            <input type="password" class="form-control" minlength="6" maxlength="12" id="cnmPassword" name="cnmPassword"  placeholder="" required>
            <div class="invalid-feedback"> Confirm Password required. </div>
          </div>

        </div>



        <hr class="mb-4">
    <button type="submit" class="btn btn-primary btn-lg btn-block">Register</button>
    </form>
  </div>
  <span class="register">Already have an account? <a href="Login.jsp">Register Here</a></span>
</div>
<footer class="my-1 pt-1 text-muted text-center text-small">
  <p class="mb-1">&copy; 2020-2021</p>

</footer>
</div>

<script>  
2.	function matchPassword() {  
3.	  var pw1 = document.getElementById("password");  
4.	  var pw2 = document.getElementById("cnmPassword");  
5.	  if(pw1 != pw2)  
6.	  {   
7.	    alert("Passwords did not match");  
8.	  } else {  
9.	    alert("Password created successfully");  
10.	  }  
11.	}  
12.	</script>  

</body>
</html>
