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
"    \"name\": \"First Scenario\",\n" +
"    \"processing_elements\": [\n" +
"        {\n" +
"            \"type\": \"List\",\n" +
"            \"input_entries\": [\n" +
"                {\n" +
"                    \"type\": \"local\",\n" +
"                    \"path\": \"c:\\\\sample\\\\text_files\"\n" +
"                }\n" +
"            ],\n" +
"            \"parameters\": [\n" +
"                {\n" +
"                    \"name\": \"Max\",\n" +
"                    \"value\": \"100\"\n" +
"                }\n" +
"            ]\n" +
"        },\n" +
"        {\n" +
"            \"type\": \"LengthFilter\",\n" +
"            \"input_entries\": [],\n" +
"            \"parameters\": [\n" +
"                {\n" +
"                    \"name\": \"Length\",\n" +
"                    \"value\": \"1024\"\n" +
"                },\n" +
"                {\n" +
"                    \"name\": \"Operator\",\n" +
"                    \"value\": \"GTE\"\n" +
"                }\n" +
"            ]\n" +
"        },\n" +
"        {\n" +
"            \"type\": \"Print\",\n" +
"            \"input_entries\": [],\n" +
"            \"parameters\": []\n" +
"        }\n" +
"    ]\n" +
"}";
        
        JsonNode node = Json.parse(testScenario);//make JsonNode
        ScenarioPOJO scenario = Json.fromJson(node,ScenarioPOJO.class);//map to class
        
        //Print the date in the Scenario
        System.out.println("Scenario name: " + scenario.getName());
        
        for(ProcessingElementPOJO element : scenario.getProcessing_elements()){
            System.out.println("Processing Element Type:  " + element.getType());
            System.out.println("    Input Entries: ");
            for (EntriesPOJO entries : element.getInput_entries()){
                System.out.println("        type: " + entries.getType());
                System.out.println("        path: " + entries.getPath());
            }
            System.out.println("    Parameters: ");
            for (ParametersPOJO param : element.getParameters()){
                System.out.println("        name: " + param.getName());
                System.out.println("        value: " + param.getValue());
            }
        }
        
        
    }

}
