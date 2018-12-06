/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package configuration;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 *
 * @author tha
 */
public class Conf {

    public static final boolean PRODUCTION = false;
    public static final String LOG_FILE_PATH = "/var/log/tomcat8/demoApp.log";
    public static final String LOG_FILE_PATH_DEVELOP = "/home/thomas/loggingdemo.log";
    private static Logger logger;
    private static Logger loggerST;
    

    public static Logger getLogger() {
        if (logger == null) {
            logger = Logger.getLogger("");
            if (configuration.Conf.PRODUCTION) {
                try {
                    FileHandler handler = new FileHandler(configuration.Conf.LOG_FILE_PATH);
                    handler.setFormatter(new VerySimpleFormatter());
                    logger.addHandler(handler);
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            } else {
                try {
                    FileHandler handler = new FileHandler("Logging-Demo-log.%u.%g.txt"); // see: http://tutorials.jenkov.com/java-logging/handlers.html
                    handler.setFormatter(new VerySimpleFormatter());
                    logger.addHandler(handler);
                } catch (IOException ex) {
                    Logger.getLogger(Conf.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }
        }
        return logger;
    }
    
    public static Logger getLoggerWithStackTrace() {
        if (loggerST == null) {
            loggerST = Logger.getLogger("Stack Trace Logger");
            try {
                    FileHandler handler = new FileHandler("loggingDemo-log.%u.%g.txt");
                    handler.setFormatter(new StackTraceFormatter());
                    logger.addHandler(handler);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } 
            }
        return logger;
    }
    
    
    
    private static class VerySimpleFormatter extends Formatter{
        String datePattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
        @Override
        public String format(LogRecord record) {
            return String.format(
                "%1$s %2$-7s %3$s\n",
                new SimpleDateFormat(datePattern).format(
                    new Date(record.getMillis())
                ),
                record.getLevel().getName(),
                formatMessage(record)
            );
        }
    }
    
    private static class StackTraceFormatter extends Formatter{
        String datePattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
        @Override
        public String format(LogRecord record) {
            return String.format(
                "%1$s %2$-7s %3$s %4$s\n",
                new SimpleDateFormat(datePattern).format(
                    new Date(record.getMillis())
                ),
                record.getLevel().getName(),
                formatMessage(record),
                record.getThrown().toString()
            );
        }
    }



}

