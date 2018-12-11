package logging;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class Logging {

    public static final boolean PRODUCTION = false;
    public static final String LOG_FILE_PATH = "/var/log/tomcat8/Fog.log";
    public static final String LOG_FILE_PATH_DEVELOP = "C:\\Users\\Rasmu\\Documents\\Datamatiker_2sem\\Modul_2_SQL\\FOG\\FOGLog.log";
    public static final String LOG_FILE_PATH_STACK = "C:\\Users\\Rasmu\\Documents\\Datamatiker_2sem\\Modul_2_SQL\\FOG\\FOGStack.txt";
    private static Logger logger;
    private static Logger loggerST;

    /**
     *  This method creates a new logger file when the program is used
     *  The log file will contain the error messages
     *  The log placement will be determinant by the PRODUCTION boolean if the production is true or false
     * @return Will return the following log that's produced
     */
    public static Logger getLogger() {
        if (logger == null) {
            logger = Logger.getLogger("");
            if (logging.Logging.PRODUCTION) {
                try {
                    FileHandler handler = new FileHandler(logging.Logging.LOG_FILE_PATH);
                    handler.setFormatter(new VerySimpleFormatter());
                    logger.addHandler(handler);
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            } else {
                try {
                    FileHandler handler = new FileHandler(LOG_FILE_PATH_DEVELOP);
                    handler.setFormatter(new VerySimpleFormatter());
                    logger.addHandler(handler);
                    getLoggerWithStackTrace();
                } catch (IOException ex) {
                    Logger.getLogger(Logging.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return logger;
    }

    /**
     * This method will print the stack trace down in the log file
     * @return Will return the newly updated logger with stack trace
     */
    public static Logger getLoggerWithStackTrace() {
        if (loggerST == null) {
            loggerST = Logger.getLogger("Stack Trace Logger");
            try {
                FileHandler handler = new FileHandler(LOG_FILE_PATH_STACK);
                handler.setFormatter(new StackTraceFormatter());
                logger.addHandler(handler);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return logger;
    }

    private static class VerySimpleFormatter extends Formatter {

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

    private static class StackTraceFormatter extends Formatter {

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
