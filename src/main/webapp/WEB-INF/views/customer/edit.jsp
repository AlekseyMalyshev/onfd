<%@page import="com.onfd.model.Customer"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
        pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link type="text/css" rel="stylesheet" href="/css/onfd.css">
        <title>OnFD Edit Customer Details</title>
    </head>
    <body>
        <%@include file="nav_bar.jsp"%>
        <center>
            <form:form action="/customer/edit" method="post" name="edit" id="edit"
                       modelAttribute="user"
                       onsubmit="validateUser(document.edit); return false;">
                <table class="edit">
                    <c:if test="${error != null}">
                        <tr>
                            <td colspan="2">
                                <p style="color: red">Could not update user.</p>
                            </td>
                        </tr>
                    </c:if>
                    <tr>
                        <td colspan="2">Your details</td>
                    </tr>
                    <tr>
                        <td class="label"><label for="nameFirst">First Name</label></td>
                        <td><form:input class="name" path="nameFirst" /></td>
                    </tr>
                    <tr>
                        <td class="label"><label for="nameLast">Last Name</label></td>
                        <td><form:input class="name" path="nameLast" /></td>
                    </tr>
                    <tr>
                        <td class="label"><label for="email">e-mail</label></td>
                        <td><form:input class="name" path="email" />
                            <form:errors class="error" path="email" /></td>
                    </tr>
                    <tr>
                        <td class="label"><label for="gender">Gender</label></td>
                        <td><c:set var="values" value="<%=Customer.Gender.values()%>" scope="session" />
                            <c:forTokens items="Female,Male,n/a" delims="," var="item" varStatus="iter">
                                <form:radiobutton path="gender" value="${values[iter.index]}" label="${item}" />
                            </c:forTokens></td>
                    </tr>
                    <tr>
                        <td class="label"><label for="phone">Phone #</label></td>
                        <td><form:input class="name" path="phone"
                                    onchange="value = formatNumber(value)" /></td>
                    </tr>
                    <tr>
                        <td class="label"><label for="address">Street Address</label></td>
                        <td><form:input class="name" path="address" /></td>
                    </tr>
                    <tr>
                        <td class="label"><label for="city">City</label></td>
                        <td><form:input class="name" path="city" /></td>
                    </tr>
                    <tr>
                        <td class="label"><label for="zipcode">Zip Code</label></td>
                        <td><form:input class="name" path="zipcode" /></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="right"><a href="/customer">Cancel</a> <a
                                href="javascript:validateUser(document.edit)">Confirm</a></td>
                    </tr>
                </table>
                <input type="submit" style="display: none" />
            </form:form>
        </center>
        <script type="text/javascript" src="/js/utilities.js"></script>
    </body>
</html>
