/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.group7.FileProcessor;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author keric
 */
public class JsonTest {
    
    String jsonSource = "{\"title\":\"coderfromScratch\"}";
      
    void parse() throws IOException{
           
        JsonNode node = Json.parse(jsonSource);
            
            System.out.println(node.get("title").asText());
            
    
    
    }
    
    
    
    
    
    
    
    
    
    
    
    
//    public JsonTest() {
//    }
//
////    @org.junit.BeforeClass
////    public static void setUpClass() throws Exception {
////    }
////
////    @org.junit.AfterClass
////    public static void tearDownClass() throws Exception {
////    }
////    
//    @BeforeClass
//    public static void setUpClass() {
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
//
//    /**
//     * Test of parse method, of class Json.
//     */
//    @org.junit.Test
//    public void testParse() throws Exception {
//        System.out.println("parse");
//        String src = "";
//        JsonNode expResult = null;
//        JsonNode result = Json.parse(src);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    
}
