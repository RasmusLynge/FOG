/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

import DBAccess.DataMapper;

public class LogicFacade {
    PriceCalculator p = new PriceCalculator();
    DataMapper dm = new  DataMapper();
    

    public static User login(String email, String password) throws GeneralException {
        return DataMapper.login(email, password);
    }

    public static User createUser(String email, String password) throws GeneralException {
        User user = new User(email, password, "customer");
        DataMapper.createUser(user);
        return user;
    }
    public Order makeOrder(int width, int length, String name, String email, String zip, String phone, String evt) throws GeneralException{
        Order o = new Order(width, length, name, email, zip, phone, evt);
        o.setPrice(p.priceCalculator(length, width));
        dm.createOrder(o);
        return o;
    }
}