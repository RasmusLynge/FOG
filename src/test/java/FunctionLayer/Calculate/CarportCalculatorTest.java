package FunctionLayer.Calculate;

import FunctionLayer.Entity.Carport;
import FunctionLayer.Exception.DMException;
import FunctionLayer.Exception.MakeOrderException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Magnus
 */
public class CarportCalculatorTest {

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
    public void postsTest240() throws DMException, MakeOrderException {
        //Arrange
        length = 240;
        //Act
        Carport result = cc.calculateAll(length, width, roof, shed);
        //minimum 4 posts
        int expectedPosts = 4;
        int actualPosts = result.getPost();
        //Assert
        Assert.assertEquals(expectedPosts, actualPosts);
    }

    @Test
    public void postsTest400() throws DMException, MakeOrderException {
        //Arrange
        length = 400;
        //Act
        Carport result = cc.calculateAll(length, width, roof, shed);
        int expectedPosts = 6;
        int actualPosts = result.getPost();
        //Assert
        Assert.assertEquals(expectedPosts, actualPosts);
    }

    @Test
    public void postsTest600() throws DMException, MakeOrderException {
        //Arrange
        length = 600;
        //Act
        Carport result = cc.calculateAll(length, width, roof, shed);
        //Assert
        //8 er max antal posts
        int expectedPosts = 8;
        int actualPosts = result.getPost();
        Assert.assertEquals(expectedPosts, actualPosts);
    }

    @Test
    public void postLength() throws DMException, MakeOrderException {
        //Act
        Carport result = cc.calculateAll(length, width, roof, shed);
        //Assert
        //90 cm under jorden, og 210 over jorden :)
        int expectedPostLength = 300;
        int actualPostLength = result.getPostLength();
        Assert.assertEquals(expectedPostLength, actualPostLength);
    }

    @Test
    public void outerLengthTest240() throws DMException, MakeOrderException {
        //Act
        Carport result = cc.calculateAll(length, width, roof, shed);
        //Outer Length vil altid være 135 cm større end den angivede længde
        int expectedOuterLength = 375;
        int actualOuterLength = result.getOuterLength();
        //Assert
        Assert.assertEquals(expectedOuterLength, actualOuterLength);
    }

    @Test
    public void outerLengthTest720() throws DMException, MakeOrderException {
        //Arrange
        length = 720;
        //Act
        Carport result = cc.calculateAll(length, width, roof, shed);
        //Outer Length vil altid være 135 cm større end den angivede længde
        int expectedOuterLength = 855;
        int actualOuterLength = result.getOuterLength();
        //Assert
        Assert.assertEquals(expectedOuterLength, actualOuterLength);
    }

    @Test
    public void outerWidthTest240() throws DMException, MakeOrderException {
        //Act
        Carport result = cc.calculateAll(length, width, roof, shed);
        //Outer width er altid 70 cm længere end den angivede bredde
        int expectedOuterWidth = 310;
        int actualOuterWidth = result.getOuterWidth();
        //Assert
        Assert.assertEquals(expectedOuterWidth, actualOuterWidth);
    }

    @Test
    public void outerWidthTest720() throws DMException, MakeOrderException {
        //Arrange
        width = 720;
        //Act
        Carport result = cc.calculateAll(length, width, roof, shed);
        //Outer width er altid 70 cm længere end den angivede bredde
        int expectedOuterWidth = 790;
        int actualOuterWidth = result.getOuterWidth();
        //Assert
        Assert.assertEquals(expectedOuterWidth, actualOuterWidth);
    }

    @Test
    public void rafterLengthTest240() throws DMException, MakeOrderException {
        //Act
        Carport result = cc.calculateAll(length, width, roof, shed);
        //Rafterne er altid lige så lange som den ydre bredde altså 70 cm længere end den indre bredde
        int expectedRafterLength = 310;
        int actualRafterLength = (int) result.getRafterLength();
        //Assert
        Assert.assertEquals(expectedRafterLength, actualRafterLength);
    }

    @Test
    public void rafterLengthTest720() throws DMException, MakeOrderException {
        //Arrange
        width = 720;
        //Act
        Carport result = cc.calculateAll(length, width, roof, shed);
        //Rafterne er altid lige så lange som den ydre bredde altså 70 cm længere end den indre bredde
        int expectedRafterLength = 790;
        int actualRafterLength = (int) result.getRafterLength();
        //Assert
        Assert.assertEquals(expectedRafterLength, actualRafterLength);
    }

