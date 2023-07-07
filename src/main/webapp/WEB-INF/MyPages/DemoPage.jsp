<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sporty Shoes</title>
</head>
<body>
<jsp:include page="components/header.jsp" />
<jsp:include page="components/topbar.jsp" />

<table border=1 cellspacing=2 cellpadding=4>
 	<tr>
 		<td><b>Product</b></td>
 		<td><b>Price</b></td>
 		<td><b>Category</b></td> 
 		<td></td>
 	</tr>
 	<c:forEach items="${list}" var="item">
 	  	<tr>
	 		<td>${item.name }</td>
 			<td>${item.price }</td>
 			<td>${mapCats.get(item.ID)}</td>
 	  		<td>
 	  			<a href="cartadditem?id=${item.ID}">Add To Cart</a>
 	  		</td>
 	  	</tr>
 	  </c:forEach>
</table>

<jsp:include page="components/footer.jsp"/>
</body>
</html>