<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<link href="./resources/css/bootstrap.css" rel="stylesheet">
<link href="./resources/css/plus-minus-input.css" rel="stylesheet">
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="./resources/js/searchForm.js"></script>
<script src="./resources/js/plus-minus-input.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Buy Now! - Product Details</title>
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
		       			width="60" height="60" hspace="20"/></a>
	  </div>
	</nav>
	
	<!-- Result not found image -->
	<c:if test="${empty product}">
		<br><br><br><br><br><br><br>
		<img src="https://webmarketingschool.com/wp-content/uploads/2018/03/nojobsfound.png"
		       			style="display: block; margin-left: auto; margin-right: auto;" width="425" height="150" />
	</c:if>


	<!-- Showing the product details -->
	<c:if test="${not empty product}">
		<table style="width:70%;margin-left:15%;margin-right:15%;">
		  <tbody>
		  	<tr>
		  		<td>
		  			<img src="${product.productImageList[0]}" onerror="this.src='http://www.wellesleysocietyofartists.org/wp-content/uploads/2015/11/image-not-found.jpg'"
			       			width="500" height="500" />
			    </td>
			    <td>
			    		<h5>${product.productName} </h5>
			    		<i style="color: #777;">${product.productDescription}</i> <br>
			    		<h6>HKD ${product.price} </h6>
			    		<c:if test="${product.stock <= 50}">
						<p class="text-danger">Low In Stock </p>
					</c:if>
					<c:if test="${product.stock > 50}">
						<p class="text-success">In Stock </p>
					</c:if>
					<form action="addShoppingCart" method="post">
						<div class="input-group plus-minus-input">
						  <div class="input-group-button">
						    <button type="button" class="button hollow circle" data-quantity="minus" data-field="quantity">
						      -
						    </button>
						  </div>
						  <input class="input-group-field" type="number" name="quantity" value="1">
						  
						  <div class="input-group-button">
						    <button type="button" class="button hollow circle" data-quantity="plus" data-field="quantity">
						      +
						    </button>
						  </div>
						</div>
						<br>
						<input type="hidden" value="${product.id}" name="productId" />
				    		<button type="submit" class="btn btn-primary" style="width:260px;">ADD TO SHOPPING CART</button>
			   		</form>
			    </td>
		  	</tr>
		  </tbody>
		</table>
	</c:if>
	
</body>
</html>