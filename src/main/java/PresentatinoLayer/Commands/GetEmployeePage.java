package PresentatinoLayer.Commands;

import FunctionLayer.Entity.User;
import FunctionLayer.Exception.NotLoggedInException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GetEmployeePage extends Command {

    public GetEmployeePage() {
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws NotLoggedInException{
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (!user.equals(null)) {
                return "employeepage";
            } else {
                throw new NotLoggedInException("Log ind igen");
            }
        }
    }
