<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<link href="./resources/css/bootstrap.css" rel="stylesheet">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Index</title>
</head>
<body>
	<h3>Index Page</h3>
</body>
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
</html>