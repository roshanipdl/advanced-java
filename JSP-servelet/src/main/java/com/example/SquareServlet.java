package com.example;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class SquareServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Object sumObj = request.getAttribute("sum");
        Object num1Obj = request.getAttribute("num1");
        Object num2Obj = request.getAttribute("num2");
        response.setContentType("text/html");
        if (sumObj != null && num1Obj != null && num2Obj != null) {
            int sum = (int) sumObj;
            int num1 = (int) num1Obj;
            int num2 = (int) num2Obj;
            int square = sum * sum;
            response.getWriter().println("<h2>Original numbers: " + num1 + " and " + num2 + "</h2>");
            response.getWriter().println("<h2>Sum: " + sum + "</h2>");
            response.getWriter().println("<h2>Square of the sum: " + square + "</h2>");
        } else {
            response.getWriter().println("Missing input values.");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
} 