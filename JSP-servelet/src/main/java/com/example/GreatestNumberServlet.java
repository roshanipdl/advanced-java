package com.example;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class GreatestNumberServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int num1 = Integer.parseInt(request.getParameter("num1"));
            int num2 = Integer.parseInt(request.getParameter("num2"));
            int num3 = Integer.parseInt(request.getParameter("num3"));

            int greatest = Math.max(num1, Math.max(num2, num3));
            request.setAttribute("result", "The greatest number is: " + greatest);
        } catch (Exception e) {
            request.setAttribute("result", "Invalid input! Please enter valid numbers.");
        }
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
} 