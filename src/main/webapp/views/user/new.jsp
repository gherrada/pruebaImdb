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
  <a class="navbar-brand ms-5" href="/user/shows">Tv Shows</a>
  <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse justify-content-end me-5" id="navbarNav">
    <ul class="navbar-nav">
      <li class="nav-item active">
        <a class="nav-link" href="/newShow">Add a show</a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="/logout">Logout</a>
      </li>
    </ul>
  </div>
</nav>

<div class="container col-8">
	<h1>Create a new show</h1>
	
	<form:form action="/addShow"  method="POST" modelAttribute="newShow">
		<label>Show title</label>
		<input type="text" name="showTitle" placeholder="ShowName">
		<label>Network</label>
		<input type="text" name="showNetwork" placeholder="Network">
		<button type="submit" class="btn btn-primary">Create!</button>
	</form:form>
	
	<div>

			<button type="button" class="btn btn-dark" value="Back" onclick="window.history.back()">Go back</button>
	</div>
	
		
	
</div>

<script type="text/javascript" src="/webjars/bootstrap/js/bootstrap.min.js"></script>

</body>
</html>