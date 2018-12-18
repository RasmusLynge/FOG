package PresentatinoLayer.Commands;

import FunctionLayer.Entity.User;
import FunctionLayer.Exception.NotLoggedInException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class GetCreateEmployeeUserPage extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws NotLoggedInException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        if (!user.equals(null) && user.getRole().equals("admin")) {
            return "createemployeeuser";
        } else {
            throw new NotLoggedInException("Du har ikke rettigheder til at logge ind her");
        }
    }
    
}
