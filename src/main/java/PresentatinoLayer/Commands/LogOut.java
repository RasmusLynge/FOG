package PresentatinoLayer.Commands;

import PresentatinoLayer.Commands.Command;
import FunctionLayer.Exception.DMException;
import FunctionLayer.Exception.MakeOrderException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogOut extends Command {

    public LogOut() {
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DMException, MakeOrderException {
        HttpSession session = request.getSession();
        session.invalidate();
        return "index";
    }

}
