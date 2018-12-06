/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer.Calculate;

import FunctionLayer.Calculate.CarportCalculator;
import FunctionLayer.Calculate.PriceCalculator;
import FunctionLayer.Entity.Carport;
import FunctionLayer.Exception.GeneralException;
import FunctionLayer.Exception.MakeOrderException;
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
 */
public class RoofCalculatorTest {
    //Arrange
    CarportCalculator cc = new CarportCalculator();
    int length = 240;
    int width = 240;
    //false means flat roof
    boolean roof = false;
    //false means no shed
    boolean shed = false;

    @Test
    public void TestFlatRoof() throws GeneralException, MakeOrderException {
        //act
        Carport result = cc.calculateAll(length, width, roof, shed);
        //Assert
        int expectedPlastmoLong = 4;
        int actualPlastmoLong = (int) result.getPlastmoLong();
        Assert.assertEquals(expectedPlastmoLong, actualPlastmoLong);
        
        int expectedPlastmoSmall = 0;
        int actualPlastmoSmall = (int) result.getPlastmoSmall();
        Assert.assertEquals(expectedPlastmoSmall, actualPlastmoSmall);
    }
    
    @Test
    public void TestTopRoofPost() throws GeneralException, MakeOrderException {
        //act
        Carport result = cc.calculateAll(length, width, true, shed);
        //Assert
        int expectedRoofPostHeight = 309;
        int actualRoofPostHeight = (int) result.getRoofPostHeight();
        Assert.assertEquals(expectedRoofPostHeight, actualRoofPostHeight);
        
        int expectedRoofPost = 3;
        int actualRoofPost = (int) result.getRoofPost();
        Assert.assertEquals(expectedRoofPost, actualRoofPost);
    }
    
    @Test
    public void TestTopRoofRafter() throws GeneralException, MakeOrderException {
        //act
        Carport result = cc.calculateAll(length, width, true, shed);
        //Assert
        int expectedRoofRafterLength = 345;
        int actualRoofRafterLength = (int) result.getRoofRafterLength();
        Assert.assertEquals(expectedRoofRafterLength, actualRoofRafterLength);
        
        int expectedRoofRafter = 6;
        int actualRoofRafter = (int) result.getRoofRafter();
        Assert.assertEquals(expectedRoofRafter, actualRoofRafter);
    }
    
    @Test
    public void TestTopRoofBeamsPrSide() throws GeneralException, MakeOrderException {
        //act
        Carport result = cc.calculateAll(length, width, true, shed);
        //Assert
        int expectedRoofBeams = 7;
        int actualRoofBeams = (int) result.getRoofBeams();
        Assert.assertEquals(expectedRoofBeams, actualRoofBeams);
    }
    
    @Test
    public void TestTopRoofTiles() throws GeneralException, MakeOrderException {
        //act
        Carport result = cc.calculateAll(length, width, true, shed);
        //Assert
        int expectedRoofTiles = 69;
        int actualRoofTiles = (int) result.getRoofTiles();
        Assert.assertEquals(expectedRoofTiles, actualRoofTiles);
    }
    
    @Test
    public void TestTopFlatHinges() throws GeneralException, MakeOrderException {
        //act
        Carport result = cc.calculateAll(length, width, true, shed);
        //Assert
        int expectedFlatHinges = 6;
        int actualFlatHinges = (int) result.getFlatHinges();
        Assert.assertEquals(expectedFlatHinges, actualFlatHinges);
    }
}

