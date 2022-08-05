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
        <a class="nav-link" href="/login">Login <span class="sr-only">(current)</span></a>
      </li>
    </ul>
  </div>
</nav>

<div class="container col-8 mt-5">
	<h1>Register</h1>
	
	<form:form action="/registro" modelAttribute="nuevoUsuario" method="POST">
		
		<div class="form-outline mb-4">
		<label class="form-label">Username</label>
		<input class="form-control" type="text" name="username" placeholder="myusername">
		</div>
		
		<div class="form-outline mb-4">
		<label class="form-label">Email</label>
		<input class="form-control" type="text" name="email" placeholder="">
		</div>
		
		<div class="form-outline mb-4">
		<label class="form-label">Password</label>
		<input class="form-control" type="password" name="password">
		</div>
		
		<div class="form-outline mb-4">
		<label class="form-label">Password Confirmation</label>
		<input class="form-control" type="password" name="passwordConfirmation">
		</div>
		
		<button type= "submit" class="btn btn-primary btn-block mb-4">Register!</button>
	</form:form>
		
	
</div>

<script type="text/javascript" src="/webjars/bootstrap/js/bootstrap.min.js"></script>

</body>
</html>