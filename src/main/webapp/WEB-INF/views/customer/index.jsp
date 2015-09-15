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
                        <th class="label">Name</th>
                        <th class="measures">Bust</th>
                        <th class="measures">Shoulder</th>
                        <th class="measures">Waist</th>
                        <th class="measures">Armhole</th>
                        <th class="measures">Preferred Fit</th>
                        <th class="measures">Added</th>
                        <th class="measures"></th>
                    </tr>
                </thead>
                <tfoot>
                    <tr>
                        <td colspan="8" align="right"><a
                                href="/customer/measurements/add/">Add</a></td>
                    </tr>
                </tfoot>
                <tbody>
                    <c:choose>
                        <c:when test="${user.measurements.size() == 0}">
                            <tr>
                                <td colspan="8" align="right">Currently you have no
                                    measurements stored.</td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <c:set var="units" value="\"" />
                            <c:forEach var="measure" items="${user.measurements}"
                                       varStatus="iter">
                                <tr id="${measure.id}">
                                    <td class="label">${measure.name}</td>
                                    <td class="measure">${measure.bust}${units}</td>
                                    <td class="measure">${measure.shoulder}${units}</td>
                                    <td class="measure">${measure.waist}${units}</td>
                                    <td class="measure">${measure.armhole}${units}</td>
                                    <td class="measure"><c:choose>
                                            <c:when test="${0 == measure.fit.ordinal()}">Tight</c:when>
                                            <c:when test="${1 == measure.fit.ordinal()}">Normal</c:when>
                                            <c:when test="${2 == measure.fit.ordinal()}">Loose</c:when>
                                        </c:choose></td>
                                    <td class="date"><fmt:formatDate value="${measure.addedDate}"
                                                    pattern="MM/dd/yyyy" /></td>
                                    <td class="measure"><a
                                            href="/customer/measurements/edit/${measure.id}">Edit</a> <a
                                            href="/customer/measurements/delete/${measure.id}">Del</a></td>
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
