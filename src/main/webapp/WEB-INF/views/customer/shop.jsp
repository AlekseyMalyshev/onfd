<%@page language="java" contentType="text/html; charset=ISO-8859-1"
        pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link type="text/css" rel="stylesheet" href="/css/onfd.css">
        <title>OnFD Customer</title>
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
                        <th class="label">Sizes</th>
                        <th class="measures">Fit</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${products.size() == 0}">
                            <tr>
                                <td colspan="5" align="right">We were not able to find any products for you.</td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="prod" items="${products}">
                                <tr>
                                    <td class="label">${prod.manufacturer.name}</td>
                                    <td class="measure"><c:choose>
                                            <c:when test="${0 == prod.type.ordinal()}">T-Shirt</c:when>
                                            <c:when test="${1 == prod.type.ordinal()}">Shorts</c:when>
                                        </c:choose>
                                    <td class="label">${prod.name}</td>
                                    <td class="label"><c:forEach var="psize" items="${prod.sizes}">
                                            <a class="${psize.customerFit}" title="${psize.customerFit}" href="/customer/fitting/${psize.id}">${psize.name}</a>
                                        </c:forEach></td>
                                    <td class="label">
                                        <c:set var="fit" value="fit_${prod.id}" />
                                        <select id="${fit}" name="${fit}" onchange="changeFit(${prod.id}, ${fit}.value)">
                                            <c:forEach items="${user.measurements}" var="measure">
                                                <option value="${measure.id}">${measure.name}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
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
