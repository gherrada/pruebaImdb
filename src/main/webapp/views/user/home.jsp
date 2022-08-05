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
  <div class="collapse navbar-collapse justify-content-end me-3" id="navbarNav">
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
	<h1>Tv shows</h1>
	
	<table class="table table-dark table-hover">
	<thead>
<tr>
    <th>Show</th>
    <th>Network</th>
</tr>
</thead>
<tbody class= "table-striped">
  
	<c:if test="${not empty showList}">
  		<c:forEach var="temp" items="${showList}">
    	<tr>
        	<td><a href="/showRating?idShow=${temp.getId()}">${temp.showTitle} </a></td>
        	<td>${temp.showNetwork}</td>
    	</tr>
    	</c:forEach>
    </c:if>
 
  
    </tbody>
</table>
	
		
	
</div>

<script type="text/javascript" src="/webjars/bootstrap/js/bootstrap.min.js"></script>

</body>
</html>