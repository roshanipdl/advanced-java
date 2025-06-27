<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Add Two Numbers</title>
</head>
<body>
    <h2>Add Two Numbers</h2>
    <form action="addServlet" method="post">
        <input type="text" name="num1" placeholder="First Number" required value="<%= request.getParameter("num1") != null ? request.getParameter("num1") : "" %>" /><br/>
        <input type="text" name="num2" placeholder="Second Number" required value="<%= request.getParameter("num2") != null ? request.getParameter("num2") : "" %>" /><br/>
        <input type="submit" value="Add and Square" />
    </form>
</body>
</html> 