<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>    
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sporty Shoes - Purchase Confirmation</title>
</head>
<body>
<jsp:include page="/WEB-INF/MyPages/components/header.jsp" />
<jsp:include page="/WEB-INF/MyPages/components/topbar.jsp" />


<br><br>
Your Order worth ${cartValue} has been completed successfully.<br><br>
Check your <a href="memberpurchases">Order History</A>
 
<jsp:include page="/WEB-INF/MyPages/components/footer.jsp" />
</body>
</html>