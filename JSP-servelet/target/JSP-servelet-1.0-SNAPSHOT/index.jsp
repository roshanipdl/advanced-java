<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Find Greatest Number</title>
</head>
<body>
    <h2>Enter Three Numbers</h2>
    <form action="greatest" method="post">
        <input type="text" name="num1" placeholder="First Number" required value="<%= request.getParameter("num1") != null ? request.getParameter("num1") : "" %>" /><br/>
        <input type="text" name="num2" placeholder="Second Number" required value="<%= request.getParameter("num2") != null ? request.getParameter("num2") : "" %>" /><br/>
        <input type="text" name="num3" placeholder="Third Number" required value="<%= request.getParameter("num3") != null ? request.getParameter("num3") : "" %>" /><br/>
        <input type="submit" value="Find Greatest" />
    </form>
    <% 
        String result = (String) request.getAttribute("result");
        if (result != null) {
    %>
        <h3><%= result %></h3>
    <% 
        }
    %>
</body>
</html> 