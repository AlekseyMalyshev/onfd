<%@page language="java" isErrorPage="true"
        contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link type="text/css" rel="stylesheet" href="/css/onfd.css">
        <title>OnFD Error</title>
    </head>
    <body>
        <h2>You are not authorized to view this page</h2>
        <%=request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI)%>
        <p>
            Try to go <a href="javascript:window.history.back()">back</a> or <a
                href="/logout">login</a>
        </p>
        <c:set var="message">${requestScope['javax.servlet.error.message']}</c:set>
            <p>
                Please <a
                    href="mailto:aleksey.malyshev@mail.ru?subject=Web site error&body=${code}${location}${message}">drop
                us an e-mail</a>, if you think you happen to be here by mistake.
        </p>
    </body>
