<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign up</title>
</head>
<body>

	<h1>Sign up here !</h1>

	<form:form action="process-signup" method="POST" modelAttribute="signupDto">
		<!-- username -->
	Username :
	<form:input path="username" />
		<br>
		<!-- password -->
	Password :
	<form:password path="password" />
		<br>

		<input type="submit" value="Signup">
	</form:form>
</body>
</html>