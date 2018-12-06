/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer.Calculate;

import FunctionLayer.Entity.Carport;
import FunctionLayer.Exception.GeneralException;
import FunctionLayer.Exception.MakeOrderException;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Simon
 */
public class CoverCalculatorTest {
    //Arrange
    CarportCalculator cc = new CarportCalculator();
    int length = 240;
    int width = 240;
    //false means flat roof
    boolean roof = true;
    //false means no shed
    boolean shed = true;

    @Test
    public void TestShedCover() throws GeneralException, MakeOrderException {
        //act
        Carport result = new Carport(length, width, roof, shed);
        int shedLength = 100;
        result.setShedLength(shedLength);
        result = cc.calculateAll(length, width, roof, shed); 
        //Assert
        int expectedPlanks = 0;
        int actualPlanks = (int) result.getPlanks();
        Assert.assertEquals(expectedPlanks, actualPlanks);
    }
    
    @Test
    public void TestRoofCover() throws GeneralException, MakeOrderException {
        //act
        Carport result = cc.calculateAll(length, width, roof, shed); 
        //Assert
        int expectedCoverPlanks = 60;
        int actualCoverPlanks = (int) result.getCoverPlanks();
        Assert.assertEquals(expectedCoverPlanks, actualCoverPlanks);
    }
}
