package PresentatinoLayer.Commands;

import PresentatinoLayer.Commands.Command;
import Facade.LogicFacade;
import FunctionLayer.Exception.DMException;
import FunctionLayer.Entity.User;
import FunctionLayer.Exception.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginException {
        LogicFacade lf = new LogicFacade();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = lf.login(email, password);
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        session.setAttribute("role", user.getRole());

        if (user.getRole().equals("employee")) {

            return "employeepage";
        } else {
            throw new LoginException("Du har ikke rettigheder til at logge ind her");
        }
    }

}
