package FunctionLayer.Calculate;

import FunctionLayer.Calculate.CarportCalculator;
import FunctionLayer.Calculate.PriceCalculator;
import FunctionLayer.Calculate.RoofCalculator;
import FunctionLayer.Entity.Carport;
import FunctionLayer.Exception.GeneralException;
import FunctionLayer.Exception.MakeOrderException;
import org.junit.Assert;
import static org.junit.Assert.fail;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Magnus
 */
public class CarportCalculatorTest {
    //Arrange

    CarportCalculator cc = new CarportCalculator();
    PriceCalculator pc = new PriceCalculator();
    int length = 240;
    int width = 240;
    //false means flat roof
    boolean roof = false;
    //false means no shed
    boolean shed = false;

    @Test
    public void PostsTest() throws GeneralException {
        //act
        Carport result = cc.calculateAll(length, width, roof, shed);
        //Assert
        //90 cm under jorden, og 210 over jorden :)
        int expectedPostLength = 300;
        int actualPostLength = result.getPostLength();
        Assert.assertEquals(expectedPostLength, actualPostLength);

        int expectedPosts = 4;
        int actualPosts = result.getPost();
        Assert.assertEquals(expectedPosts, actualPosts);
    }

    @Test
    public void OuterMessurementTest() throws GeneralException {
        //act
        Carport result = cc.calculateAll(length, width, roof, shed);
        //Assert
        int expectedOuterLength = 375;
        int actualOuterLength = result.getOuterLength();
        Assert.assertEquals(expectedOuterLength, actualOuterLength);

        int expectedOuterWidth = 310;
        int actualOuterWidth = result.getOuterWidth();
        Assert.assertEquals(expectedOuterWidth, actualOuterWidth);
    }

    @Test
    public void RaftersTest() throws GeneralException {
        //act
        Carport result = cc.calculateAll(length, width, roof, shed);
        //Assert
        if (roof) {
            int expectedRafters = 6 + 1;
            int actualRafters = result.getRafter();
            Assert.assertEquals(expectedRafters, actualRafters);

            int expectedRafterSpacing = 62;
            int actualRafterSpacing = (int) result.getRafterSpacing();
            Assert.assertEquals(expectedRafterSpacing, actualRafterSpacing);
        } else {
            int expectedRafters = 6;
            int actualRafters = result.getRafter();
            Assert.assertEquals(expectedRafters, actualRafters);

            int expectedRafterSpacing = 62;
            int actualRafterSpacing = (int) result.getRafterSpacing();
            Assert.assertEquals(expectedRafterSpacing, actualRafterSpacing);
        }

        int expectedRafterLength = 310;
        int actualRafterLength = (int) result.getRafterLength();
        Assert.assertEquals(expectedRafterLength, actualRafterLength);
    }

    @Test
    public void BeamLengthTest() throws GeneralException {
        //act
        Carport result = cc.calculateAll(length, width, roof, shed);
        //Assert
        int expectedBeamLength = 375;
        int actualBeamLength = (int) result.getBeamLength();
        Assert.assertEquals(expectedBeamLength, actualBeamLength);

        if (roof) {
            int expectedBeams = 2 + 1;
            int actualBeams = result.getBeam();
            Assert.assertEquals(expectedBeams, actualBeams);
        } else {
            int expectedBeams = 2;
            int actualBeams = result.getBeam();
            Assert.assertEquals(expectedBeams, actualBeams);
        }
    }

    @Test
    public void TotalLHingesTest() throws GeneralException {
        //act
        Carport result = cc.calculateAll(length, width, roof, shed);
        //Assert
        int expectedTotalLHinges = 32;
        int actualTotalLHinges = (int) result.getLHinges();
        Assert.assertEquals(expectedTotalLHinges, actualTotalLHinges);
    }

    @Test
    public void TotalScrewsTest() throws GeneralException {
        //act
        Carport result = cc.calculateAll(length, width, roof, shed);
        //Assert
        //Skelettets skruer + dem i taget 
        int expectedTotalScrews = 278;
        int actualTotalScrews = (int) result.getScrews();
        Assert.assertEquals(expectedTotalScrews, actualTotalScrews);
    }

    @Test
    public void TotalScrewBoxesTest() throws GeneralException {
        //act
        Carport result = cc.calculateAll(length, width, roof, shed);
        //Assert
        int expectedTotalScrewBoxes = 2;
        int actualTotalScrewBoxes = (int) result.getScrewBoxes();
        Assert.assertEquals(expectedTotalScrewBoxes, actualTotalScrewBoxes);
    }
    
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void TestLimitsException() throws GeneralException {
        //act
        //Længden og bredden skal være mellem 240 og 720
        Carport result = cc.calculateAll(200, 200, roof, shed);
        //Assert
        exceptionRule.expect(MakeOrderException.class);
        
       
    }
    
    @Test
    public void TestRightInputException() throws GeneralException {
        //act
        Carport result = cc.calculateAll(200, 200, roof, shed);
        //Assert
        exceptionRule.expect(NumberFormatException.class);
        String s = Integer.toString(result.getWidth());
        //Bliver til 200a hvilket bør give NumberFormatException
        s = s + "a";
        Integer.parseInt(s);
    }

    //    @Test
//    public void priceTest() throws GeneralException{
//        //Arrange
//        PriceCalculator priceCalculator = new PriceCalculator();
//        int length = 240; 
//        int width = 240;
//            //false means flat roof
//        boolean roof = false;
//            //false means no shed
//        boolean shed = false;
//        //Act
//        double result = priceCalculator.priceCalculator(length, width, roof, shed);
//        
//        //Assert
//        double expected = 1078.4;
//        double actual = result;
//        Assert.assertEquals(expected, actual, 0.005);
//    }
}
