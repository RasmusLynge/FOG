/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer.Calculate;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Simon
 */
public class PriceCalculatorTest {
    
    public PriceCalculatorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of priceCalculator method, of class PriceCalculator.
     */
    @Test
    public void testPriceCalculator() throws Exception {
        System.out.println("priceCalculator");
        int length = 0;
        int width = 0;
        boolean roof = false;
        boolean shed = false;
        PriceCalculator instance = new PriceCalculator();
        double expResult = 0.0;
        double result = instance.priceCalculator(length, width, roof, shed);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
