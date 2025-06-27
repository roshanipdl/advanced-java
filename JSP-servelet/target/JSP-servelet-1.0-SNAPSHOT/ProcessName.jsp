<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Process Name</title>
</head>
<body>
<%
    String firstName = request.getParameter("FirstName");
    if (firstName != null && !firstName.trim().isEmpty()) {
%>
    <h2>Hello, <%= firstName %>!</h2>
<%
    } else {
%>
    <h2>No name was entered. Please go back and try again.</h2>
<%
    }
%>
</body>
</html> 