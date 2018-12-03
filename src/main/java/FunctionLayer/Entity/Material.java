/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer.Entity;

/**
 *
 * @author Mathias
 */
public class Material {
    Carport c; 
    String name; 
    int length;
    int amount;
    double price;

    public Material(String name, int length, int amount, double price) {
        this.name = name;
        this.length = length;
        this.amount = amount;
        this.price = price;
    }

   

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Carport getC() {
        return c;
    }

    public void setC(Carport c) {
        this.c = c;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    
    
}
