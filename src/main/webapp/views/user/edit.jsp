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

<div class="container col-8 mt-3  justify-content-center">
	<h1><c:out value="${showEdit.showTitle}" default="Show"/></h1>
	
	<div class="container col-6">
	<h1>Update the show</h1>
	
	<form:form action="/editForm"  method="POST" modelAttribute="updateShow">
		<label>Show title</label>
		<input type="text" name="showTitle" value="${showEdit.showTitle}">
		<label>Network</label>
		<input type="text" name="showNetwork" value="${showEdit.showNetwork}">
		<input type="hidden" name="id" value="${showEdit.id}">
		<button type="submit" class="btn btn-primary">Update</button>
	</form:form>
	</div>
	
	<div id="botones">
			<a class="btn btn-danger me-1" href="/eliminar?idShow=${showEdit.id}">	
				Delete
			</a>
			
			<button type="button" class="btn btn-dark" onclick="window.history.back()">Go back</button>
	</div>
	
</div>

<script type="text/javascript" src="/webjars/bootstrap/js/bootstrap.min.js"></script>

</body>
</html>