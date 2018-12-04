/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer.Calculate;

import FunctionLayer.Entity.Carport;
import FunctionLayer.Entity.Material;
import java.util.ArrayList;
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
public class MaterialCalculatorTest {
    
    public MaterialCalculatorTest() {
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
     * Test of materialList method, of class MaterialCalculator.
     */
    @Test
    public void testMaterialList() throws Exception {
        System.out.println("materialList");
        Carport c = null;
        MaterialCalculator instance = new MaterialCalculator();
        ArrayList<Material> expResult = null;
        ArrayList<Material> result = instance.materialList(c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
