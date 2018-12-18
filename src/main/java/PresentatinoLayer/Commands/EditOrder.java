package PresentatinoLayer.Commands;

import FunctionLayer.Entity.Material;
import PresentatinoLayer.Commands.Command;
import FunctionLayer.Exception.DMException;
import Facade.LogicFacade;
import FunctionLayer.Entity.Order;
import FunctionLayer.Entity.User;
import FunctionLayer.Exception.MakeOrderException;
import FunctionLayer.Exception.NotLoggedInException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditOrder extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DMException, MakeOrderException, NotLoggedInException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (!user.equals(null) && user.getRole().equals("employee")) {

            int id = Integer.parseInt(request.getParameter("orderID"));
            int width = Integer.parseInt(request.getParameter("width"));
            int length = Integer.parseInt(request.getParameter("length"));
            int roof = Integer.parseInt(request.getParameter("roof"));
            String state = request.getParameter("State");

            if (state.equalsIgnoreCase("ForespÃ¸rgsel")) {
                state = "Forespørgsel";
            }
            if (state.equalsIgnoreCase("Afventer sÃ¦lger")) {
                state = "Afventer sælger";
            }

            LogicFacade lf = new LogicFacade();
            Order order = lf.editOrder(id, width, length, roof, state);
            ArrayList<Material> materialList;
            materialList = order.getCarport().getList();
            request.setAttribute("order", order);
            request.setAttribute("materiallist", materialList);
            return "showorderdetails";
        } else {
            throw new NotLoggedInException("Log ind igen");
        }
    }
}
