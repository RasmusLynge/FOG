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
import static FunctionLayer.Rule.Rules.PLANKLENGTH;
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
    public void testPlastmoLongAmountFlatRoof() throws GeneralException, MakeOrderException {
        //act
        Carport result = cc.calculateAll(length, width, roof, shed);
        result.setList(mc.materialList(result));
        ArrayList<Material> l = result.getList();
        //Assert
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getName().equals("Plastmo Ecolite blåtonet") && l.get(i).getLength() == PLASTMOLENGTHLONG) {
                //bruges kun til fladt tag som den er her derfor 4
                int expectedPlastmoLongAmount = 4;
                int actualPlastmoLongAmount = l.get(i).getAmount();

                Assert.assertEquals(expectedPlastmoLongAmount, actualPlastmoLongAmount);
            }
        }
    }
    
    @Test
    public void testPlastmoLongAmountTopRoof() throws GeneralException, MakeOrderException {
        Carport result = cc.calculateAll(length, width, true, shed);
        result.setList(mc.materialList(result));
        ArrayList<Material> l = result.getList();
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getName().equals("Plastmo Ecolite blåtonet") && l.get(i).getLength() == PLASTMOLENGTHLONG) {
                //bruges kun til fladt tag, så bør være 0
                int expectedPlastmoLongAmount = 0;
                int actualPlastmoLongAmount = l.get(i).getAmount();

                Assert.assertEquals(expectedPlastmoLongAmount, actualPlastmoLongAmount);
            }
        }
    }

    @Test
    public void testPlastmoSmallAmountFlatRoof() throws GeneralException, MakeOrderException {
        Carport result = cc.calculateAll(length, width, roof, shed);
        result.setList(mc.materialList(result));
        ArrayList<Material> l = result.getList();
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getName().equals("Plastmo Ecolite blåtonet") && l.get(i).getLength() == PLASTMOLENGTHSMALL) {
                //bruges kun til fladt tag, så bør være 0
                int expectedPlastmoSmallAmount = 0;
                int actualPlastmoSmallAmount = l.get(i).getAmount();

                Assert.assertEquals(expectedPlastmoSmallAmount, actualPlastmoSmallAmount);
            }
        }
    }
    
    @Test
    public void testPlastmoSmallAmountTopRoof() throws GeneralException, MakeOrderException {
        Carport result = cc.calculateAll(length, width, true, shed);
        result.setList(mc.materialList(result));
        ArrayList<Material> l = result.getList();
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getName().equals("Plastmo Ecolite blåtonet") && l.get(i).getLength() == PLASTMOLENGTHSMALL) {
                //bruges kun til fladt tag, så bør være 0
                int expectedPlastmoLongAmount = 0;
                int actualPlastmoLongAmount = l.get(i).getAmount();

                Assert.assertEquals(expectedPlastmoLongAmount, actualPlastmoLongAmount);
            }
        }
    }

    @Test
    public void testScrewBoxesAmount() throws GeneralException, MakeOrderException {
        //act
        Carport result = cc.calculateAll(length, width, roof, shed);
        result.setList(mc.materialList(result));
        ArrayList<Material> l = result.getList();
        //Assert
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getName().equals("Skruer 200 stk.")) {
                int expectedScrewBoxesAmount = 2;
                int actualScrewBoxesAmount = l.get(i).getAmount();
                Assert.assertEquals(expectedScrewBoxesAmount, actualScrewBoxesAmount);
            }
        }
    }

    @Test
    public void testFlatHingesAmount() throws GeneralException, MakeOrderException {
        //act
        Carport result = cc.calculateAll(length, width, roof, shed);
        result.setList(mc.materialList(result));
        ArrayList<Material> l = result.getList();
        //Assert
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getName().equals("FladtBeslag")) {
                int expectedFlatHingesAmount = 2;
                int actualFlatHingesAmount = l.get(i).getAmount();
                Assert.assertEquals(expectedFlatHingesAmount, actualFlatHingesAmount);
            }
        }
    }

    @Test
    public void testRoofTilesAmount() throws GeneralException, MakeOrderException {
        //act
        Carport result = cc.calculateAll(length, width, roof, shed);
        result.setList(mc.materialList(result));
        ArrayList<Material> l = result.getList();
        //Assert
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getName().equals("Tegl")) {
                int expectedRoofTilesAmount = 2;
                int actualRoofTilesAmount = l.get(i).getAmount();
                Assert.assertEquals(expectedRoofTilesAmount, actualRoofTilesAmount);
            }
        }
    }

    @Test
    public void testLHingesAmount() throws GeneralException, MakeOrderException {
        //act
        Carport result = cc.calculateAll(length, width, roof, shed);
        result.setList(mc.materialList(result));
        ArrayList<Material> l = result.getList();
        //Assert
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getName().equals("LBeslag")) {
                int expectedLHingesAmount = 32;
                int actualLHingesAmount = l.get(i).getAmount();
                Assert.assertEquals(expectedLHingesAmount, actualLHingesAmount);
            }
        }
    }

    @Test
    public void testPlanksAmount() throws GeneralException, MakeOrderException {
        //act
        Carport result = cc.calculateAll(length, width, roof, shed);
        result.setList(mc.materialList(result));
        ArrayList<Material> l = result.getList();
        //Assert
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getName().equals("25x150	mm.	trykimp. Bræt") && l.get(i).getLength() == PLANKLENGTH) {
                int expectedPlanksAmount = 2;
                int actualPlanksAmount = l.get(i).getAmount();
                Assert.assertEquals(expectedPlanksAmount, actualPlanksAmount);
            }
        }
    }

    @Test
    public void testCoverStabilizerPlankSmallAmount() throws GeneralException, MakeOrderException {
        //act
        Carport result = cc.calculateAll(length, width, roof, shed);
        result.setList(mc.materialList(result));
        ArrayList<Material> l = result.getList();
        //Assert
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getName().equals("45x95 Reglar ubh.") && l.get(i).getLength() == 240) {
                int expectedCoverStabilizerPlankSmallAmount = 2;
                int actualCoverStabilizerPlankSmallAmount = l.get(i).getAmount();
                Assert.assertEquals(expectedCoverStabilizerPlankSmallAmount, actualCoverStabilizerPlankSmallAmount);
            }
        }
    }

    @Test
    public void testCoverStabilizerPlanksLongAmount() throws GeneralException, MakeOrderException {
        //act
        Carport result = cc.calculateAll(length, width, roof, shed);
        result.setList(mc.materialList(result));
        ArrayList<Material> l = result.getList();
        //Assert
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getName().equals("45x95 Reglar ubh.") && l.get(i).getLength() == 360) {
                int expectedCoverStabilizerPlanksLongAmount = 2;
                int actualCoverStabilizerPlanksLongAmount = l.get(i).getAmount();
                Assert.assertEquals(expectedCoverStabilizerPlanksLongAmount, actualCoverStabilizerPlanksLongAmount);
            }
        }
    }

    @Test
    public void testDoorKnobAmount() throws GeneralException, MakeOrderException {
        //act
        Carport result = cc.calculateAll(length, width, roof, shed);
        result.setList(mc.materialList(result));
        ArrayList<Material> l = result.getList();
        //Assert
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getName().equals("Dørhåndtag")) {
                int expectedDoorKnobAmount = 2;
                int actualDoorKnobAmount = l.get(i).getAmount();
                Assert.assertEquals(expectedDoorKnobAmount, actualDoorKnobAmount);
            }
        }
    }

    @Test
    public void testDoorHingeAmount() throws GeneralException, MakeOrderException {
        //act
        Carport result = cc.calculateAll(length, width, roof, shed);
        result.setList(mc.materialList(result));
        ArrayList<Material> l = result.getList();
        //Assert
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getName().equals("Dør hængsel")) {
                int expectedDoorHingeAmount = 0;
                int actualDoorHingeAmount = l.get(i).getAmount();
                Assert.assertEquals(expectedDoorHingeAmount, actualDoorHingeAmount);
            }
        }
    }
}
