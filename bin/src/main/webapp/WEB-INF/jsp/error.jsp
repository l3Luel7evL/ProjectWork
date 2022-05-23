<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>ERROR</title>
		<link href="../../webjars/bootstrap/4.0.0/css/bootstrap.min.css"
			rel="stylesheet" />
		<script src="../../webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
		<script src="../../webjars/jquery/3.0.0/js/jquery.min.js"></script>
	</head>
	<body>
		<jsp:include page="header.jsp"/>
		<h2 style="color: red"> <%=exception.getMessage()%></h2>
	</body>
	<jsp:include page="footer.jsp"/>
</html>