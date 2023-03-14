/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group7.FileProcessor;

import com.fasterxml.jackson.databind.JsonNode;
import com.group7.FileProcessor.pojo.*;
import java.io.IOException;

/**
 *
 * @author keric
 */
public class Scenario {

    public static void main(String[] args) throws IOException {
           

String testScenario = "{\n" +
"    \"name\": \"Test Scenario\",\n" +
"    \"processing_elements\": [     \n" +
"        {\n" +
"            \"type\": \"Print\",\n" +
"            \"input_entries\": [\n" +
"                {\n" +
"                    \"type\": \"local\",\n" +
"                    \"path\": \"c:\\\\sample\\\\text_files\"\n" +
"                }\n" +
"            ],\n" +
"            \"parameters\": []\n" +
"        }\n" +
"    ]\n" +
"}";
        
        JsonNode node = Json.parse(testScenario);//make JsonNode
        ScenarioPOJO scenario = Json.fromJson(node,ScenarioPOJO.class);//map to class
        
        System.out.println("Scenario name: " + scenario.getName());
        
        for(ProcessingElementPOJO element : scenario.getElements()){
            System.out.println("Processing Element Type:  " + element.getType());
        }
        
        
    }

}
