<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet" href="/css/onfd.css">
<title>OnFD Vendor</title>
</head>
<body>
	<%@include file="nav_bar.jsp"%>
	<center>
		<table class="measures">
			<thead>
				<tr>
					<th class="label">Manufacturer</th>
					<th class="measures">Type</th>
					<th class="measures">Name</th>
					<th class="measures">Gender</th>
					<th class="measures">Sizes</th>
					<th class="measures"></th>
					<th class="measures"></th>
				</tr>
			</thead>
			<tfoot>
				<tr>
					<td colspan="6" align="right"><a title="Add new product" href="/vendor/product/add/">Add</a></td>
				</tr>
			</tfoot>
			<tbody>
				<c:choose>
					<c:when test="${user.products.size() == 0}">
						<tr>
							<td colspan="6" align="right">Currently you have no products
								defined.</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="prod" items="${user.products}">
							<tr>
								<td class="label">${prod.manufacturer.name}</td>
								<td class="measure"><c:choose>
										<c:when test="${0 == prod.type.ordinal()}">T-Shirt</c:when>
										<c:when test="${1 == prod.type.ordinal()}">Shorts</c:when>
									</c:choose></td>
								<td class="label">${prod.name}</td>
								<td class="measure"><c:choose>
										<c:when test="${0 == prod.gender.ordinal()}">Female</c:when>
										<c:when test="${1 == prod.gender.ordinal()}">Male</c:when>
										<c:when test="${2 == prod.gender.ordinal()}">Unisex</c:when>
									</c:choose></td>
								<td class="label"><c:forEach var="psize" items="${prod.sizes}">
										<a title="Edit size" href="/vendor/product/size/${prod.id}/${psize.productSize}">${psize.name}</a>
									</c:forEach><a title="Add new size" href="/vendor/product/size/${prod.id}/new">Add</a></td>
								<td class="date"><fmt:formatDate value="${prod.addedDate}"
										pattern="MM/dd/yyyy" /></td>
								<td class="measure"><a title="Edit product"
									href="/vendor/product/edit/${prod.id}">Edit</a> <a title="Delete product"
									href="/vendor/product/delete/${prod.id}">Del</a></td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</center>
	<script type="text/javascript" src="/js/utilities.js"></script>
</body>
</html>
