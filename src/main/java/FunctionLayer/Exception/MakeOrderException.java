package FunctionLayer.Exception;

public class MakeOrderException extends Exception {

    public MakeOrderException(String msg) {
        super(msg);
        System.out.println(msg);
    }

}
