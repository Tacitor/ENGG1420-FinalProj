/*
 * Lukas Krampitz and Group 7
 * Mar 29, 2023
 * The main sequencer for the file processor. This class is the main high level logic control of the file processor
 */
package com.group7.FileProcessor;

import com.fasterxml.jackson.databind.JsonNode;
import com.group7.FileProcessor.pojo.EntriesPOJO;
import com.group7.FileProcessor.pojo.ParametersPOJO;
import com.group7.FileProcessor.pojo.ProcessingElementPOJO;
import com.group7.FileProcessor.pojo.ScenarioPOJO;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Tacitor
 */
public class Sequencer {

    //Attributes
    private ArrayList<ProcessingElementPOJO> processingElements; //Type of ProcessingElementPOJO is temporary. Will need the ProcessingElement interface when it is ready
    private String scenarioName;

    private boolean readyToProcess; //Control and protection var. If this is true then the sequencer may run. If this is false then there has not been a scenarioAsString stored, or the previous scenario is done can a new one needs to be selected.
    private String scenarioAsString; //The .JSON file that contains the senario as a String. Line for line. Passed in by the Main.java class.

    /**
     * Primary constructor for the Sequencer
     */
    public Sequencer() {
        readyToProcess = false;
        scenarioAsString = null; //Defaults to null when first run.
    }

    /**
     * Accessor for the scenario as a String.
     *
     * @return - the scenario string
     */
    public String getScenarioAsString() {
        return scenarioAsString;
    }

    /**
     * Mutator for the scenario as a String. Checks to ensure this is not a null
     * string or an empty string
     *
     * @param senarioAsString
     */
    public synchronized void setScenarioAsString(String senarioAsString) {
        //check for a null or empty string
        if (senarioAsString != null && !senarioAsString.equalsIgnoreCase("")) {
            this.scenarioAsString = senarioAsString;
            //allow for processing
            readyToProcess = true;
        }
    }

    /**
     * Begin running through the scenario. This requires the scenarioAsString to
     * be provided
     */
    public synchronized void run() {

        //check if the senario may run
        if (readyToProcess) {

            //try catch any exceptions
            try {

                //Use Jackson to pasrse the scenario
                JsonNode node = Json.parse(scenarioAsString);//make JsonNode
                ScenarioPOJO scenario = Json.fromJson(node, ScenarioPOJO.class);//map to class

                //exratract the name
                scenarioName = scenario.getName();

                //Print the date in the Scenario
                System.out.println("Scenario name: " + scenario.getName());

                for (ProcessingElementPOJO element : scenario.getProcessing_elements()) {
                    System.out.println("Processing Element Type:  " + element.getType());
                    System.out.println("    Input Entries: ");
                    for (EntriesPOJO entries : element.getInput_entries()) {
                        System.out.println("        type: " + entries.getType());
                        System.out.println("        path: " + entries.getPath());
                        System.out.println("        Laserfiche Repo ID: " + entries.getRepositoryId());
                        System.out.println("        Laserfiche Entry ID: " + entries.getEntryId());
                    }
                    System.out.println("    Parameters: ");
                    for (ParametersPOJO param : element.getParameters()) {
                        System.out.println("        name: " + param.getName());
                        System.out.println("        value: " + param.getValue());
                    }
                }

            } catch (IOException e) {
                System.out.println("ERROR: IOException when reading parsing and reading the scenarioAsString in Sequencer.java\n" + e);
            }

        } else {
            System.out.println("Error: Not ready to process.\nPlease select a .JSON file to start or a new one to continue.");
        }

    }

}
