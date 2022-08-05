<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
    
<!DOCTYPE html>
<html>
<head>

<meta charset="charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/webjars/bootstrap/css/bootstrap.min.css" />

<title>Sistema Imdb</title>
</head>
<body>

<!-- Nav bar -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand ms-5" href="#">Tv Shows</a>
  <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav">
      <li class="nav-item active">
        <a class="nav-link" href="#">Login <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/registroForm">Registration</a>
      </li>
    </ul>
  </div>
</nav>

<c:if test="${not empty mensaje}"> 
	<div class="alert alert-success alert-dismissible fade show" role="alert">
		<span>Success !  User has been registered</span>
		<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
	</div>
</c:if>

<c:if test="${not empty passwordError}"> 
	<div class="alert alert-warning alert-dismissible fade show" role="alert">
		<span>Error ! Fields password and password Confirmation do not match</span>
		<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
	</div>
</c:if>


<div class="container col-4 d-block mt-4">

<h1>Login</h1>
<form:form action="/login" method="POST">
  <!-- Email input -->
  <div class="form-outline mb-4">
  	<label class="form-label" for="form2Example1">Email address</label>
    <input type="email" id="email" name="email" class="form-control" />
    
  </div>

  <!-- Password input -->
  <div class="form-outline mb-4">
   	<label class="form-label" for="form2Example2">Password</label>
    <input type="password" id="password" name="password" class="form-control" />
   
  </div>
  
  <!-- Submit button -->
  <button type="submit" class="btn btn-primary btn-block mb-4">Login!</button>
</form:form>

</div>

<script type="text/javascript" src="/webjars/bootstrap/js/bootstrap.min.js"></script>

</body>
</html>