    @Test
    public void amountOfRaftersFlatRoof240() throws DMException, MakeOrderException {
        //Act
        Carport result = cc.calculateAll(length, width, roof, shed);
        //minimum 6 rafters og vokser med en pr 60 cm på længden af carporten
        int expectedRafters = 6;
        int actualRafters = result.getRafter();
        //Assert
        Assert.assertEquals(expectedRafters, actualRafters);
    }

    @Test
    public void amountOfRaftersFlatRoof300() throws DMException, MakeOrderException {
        //Arrange
        length = 300;
        //Act
        Carport result = cc.calculateAll(length, width, roof, shed);
        //minimum 6 rafters med 60 cm i mellem hver
        int expectedRafters = 7;
        int actualRafters = result.getRafter();
        //Assert
        Assert.assertEquals(expectedRafters, actualRafters);
    }

    @Test
    public void amountOfRaftersFlatRoof360() throws DMException, MakeOrderException {
        //Arrange
        length = 360;
        //Act
        Carport result = cc.calculateAll(length, width, roof, shed);
        //minimum 6 rafters med 60 cm i mellem hver
        int expectedRafters = 8;
        int actualRafters = result.getRafter();
        //Assert
        Assert.assertEquals(expectedRafters, actualRafters);
    }

    @Test
    public void amountOfRaftersFlatRoof420() throws DMException, MakeOrderException {
        //Arrange
        length = 420;
        //Act
        Carport result = cc.calculateAll(length, width, roof, shed);
        //minimum 6 rafters med 60 cm i mellem hver
        int expectedRafters = 9;
        int actualRafters = result.getRafter();
        //Assert
        Assert.assertEquals(expectedRafters, actualRafters);
    }

    @Test
    public void amountOfRaftersFlatRoof480() throws DMException, MakeOrderException {
        //Arrange
        length = 480;
        //Act
        Carport result = cc.calculateAll(length, width, roof, shed);
        //minimum 6 rafters med 60 cm i mellem hver
        int expectedRafters = 10;
        int actualRafters = result.getRafter();
        //Assert
        Assert.assertEquals(expectedRafters, actualRafters);
    }

    @Test
    public void amountOfRaftersFlatRoof540() throws DMException, MakeOrderException {
        //Arrange
        length = 540;
        //Act
        Carport result = cc.calculateAll(length, width, roof, shed);
        //minimum 6 rafters med 60 cm i mellem hver
        int expectedRafters = 11;
        int actualRafters = result.getRafter();
        //Assert
        Assert.assertEquals(expectedRafters, actualRafters);
    }

    @Test
    public void amountOfRaftersFlatRoof600() throws DMException, MakeOrderException {
        //Arrange
        length = 600;
        //Act
        Carport result = cc.calculateAll(length, width, roof, shed);
        //minimum 6 rafters med 60 cm i mellem hver
        int expectedRafters = 12;
        int actualRafters = result.getRafter();
        //Assert
        Assert.assertEquals(expectedRafters, actualRafters);
    }

    @Test
    public void amountOfRaftersFlatRoof660() throws DMException, MakeOrderException {
        //Arrange
        length = 660;
        //Act
        Carport result = cc.calculateAll(length, width, roof, shed);
        //minimum 6 rafters med 60 cm i mellem hver
        int expectedRafters = 13;
        int actualRafters = result.getRafter();
        //Assert
        Assert.assertEquals(expectedRafters, actualRafters);
    }

    @Test
    public void amountOfRaftersFlatRoof720() throws DMException, MakeOrderException {
        //Arrange
        length = 720;
        //Act
        Carport result = cc.calculateAll(length, width, roof, shed);
        //minimum 6 rafters med 60 cm i mellem hver
        int expectedRafters = 14;
        int actualRafters = result.getRafter();
        //Assert
        Assert.assertEquals(expectedRafters, actualRafters);
    }

    @Test
    public void amountOfRaftersTopRoof240() throws DMException, MakeOrderException {
        //Act
        Carport result = cc.calculateAll(length, width, true, shed);
        //en carport med rejst tag bruger et mindre antal rafters end en med fladt tag
        int expectedRafters = 3;
        int actualRafters = result.getRafter();
        //Assert
        Assert.assertEquals(expectedRafters, actualRafters);
    }

