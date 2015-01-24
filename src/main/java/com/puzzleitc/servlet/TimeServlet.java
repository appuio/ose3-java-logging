package com.puzzleitc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/")
public class TimeServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.println("<html><head><title>Time</title></head><body>");
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ssXXX");
        Calendar cal = Calendar.getInstance();
        writer.println(dateFormat.format(cal.getTime()));
        writer.println("</body></html>");
        writer.close();
     }
 }
