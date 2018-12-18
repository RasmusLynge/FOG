package PresentatinoLayer.Commands;

import Facade.LogicFacade;
import FunctionLayer.Entity.User;
import FunctionLayer.Exception.DMException;
import FunctionLayer.Exception.LoginException;
import FunctionLayer.Exception.NotLoggedInException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class CreateEmployeeUsers extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginException, NotLoggedInException, DMException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (!user.equals(null) && user.getRole().equals("admin")) {
            LogicFacade lf = new LogicFacade();
            String email = request.getParameter("email");
            String password1 = request.getParameter("password1");
            String password2 = request.getParameter("password2");
            if (password1.equals(password2)) {
                lf.createEmployeeUser(email, password1);
                return "adminpage";
            } else {
                throw new LoginException("De to adgangskoder er ikke ens");
            }
        } else {
            throw new NotLoggedInException("Log ind igen");
        }
    }

}
