<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Academies List</title>
		<link href="../../webjars/bootstrap/4.0.0/css/bootstrap.min.css"
			rel="stylesheet" />
		<script src="../../webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
		<script src="../../webjars/jquery/3.0.0/js/jquery.min.js"></script>
	</head>
	<body>
	<jsp:include page="header.jsp" />
		<div class="container">
			<table class="table table-bordered" >
				<thead>
					<tr>
						<th scope="col" >Fiscal Code</th>
						<th scope="col">First Name</th>
						<th scope="col">Last Name</th>
						<th scope="col">Birth Date</th>
						<th colspan="2"  style="align-content: center;">Actions:</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${students}" var="student">
						<tr>
							<td>${student.fiscalCode}</td>
							<td>${student.firstName}</td>
							<td>${student.lastName}</td>
							<td>${student.birthDate}</td>
							<td align="center">
								<spring:url value="/students/update/${student.fiscalCode}" var="editURL"/>
								<a class="btn btn-info" href="${editURL}" role="button">
									<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
  										<path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
 										<path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
									</svg>	Update</a>
							</td>
							<td align="center">
								<spring:url value="/students/remove/${student.fiscalCode}/all" var="deleteURL"/>
								<a class="btn btn-danger" href="${deleteURL}" role="button">
								<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-dash-fill" viewBox="0 0 16 16">
  									<path fill-rule="evenodd" d="M11 7.5a.5.5 0 0 1 .5-.5h4a.5.5 0 0 1 0 1h-4a.5.5 0 0 1-.5-.5z"/>
  									<path d="M1 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H1zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
								</svg>	Delete</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<spring:url value="/student" var="addURL"/>
			<a class="btn btn-success" href="${addURL}" role="button">
				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-plus-fill" viewBox="0 0 16 16">
  					<path d="M1 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H1zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
  					<path fill-rule="evenodd" d="M13.5 5a.5.5 0 0 1 .5.5V7h1.5a.5.5 0 0 1 0 1H14v1.5a.5.5 0 0 1-1 0V8h-1.5a.5.5 0 0 1 0-1H13V5.5a.5.5 0 0 1 .5-.5z"/>
				</svg>	Add New Student
			</a>
		</div>
	</body>
	<jsp:include page="footer.jsp" />
</html>