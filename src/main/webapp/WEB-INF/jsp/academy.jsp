<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="h-100">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Or Update Academy</title>
<link href="../../webjars/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="css/global.css">

<script src="../../webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="../../webjars/jquery/3.0.0/js/jquery.min.js"></script>

</head>
<body class="h-100">
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>
	<jsp:include page="header.jsp" />

	<div class="container h-75"
		style="margin-bottom: 10px; margin-top: 10px;">
		<div class="row h-75 justify-content-center align-items-center">
			<div class="col-10 col-md-8 col-lg-6">
				<spring:url value="/academies" var="addURL" />
				<h2>Add Academy</h2>
				<form class="form-container">
					<form:form modelAttribute="academy" method="post"
						action="${addURL}" cssClass="form">
						<c:if test="${academy.code==null}">
							<div class="form-group">
								<label>Code</label>
								<form:input path="code" cssClass="form-control" id="code"
									required="required" />
							</div>
						</c:if>

						<c:if test="${academy.code!=null}">
							<form:hidden path="code" />
						</c:if>

						<div class="form-group">
							<label>Title</label>
							<form:input path="title" cssClass="form-control" id="title"
								required="required" />
						</div>

						<div class="form-group">
							<label>Location</label>
							<form:input path="location" cssClass="form-control" id="location"
								required="required" />
						</div>

						<div class="form-group">
							<label>Start Date</label>
							<form:input path="startDate" type="date" cssClass="form-control"
								id="startDate" required="required" />
						</div>

						<div class="form-group">
							<label>End Date</label>
							<form:input path="endDate" type="date" cssClass="form-control"
								id="endDate" required="required" />
						</div>

						<button type="submit" class="btn btn-success">
							<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
								fill="currentColor" class="bi bi-plus-square-fill"
								viewBox="0 0 16 16"> <path
								d="M2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2zm6.5 4.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3a.5.5 0 0 1 1 0z" />
							</svg>
							Add or Update Academy
						</button>
					</form:form>
				</form>
			</div>
		</div>
	</div>
</body>
<jsp:include page="footer.jsp" />
</html>