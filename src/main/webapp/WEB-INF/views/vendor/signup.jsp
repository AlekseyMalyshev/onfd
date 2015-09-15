<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet" href="/css/onfd.css">
<title>Add OnFD vendor</title>
</head>
<body>
	<%@include file="../nav_bar.jsp"%>
	<div></div>
	<center>
		<p>Welcome, New Vendor.</p>
		<form:form action="/vendor/signup" method="post" name="signup"
			id="signup" modelAttribute="user"
			onsubmit="validateDetails(document.signup); return false;">
			<table class="edit">
				<c:if test="${error != null}">
					<tr>
						<td colspan="2">
							<p style="color: red">An error occurred while processing
								request. Please try again.</p>
						</td>
					</tr>
				</c:if>
				<tr>
					<td colspan="2">Please enter your credentials below.</td>
				</tr>
				<tr>
					<td class="label"><label for="login">Login</label></td>
					<td><form:input path="login" class="name"
							onchange="checkLogin(value)" /></td>
				</tr>
				<tr>
					<td class="label"><label for="pass">Password</label></td>
					<td><form:input path="password" type="password" class="pass" /></td>
				</tr>
				<tr>
					<td class="label"><label for="pass_">Password</label></td>
					<td><input name="pass_" id="pass_" type="password" class="pass" /></td>
				</tr>
				<tr>
					<td class="label"><label for="name_first">First Name</label></td>
					<td><form:input path="nameFirst" class="name" /></td>
				</tr>
				<tr>
					<td class="label"><label for="name_last">Last Name</label></td>
					<td><form:input path="nameLast" class="name" /></td>
				</tr>
				<tr>
					<td class="label"><label for="email">e-mail</label></td>
					<td><form:input path="email" class="name" /></td>
				</tr>
				<tr>
					<td class="label"><label for="company">Company</label></td>
					<td><form:input path="company" class="name" /></td>
				</tr>
				<tr>
					<td colspan="2" align="right"><a href="/login">Cancel</a> <a
						href="javascript:validateDetails(document.signup)">Confirm</a></td>
				</tr>
			</table>
			<input type="submit" style="display: none" />
		</form:form>
	</center>
	<script type="text/javascript" src="/js/utilities.js"></script>
</body>
</html>