    @Test
    public void amountOfRaftersTopRoof400() throws DMException, MakeOrderException {
        //Arrange
        length = 400;
        //Act
        Carport result = cc.calculateAll(length, width, true, shed);
        //der går 160 cm mellem hver rafter
        int expectedRafters = 4;
        int actualRafters = result.getRafter();
        //Assert
        Assert.assertEquals(expectedRafters, actualRafters);
    }

    @Test
    public void amountOfRaftersTopRoof600() throws DMException, MakeOrderException {
        //Arrange
        length = 600;
        //Act
        Carport result = cc.calculateAll(length, width, true, shed);
        //der går 160 cm mellem hver rafter
        int expectedRafters = 5;
        int actualRafters = result.getRafter();
        //Assert
        Assert.assertEquals(expectedRafters, actualRafters);
    }

    @Test
    public void testRafterSpacingFlatRoof() throws DMException, MakeOrderException {
        //Act
        Carport result = cc.calculateAll(length, width, roof, shed);
        //Rafter Spacing på en carport med fladt tag er altid så tæt på 60 som muligt
        int expectedRafterSpacing = 62;
        int actualRafterSpacing = (int) result.getRafterSpacing();
        //Assert
        Assert.assertEquals(expectedRafterSpacing, actualRafterSpacing);
    }

    @Test
    public void testRafterSpacingTopRoof() throws DMException, MakeOrderException {
        //Act
        Carport result = cc.calculateAll(length, width, true, shed);
        //Rafter Spacing på en carport med fladt tag er altid så tæt på 60 som muligt
        int expectedRafterSpacing = 120;
        int actualRafterSpacing = (int) result.getRafterSpacing();
        //Assert
        Assert.assertEquals(expectedRafterSpacing, actualRafterSpacing);
    }

    @Test
    public void beamLengthTest240() throws DMException, MakeOrderException {
        //Act
        Carport result = cc.calculateAll(length, width, roof, shed);
        //Beamsne er altid lige så lange som den ydre længde altså 135 cm længere end den indre længde
        int expectedBeamLength = 375;
        int actualBeamLength = (int) result.getBeamLength();
        //Assert
        Assert.assertEquals(expectedBeamLength, actualBeamLength);
    }

    @Test
    public void beamLengthTest720() throws DMException, MakeOrderException {
        //Arrange
        length = 720;
        //Act
        Carport result = cc.calculateAll(length, width, roof, shed);
        //beamse er altid lige så lange som den ydre længde altså 135 cm længere end den indre længde
        int expectedRafterLength = 855;
        int actualRafterLength = (int) result.getBeamLength();
        //Assert
        Assert.assertEquals(expectedRafterLength, actualRafterLength);
    }

    @Test
    public void amountOfBeamsFlatRoof240x240() throws DMException, MakeOrderException {
        //Act
        Carport result = cc.calculateAll(length, width, roof, shed);
        //mængde af beams vil altid være 2 på en carport med fladt tag
        int expectedBeams = 2;
        int actualBeams = result.getBeam();
        //Assert
        Assert.assertEquals(expectedBeams, actualBeams);
    }

    @Test
    public void amountOfBeamsFlatRoof720x720() throws DMException, MakeOrderException {
        //Arrange
        length = 720;
        width = 720;
        //Act
        Carport result = cc.calculateAll(length, width, roof, shed);
        //mængde af beams vil altid være 2 på en carport med fladt tag
        int expectedBeams = 2;
        int actualBeams = result.getBeam();
        //Assert
        Assert.assertEquals(expectedBeams, actualBeams);
    }

    @Test
    public void amountOfBeamsTopRoof240x240() throws DMException, MakeOrderException {
        //Act
        Carport result = cc.calculateAll(length, width, true, shed);
        //mængde af beams vil altid være 3 på en carport med rejst tag
        int expectedBeams = 3;
        int actualBeams = result.getBeam();
        //Assert
        Assert.assertEquals(expectedBeams, actualBeams);
    }

    @Test
    public void amountOfBeamsTopRoof720x720() throws DMException, MakeOrderException {
        //Arrange
        length = 720;
        width = 720;
        //Act
        Carport result = cc.calculateAll(length, width, true, shed);
        //mængde af beams vil altid være 3 på en carport med rejst tag
        int expectedBeams = 3;
        int actualBeams = result.getBeam();
        //Assert
        Assert.assertEquals(expectedBeams, actualBeams);
    }

