<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<link href="./resources/css/bootstrap.css" rel="stylesheet">
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="./resources/js/searchForm.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Buy Now! - Registration</title>
</head>

<body>
	<!-- navigation bar -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
	  <!-- Logo image (Buy Now) -->
		<img src="http://keyursavaliya.com/wp-content/uploads/2017/08/buynow-logo-web-hostingpng.png"
		       			width="175" height="75" hspace="20"/>
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	
	  <div class="collapse navbar-collapse" id="navbarColor01">
	    <ul class="navbar-nav mr-auto">
	      <li class="nav-item active">
	        <a class="nav-link" href="/RetailWebapp">Home <span class="sr-only">(current)</span></a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="category?categoryName=clothing">Clothing</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="category?categoryName=bags">Bags</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="category?categoryName=accessories">Accessories</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="#">About</a>
	    </ul>
	    <form id="searchForm" class="form-inline my-2 my-lg-0" onsubmit="submitSearch()">
	      <input name="searchKeyword" class="form-control mr-sm-2" type="text" placeholder="Search for name or description">
	      <button class="btn btn-secondary my-2 my-sm-0" type="submit">Search</button>
	    </form>
	     <!-- Shopping cart -->
		<a href="shoppingCart"><img src="https://cdn4.iconfinder.com/data/icons/shopping-21/64/shopping-06-512.png"
			       			width="60" height="60" hspace="10"/></a>
		<!-- User Login -->
		<c:if test="${ empty sessionScope.user }">
			<a href="userLogin">
				<img src="http://cdn.onlinewebfonts.com/svg/img_518099.png"
			       			width="50" height="50" hspace="10"/></a>
		</c:if>
				
		<!-- User Center -->
		<c:if test="${ not empty sessionScope.user }">
			<a href="userCenter">
				<img src="http://cdn.onlinewebfonts.com/svg/img_55925.png"
			       			width="50" height="50" hspace="10"/></a>
		</c:if>
		
		<!-- User Log out -->
		<c:if test="${ not empty sessionScope.user }">
			<a href="userLogout">
				<img src="http://cdn.onlinewebfonts.com/svg/img_71488.png"
			       			width="50" height="50" hspace="10"/></a>
		</c:if>
		
		<!-- Registration -->
		<c:if test="${ empty sessionScope.user }">
	  		<div>
			<a href="register"><img src="https://cdn0.iconfinder.com/data/icons/academics-linear-black/2048/Register-512.png"
			       			width="60" height="60" hspace="10"/></a>
		  	</div>
	  	</c:if>
	  </div>
	</nav>
	
	<br>
		<h3 style="margin-left:45px; color: #EB6864;">Registration Form</h3>
	<br>
	
	<form:form method="POST" action="registerUser" modelAttribute="user">
		<table style="width:70%;margin-left:15%;margin-right:15%;">
		<tbody>
			<tr>
				<td>
					<label for="username">Username</label>
      				<form:input path="username" type="text" class="form-control" placeholder="Enter username" required="required"/>
      			</td>
			</tr>
			<tr>
				<td>
					<br><label for="password">Password</label>
      				<form:input path="password" type="password" class="form-control" placeholder="Enter password" required="required"/>
      			</td>
			</tr>
			<tr>
				<td>
		      		<br><label for="email">Email address</label>
		      		<form:input path="email" type="email" class="form-control" placeholder="Enter email" required="required"/>
		      	</td>
	   		</tr>
			<tr>
				<td>
					<br><label for="address">Address</label>
      				<form:input path="address" type="text" class="form-control" placeholder="Enter address" required="required"/>
      			</td>
			</tr>
			<tr>
				<td align="right"><br><button type="submit" class="btn btn-primary">Submit</button></td>
			</tr>
			</tbody>
		</table>
	</form:form>
	
</body>
</html>