/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer.Calculate;

import FunctionLayer.Entity.Carport;
import FunctionLayer.Entity.Material;
import FunctionLayer.Exception.GeneralException;
import FunctionLayer.Exception.MakeOrderException;
import static FunctionLayer.Rule.Rules.PLASTMOLENGTHLONG;
import static FunctionLayer.Rule.Rules.PLASTMOLENGTHSMALL;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Simon
 *
 */
public class MaterialCalculatorTest {

    MaterialCalculator mc = new MaterialCalculator();
    CarportCalculator cc = new CarportCalculator();
    PriceCalculator pc = new PriceCalculator();
    int length = 240;
    int width = 240;
    //false means flat roof
    boolean roof = false;
    //false means no shed
    boolean shed = false;

    @Test
    public void testPlastmoLongAmount() throws GeneralException, MakeOrderException {
        //act
        Carport result = cc.calculateAll(length, width, roof, shed);
        result.setList(mc.materialList(result));
        ArrayList<Material> l = result.getList();
        //Assert
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getName().equals("Plastmo Ecolite blåtonet") && l.get(i).getLength() == PLASTMOLENGTHLONG) {
                int expectedPlastmoLongAmount = 0;
                int actualPlastmoLongAmount = l.get(i).getAmount();
                Assert.assertEquals(expectedPlastmoLongAmount, actualPlastmoLongAmount);
            }
        }
    }
    
    @Test
    public void testPlastmoSmallAmount() throws GeneralException, MakeOrderException {
        //act
        Carport result = cc.calculateAll(length, width, roof, shed);
        result.setList(mc.materialList(result));
        ArrayList<Material> l = result.getList();
        //Assert
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getName().equals("Plastmo Ecolite blåtonet") && l.get(i).getLength() == PLASTMOLENGTHSMALL) {
                int expectedPlastmoSmallAmount = 8;
                int actualPlastmoSmallAmount = l.get(i).getAmount();
                Assert.assertEquals(expectedPlastmoSmallAmount, actualPlastmoSmallAmount);
            }
        }
    }

    @Test
    public void testPostAmount() throws GeneralException, MakeOrderException {
        //act
        Carport result = cc.calculateAll(length, width, roof, shed);
        result.setList(mc.materialList(result));
        ArrayList<Material> l = result.getList();
        //Assert
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getName().equals("97x97	mm.	trykimp. Stolpe")) {
                int expectedPostAmount = 0;
                int actualPostAmount = l.get(i).getAmount();
                Assert.assertEquals(expectedPostAmount, actualPostAmount);
            }
        }
    }
}
