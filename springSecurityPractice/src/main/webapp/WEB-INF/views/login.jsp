<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>

	<!-- Write some code to handle the invalid cridentials -->
	<c:if test="${param.error !=null}">
	<i style="color: red;">Invalid login or password</i>
	</c:if>
	
	<!-- Write some code to handle the logout -->
	<c:if test="${param.logout !=null}">
	<i style="color: green;">You are successfully loged out ! Signin again.</i>
	</c:if>
	
	<h1>My custom login page</h1>
	
	<form:form>
		Username: <input type="text" name="username">
		<br>
		Password: <input type="password" name="password">
		<br>
		<input type="submit" value="Login">
	</form:form>
</body>
</html>