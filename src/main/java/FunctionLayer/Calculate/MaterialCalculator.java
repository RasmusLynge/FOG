/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer.Calculate;

import java.util.List;
import FunctionLayer.Entity.Material;
import FunctionLayer.Entity.Carport;
import java.util.ArrayList;
import DBAccess.DataMapper;
/**
 *
 * @author Magnus
 */
public class MaterialCalculator {

    public List<Material> materialList(int length, Carport c) {
        DataMapper dm = new DataMapper();
        dm.
        int counterRafterLong = 0;
        int counterRafterSmall = 0;

        if (c.getRafterLength() <= 480) {
            counterRafterSmall += c.getRafter();
        } else if (c.getRafterLength() <= 600) {
            counterRafterLong += c.getRafter();
        } else if (c.getRafterLength() > 600) {
            int restLength = c.getRafterLength() - 600;
            counterRafterLong += c.getRafter();
            counterRafterSmall += 1;
            int currentLength = 480;
            for (int i = 0; i < c.getRafter(); i++) {
                if (restLength < currentLength) {
                    currentLength -= restLength;
                } else {
                    counterRafterSmall += 1;
                    currentLength = 480 - restLength;
                }
            }
            c.setFlatHinges(c.getFlatHinges() + counterRafterSmall * 2);
        }
        
        
        c.getRoofRafter();

        /*

         */
        return list;
    }

}
