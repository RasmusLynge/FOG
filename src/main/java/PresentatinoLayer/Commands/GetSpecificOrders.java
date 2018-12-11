/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author Rasmu
 */
public class GetSpecificOrders extends Command {

    public GetSpecificOrders() {
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DMException, MakeOrderException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        ArrayList<Order> ol = new ArrayList();

        String state = "";

        state = request.getParameter("state");
        System.out.println("TESSST : " + state);
        if (state.equalsIgnoreCase("ForespÃ¸rgsel")) {
            state = "Forespørgsel";
        }
        if (state.equalsIgnoreCase("Afventer sÃ¦lger")) {
            state = "Afventer sælger";
        }
        request.setAttribute("state", state);
        System.out.println("NEW TESSST : " + state);

        if ("employee".equals(user.getRole())) {
            ol = LogicFacade.getSpecificOrders(state);
            if (ol.isEmpty()) {
                System.out.println("Tom liste");
                session.setAttribute("getSpecificOrders", ol);
            } else {
                session.setAttribute("getSpecificOrders", ol);
            }
        }
        return "showspecificorders";
    }

}
