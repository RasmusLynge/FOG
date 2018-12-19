package PresentatinoLayer.Commands;

import Facade.LogicFacade;
import FunctionLayer.Entity.User;
import FunctionLayer.Exception.DMException;
import FunctionLayer.Exception.NotLoggedInException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Rasmu
 */
public class GetEmployeeUsers extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DMException, NotLoggedInException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        LogicFacade lf = new LogicFacade();
        ArrayList<User> userl = new ArrayList();
        
        if (!user.equals(null) && user.getRole().equals("admin")) {
            userl = lf.getAllEmployeeUsers();
            
            
            session.setAttribute("getAllEmployeeUsers", userl);
            
        } else {
            throw new NotLoggedInException("Log ind igen");
        }

        return "deleteemployeeusers";
    }
}
