/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentatinoLayer.Commands;

import PresentatinoLayer.Commands.Command;
import FunctionLayer.Exception.DMException;
import FunctionLayer.Exception.MakeOrderException;
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
    String execute(HttpServletRequest request, HttpServletResponse response) throws DMException, MakeOrderException {
        HttpSession session = request.getSession();
        session.invalidate();
        return"index";
    }
    
}
