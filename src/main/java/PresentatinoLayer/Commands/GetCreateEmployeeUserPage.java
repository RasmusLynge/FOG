/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentatinoLayer.Commands;

import FunctionLayer.Entity.User;
import FunctionLayer.Exception.DMException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Rasmu
 */
public class GetCreateEmployeeUserPage extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DMException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user.equals(null)) {
            throw new DMException("Du har ikke rettigheder til at logge ind her");
        }
        if (!user.equals(null) && user.getRole().equals("admin")) {
            return "createemployeeuser";
        } else {
            throw new DMException("Du har ikke rettigheder til at logge ind her");
        }
    }
    
}
