package FunctionLayer.Calculate;

import FunctionLayer.Entity.Carport;
import FunctionLayer.Entity.Material;
import FunctionLayer.Exception.DMException;
import FunctionLayer.Exception.MakeOrderException;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PriceCalculatorTest {

    MaterialCalculator mc = new MaterialCalculator();
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
    public void price240x240() throws DMException, MakeOrderException {
        //Arrange
        double actualPrice = 0;
        double expectedPrice = 1750.0;
        //Act
        Carport result = cc.calculateAll(length, width, roof, shed);
        result.setList(mc.materialList(result));
        ArrayList<Material> l = result.getList();
        for (int i = 0; i < l.size(); i++) {
            actualPrice += l.get(i).getAmount() * l.get(i).getPrice();
        }
        //Assert
        Assert.assertEquals(expectedPrice, actualPrice, 0.005);
    }

    @Test
    public void priceTopRoof240x240() throws DMException, MakeOrderException {
        //Arrange
        double actualPrice = 0;
        double expectedPrice = 3033.0;
        //Act
        Carport result = cc.calculateAll(length, width, true, shed);
        result.setList(mc.materialList(result));
        ArrayList<Material> l = result.getList();
        for (int i = 0; i < l.size(); i++) {
            actualPrice += l.get(i).getAmount() * l.get(i).getPrice();
        }
        //Assert
        Assert.assertEquals(expectedPrice, actualPrice, 0.00);
    }

    @Test
    public void priceWithShed240x240() throws DMException, MakeOrderException {
        //Arrange
        double actualPrice = 0;
        double expectedPrice = 2077.0;
        //Act
        Carport result = cc.calculateAll(length, width, roof, true);
        result.setList(mc.materialList(result));
        ArrayList<Material> l = result.getList();
        for (int i = 0; i < l.size(); i++) {
            actualPrice += l.get(i).getAmount() * l.get(i).getPrice();
        }
        //Assert
        Assert.assertEquals(expectedPrice, actualPrice, 0.005);
    }

    @Test
    public void price480x480() throws DMException, MakeOrderException {
        //Arrange
        length = 480;
        width = 480;
        double actualPrice = 0;
        double expectedPrice = 3033.0;
        //Act
        Carport result = cc.calculateAll(length, width, roof, shed);
        result.setList(mc.materialList(result));
        ArrayList<Material> l = result.getList();
        for (int i = 0; i < l.size(); i++) {
            actualPrice += l.get(i).getAmount() * l.get(i).getPrice();
        }
        //Assert
        Assert.assertEquals(expectedPrice, actualPrice, 0.005);
    }

    @Test
    public void priceTopRoof480x480() throws DMException, MakeOrderException {
        //Arrange
        length = 480;
        width = 480;
        double actualPrice = 0;
        double expectedPrice = 5628.0;
        //Act
        Carport result = cc.calculateAll(length, width, true, shed);
        result.setList(mc.materialList(result));
        ArrayList<Material> l = result.getList();
        for (int i = 0; i < l.size(); i++) {
            actualPrice += l.get(i).getAmount() * l.get(i).getPrice();
        }
        //Assert
        Assert.assertEquals(expectedPrice, actualPrice, 0.005);
    }

    @Test
    public void priceWithShed480x480() throws DMException, MakeOrderException {
        //Arrange
        length = 480;
        width = 480;
        double actualPrice = 0;
        double expectedPrice = 3564.0;
        //Act
        Carport result = cc.calculateAll(length, width, roof, true);
        result.setList(mc.materialList(result));
        ArrayList<Material> l = result.getList();
        for (int i = 0; i < l.size(); i++) {
            actualPrice += l.get(i).getAmount() * l.get(i).getPrice();
        }
        //Assert
        Assert.assertEquals(expectedPrice, actualPrice, 0.005);
    }

    @Test
    public void price720x720() throws DMException, MakeOrderException {
        //Arrange
        length = 720;
        width = 720;
        double actualPrice = 0;
        double expectedPrice = 4890.0;
        //Act
        Carport result = cc.calculateAll(length, width, roof, shed);
        result.setList(mc.materialList(result));
        ArrayList<Material> l = result.getList();
        for (int i = 0; i < l.size(); i++) {
            actualPrice += l.get(i).getAmount() * l.get(i).getPrice();
        }
        //Assert
        Assert.assertEquals(expectedPrice, actualPrice, 0.005);
    }

    @Test
    public void priceTopRoof720x720() throws DMException, MakeOrderException {
        //Arrange
        length = 720;
        width = 720;
        double actualPrice = 0;
        double expectedPrice = 11347.0;
        //Act
        Carport result = cc.calculateAll(length, width, true, shed);
        result.setList(mc.materialList(result));
        ArrayList<Material> l = result.getList();
        for (int i = 0; i < l.size(); i++) {
            actualPrice += l.get(i).getAmount() * l.get(i).getPrice();
        }
        //Assert
        Assert.assertEquals(expectedPrice, actualPrice, 0.005);
    }

    @Test
    public void priceWithShed720x720() throws DMException, MakeOrderException {
        //Arrange
        length = 720;
        width = 720;
        double actualPrice = 0;
        double expectedPrice = 5489.0;
        //Act
        Carport result = cc.calculateAll(length, width, roof, true);
        result.setList(mc.materialList(result));
        ArrayList<Material> l = result.getList();
        for (int i = 0; i < l.size(); i++) {
            actualPrice += l.get(i).getAmount() * l.get(i).getPrice();
        }
        //Assert
        Assert.assertEquals(expectedPrice, actualPrice, 0.005);
    }
}
