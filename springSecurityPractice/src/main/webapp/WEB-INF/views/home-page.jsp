<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home page</title>
</head>
<body>
	<h1>Hi ${username }</h1>
	<h4>Roles Assigned : ${roles }</h4>

	<sec:authorize access='hasAuthority("Trainer")'>

		<a href="trainer">Show Trainer's dashboard.</a>
	<br>
	</sec:authorize>
	<sec:authorize access='hasAuthority("Coder")'>
		<a href="coder">Show Coder's dashboard.</a>
	</sec:authorize>
	
	
	<br>
	<br><br>
	
	<a href="/changePassword">Change Password</a>
	<br>
	<a href="/deleteUser?username=${username }">Delete Account</a>
	<br>

	<p>Spring security.....</p>

	<a href="logout"><input type="button" value="Logout"> </a>
</body>
</html>