package FunctionLayer.Calculate;

import FunctionLayer.Entity.Carport;
import FunctionLayer.Exception.DMException;
import FunctionLayer.Exception.MakeOrderException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class RoofCalculatorTest {

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
    public void flatRoofPlasmoLong240x240() throws DMException, MakeOrderException {
        //Act
        Carport result = cc.calculateAll(length, width, roof, shed);
        int expectedPlastmoLong = 4;
        int actualPlastmoLong = (int) result.getPlastmoLong();
        //Assert
        Assert.assertEquals(expectedPlastmoLong, actualPlastmoLong);
    }

    @Test
    public void flatRoofPlasmoLong480x480() throws DMException, MakeOrderException {
        //Arrange
        length = 480;
        width = 480;
        //Act
        Carport result = cc.calculateAll(length, width, roof, shed);
        int expectedPlastmoLong = 12;
        int actualPlastmoLong = (int) result.getPlastmoLong();
        //Assert
        Assert.assertEquals(expectedPlastmoLong, actualPlastmoLong);
    }

    @Test
    public void flatRoofPlasmoLong720x720() throws DMException, MakeOrderException {
        //Arrange
        length = 720;
        width = 720;
        //Act
        Carport result = cc.calculateAll(length, width, roof, shed);
        int expectedPlastmoLong = 16;
        int actualPlastmoLong = (int) result.getPlastmoLong();
        //Assert
        Assert.assertEquals(expectedPlastmoLong, actualPlastmoLong);
    }

    @Test
    public void flatRoofPlastmoSmall240x240() throws DMException, MakeOrderException {
        //Act
        Carport result = cc.calculateAll(length, width, roof, shed);
        int expectedPlastmoSmall = 0;
        int actualPlastmoSmall = (int) result.getPlastmoSmall();
        //Assert
        Assert.assertEquals(expectedPlastmoSmall, actualPlastmoSmall);
    }

    @Test
    public void flatRoofPlastmoSmall480x480() throws DMException, MakeOrderException {
        //Arrange
        length = 480;
        width = 480;
        //Act
        Carport result = cc.calculateAll(length, width, roof, shed);
        int expectedPlastmoSmall = 0;
        int actualPlastmoSmall = (int) result.getPlastmoSmall();
        //Assert
        Assert.assertEquals(expectedPlastmoSmall, actualPlastmoSmall);
    }

    @Test
    public void flatRoofPlastmoSmall720x720() throws DMException, MakeOrderException {
        //Arrange
        length = 720;
        width = 720;
        //Act
        Carport result = cc.calculateAll(length, width, roof, shed);
        int expectedPlastmoSmall = 8;
        int actualPlastmoSmall = (int) result.getPlastmoSmall();
        //Assert
        Assert.assertEquals(expectedPlastmoSmall, actualPlastmoSmall);
    }

    @Test
    public void topRoofPostHeight240x240() throws DMException, MakeOrderException {
        //Act
        Carport result = cc.calculateAll(length, width, true, shed);
        int expectedRoofPostHeight = 41;
        int actualRoofPostHeight = (int) result.getRoofPostHeight();
        //Assert
        Assert.assertEquals(expectedRoofPostHeight, actualRoofPostHeight);
    }

    @Test
    public void topRoofPostHeight480x480() throws DMException, MakeOrderException {
        //Arrange
        length = 480;
        width = 480;
        //Act
        Carport result = cc.calculateAll(length, width, true, shed);
        int expectedRoofPostHeight = 73;
        int actualRoofPostHeight = (int) result.getRoofPostHeight();
        //Assert
        Assert.assertEquals(expectedRoofPostHeight, actualRoofPostHeight);
    }

    @Test
    public void topRoofPostHeight720x720() throws DMException, MakeOrderException {
        //Arrange
        length = 720;
        width = 720;
        //Act
        Carport result = cc.calculateAll(length, width, true, shed);
        int expectedRoofPostHeight = 105;
        int actualRoofPostHeight = (int) result.getRoofPostHeight();
        //Assert
        Assert.assertEquals(expectedRoofPostHeight, actualRoofPostHeight);
    }

    @Test
    public void topRoofPosts240x240() throws DMException, MakeOrderException {
        //Act
        Carport result = cc.calculateAll(length, width, true, shed);
        int expectedRoofPost = 3;
        int actualRoofPost = (int) result.getRoofPost();
        //Assert
        Assert.assertEquals(expectedRoofPost, actualRoofPost);
    }

    @Test
    public void topRoofPosts480x480() throws DMException, MakeOrderException {
        //Arrange
        length = 480;
        width = 480;
        //Act
        Carport result = cc.calculateAll(length, width, true, shed);
        int expectedRoofPost = 4;
        int actualRoofPost = (int) result.getRoofPost();
        //Assert
        Assert.assertEquals(expectedRoofPost, actualRoofPost);
    }

    @Test
    public void topRoofPosts720x720() throws DMException, MakeOrderException {
        //Arrange
        length = 720;
        width = 720;
        //Act
        Carport result = cc.calculateAll(length, width, true, shed);
        int expectedRoofPost = 5;
        int actualRoofPost = (int) result.getRoofPost();
        //Assert
        Assert.assertEquals(expectedRoofPost, actualRoofPost);
    }

    @Test
    public void topRoofRafterLength240x240() throws DMException, MakeOrderException {
        //Act
        Carport result = cc.calculateAll(length, width, true, shed);
        int expectedRoofRafterLength = 160;
        int actualRoofRafterLength = (int) result.getRoofRafterLength();
        //Assert
        Assert.assertEquals(expectedRoofRafterLength, actualRoofRafterLength);
    }

    @Test
    public void topRoofRafterLength480x480() throws DMException, MakeOrderException {
        //Arrange
        length = 480;
        width = 480;
        //Act
        Carport result = cc.calculateAll(length, width, true, shed);
        int expectedRoofRafterLength = 284;
        int actualRoofRafterLength = (int) result.getRoofRafterLength();
        //Assert
        Assert.assertEquals(expectedRoofRafterLength, actualRoofRafterLength);
    }

    @Test
    public void topRoofRafterLength720x720() throws DMException, MakeOrderException {
        //Arrange
        length = 720;
        width = 720;
        //Act
        Carport result = cc.calculateAll(length, width, true, shed);
        int expectedRoofRafterLength = 408;
        int actualRoofRafterLength = (int) result.getRoofRafterLength();
        //Assert
        Assert.assertEquals(expectedRoofRafterLength, actualRoofRafterLength);
    }

    @Test
    public void topRoofRafters240x240() throws DMException, MakeOrderException {
        //Act
        Carport result = cc.calculateAll(length, width, true, shed);
        int expectedRoofRafter = 6;
        int actualRoofRafter = (int) result.getRoofRafter();
        //Assert
        Assert.assertEquals(expectedRoofRafter, actualRoofRafter);
    }

    @Test
    public void topRoofRafters480x480() throws DMException, MakeOrderException {
        //Arrange
        length = 480;
        width = 480;
        //Act
        Carport result = cc.calculateAll(length, width, true, shed);
        int expectedRoofRafter = 8;
        int actualRoofRafter = (int) result.getRoofRafter();
        //Assert
        Assert.assertEquals(expectedRoofRafter, actualRoofRafter);
    }

    @Test
    public void topRoofRafters720x720() throws DMException, MakeOrderException {
        //Arrange
        length = 720;
        width = 720;
        //Act
        Carport result = cc.calculateAll(length, width, true, shed);
        int expectedRoofRafter = 10;
        int actualRoofRafter = (int) result.getRoofRafter();
        //Assert
        Assert.assertEquals(expectedRoofRafter, actualRoofRafter);
    }

    @Test
    public void topRoofBeamsPrSide240x240() throws DMException, MakeOrderException {
        //Act
        Carport result = cc.calculateAll(length, width, true, shed);
        int expectedRoofBeams = 3;
        int actualRoofBeams = (int) result.getRoofBeams();
        //Assert
        Assert.assertEquals(expectedRoofBeams, actualRoofBeams);
    }

    @Test
    public void topRoofBeamsPrSide480x480() throws DMException, MakeOrderException {
        //Arrange
        length = 480;
        width = 480;
        //Act
        Carport result = cc.calculateAll(length, width, true, shed);
        int expectedRoofBeams = 6;
        int actualRoofBeams = (int) result.getRoofBeams();
        //Assert
        Assert.assertEquals(expectedRoofBeams, actualRoofBeams);
    }

    @Test
    public void topRoofBeamsPrSide720x720() throws DMException, MakeOrderException {
        //Arrange
        length = 720;
        width = 720;
        //Act
        Carport result = cc.calculateAll(length, width, true, shed);
        int expectedRoofBeams = 9;
        int actualRoofBeams = (int) result.getRoofBeams();
        //Assert
        Assert.assertEquals(expectedRoofBeams, actualRoofBeams);
    }

    @Test
    public void topRoofTiles240x240() throws DMException, MakeOrderException {
        //Act
        Carport result = cc.calculateAll(length, width, true, shed);
        int expectedRoofTiles = 32;
        int actualRoofTiles = (int) result.getRoofTiles();
        //Assert
        Assert.assertEquals(expectedRoofTiles, actualRoofTiles);
    }

    @Test
    public void topRoofTiles480x480() throws DMException, MakeOrderException {
        //Arrange
        length = 480;
        width = 480;
        //Act
        Carport result = cc.calculateAll(length, width, true, shed);
        int expectedRoofTiles = 79;
        int actualRoofTiles = (int) result.getRoofTiles();
        //Assert
        Assert.assertEquals(expectedRoofTiles, actualRoofTiles);
    }

    @Test
    public void topRoofTiles720x720() throws DMException, MakeOrderException {
        //Arrange
        length = 720;
        width = 720;
        //Act
        Carport result = cc.calculateAll(length, width, true, shed);
        int expectedRoofTiles = 146;
        int actualRoofTiles = (int) result.getRoofTiles();
        //Assert
        Assert.assertEquals(expectedRoofTiles, actualRoofTiles);
    }

    @Test
    public void topRoofFlatHinges240x240() throws DMException, MakeOrderException {
        //Act
        Carport result = cc.calculateAll(length, width, true, shed);
        int expectedFlatHinges = 6;
        int actualFlatHinges = (int) result.getFlatHinges();
        //Assert
        Assert.assertEquals(expectedFlatHinges, actualFlatHinges);
    }

    @Test
    public void topRoofFlatHinges480x480() throws DMException, MakeOrderException {
        //Arrange
        length = 480;
        width = 480;
        //Act
        Carport result = cc.calculateAll(length, width, true, shed);
        int expectedFlatHinges = 8;
        int actualFlatHinges = (int) result.getFlatHinges();
        //Assert
        Assert.assertEquals(expectedFlatHinges, actualFlatHinges);
    }

    @Test
    public void topRoofFlatHinges720x720() throws DMException, MakeOrderException {
        //Arrange
        length = 720;
        width = 720;
        //Act
        Carport result = cc.calculateAll(length, width, true, shed);
        int expectedFlatHinges = 10;
        int actualFlatHinges = (int) result.getFlatHinges();
        //Assert
        Assert.assertEquals(expectedFlatHinges, actualFlatHinges);
    }
}
