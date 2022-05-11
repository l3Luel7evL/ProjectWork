<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="h-100">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Add Or Update Student</title>
		<link href="../../webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" />
		<script src="../../webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
		<script src="../../webjars/jquery/3.0.0/js/jquery.min.js"></script>
	</head>
	<body class="h-100">	
	<div class="container h-100" >
		<div class="row h-100 justify-content-center align-items-center"> 
		<div class="col-10 col-md-8 col-lg-6">
		<jsp:include page="header.jsp" />
		<div class="container">
			<spring:url value="/students" var="addURL"/>
			<h2>Add Student</h2>

			<form:form modelAttribute="student" method="post" action="${addURL}" cssClass="form">
			 
			 <c:if test="${student.fiscalCode==null}" >
				<div class="form-group">
					<label>Fiscal Code</label>
					<form:input path="fiscalCode" cssClass="form-control" id="fiscalCode" required="required"/>
				</div>
			 </c:if>
			 <c:if test="${student.fiscalCode!=null}" >
			 	<form:hidden path="fiscalCode" />
			 </c:if>
				<div class="form-group">
					<label>First Name</label>
					<form:input path="firstName" cssClass="form-control" id="firstName" required="required"/>
				</div>
				<div class="form-group">
					<label>Last Name</label>
					<form:input path="lastName" cssClass="form-control" id="lastName" required="required"/>
				</div>
				<div class="form-group">
					<label>Age</label>
					<form:input path="age" cssClass="form-control" id="age" required="required"/>
				</div>
				<button type="submit" class="btn btn-success">Add or Update Student</button>
			</form:form>
		</div>
	</body>
	<jsp:include page="footer.jsp" />
</html>