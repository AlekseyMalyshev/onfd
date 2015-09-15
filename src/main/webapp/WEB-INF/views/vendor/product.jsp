<%@page import="com.onfd.model.Product"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet" href="/css/onfd.css">
<title>OnFD Product</title>
</head>
<body>
	<%@include file="nav_bar.jsp"%>
	<center>
		<form:form action="/vendor/product" method="post" name="product"
			modelAttribute="product">
			<table align="center">
				<tr>
					<td class="label"><label for="manufacturer">Manufacturer</label></td>
					<td><form:input readonly="true" class="name"
							path="manufacturer.name" /></td>
				</tr>
				<tr>
					<td class="label"><label for="type">Type</label></td>
					<td><c:set var="values" value="<%=Product.Type.values()%>" scope="session" />
						<form:select path="type">
							<c:forTokens items="T-Shirt,Pants" delims="," var="item" varStatus="iter">
								<form:option value="${values[iter.index]}" label="${item}" />
							</c:forTokens>
						</form:select></td>
				</tr>
				<tr>
					<td class="label"><label for="name">Name</label></td>
					<td><form:input class="name" path="name" /></td>
				</tr>
				<tr>
					<td class="label"><label for="gender">Gender</label></td>
					<td><c:set var="values" value="<%=Product.Gender.values()%>" scope="session" />
						<form:select path="gender">
							<c:forTokens items="Female,Male,Unisex" delims="," var="item" varStatus="iter">
								<form:option value="${values[iter.index]}" label="${item}" />
							</c:forTokens>
						</form:select></td>
				</tr>
				<tr>
					<td colspan="2" align="right"><a href="/vendor">Cancel</a> <a
						href="javascript:document.product.submit()">Confirm</a></td>
				</tr>
			</table>
			<input type="submit" style="display: none" />
		</form:form>
	</center>
	<script type="text/javascript" src="/js/utilities.js"></script>
</body>
</html>
