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

@SuppressWarnings("serial")
@WebServlet("/")
public class TimeServlet extends HttpServlet {

    public static final Logger logger = LogManager.getFormatterLogger(TimeServlet.class);

    public static void main(String[] args) {
      logger.info(map("a", "1", "b", "2").getFormattedMessage(new String[]{"JSON"}));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        logger.info(map("url", req.getRequestURL().toString()).getFormattedMessage(new String[]{"JSON"}));
        System.out.println("{\"timeMillis\":1463644362907,\"thread\":\"default task-4\",\"level\":\"INFO\",\"loggerName\":\"com.puzzleitc.servlet.TimeServlet\",\"message\":{\"url\":\"http://ose3-java-logging-dtschan.ose3-lab.puzzle.ch/\"},\"endOfBatch\":false,\"loggerFqcn\":\"org.apache.logging.log4j.spi.AbstractLogger\",\"contextMap\":[],\"source\":{\"class\":\"com.puzzleitc.servlet.TimeServlet\",\"method\":\"doGet\",\"file\":\"TimeServlet.java\",\"line\":32}}");
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.println("<html><head><title>Time</title></head><body>");
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss 'UTC'XXX");
        Calendar cal = Calendar.getInstance();
        writer.println(dateFormat.format(cal.getTime()));
        writer.println("</body></html>");
        writer.close();
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
