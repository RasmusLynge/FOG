package FunctionLayer.Exception;

public class LoginException extends Exception {

    public LoginException(String msg) {
        super(msg);
        System.out.println(msg);

    }
}
