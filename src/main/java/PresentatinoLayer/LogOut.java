/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentatinoLayer;

import FunctionLayer.GeneralException;
import FunctionLayer.MakeOrderException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Rasmu
 */
public class LogOut extends Command {

    public LogOut() {
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws GeneralException, MakeOrderException {
        HttpSession session = request.getSession();
        session.invalidate();
        return"index";
    }
    
}
