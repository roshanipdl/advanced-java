<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add and Square Numbers</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            max-width: 500px;
            margin: 20px auto;
            padding: 20px;
        }
        .form-group {
            margin-bottom: 15px;
        }
        input[type="number"] {
            padding: 5px;
            width: 200px;
        }
        input[type="submit"] {
            padding: 8px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
        }
        .result {
            margin-top: 20px;
            padding: 10px;
            background-color: #f0f0f0;
        }
    </style>
</head>
<body>
    <h2>Add and Square Numbers</h2>
    
    <form method="post">
        <div class="form-group">
            <label for="num1">First Number:</label><br>
            <input type="number" id="num1" name="num1" required>
        </div>
        
        <div class="form-group">
            <label for="num2">Second Number:</label><br>
            <input type="number" id="num2" name="num2" required>
        </div>
        
        <input type="submit" value="Add and Square">
    </form>

    <%
    if (request.getMethod().equals("POST")) {
        try {
            int num1 = Integer.parseInt(request.getParameter("num1"));
            int num2 = Integer.parseInt(request.getParameter("num2"));
            
            int sum = num1 + num2;
            int square = sum * sum;
    %>
            <div class="result">
                <h3>Result:</h3>
                <p>The sum of <%= num1 %> and <%= num2 %> is: <strong><%= sum %></strong></p>
                <p>The square of the sum is: <strong><%= square %></strong></p>
            </div>
    <%
        } catch (NumberFormatException e) {
    %>
            <div class="result" style="color: red;">
                <p>Please enter valid numbers.</p>
            </div>
    <%
        }
    }
    %>
</body>
</html> 