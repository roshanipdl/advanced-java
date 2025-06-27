package com.example;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class AddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int num1 = Integer.parseInt(request.getParameter("num1"));
            int num2 = Integer.parseInt(request.getParameter("num2"));
            int sum = num1 + num2;
            request.setAttribute("num1", num1);
            request.setAttribute("num2", num2);
            request.setAttribute("sum", sum);
            RequestDispatcher rd = request.getRequestDispatcher("sqServlet");
            rd.forward(request, response);
        } catch (Exception e) {
            response.getWriter().println("Invalid input! Please enter valid numbers.");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
} 