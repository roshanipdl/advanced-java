<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Enter Your Name</title>
</head>
<body>
    <h2>Enter Your First Name</h2>
    <form action="ProcessName.jsp" method="post">
        <input type="text" name="FirstName" maxlength="50" required placeholder="First Name" />
        <input type="submit" value="Submit" />
    </form>
</body>
</html> 