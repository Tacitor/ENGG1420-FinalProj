/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package elements;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author gozen
 */
public class RenameTest {
    
    public RenameTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of process method, of class Rename.
     */
    @Test
    public void testProcess() {
        System.out.println("process");
        Rename instance = new Rename();
        instance.process();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSuffix method, of class Rename.
     */
    @Test
    public void testGetSuffix() {
        System.out.println("getSuffix");
        Rename instance = new Rename();
        String expResult = "";
        String result = instance.getSuffix();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSuffix method, of class Rename.
     */
    @Test
    public void testSetSuffix() {
        System.out.println("setSuffix");
        String suffix = "";
        Rename instance = new Rename();
        instance.setSuffix(suffix);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
