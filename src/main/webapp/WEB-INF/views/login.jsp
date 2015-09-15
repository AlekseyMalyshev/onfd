<%@page language="java" contentType="text/html; charset=ISO-8859-1"
        pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link type="text/css" rel="stylesheet" href="/css/onfd.css">
        <title>OnFD Login</title>
    </head>
    <body>
        <%@include file="nav_bar.jsp"%>
        <div></div>
        <center>
            <form action="/login" method="post" name="login">
                <table class="edit">
                    <c:if test="${param.logout != null}">
                        <tr>
                            <td style="color: blue" colspan="2">You successfully logged
                                out.</td>
                        </tr>
                    </c:if>
                    <c:out value="${SPRING_SECURITY_403_EXCEPTION}" />
                    <tr>
                        <c:choose>
                            <c:when test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
                                <td style="color: crimson" colspan="2">Bad Credentials.
                                    Please Try again.</td>
                                </c:when>
                                <c:otherwise>
                                <td colspan="2">Please enter your credentials below.</td>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                    <tr>
                        <td class="label"><label for="username">Login</label></td>
                        <td><input id="user" name="user" class="name" /></td>
                    </tr>
                    <tr>
                        <td class="label"><label for="password">Password</label></td>
                        <td><input id="pass" name="pass" type="password" class="pass" /></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="right"><a
                                href="javascript:document.login.submit()">Confirm</a></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="right"><a href="customer/signup">
                                Register as a customer</a></td>
                    <tr>
                        <td colspan="2" align="right"><a href="vendor/signup">
                                Register as a vendor</a></td>
                    </tr>
                </table>
                <input type="hidden" name="${_csrf.parameterName}"
                       value="${_csrf.token}" /> <input type="submit" style="display: none" />
            </form>
        </center>
    </body>
</html>
