package PresentatinoLayer.Commands;

import FunctionLayer.Entity.Order;
import FunctionLayer.Entity.User;
import FunctionLayer.Exception.DMException;
import FunctionLayer.Exception.MakeOrderException;
import Facade.LogicFacade;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GetSpecificOrders extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DMException, MakeOrderException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        ArrayList<Order> ol = new ArrayList();
        LogicFacade lf = new LogicFacade();
        String state = "";

        state = request.getParameter("state");
        if (state.equalsIgnoreCase("ForespÃ¸rgsel")) {
            state = "Forespørgsel";
        }
        if (state.equalsIgnoreCase("Afventer sÃ¦lger")) {
            state = "Afventer sælger";
        }
        request.setAttribute("state", state);

        if ("employee".equals(user.getRole())) {
            ol = lf.getSpecificOrders(state);
            if (ol.isEmpty()) {
                session.setAttribute("getSpecificOrders", ol);
            } else {
                session.setAttribute("getSpecificOrders", ol);
            }
        }
        return "showspecificorders";
    }

}
