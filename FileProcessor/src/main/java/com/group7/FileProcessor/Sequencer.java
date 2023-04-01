/*
 * Lukas Krampitz and Group 7
 * Mar 29, 2023
 * The main sequencer for the file processor. This class is the main high level logic control of the file processor
 */
package com.group7.FileProcessor;

import com.fasterxml.jackson.databind.JsonNode;
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
            boolean continueScenario = false; //Should the scenario be allowed to continue runing. Default to false since the .JSON has not been read in yet.

            //Use Jackson to pasrse the scenario
            //Code coppied from Scenario.java that Eric @Fluff-E wrote
            ScenarioPOJO scenario = new ScenarioPOJO();
            try {
                JsonNode node = Json.parse(scenarioAsString);//make JsonNode
                scenario = Json.fromJson(node, ScenarioPOJO.class); //map to class

                //If this all ran and no exception ocurred than allow the processing to continue
                continueScenario = true;
            } catch (IOException e) {
                System.out.println("ERROR: IOException when mapping .Json file to ScenarioPOJO in Sequencer.java\n" + e);

                //make sure the rest cannot run since there was an exception
                continueScenario = false;
            }

            //only run the scenario if there was no error when reading the JSON
            if (continueScenario) {

                scenario.print();

                //Get the list of processing elements
                processingElements = scenario.getProcessing_elements();

                //=-=-=-=-=-=-=-=-=-=
                //TODO this needs to map the POJOs to the Shalev Element
                //=-=-=-=-=-=-=-=-=-=
                
                //loop through all the processing elements and run them
                for (int i = 0; i < processingElements.size(); i++) {

                    //TODO add the proccessing feature
                    //run the current element
                    //processingElements.get(i).process();
                    //update the next element to have its input be the output of the other
                    //check for out of bounds too
                    if (i != (processingElements.size() - 1)) {

                        //TODO add the ablility to get the output of a processing element
                        //update the next element
                        //processingElements.get(i + 1).setInput_entries(processingElements.get(i).getOutput());
                    }
                }
            }

        } else {
            System.out.println("Error: Not ready to process.\nPlease select a .JSON file to start or a new one to continue.");
        }

    }

}
