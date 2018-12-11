package FunctionLayer.Exception;

public class LoginException extends Exception {

    /**
     *
     * @param msg
     */
    public LoginException(String msg) {
        super(msg);
        System.out.println(msg);

    }
}
