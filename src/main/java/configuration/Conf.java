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
import logger.Logging;

/**
 *
 * @author Rasmus
 */
public class Conf {

    public static final boolean PRODUCTION = false;
    public static final String LOGFILEPATH = "/var/log/tomcat8/demoApp.log";
    private static Logger logger;

    public static Logger getLogger() {
        if (logger == null) {
            logger = Logger.getLogger("");
            if (configuration.Conf.PRODUCTION) {
                try {
                    FileHandler handler = new FileHandler(configuration.Conf.LOGFILEPATH);
                    handler.setFormatter(new VerySimpleFormatter());
                    logger.addHandler(handler);
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            } else {
                try {
                    FileHandler handler = new FileHandler("loggingDemo-log.%u.%g.txt");
                    handler.setFormatter(new VerySimpleFormatter());
                    logger.addHandler(handler);
                } catch (IOException ex) {
                    Logger.getLogger(Conf.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return logger;
    }

    private static class VerySimpleFormatter extends Formatter {

        String datePattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

        @Override
        public String format(LogRecord record) {
            return String.format("%1$s %2$-7s %3$s\n", new SimpleDateFormat(datePattern).format(new Date(record.getMillis())),
                    record.getLevel().getName(),
                    formatMessage(record)
            );
        }
    }
}
