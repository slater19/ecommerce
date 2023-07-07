<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>     
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sporty Shoes - Dashboard</title>
</head>
<body>
<jsp:include page="components/header.jsp" />
<jsp:include page="components/topbar.jsp" />


<h1>${pageTitle}</h1>
<jsp:include page="components/footer.jsp"/>
</body>
</html>