    @Test
    public void totalLHinges240x240() throws DMException, MakeOrderException {
        //Act
        Carport result = cc.calculateAll(length, width, roof, shed);
        int expectedTotalLHinges = 32;
        int actualTotalLHinges = (int) result.getLHinges();
        //Assert
        Assert.assertEquals(expectedTotalLHinges, actualTotalLHinges);
    }
    
    @Test
    public void totalLHingesTopRoof240x240() throws DMException, MakeOrderException {
        //Act
        Carport result = cc.calculateAll(length, width, true, shed);
        int expectedTotalLHinges = 20;
        int actualTotalLHinges = (int) result.getLHinges();
        //Assert
        Assert.assertEquals(expectedTotalLHinges, actualTotalLHinges);
    }
    
    @Test
    public void totalLHinges480x480() throws DMException, MakeOrderException {
        //Arrange
        length = 480;
        width = 480;
        //Act
        Carport result = cc.calculateAll(length, width, roof, shed);
        int expectedTotalLHinges = 48;
        int actualTotalLHinges = (int) result.getLHinges();
        //Assert
        Assert.assertEquals(expectedTotalLHinges, actualTotalLHinges);
    }
    
    @Test
    public void totalLHingesTopRoof480x480() throws DMException, MakeOrderException {
        //Arrange
        length = 480;
        width = 480;
        //Act
        Carport result = cc.calculateAll(length, width, true, shed);
        int expectedTotalLHinges = 24;
        int actualTotalLHinges = (int) result.getLHinges();
        //Assert
        Assert.assertEquals(expectedTotalLHinges, actualTotalLHinges);
    }
    
    @Test
    public void totalLHinges720x720() throws DMException, MakeOrderException {
        //Arrange
        length = 720;
        width = 720;
        //Act
        Carport result = cc.calculateAll(length, width, roof, shed);
        int expectedTotalLHinges = 64;
        int actualTotalLHinges = (int) result.getLHinges();
        //Assert
        Assert.assertEquals(expectedTotalLHinges, actualTotalLHinges);
    }
    
    @Test
    public void totalLHingesTopRoof720x720() throws DMException, MakeOrderException {
        //Arrange
        length = 720;
        width = 720;
        //Act
        Carport result = cc.calculateAll(length, width, true, shed);
        int expectedTotalLHinges = 28;
        int actualTotalLHinges = (int) result.getLHinges();
        //Assert
        Assert.assertEquals(expectedTotalLHinges, actualTotalLHinges);
    }

    @Test
    public void totalScrews240x240() throws DMException, MakeOrderException {
        //Act
        Carport result = cc.calculateAll(length, width, roof, shed);
        //Skelettets skruer + dem i taget 
        int expectedTotalScrews = 278;
        int actualTotalScrews = (int) result.getScrews();
        //Assert
        Assert.assertEquals(expectedTotalScrews, actualTotalScrews);
    }
    
    @Test
    public void totalScrewsTopRoof240x240() throws DMException, MakeOrderException {
        //Act
        Carport result = cc.calculateAll(length, width, true, shed);
        //Skelettets skruer + dem i taget 
        int expectedTotalScrews = 164;
        int actualTotalScrews = (int) result.getScrews();
        //Assert
        Assert.assertEquals(expectedTotalScrews, actualTotalScrews);
    }
    
    @Test
    public void totalScrews480x480() throws DMException, MakeOrderException {
        //Arrange
        length = 480;
        width = 480;
        //Act
        Carport result = cc.calculateAll(length, width, roof, shed);
        //Skelettets skruer + dem i taget 
        int expectedTotalScrews = 642;
        int actualTotalScrews = (int) result.getScrews();
        //Assert
        Assert.assertEquals(expectedTotalScrews, actualTotalScrews);
    }
    
    @Test
    public void totalScrewsTopRoof480x480() throws DMException, MakeOrderException {
        //Arrange
        length = 480;
        width = 480;
        //Act
        Carport result = cc.calculateAll(length, width, true, shed);
        //Skelettets skruer + dem i taget 
        int expectedTotalScrews = 208;
        int actualTotalScrews = (int) result.getScrews();
        //Assert
        Assert.assertEquals(expectedTotalScrews, actualTotalScrews);
    }

