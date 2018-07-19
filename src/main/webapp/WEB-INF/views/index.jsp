<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<link href="./resources/css/bootstrap.css" rel="stylesheet">
<head>
<script type="text/javascript">
	function submitSearch(){
	    var search_src = "find?" + document.getElementsByName("searchKeyword")[0].value;
	    var search_form = document.getElementById('searchForm');
	    search_form.action = search_src ;
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Buy Now!</title>
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
	        <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
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
	  </div>
	</nav>
	
	<!-- Result not found image -->
	<c:if test="${empty indexProductBasicDisaplays}">
		<br><br><br><br><br><br><br>
		<img src="https://webmarketingschool.com/wp-content/uploads/2018/03/nojobsfound.png"
		       			style="display: block; margin-left: auto; margin-right: auto;" width="425" height="150" />
	</c:if>
	
	<!-- table for listing all products -->
	<table style="width:70%;margin-left:15%;margin-right:15%;">
	  <tbody>
	  	<tr>
		     <c:forEach items="${indexProductBasicDisaplays}" var="ipbd" varStatus="loop">
		       <c:if test="${not loop.first and loop.index % 3 == 0}"> 
		            		</tr>
		            <tr>
		       </c:if>
		       	<td style="padding: 5px 10px 5px 5px; align=center">
		       		<img src="${ipbd.thumbnail}" onerror="this.src='http://www.wellesleysocietyofartists.org/wp-content/uploads/2015/11/image-not-found.jpg'"
		       			width="200" height="200" /> <br>
					${ipbd.productName}<br>
					 ${ipbd.price}<br>
		             ${ipbd.thumbnail}<br>
		       	</td>
		     </c:forEach>
	     </tr>
	  </tbody>
	</table>
</body>
</html>