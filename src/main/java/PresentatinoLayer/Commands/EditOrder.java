package PresentatinoLayer.Commands;

import FunctionLayer.Entity.Material;
import PresentatinoLayer.Commands.Command;
import FunctionLayer.Exception.DMException;
import Facade.LogicFacade;
import FunctionLayer.Entity.Order;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditOrder extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DMException {
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

        try {
            LogicFacade lf = new LogicFacade();
            Order o = lf.editOrder(id, width, length, roof, state);
            ArrayList<Material> materialList;
            materialList = o.getCarport().getList();
            request.setAttribute("order", o);
            request.setAttribute("materiallist", materialList);
            return "showorderdetails";

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error");
        }
        return "showorderdetails";
    }
}
