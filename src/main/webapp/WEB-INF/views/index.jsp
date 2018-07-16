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
<table border="1">
  <tbody>
     <c:forEach items="${indexProductBasicDisaplays}" var="ipbd">
       <tr>
         <td>${ipbd.productName}</td>
         <td>${ipbd.price}</td>
         <td>${ipbd.thumbnail}</td>
       </tr>
     </c:forEach>
  </tbody>
</table>
</html>