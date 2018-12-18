package FunctionLayer.Exception;

public class LoggerException extends Exception {

    /**
     * This method is used as an exception for logger error's
     *
     * @param msg That contains the error message from IOException
     */
    public LoggerException(String msg) {

        super(msg);
    }
}
