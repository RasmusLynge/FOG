package PresentatinoLayer.Commands;

import Facade.LogicFacade;
import FunctionLayer.Entity.User;
import FunctionLayer.Exception.DMException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class DeleteUser extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DMException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        LogicFacade lf = new LogicFacade();
        ArrayList<User> userl = new ArrayList();
        int userId = Integer.parseInt(request.getParameter("userId"));

        lf.deleteUser(userId);
        if (user.getRole().equals("admin")) {
            userl = lf.getAllEmployeeUsers();

            if (userl.isEmpty()) {
                System.out.println("Tom liste");
                //Burde kaste exception
            }
            session.setAttribute("getAllEmployeeUsers", userl);
        }
        return "deleteemployeeusers";
    }

}
