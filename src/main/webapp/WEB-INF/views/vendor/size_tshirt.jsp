<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet" href="/css/onfd.css">
<title>OnFD Product Sizes</title>
</head>
<body>
	<%@include file="nav_bar.jsp"%>
	<center>
		<form:form action="/vendor/product/size" method="post" name="psize"
			id="psize" modelAttribute="psize">
			<table class="measures">
				<thead>
					<tr>
						<th class="measures">${psize.product.manufacturer.name}</th>
						<th class="measures">
							<c:choose>
								<c:when test="${0 == psize.product.type.ordinal()}">T-Shirt</c:when>
								<c:when test="${1 == psize.product.type.ordinal()}">Shorts</c:when>
							</c:choose></th>
						<th class="measures">${psize.product.name}</th>
					</tr>
				</thead>
			</table>
			<table class="measures">
				<tfoot>
					<tr>
						<td colspan="4" align="right"><a
							href="/vendor/product/size/delete/${psize.product.id}/${psize.name}">Delete</a>
							<a href="/vendor">Cancel</a> <a
							href="javascript:document.psize.submit()">Confirm</a></td>
					</tr>
				</tfoot>
				<tbody>
					<c:set var="units" value="\"" />
					<tr>
						<td class="label"><label for="productSize">Size</label></td>
						<td><form:select onchange="changeSize(${psize.product.id}, productSize.value)" path="productSize">
								<form:options items="<%=com.onfd.model.TShirtSize.Size.values()%>" />
							</form:select></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td class="label"><label for="bust">Bust</label></td>
						<td><form:input class="measure" path="bust" />${units}</td>
						<td class="label"><label for="shoulder"></label>Shoulder</td>
						<td><form:input class="measure" path="shoulder" />${units}</td>
					</tr>
					<tr>
						<td class="label"><label for="waist">Waist</label></td>
						<td><form:input class="measure" path="waist" />${units}</td>
						<td class="label"><label for="armhole"></label>Armhole</td>
						<td><form:input class="measure" path="armhole" />${units}</td>
					</tr>
					<tr>
						<td class="label"><label for="frontLength">Front Length</label></td>
						<td><form:input class="measure" path="frontLength" />${units}</td>
						<td class="label"><label for="backLength"></label>Back Length</td>
						<td><form:input class="measure" path="backLength" />${units}</td>
					</tr>
					<tr>
						<td class="label"><label for="neckDepth">Neck Depth</label></td>
						<td><form:input class="measure" path="neckDepth" />${units}</td>
						<td class="label"><label for="neckWidth"></label>Neck Width</td>
						<td><form:input class="measure" path="neckWidth" />${units}</td>
					</tr>
					<tr>
						<td class="label"><label for="sleeveLength">Sleeve Length</label></td>
						<td><form:input class="measure" path="sleeveLength" />${units}</td>
						<td class="label"><label for="sleeveWidth"></label>Sleeve
							Width</td>
						<td><form:input class="measure" path="sleeveWidth" />${units}</td>
					</tr>
					<tr>
						<td class="label"><label for="hem">Hem</label></td>
						<td><form:input class="measure" path="hem" />${units}</td>
						<td></td>
						<td></td>
					</tr>
				</tbody>
			</table>
			<input type="submit" style="display: none" />
		</form:form>
	</center>
	<script type="text/javascript" src="/js/utilities.js"></script>
</body>
</html>
