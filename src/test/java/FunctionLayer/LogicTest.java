package FunctionLayer;

import java.util.HashMap;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Magnus
 */
public class LogicTest {
    
    @Test
    public void priceTest() throws GeneralException{
        //Arrange
        PriceCalculator priceCalculator = new PriceCalculator();
        
        //Act
        double res = priceCalculator.priceCalculator(240, 240);
        
        //Assert
        double expected = 1078.4;
        double actual = res;
        Assert.assertEquals(expected, actual, 0.005);
    }
    
    @Test
    public void rafterTotalTest(){
        //Arrange
        CarportCalculator carportCalculator = new CarportCalculator();
        
        //act
        HashMap<String, Integer> hmap = carportCalculator.calculateAll(240, 240);
        
        //Assert
        int expected = 6;
        int actual = hmap.get("totalRafters");
        Assert.assertEquals(expected, actual);
    }

}
