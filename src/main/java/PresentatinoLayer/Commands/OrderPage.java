package PresentatinoLayer.Commands;

import PresentatinoLayer.Commands.Command;
import FunctionLayer.Exception.DMException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrderPage extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DMException {

        return "orderpage";
    }
}
