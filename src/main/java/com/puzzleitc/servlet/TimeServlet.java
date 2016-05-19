package com.puzzleitc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.ThreadContext;

@SuppressWarnings("serial")
@WebServlet("/")
public class TimeServlet extends HttpServlet {

    public static final Logger logger = LogManager.getFormatterLogger(TimeServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      ThreadContext.put("remoteAddr", req.getRemoteAddr());
      ThreadContext.put("url", req.getRequestURL().toString());
      ThreadContext.put("freeMem", String.valueOf(Runtime.getRuntime().freeMemory()));

      logger.info("Serving get request");

      resp.setContentType("text/html");
      PrintWriter writer = resp.getWriter();
      writer.println("<html><head><title>Time</title></head><body>");
      DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss 'UTC'XXX");
      Calendar cal = Calendar.getInstance();
      writer.println(dateFormat.format(cal.getTime()));
      writer.println("</body></html>");
      writer.close();

      ThreadContext.clearAll();
    }
 }
