/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer.Calculate;

import DBAccess.DataMapper;
import FunctionLayer.Entity.Carport;
import FunctionLayer.Entity.Material;
import java.util.ArrayList;

/**
 *
 * @author Mathias
 */
public class ShedCalc {
    Carport c; 
    DataMapper db = new DataMapper();

    public ShedCalc(Carport c) {
        this.c = c;
    }
    public void setPlanksForShed(Carport c){
        int shedWidth = c.getWidth();
        int shedHeight = c.getPostLength();
//        ArrayList<Material> = c.getList();

    }
    
}
