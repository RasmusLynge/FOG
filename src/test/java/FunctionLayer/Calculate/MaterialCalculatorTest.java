package FunctionLayer.Calculate;

import FunctionLayer.Entity.Carport;
import FunctionLayer.Entity.Material;
import FunctionLayer.Exception.DMException;
import FunctionLayer.Exception.MakeOrderException;
import static FunctionLayer.Rule.Rules.PLANKLENGTH;
import static FunctionLayer.Rule.Rules.PLASTMOLENGTHLONG;
import static FunctionLayer.Rule.Rules.PLASTMOLENGTHSMALL;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MaterialCalculatorTest {

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
    public void plastmoLongAmountFlatRoof240x240() throws DMException, MakeOrderException {
        //Act
        Carport result = cc.calculateAll(length, width, roof, shed);
        result.setList(mc.materialList(result));
        ArrayList<Material> l = result.getList();
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getName().equals("Plastmo Ecolite blåtonet") && l.get(i).getLength() == PLASTMOLENGTHLONG) {
                //bruges kun til fladt tag som den er her derfor 4
                int expectedPlastmoLongAmount = 4;
                int actualPlastmoLongAmount = l.get(i).getAmount();
                //Assert
                Assert.assertEquals(expectedPlastmoLongAmount, actualPlastmoLongAmount);
            }
        }
    }

    @Test
    public void plastmoLongAmountFlatRoof480x480() throws DMException, MakeOrderException {
        //Arrange
        length = 480;
        width = 480;
        //Act
        Carport result = cc.calculateAll(length, width, roof, shed);
        result.setList(mc.materialList(result));
        ArrayList<Material> l = result.getList();
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getName().equals("Plastmo Ecolite blåtonet") && l.get(i).getLength() == PLASTMOLENGTHLONG) {
                //bruges kun til fladt tag som den er her derfor 12
                int expectedPlastmoLongAmount = 12;
                int actualPlastmoLongAmount = l.get(i).getAmount();
                //Assert
                Assert.assertEquals(expectedPlastmoLongAmount, actualPlastmoLongAmount);
            }
        }
    }

    @Test
    public void plastmoLongAmountFlatRoof720x720() throws DMException, MakeOrderException {
        //Arrange
        length = 720;
        width = 720;
        //Act
        Carport result = cc.calculateAll(length, width, roof, shed);
        result.setList(mc.materialList(result));
        ArrayList<Material> l = result.getList();
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getName().equals("Plastmo Ecolite blåtonet") && l.get(i).getLength() == PLASTMOLENGTHLONG) {
                //bruges kun til fladt tag som den er her derfor 16
                int expectedPlastmoLongAmount = 16;
                int actualPlastmoLongAmount = l.get(i).getAmount();
                //Assert
                Assert.assertEquals(expectedPlastmoLongAmount, actualPlastmoLongAmount);
            }
        }
    }

    @Test
    public void plastmoLongAmountTopRoof() throws DMException, MakeOrderException {
        //Act
        Carport result = cc.calculateAll(length, width, true, shed);
        result.setList(mc.materialList(result));
        ArrayList<Material> l = result.getList();
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getName().equals("Plastmo Ecolite blåtonet") && l.get(i).getLength() == PLASTMOLENGTHLONG) {
                //bruges kun til fladt tag, så bør være 0
                int expectedPlastmoLongAmount = 0;
                int actualPlastmoLongAmount = l.get(i).getAmount();
                //Assert
                Assert.assertEquals(expectedPlastmoLongAmount, actualPlastmoLongAmount);
            }
        }
    }

    @Test
    public void plastmoSmallAmountFlatRoof240x240() throws DMException, MakeOrderException {
        //Act
        Carport result = cc.calculateAll(length, width, roof, shed);
        result.setList(mc.materialList(result));
        ArrayList<Material> l = result.getList();
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getName().equals("Plastmo Ecolite blåtonet") && l.get(i).getLength() == PLASTMOLENGTHSMALL) {
                //bruges kun til fladt tag som den er her derfor 16
                int expectedPlastmoSmallAmount = 0;
                int actualPlastmoSmallAmount = l.get(i).getAmount();
                //Assert
                Assert.assertEquals(expectedPlastmoSmallAmount, actualPlastmoSmallAmount);
            }
        }
    }

    @Test
    public void plastmoSmallAmountFlatRoof480x480() throws DMException, MakeOrderException {
        //Arrange
        length = 480;
        width = 480;
        //Act
        Carport result = cc.calculateAll(length, width, roof, shed);
        result.setList(mc.materialList(result));
        ArrayList<Material> l = result.getList();
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getName().equals("Plastmo Ecolite blåtonet") && l.get(i).getLength() == PLASTMOLENGTHSMALL) {
                //bruges kun til fladt tag som den er her derfor 16
                int expectedPlastmoSmallAmount = 0;
                int actualPlastmoSmallAmount = l.get(i).getAmount();
                //Assert
                Assert.assertEquals(expectedPlastmoSmallAmount, actualPlastmoSmallAmount);
            }
        }
    }

    @Test
    public void plastmoSmallAmountFlatRoof720x720() throws DMException, MakeOrderException {
        //Arrange
        length = 720;
        width = 720;
        //Act
        Carport result = cc.calculateAll(length, width, roof, shed);
        result.setList(mc.materialList(result));
        ArrayList<Material> l = result.getList();
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getName().equals("Plastmo Ecolite blåtonet") && l.get(i).getLength() == PLASTMOLENGTHSMALL) {
                //bruges kun til fladt tag som den er her derfor 16
                int expectedPlastmoSmallAmount = 8;
                int actualPlastmoSmallAmount = l.get(i).getAmount();
                //Assert
                Assert.assertEquals(expectedPlastmoSmallAmount, actualPlastmoSmallAmount);
            }
        }
    }

    @Test
    public void plastmoSmallAmountTopRoof() throws DMException, MakeOrderException {
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
    public void flatHingesAmount240x240() throws DMException, MakeOrderException {
        //Act
        Carport result = cc.calculateAll(length, width, roof, shed);
        result.setList(mc.materialList(result));
        ArrayList<Material> l = result.getList();
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getName().equals("FladtBeslag")) {
                int expectedFlatHingesAmount = 0;
                int actualFlatHingesAmount = l.get(i).getAmount();
                //Assert
                Assert.assertEquals(expectedFlatHingesAmount, actualFlatHingesAmount);
            }
        }
    }

    @Test
    public void flatHingesAmountTopRoof240x240() throws DMException, MakeOrderException {
        //Act
        Carport result = cc.calculateAll(length, width, true, shed);
        result.setList(mc.materialList(result));
        ArrayList<Material> l = result.getList();
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getName().equals("FladtBeslag")) {
                int expectedFlatHingesAmount = 6;
                int actualFlatHingesAmount = l.get(i).getAmount();
                //Assert
                Assert.assertEquals(expectedFlatHingesAmount, actualFlatHingesAmount);
            }
        }
    }

    @Test
    public void flatHingesAmountWithShed240x240() throws DMException, MakeOrderException {
        //Act
        Carport result = cc.calculateAll(length, width, roof, true);
        result.setList(mc.materialList(result));
        ArrayList<Material> l = result.getList();
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getName().equals("FladtBeslag")) {
                int expectedFlatHingesAmount = 0;
                int actualFlatHingesAmount = l.get(i).getAmount();
                //Assert
                Assert.assertEquals(expectedFlatHingesAmount, actualFlatHingesAmount);
            }
        }
    }

    @Test
    public void flatHingesAmount480x480() throws DMException, MakeOrderException {
        //Arrange
        length = 480;
        width = 480;
        //Act
        Carport result = cc.calculateAll(length, width, roof, shed);
        result.setList(mc.materialList(result));
        ArrayList<Material> l = result.getList();
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getName().equals("FladtBeslag")) {
                int expectedFlatHingesAmount = 4;
                int actualFlatHingesAmount = l.get(i).getAmount();
                //Assert
                Assert.assertEquals(expectedFlatHingesAmount, actualFlatHingesAmount);
            }
        }
    }

    @Test
    public void flatHingesAmountTopRoof480x480() throws DMException, MakeOrderException {
        //Arrange
        length = 480;
        width = 480;
        //Act
        Carport result = cc.calculateAll(length, width, true, shed);
        result.setList(mc.materialList(result));
        ArrayList<Material> l = result.getList();
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getName().equals("FladtBeslag")) {
                int expectedFlatHingesAmount = 12;
                int actualFlatHingesAmount = l.get(i).getAmount();
                //Assert
                Assert.assertEquals(expectedFlatHingesAmount, actualFlatHingesAmount);
            }
        }
    }

    @Test
    public void flatHingesAmountWithShed480x480() throws DMException, MakeOrderException {
        //Arrange
        length = 480;
        width = 480;
        //Act
        Carport result = cc.calculateAll(length, width, roof, true);
        result.setList(mc.materialList(result));
        ArrayList<Material> l = result.getList();
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getName().equals("FladtBeslag")) {
                int expectedFlatHingesAmount = 12;
                int actualFlatHingesAmount = l.get(i).getAmount();
                //Assert
                Assert.assertEquals(expectedFlatHingesAmount, actualFlatHingesAmount);
            }
        }
    }

    @Test
    public void flatHingesAmount720x720() throws DMException, MakeOrderException {
        //Arrange
        length = 720;
        width = 720;
        //Act
        Carport result = cc.calculateAll(length, width, roof, shed);
        result.setList(mc.materialList(result));
        ArrayList<Material> l = result.getList();
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getName().equals("FladtBeslag")) {
                int expectedFlatHingesAmount = 20;
                int actualFlatHingesAmount = l.get(i).getAmount();
                //Assert
                Assert.assertEquals(expectedFlatHingesAmount, actualFlatHingesAmount);
            }
        }
    }

    @Test
    public void flatHingesAmountTopRoof720x720() throws DMException, MakeOrderException {
        //Arrange
        length = 720;
        width = 720;
        //Act
        Carport result = cc.calculateAll(length, width, true, shed);
        result.setList(mc.materialList(result));
        ArrayList<Material> l = result.getList();
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getName().equals("FladtBeslag")) {
                int expectedFlatHingesAmount = 40;
                int actualFlatHingesAmount = l.get(i).getAmount();
                //Assert
                Assert.assertEquals(expectedFlatHingesAmount, actualFlatHingesAmount);
            }
        }
    }

    @Test
    public void flatHingesAmountWithShed720x720() throws DMException, MakeOrderException {
        //Arrange
        length = 720;
        width = 720;
        //Act
        Carport result = cc.calculateAll(length, width, roof, true);
        result.setList(mc.materialList(result));
        ArrayList<Material> l = result.getList();
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getName().equals("FladtBeslag")) {
                int expectedFlatHingesAmount = 30;
                int actualFlatHingesAmount = l.get(i).getAmount();
                //Assert
                Assert.assertEquals(expectedFlatHingesAmount, actualFlatHingesAmount);
            }
        }
    }

    @Test
    public void flatRoofTilesAmount() throws DMException, MakeOrderException {
        //Act
        Carport result = cc.calculateAll(length, width, roof, shed);
        result.setList(mc.materialList(result));
        ArrayList<Material> l = result.getList();
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getName().equals("Tegl")) {
                //Bør være 0 da vi ikke bruger tegl på fladt tag
                int expectedRoofTilesAmount = 0;
                int actualRoofTilesAmount = l.get(i).getAmount();
                //Assert
                Assert.assertEquals(expectedRoofTilesAmount, actualRoofTilesAmount);
            }
        }
    }

    @Test
    public void topRoofTilesAmount240x240() throws DMException, MakeOrderException {
        //Act
        Carport result = cc.calculateAll(length, width, true, shed);
        result.setList(mc.materialList(result));
        ArrayList<Material> l = result.getList();
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getName().equals("Tegl")) {
                int expectedRoofTilesAmount = 32;
                int actualRoofTilesAmount = l.get(i).getAmount();
                //Assert
                Assert.assertEquals(expectedRoofTilesAmount, actualRoofTilesAmount);
            }
        }
    }

    @Test
    public void topRoofTilesAmount480x480() throws DMException, MakeOrderException {
        //Arrange
        length = 480;
        width = 480;
        //Act
        Carport result = cc.calculateAll(length, width, true, shed);
        result.setList(mc.materialList(result));
        ArrayList<Material> l = result.getList();
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getName().equals("Tegl")) {
                int expectedRoofTilesAmount = 79;
                int actualRoofTilesAmount = l.get(i).getAmount();
                //Assert
                Assert.assertEquals(expectedRoofTilesAmount, actualRoofTilesAmount);
            }
        }
    }

    @Test
    public void topRoofTilesAmount720x720() throws DMException, MakeOrderException {
        //Arrange
        length = 720;
        width = 720;
        //Act
        Carport result = cc.calculateAll(length, width, true, shed);
        result.setList(mc.materialList(result));
        ArrayList<Material> l = result.getList();
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getName().equals("Tegl")) {
                int expectedRoofTilesAmount = 146;
                int actualRoofTilesAmount = l.get(i).getAmount();
                //Assert
                Assert.assertEquals(expectedRoofTilesAmount, actualRoofTilesAmount);
            }
        }
    }

    @Test
    public void planksAmount240x240() throws DMException, MakeOrderException {
        //Act
        Carport result = cc.calculateAll(length, width, roof, shed);
        result.setList(mc.materialList(result));
        ArrayList<Material> l = result.getList();
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getName().equals("25x150	mm.	trykimp. Bræt") && l.get(i).getLength() == PLANKLENGTH) {
                int expectedPlanksAmount = 60;
                int actualPlanksAmount = l.get(i).getAmount();
                //Assert
                Assert.assertEquals(expectedPlanksAmount, actualPlanksAmount);
            }
        }
    }

    @Test
    public void planksAmount480x480() throws DMException, MakeOrderException {
        //Arrange
        length = 480;
        width = 480;
        //Act
        Carport result = cc.calculateAll(length, width, roof, shed);
        result.setList(mc.materialList(result));
        ArrayList<Material> l = result.getList();
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getName().equals("25x150	mm.	trykimp. Bræt") && l.get(i).getLength() == PLANKLENGTH) {
                int expectedPlanksAmount = 120;
                int actualPlanksAmount = l.get(i).getAmount();
                //Assert
                Assert.assertEquals(expectedPlanksAmount, actualPlanksAmount);
            }
        }
    }

    @Test
    public void planksAmount720x720() throws DMException, MakeOrderException {
        //Arrange
        length = 720;
        width = 720;
        //Act
        Carport result = cc.calculateAll(length, width, roof, shed);
        result.setList(mc.materialList(result));
        ArrayList<Material> l = result.getList();
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getName().equals("25x150	mm.	trykimp. Bræt") && l.get(i).getLength() == PLANKLENGTH) {
                int expectedPlanksAmount = 180;
                int actualPlanksAmount = l.get(i).getAmount();
                //Assert
                Assert.assertEquals(expectedPlanksAmount, actualPlanksAmount);
            }
        }
    }

    @Test
    public void coverStabilizerPlankSmallAmount() throws DMException, MakeOrderException {
        //Act
        Carport result = cc.calculateAll(length, width, roof, shed);
        result.setList(mc.materialList(result));
        ArrayList<Material> l = result.getList();
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getName().equals("45x95 Reglar ubh.") && l.get(i).getLength() == 240) {
                //de bruger kun disse planker når shed = true
                int expectedCoverStabilizerPlankSmallAmount = 0;
                int actualCoverStabilizerPlankSmallAmount = l.get(i).getAmount();
                //Assert
                Assert.assertEquals(expectedCoverStabilizerPlankSmallAmount, actualCoverStabilizerPlankSmallAmount);
            }
        }
    }

    @Test
    public void coverStabilizerPlankSmallAmountWithShed240x240() throws DMException, MakeOrderException {
        //act
        Carport result = cc.calculateAll(length, width, roof, true);
        result.setList(mc.materialList(result));
        ArrayList<Material> l = result.getList();
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getName().equals("45x95 Reglar ubh.") && l.get(i).getLength() == 240) {
                int expectedCoverStabilizerPlankSmallAmount = 4;
                int actualCoverStabilizerPlankSmallAmount = l.get(i).getAmount();
                //Assert
                Assert.assertEquals(expectedCoverStabilizerPlankSmallAmount, actualCoverStabilizerPlankSmallAmount);
            }
        }
    }

    @Test
    public void coverStabilizerPlankSmallAmountWithShed720x720() throws DMException, MakeOrderException {
        //Arrange
        length = 720;
        width = 720;
        //Act
        Carport result = cc.calculateAll(length, width, roof, true);
        result.setList(mc.materialList(result));
        ArrayList<Material> l = result.getList();
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getName().equals("45x95 Reglar ubh.") && l.get(i).getLength() == 240) {
                int expectedCoverStabilizerPlankSmallAmount = 5;
                int actualCoverStabilizerPlankSmallAmount = l.get(i).getAmount();
                //Assert
                Assert.assertEquals(expectedCoverStabilizerPlankSmallAmount, actualCoverStabilizerPlankSmallAmount);
            }
        }
    }

    @Test
    public void coverStabilizerPlanksLongAmount() throws DMException, MakeOrderException {
        //Act
        Carport result = cc.calculateAll(length, width, roof, shed);
        result.setList(mc.materialList(result));
        ArrayList<Material> l = result.getList();
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getName().equals("45x95 Reglar ubh.") && l.get(i).getLength() == 360) {
                //de bruger kun disse planker når shed = true
                int expectedCoverStabilizerPlanksLongAmount = 0;
                int actualCoverStabilizerPlanksLongAmount = l.get(i).getAmount();
                //Assert
                Assert.assertEquals(expectedCoverStabilizerPlanksLongAmount, actualCoverStabilizerPlanksLongAmount);
            }
        }
    }

    @Test
    public void coverStabilizerPlanksLongAmountWithShed240x240() throws DMException, MakeOrderException {
        //Act
        Carport result = cc.calculateAll(length, width, roof, true);
        result.setList(mc.materialList(result));
        ArrayList<Material> l = result.getList();
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getName().equals("45x95 Reglar ubh.") && l.get(i).getLength() == 360) {
                int expectedCoverStabilizerPlanksLongAmount = 0;
                int actualCoverStabilizerPlanksLongAmount = l.get(i).getAmount();
                //Assert
                Assert.assertEquals(expectedCoverStabilizerPlanksLongAmount, actualCoverStabilizerPlanksLongAmount);
            }
        }
    }

    @Test
    public void coverStabilizerPlanksLongAmountWithShed480x480() throws DMException, MakeOrderException {
        //Arrange
        length = 480;
        width = 480;
        //Act
        Carport result = cc.calculateAll(length, width, roof, true);
        result.setList(mc.materialList(result));
        ArrayList<Material> l = result.getList();
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getName().equals("45x95 Reglar ubh.") && l.get(i).getLength() == 360) {
                int expectedCoverStabilizerPlanksLongAmount = 4;
                int actualCoverStabilizerPlanksLongAmount = l.get(i).getAmount();
                //Assert
                Assert.assertEquals(expectedCoverStabilizerPlanksLongAmount, actualCoverStabilizerPlanksLongAmount);
            }
        }
    }

    @Test
    public void coverStabilizerPlanksLongAmountWithShed720x720() throws DMException, MakeOrderException {
        //Arrange
        length = 720;
        width = 720;
        //Act
        Carport result = cc.calculateAll(length, width, roof, true);
        result.setList(mc.materialList(result));
        ArrayList<Material> l = result.getList();
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getName().equals("45x95 Reglar ubh.") && l.get(i).getLength() == 360) {
                int expectedCoverStabilizerPlanksLongAmount = 4;
                int actualCoverStabilizerPlanksLongAmount = l.get(i).getAmount();
                //Assert
                Assert.assertEquals(expectedCoverStabilizerPlanksLongAmount, actualCoverStabilizerPlanksLongAmount);
            }
        }
    }

    @Test
    public void doorKnobAmount() throws DMException, MakeOrderException {
        //Act
        Carport result = cc.calculateAll(length, width, roof, shed);
        result.setList(mc.materialList(result));
        ArrayList<Material> l = result.getList();
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getName().equals("Dørhåndtag")) {
                int expectedDoorKnobAmount = 2;
                int actualDoorKnobAmount = l.get(i).getAmount();
                //Assert
                Assert.assertEquals(expectedDoorKnobAmount, actualDoorKnobAmount);
            }
        }
    }

    @Test
    public void testDoorHingeAmount() throws DMException, MakeOrderException {
        //Act
        Carport result = cc.calculateAll(length, width, roof, shed);
        result.setList(mc.materialList(result));
        ArrayList<Material> l = result.getList();
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getName().equals("Dør hængsel")) {
                int expectedDoorHingeAmount = 0;
                int actualDoorHingeAmount = l.get(i).getAmount();
                //Assert
                Assert.assertEquals(expectedDoorHingeAmount, actualDoorHingeAmount);
            }
        }
    }
}
