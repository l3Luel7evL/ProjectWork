<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="h-100">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Relate Student &amp; Academy</title>
		<link href="../../webjars/bootstrap/4.0.0/css/bootstrap.min.css"
			rel="stylesheet" />
		<script src="../../webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
		<script src="../../webjars/jquery/3.0.0/js/jquery.min.js"></script>
	</head>
	<body class="h-100">
		<jsp:include page="header.jsp" />
	
		<div class="container h-100">
			<div class="row h-100 justify-content-center align-items-center">
				<div class="col-10 col-md-8 col-lg-6">
					<div class="container">
						<spring:url value="/relate/students" var="addURL" />
						<h2>Add Student</h2>
	
						<form:form modelAttribute="student" method="post"
							action="${addURL}" cssClass="form">
							
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
							<label>Birth Date</label>
							<form:input path="birthDate" type="date" cssClass="form-control" id="birthDate" required="required"/>
						</div>
					 	
					 	<div class="form-group">
							<label>Academies</label>
							<form:select path="academies" cssClass="form-control" id="academies" required="required">
								<form:options items="${academies}" itemLabel="title"/>
							</form:select>
						</div>
					 	
						<button type="submit" class="btn btn-success">
							<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-left-right" viewBox="0 0 16 16">
		  						<path fill-rule="evenodd" d="M1 11.5a.5.5 0 0 0 .5.5h11.793l-3.147 3.146a.5.5 0 0 0 .708.708l4-4a.5.5 0 0 0 0-.708l-4-4a.5.5 0 0 0-.708.708L13.293 11H1.5a.5.5 0 0 0-.5.5zm14-7a.5.5 0 0 1-.5.5H2.707l3.147 3.146a.5.5 0 1 1-.708.708l-4-4a.5.5 0 0 1 0-.708l4-4a.5.5 0 1 1 .708.708L2.707 4H14.5a.5.5 0 0 1 .5.5z"/>
							</svg>	Relate Student and Academies
						</button>
					</form:form>
					</div>
				</div>
			</div>
		</div>
	</body>
	
	<jsp:include page="footer.jsp" />
</html>