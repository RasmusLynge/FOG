package PresentatinoLayer.Commands;

import PresentatinoLayer.Commands.Command;
import FunctionLayer.Exception.DMException;
import Facade.LogicFacade;
import FunctionLayer.Entity.Order;
import FunctionLayer.Entity.User;
import FunctionLayer.Exception.NotLoggedInException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GetOrders extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DMException, NotLoggedInException{
        LogicFacade lf = new LogicFacade();
        ArrayList<Order> ol = new ArrayList();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (!user.equals(null) && user.getRole().equals("employee")) {
            ol = lf.getAllOrders();
            if (!ol.isEmpty()) {
                session.setAttribute("getAllOrders", ol);
            }
                return "showorders";
        } else {
            throw new NotLoggedInException("Log ind igen");
        }
    }
}