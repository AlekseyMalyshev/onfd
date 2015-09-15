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
        <h2>Opps... something went wrong.</h2>
        <c:set var="code"><%=request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE)%></c:set>
        <c:set var="location"><%=request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI)%></c:set>
        <c:set var="message">${requestScope['javax.servlet.error.message']}</c:set>
            <div>
                <p>
                    Try to go <a href="javascript:window.history.back()">back</a> or <a
                        href="/logout">login</a>
                </p>
            </div>
            <p>Error Code: ${code}</p>
        <p>Page Location: ${location}</p>
        <c:if test="${message != ''}">
            <pre>
			<code>
                    <c:out value="${message}" escapeXml="true" />
			</code>
            </pre>
        </c:if>
    </body>