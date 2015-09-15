<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet" href="/css/onfd.css">
<title>OnFD Select Manufacturer</title>
</head>
<body>
	<%@include file="nav_bar.jsp"%>
	<center>
		<form:form action="/vendor/product/add" method="post" name="manuf"
			modelAttribute="manuf">
			<table align="center">
				<tr>
					<td class="label"><label for="manufs">Select
							Manufacturer</label></td>
				</tr>
				<tr>
					<td><form:input class="manuf" path="name"
							onchange="document.manuf.id.value = -1" /></td>
				</tr>
				<tr>
					<td><select class="manuf" size="5" id="id" name="id"
						onchange="updateManufName(document.manuf)">
							<c:forEach items="${manufs}" var="manuf">
								<option value="${manuf.id}">${manuf.name}</option>
							</c:forEach>
					</select>
				</tr>
				<tr>
					<td colspan="2" align="right"><a href="/vendor">Cancel</a> <a
						href="javascript:document.manuf.submit()">Confirm</a></td>
				</tr>
			</table>
			<input type="submit" style="display: none" />
		</form:form>
	</center>
	<script type="text/javascript" src="/js/utilities.js"></script>
</body>
</html>
