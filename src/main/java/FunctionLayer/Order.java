/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

/**
 *
 * @author Rasmu
 */
public class Order {
    
    private int width, length;
    private double price;
    private String id, name, email, zip, phone, evt, orderdate, state;
    private boolean flat_roof;
         
    public Order(int width, int length, String name, String email, String zip, String phone, String evt) {
        this.width = width;
        this.length = length;
        this.name = name;
        this.email = email;
        this.zip = zip;
        this.phone = phone;
        this.evt = evt;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    

    public String getId() {
        return id;
    }
    

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getZip() {
        return zip;
    }

    public String getPhone() {
        return phone;
    }

    public String getEvt() {
        return evt;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEvt(String evt) {
        this.evt = evt;
    }

    public boolean isFlat_roof() {
        return flat_roof;
    }

    public void setFlat_roof(boolean flat_roof) {
        this.flat_roof = flat_roof;
    }

    public String getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(String orderdate) {
        this.orderdate = orderdate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    
    
}
