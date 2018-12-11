package FunctionLayer.Exception;

public class DMException extends Exception {

    public DMException(String msg) {

        super(msg);
        System.out.println(msg);
    }

}
