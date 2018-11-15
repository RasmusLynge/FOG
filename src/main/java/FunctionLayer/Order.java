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
    private String id, name, email, zip, phone, evt;

    public Order(int width, int length, String name, String email, String zip, String phone, String evt) {
        this.width = width;
        this.length = length;
        this.name = name;
        this.email = email;
        this.zip = zip;
        this.phone = phone;
        this.evt = evt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    
}
