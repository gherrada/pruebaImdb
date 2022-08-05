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

<!-- <h1> <c:out value="${nombre}"/></h1> -->


<div class="container col-8 mt-2">
	
	<h1>Title: <c:out value="${show.showTitle}"/></h1>
	<h2>Network: <c:out value="${show.showNetwork}"/></h2>

	<h3 class="fw-bold">Users who rated this show</h3>
	
	<table class="table table-dark table-hover">
	<thead>
		<tr>
    		<th>Name</th>
    		<th>Rating</th>
		</tr>
	</thead>
	<tbody class= "table-striped">
  
	<c:if test="${not empty userList}">
  		<c:forEach var="temp" items="${userList}" varStatus="theCount">
    	<tr>
        	<td>${temp} </td>
        	<td>${ratingList[theCount.index]}</td>
    	</tr>
    	</c:forEach>
    </c:if>
 
    </tbody>
</table>

		<div class="my-2">
		<a href="/editShow?idShow=${show.id}">
			<button type="button"  class="btn btn-info">Edit</button>
		</a>
		</div>
	
	
	
		<form:form  action="/rate" method="POST">
		<div class="row mx-auto">
		<div class="col-2">
			<h4>Leave a rating:</h4>
		</div>
		<div class="col-2">
			<input type="number" name="rating" min=1 max= 5 maxlength=1 class="form-control" placeholder="">
			<input type="hidden" name="idShow" value="${show.id}">
		</div>
		<div class="col-2">
			<button type="submit" class="btn btn-primary">Rate!</button>
		</div>
		</div>
		</form:form>
	
		<div class="mt-5">
			<button type="button" class="btn btn-dark" value="Back" onclick="window.history.back()">Go back</button>
		</div>
	
</div>

<script type="text/javascript" src="/webjars/bootstrap/js/bootstrap.min.js"></script>

</body>
</html>