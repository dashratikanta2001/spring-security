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
<title>Change Password</title>
</head>
<body>

	<div align="center">
	<c:if test="${param.invalidPassword !=null}">
	<i style="color: red;">Invalid Old Password</i>
	</c:if>
	<c:if test="${param.passwordNotMatch !=null}">
	<i style="color: red;">New password and confirm password does not match</i>
	</c:if>
	
		<h1>Reset Password</h1>
		<form:form action="save-password" method="POST" modelAttribute="password-chng">
			<label> Old Password: </label>
			<form:input path="oldPassword" />
			<br>
			<label> New Password: </label>
			<form:input path="newPassword" />
			<br>
			<label> Conform Password: </label>
			<form:input path="confirmPassword" />
			<br>
			<input type="submit" value="Change Password">
		</form:form>
	</div>

</body>
</html>