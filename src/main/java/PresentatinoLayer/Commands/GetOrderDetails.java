package PresentatinoLayer.Commands;

import FunctionLayer.Entity.Material;
import PresentatinoLayer.Commands.Command;
import Facade.LogicFacade;
import FunctionLayer.Entity.Order;
import FunctionLayer.Entity.User;
import FunctionLayer.Exception.DMException;
import FunctionLayer.Exception.MakeOrderException;
import FunctionLayer.Exception.NotLoggedInException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GetOrderDetails extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws NotLoggedInException, DMException, MakeOrderException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (!user.equals(null) && user.getRole().equals("employee")) {

            LogicFacade lf = new LogicFacade();
            int orderid = Integer.parseInt(request.getParameter("orderID"));
            Order o = lf.getOrderByID(orderid);
            ArrayList<Material> materialList = o.getCarport().getList();
            request.setAttribute("order", o);
            request.setAttribute("materiallist", materialList);
            return "showorderdetails";
        } else {
            throw new NotLoggedInException("log ind igen");
        }
    }
}
