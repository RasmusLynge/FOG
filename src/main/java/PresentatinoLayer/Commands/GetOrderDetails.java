package PresentatinoLayer.Commands;

import FunctionLayer.Entity.Material;
import PresentatinoLayer.Commands.Command;
import FunctionLayer.Exception.DMException;
import Facade.LogicFacade;
import FunctionLayer.Entity.Order;
import FunctionLayer.Exception.MakeOrderException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetOrderDetails extends Command {
    
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DMException, MakeOrderException {
        LogicFacade lf = new LogicFacade();
        int orderid = Integer.parseInt(request.getParameter("orderID")); 
        Order o = lf.getOrderByID(orderid);
        ArrayList<Material> materialList = o.getCarport().getList();
        request.setAttribute("order", o);
        request.setAttribute("materiallist", materialList);
        return "showorderdetails";
    }
}
