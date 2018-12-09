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
import org.junit.Before;
import org.junit.Test;
import org.junit.BeforeClass;

/**
 *
 * @author Simon
 */
public class CoverCalculatorTest {
    CarportCalculator cc = new CarportCalculator();
    static int length, width;
    static boolean roof, shed;
    
    @BeforeClass
    public static void setUpClass() {
        roof = false;
        shed = false;
    }

    @Before
    public void setUp() {
        length = 240;
        width = 240;
    }

    @Test
    public void shedCover240x240() throws GeneralException, MakeOrderException {
        //Act
        Carport result = cc.calculateAll(length, width, roof, true); 
        int expectedPlanks = 0;
        int actualPlanks = (int) result.getPlanks();
        //Assert
        Assert.assertEquals(expectedPlanks, actualPlanks);
    }
    
    @Test
    public void shedCover480x480() throws GeneralException, MakeOrderException {
        //Arrange
        length = 480;
        width = 480;
        //Act
        Carport result = cc.calculateAll(length, width, roof, true); 
        int expectedPlanks = 0;
        int actualPlanks = (int) result.getPlanks();
        //Assert
        Assert.assertEquals(expectedPlanks, actualPlanks);
    }
    
    @Test
    public void shedCover720x720() throws GeneralException, MakeOrderException {
        //Arrange
        length = 720;
        width = 720;
        //Act
        Carport result = cc.calculateAll(length, width, roof, true); 
        int expectedPlanks = 0;
        int actualPlanks = (int) result.getPlanks();
        //Assert
        Assert.assertEquals(expectedPlanks, actualPlanks);
    }
    
    @Test
    public void flatRoofCoverPlanks240x240() throws GeneralException, MakeOrderException {
        //Act
        Carport result = cc.calculateAll(length, width, roof, shed); 
        int expectedCoverPlanks = 60;
        int actualCoverPlanks = (int) result.getCoverPlanks();
        //Assert
        Assert.assertEquals(expectedCoverPlanks, actualCoverPlanks);
    }
    
    @Test
    public void topRoofCoverPlanks240x240() throws GeneralException, MakeOrderException {
        //Act
        Carport result = cc.calculateAll(length, width, true, shed); 
        int expectedCoverPlanks = 60;
        int actualCoverPlanks = (int) result.getCoverPlanks();
        //Assert
        Assert.assertEquals(expectedCoverPlanks, actualCoverPlanks);
    }
    
    @Test
    public void flatRoofCoverPlanks480x480() throws GeneralException, MakeOrderException {
        //Arrange
        length = 480;
        width = 480;
        //Act
        Carport result = cc.calculateAll(length, width, roof, shed); 
        int expectedCoverPlanks = 120;
        int actualCoverPlanks = (int) result.getCoverPlanks();
        //Assert
        Assert.assertEquals(expectedCoverPlanks, actualCoverPlanks);
    }
    
    @Test
    public void topRoofCoverPlanks480x480() throws GeneralException, MakeOrderException {
        //Arrange
        length = 480;
        width = 480;
        //Act
        Carport result = cc.calculateAll(length, width, true, shed); 
        int expectedCoverPlanks = 120;
        int actualCoverPlanks = (int) result.getCoverPlanks();
        //Assert
        Assert.assertEquals(expectedCoverPlanks, actualCoverPlanks);
    }
    
    @Test
    public void flatRoofCoverPlanks720x720() throws GeneralException, MakeOrderException {
        //Arrange
        length = 720;
        width = 720;
        //Act
        Carport result = cc.calculateAll(length, width, roof, shed); 
        int expectedCoverPlanks = 180;
        int actualCoverPlanks = (int) result.getCoverPlanks();
        //Assert
        Assert.assertEquals(expectedCoverPlanks, actualCoverPlanks);
    }
    
    @Test
    public void topRoofCoverPlanks720x720() throws GeneralException, MakeOrderException {
        //Arrange
        length = 720;
        width = 720;
        //Act
        Carport result = cc.calculateAll(length, width, true, shed); 
        int expectedCoverPlanks = 180;
        int actualCoverPlanks = (int) result.getCoverPlanks();
        //Assert
        Assert.assertEquals(expectedCoverPlanks, actualCoverPlanks);
    }
}
