<%@page import="com.onfd.model.Measurement"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
        pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link type="text/css" rel="stylesheet" href="/css/onfd.css">
        <title>OnFD Measurements</title>
    </head>
    <body>
        <%@include file="nav_bar.jsp"%>
        <div></div>
        <center>
            <c:set var="units" value="\"" />
            <form:form action="/customer/measurements" method="post"
                       name="measurement" id="measurement" modelAttribute="measure">
                <table class="edit">
                    <thead>
                        <tr>
                            <td class="label"><label for="name">Name</label></td>
                            <td class="label"><label for="bust">Bust</label></td>
                            <td class="label"><label for="shoulder">Shoulder</label></td>
                            <td class="label"><label for="waist">Waist</label></td>
                            <td class="label"><label for="armhole">Armhole</label></td>
                            <td class="label"><label for="fit">Preferred Fit</label></td>
                        </tr>
                    </thead>
                    <tfoot>
                        <tr>
                            <td colspan="6" align="right"><a href="/customer">Cancel</a> <a
                                    href="javascript:document.measurement.submit()">Confirm</a></td>
                        </tr>
                    </tfoot>
                    <tbody>
                        <tr>
                            <td><form:input class="label" path="name" /></td>
                            <td><form:input class="measure" path="bust" />${units}</td>
                            <td><form:input class="measure" path="shoulder" />${units}</td>
                            <td><form:input class="measure" path="waist" />${units}</td>
                            <td><form:input class="measure" path="armhole" />${units}</td>
                            <td><c:set var="values" value="<%=Measurement.Fit.values()%>" scope="session" />
                                <form:select path="fit">
                                    <c:forTokens items="Tight,Normal,Loose" delims="," var="item" varStatus="iter">
                                        <form:option value="${values[iter.index]}" label="${item}" />
                                    </c:forTokens>
                                </form:select></td>
                        </tr>
                    </tbody>
                </table>
                <input type="submit" style="display: none" />
            </form:form>
        </center>
        <script type="text/javascript" src="/js/utilities.js"></script>
    </body>
</html>
