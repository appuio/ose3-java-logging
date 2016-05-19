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
import org.apache.logging.log4j.message.MapMessage;
import org.apache.logging.log4j.ThreadContext;

@SuppressWarnings("serial")
@WebServlet("/")
public class TimeServlet extends HttpServlet {

    public static final Logger logger = LogManager.getFormatterLogger(TimeServlet.class);

    public static void main(String[] args) {
      ThreadContext.put("freeMem", String.valueOf(Runtime.getRuntime().freeMemory()));

      logger.info(map("a", "1", "b", "2"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      ThreadContext.put("url", req.getRequestURL().toString());
      ThreadContext.put("freeMem", String.valueOf(Runtime.getRuntime().freeMemory()));

      logger.info(map("url", req.getRequestURL().toString()));

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


    private static MapMessage map(String... data){
      HashMap<String, String> result = new HashMap<String, String>();

      if(data.length % 2 != 0) 
          throw new IllegalArgumentException("Odd number of arguments");      

      String key = null;
      Integer step = -1;

      for(String value : data){
          step++;
          switch(step % 2){
          case 0: 
              if(value == null)
                  throw new IllegalArgumentException("Null key value"); 
              key = value;
              continue;
          case 1:             
              result.put(key, value);
              break;
          }
      }

      return new MapMessage(result);
    }
 }
