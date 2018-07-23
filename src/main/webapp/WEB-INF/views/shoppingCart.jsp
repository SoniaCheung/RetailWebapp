<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<link href="./resources/css/bootstrap.css" rel="stylesheet">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Buy Now! - Shopping Cart</title>
</head>
<body>
	<!-- Shopping Cart is empty -->
	<c:if test="${empty sessionScope.shoppingCart}">
		<br><br><br><br><br><br><br>
		<h4 align=center>Oops! Your Shopping Cart is empty!</h4>
		<br>
		<img src="http://www.megabakeenergybar.com.au/wp-content/themes/mrtailor/images/empty_cart.png"
		       			style="display: block; margin-left: auto; margin-right: auto;" width="475" height="250" />
	</c:if>
	
	<c:if test="${not empty sessionScope.shoppingCart}">
		<table style="width:70%;margin-left:15%;margin-right:15%;">
		  <tbody>
	  		<c:forEach items="${sessionScope.shoppingCart.productMap}" var="productMapItem">
		  		<tr>
			  		<td>
			  		${ productMapItem.key.productName } <br>
			  		${ productMapItem.value }
			  		</td>
			  	</tr>
	  		</c:forEach>
		  </tbody>
		</table>
	</c:if>
</body>
</html>