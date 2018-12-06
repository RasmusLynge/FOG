package FunctionLayer.Calculate;
 
import FunctionLayer.Entity.Carport;
import FunctionLayer.Exception.GeneralException;
import FunctionLayer.Exception.MakeOrderException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Magnus
 */
public class CarportCalculatorTest {
    //Arrange
    @Rule
    public ExpectedException exceptionRule; 
    
    @Before
    public void setUp() {
        exceptionRule = ExpectedException.none();
    }
    
    CarportCalculator cc = new CarportCalculator();
    PriceCalculator pc = new PriceCalculator();
    int length = 240;
    int width = 240;
    //false means flat roof
    boolean roof = false;
    //false means no shed
    boolean shed = false;

    @Test
    public void PostsTest() throws GeneralException, MakeOrderException {
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
    public void OuterMessurementTest() throws GeneralException, MakeOrderException {
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
    public void RaftersTest() throws GeneralException, MakeOrderException {
        //Arrange
        Carport result = cc.calculateAll(length, width, roof, shed);
        if (roof) {
            //act
            int expectedRafters = 6 + 1;
            int actualRafters = result.getRafter();
            //Assert
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
    public void BeamLengthTest() throws GeneralException, MakeOrderException {
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
    public void TotalLHingesTest() throws GeneralException, MakeOrderException {
        //act
        Carport result = cc.calculateAll(length, width, roof, shed);
        //Assert
        int expectedTotalLHinges = 32;
        int actualTotalLHinges = (int) result.getLHinges();
        Assert.assertEquals(expectedTotalLHinges, actualTotalLHinges);
    }

    @Test
    public void TotalScrewsTest() throws GeneralException, MakeOrderException {
        //act
        Carport result = cc.calculateAll(length, width, roof, shed);
        //Assert
        //Skelettets skruer + dem i taget 
        int expectedTotalScrews = 278;
        int actualTotalScrews = (int) result.getScrews();
        Assert.assertEquals(expectedTotalScrews, actualTotalScrews);
    }

    @Test
    public void TotalScrewBoxesTest() throws GeneralException, MakeOrderException {
        //act
        Carport result = cc.calculateAll(length, width, roof, shed);
        //Assert
        int expectedTotalScrewBoxes = 2;
        int actualTotalScrewBoxes = (int) result.getScrewBoxes();
        Assert.assertEquals(expectedTotalScrewBoxes, actualTotalScrewBoxes);
    }

    @Test(expected = MakeOrderException.class)
    public void TestLowerLimitLenghtException() throws GeneralException, MakeOrderException {
        //act
        //Længden skal være over eller = 240 
        Carport result = cc.calculateAll(200, width, roof, shed);
        //Assert
    }
    
    @Test(expected = MakeOrderException.class)
    public void TestLowerLimitWidthException() throws GeneralException, MakeOrderException {
        //act
        //Bredden skal være over eller = 240 
        Carport result = cc.calculateAll(length, 200, roof, shed);
        //Assert
    }
    @Test(expected = MakeOrderException.class)
    public void TestUpperLimitLenghtException() throws GeneralException, MakeOrderException {
        //act
        //Længden skal være under eller = 720
        Carport result = cc.calculateAll(800, width, roof, shed);
        //Assert
    }
    @Test(expected = MakeOrderException.class)
    public void TestUpperLimitWidthException() throws GeneralException, MakeOrderException {
        //act
        //Bredden skal være under eller = 720
        Carport result = cc.calculateAll(length, 800, roof, shed);
        //Assert
    }
//    @Test
//    public void TestRightInputException() throws GeneralException {
//        //act
//        Carport result = cc.calculateAll(200, 200, roof, shed);
//        //Assert
//        exceptionRule.expect(NumberFormatException.class);
//        String s = Integer.toString(result.getWidth());
//        //Bliver til 200a hvilket bør give NumberFormatException
//        s = s + "a";
//        Integer.parseInt(s);
//    }

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
