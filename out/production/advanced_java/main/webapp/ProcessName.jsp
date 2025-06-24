<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Process Name</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            max-width: 500px;
            margin: 20px auto;
            padding: 20px;
        }
        .result {
            margin-top: 20px;
            padding: 10px;
            background-color: #f0f0f0;
        }
    </style>
</head>
<body>
    <%
    if (request.getMethod().equals("POST")) {
        String firstName = request.getParameter("firstName");
        if (firstName != null && !firstName.trim().isEmpty()) {
    %>
            <div class="result">
                <h3>Welcome!</h3>
                <p>Hello, <strong><%= firstName %></strong>!</p>
            </div>
    <%
        } else {
    %>
            <div class="result" style="color: red;">
                <p>Please enter a valid name.</p>
            </div>
    <%
        }
    }
    %>
    
    <p><a href="nameForm.html">Back to Form</a></p>
</body>
</html> 