    @Test
    public void totalScrews720x720() throws DMException, MakeOrderException {
        //Arrange
        length = 720;
        width = 720;
        //Act
        Carport result = cc.calculateAll(length, width, roof, shed);
        //Skelettets skruer + dem i taget 
        int expectedTotalScrews = 1166;
        int actualTotalScrews = (int) result.getScrews();
        //Assert
        Assert.assertEquals(expectedTotalScrews, actualTotalScrews);
    }
    
    @Test
    public void totalScrewsTopRoof720x720() throws DMException, MakeOrderException {
        //Arrange
        length = 720;
        width = 720;
        //Act
        Carport result = cc.calculateAll(length, width, true, shed);
        //Skelettets skruer + dem i taget 
        int expectedTotalScrews = 252;
        int actualTotalScrews = (int) result.getScrews();
        //Assert
        Assert.assertEquals(expectedTotalScrews, actualTotalScrews);
    }
    
    @Test
    public void totalScrewBoxes240x240() throws DMException, MakeOrderException {
        //Act
        Carport result = cc.calculateAll(length, width, roof, shed);
        int expectedTotalScrewBoxes = 2;
        int actualTotalScrewBoxes = (int) result.getScrewBoxes();
        //Assert
        Assert.assertEquals(expectedTotalScrewBoxes, actualTotalScrewBoxes);
    }
    
    @Test
    public void totalScrewBoxesTopRoof240x240() throws DMException, MakeOrderException {
        //Act
        Carport result = cc.calculateAll(length, width, roof, shed);
        //should be 1
        int expectedTotalScrewBoxes = 1;
        int actualTotalScrewBoxes = (int) result.getScrewBoxes();
        //Assert
        Assert.assertEquals(expectedTotalScrewBoxes, actualTotalScrewBoxes);
    }
    
    @Test
    public void totalScrewBoxes480x480() throws DMException, MakeOrderException {
        //Arrange
        length = 480;
        width = 480;
        //Act
        Carport result = cc.calculateAll(length, width, roof, shed);
        int expectedTotalScrewBoxes = 4;
        int actualTotalScrewBoxes = (int) result.getScrewBoxes();
        //Assert
        Assert.assertEquals(expectedTotalScrewBoxes, actualTotalScrewBoxes);
    }
    
    @Test
    public void totalScrewBoxesTopRoof480x480() throws DMException, MakeOrderException {
        //Arrange
        length = 480;
        width = 480;
        //Act
        Carport result = cc.calculateAll(length, width, true, shed);
        int expectedTotalScrewBoxes = 2;
        int actualTotalScrewBoxes = (int) result.getScrewBoxes();
        //Assert
        Assert.assertEquals(expectedTotalScrewBoxes, actualTotalScrewBoxes);
    }
    
    @Test
    public void totalScrewBoxes720x720() throws DMException, MakeOrderException {
        //Arrange
        length = 720;
        width = 720;
        //Act
        Carport result = cc.calculateAll(length, width, roof, shed);
        int expectedTotalScrewBoxes = 6;
        int actualTotalScrewBoxes = (int) result.getScrewBoxes();
        //Assert
        Assert.assertEquals(expectedTotalScrewBoxes, actualTotalScrewBoxes);
    }
    
    @Test
    public void totalScrewBoxesTopRoof720x720() throws DMException, MakeOrderException {
        //Arrange
        length = 720;
        width = 720;
        //Act
        Carport result = cc.calculateAll(length, width, true, shed);
        int expectedTotalScrewBoxes = 2;
        int actualTotalScrewBoxes = (int) result.getScrewBoxes();
        //Assert
        Assert.assertEquals(expectedTotalScrewBoxes, actualTotalScrewBoxes);
    }

    @Test(expected = MakeOrderException.class)
    public void testLowerLimitLenghtException() throws DMException, MakeOrderException {
        //Længden skal være over eller = 240 
        cc.calculateAll(200, width, roof, shed);
    }

    @Test(expected = MakeOrderException.class)
    public void testLowerLimitWidthException() throws DMException, MakeOrderException {
        //Bredden skal være over eller = 240 
        cc.calculateAll(length, 200, roof, shed);
    }

    @Test(expected = MakeOrderException.class)
    public void testUpperLimitLenghtException() throws DMException, MakeOrderException {
        //Længden skal være under eller = 720
        cc.calculateAll(800, width, roof, shed);
    }

    @Test(expected = MakeOrderException.class)
    public void testUpperLimitWidthException() throws DMException, MakeOrderException {
        //Bredden skal være under eller = 720
        cc.calculateAll(length, 800, roof, shed);
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
