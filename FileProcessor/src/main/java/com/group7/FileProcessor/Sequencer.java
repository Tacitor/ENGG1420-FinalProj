/*
 * Lukas Krampitz and Group 7
 * Mar 29, 2023
 * The main sequencer for the file processor. This class is the main high level logic control of the file processor
 */
package com.group7.FileProcessor;

import com.fasterxml.jackson.databind.JsonNode;
import com.group7.FileProcessor.entries.*;
import com.group7.FileProcessor.pojo.*;
import elements.List;
import elements.Print;
import elements.Rename;
import elements.Split;
import elements.filters.ContentFilter;
import elements.filters.CountFilter;
import elements.filters.LengthFilter;
import elements.filters.NameFilter;
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
                //ENTRYGENNY
                //read type if local read path, test if file or folder
                //Reads entrys from scenarioPOJO to create the arraylist of Entries
                //=-=-=-=-=-=-=-=-=-=
                //Instantiate each processing element
                NameFilter nameFilter = new NameFilter();
                LengthFilter lengthFilter = new LengthFilter();
                ContentFilter contentFilter = new ContentFilter();
                CountFilter countFilter = new CountFilter();
                Split split = new Split();
                List list = new List();
                Rename rename = new Rename();
                Print print = new Print();

                ArrayList<ParametersPOJO> params;//variable for storing parameters retrieved from scenarioPOJO
                ArrayList<EntriesPOJO> entries;//variable for entries information from json

                ArrayList<Entry> processEntries = new ArrayList();
                EntryGenny entryGenny = new EntryGenny();

                //Sequence Processing Elements
                for (ProcessingElementPOJO element : processingElements) {
                    params = element.getParameters();//ArrayList of paramaters
                    entries = element.getInput_entries();
                    //Identify element type and assign paramaters to element object using setters then call process()

                    if (element.getType().equalsIgnoreCase("Print")) {
                        //should loop through entries array
                        if (!entries.isEmpty()) {
                            processEntries.add(entryGenny.addEntry(entries.get(0)));//Update entries: get path, send to Genny, add return to entries
                        }//
                        System.out.println("Run Print element");
                        print.setInputEntries(processEntries);
                        print.process();
                        processEntries = print.getOutputEntries();

                    } else if (element.getType().equalsIgnoreCase("NameFilter")) {
                        //should loop through entries array
                        if (!entries.isEmpty()) {
                            processEntries.add(entryGenny.addEntry(entries.get(0)));//get path, send to Genny, add return to entries
                        }//
                        System.out.println("Run NameFilter element with Key: " + params.get(0).getValue());
                        nameFilter.setInputEntries(processEntries);
                        nameFilter.setKey(params.get(0).getValue());
                        nameFilter.process();
                        processEntries = nameFilter.getOutputEntries();

                    } else if (element.getType().equalsIgnoreCase("LengthFilter")) {
                        //should loop through entries array
                        if (!entries.isEmpty()) {
                            processEntries.add(entryGenny.addEntry(entries.get(0)));//Update entries: get path, send to Genny, add return to entries
                        }//
                        System.out.println("Run LengthFilter element with Length: " + params.get(0).getValue() + " Operator: " + params.get(1).getValue());
                        lengthFilter.setInputEntries(processEntries);
                        lengthFilter.setLength(Long.parseLong(params.get(0).getValue()));//long.parse turns string to long
                        lengthFilter.setOperator(params.get(1).getValue());
                        lengthFilter.process();
                        processEntries = lengthFilter.getOutputEntries();

                    } else if (element.getType().equalsIgnoreCase("ContentFilter")) {
                        //should loop through entries array
                        if (!entries.isEmpty()) {
                            processEntries.add(entryGenny.addEntry(entries.get(0)));//Update entries: get path, send to Genny, add return to entries
                        }//
                        System.out.println("Run ContentFilter element with Key: " + params.get(0).getValue());
                        contentFilter.setInputEntries(processEntries);
                        contentFilter.setKey(params.get(0).getValue());
                        contentFilter.process();
                        processEntries = contentFilter.getOutputEntries();

                    } else if (element.getType().equalsIgnoreCase("CountFilter")) {
                        //should loop through entries array
                        if (!entries.isEmpty()) {
                            processEntries.add(entryGenny.addEntry(entries.get(0)));//Update entries: get path, send to Genny, add return to entries
                        }//
                        System.out.println("Run CountFilter element with Key: " + params.get(0).getValue() + " Min: " + params.get(1).getValue());
                        countFilter.setInputEntries(processEntries);
                        countFilter.setKey(params.get(0).getValue());
                        countFilter.process();
                        processEntries = countFilter.getOutputEntries();

                    } else if (element.getType().equalsIgnoreCase("Split")) {
                        //should loop through entries array
                        if (!entries.isEmpty()) {
                            processEntries.add(entryGenny.addEntry(entries.get(0)));//Update entries: get path, send to Genny, add return to entries
                        }//
                        System.out.println("Run Split element with Lines: " + params.get(0).getValue());
                        split.setInputEntries(processEntries);
                        split.setLines(Integer.parseInt(params.get(0).getValue()));
                        split.process();
                        processEntries = split.getOutputEntries();

                    } else if (element.getType().equalsIgnoreCase("List")) {
                        //should loop through entries array
                        if (!entries.isEmpty()) {
                            processEntries.add(entryGenny.addEntry(entries.get(0)));//Update entries: get path, send to Genny, add return to entries
                        }//
                        System.out.println("Run List element with Max: " + params.get(0).getValue());
                        list.setInputEntries(processEntries);
                        list.setMax(Integer.parseInt(params.get(0).getValue()));
                        list.process();
                        processEntries = list.getOutputEntries();

                    } else if (element.getType().equalsIgnoreCase("Rename")) {
                        //should loop through entries array
                        if (!entries.isEmpty()) {
                            processEntries.add(entryGenny.addEntry(entries.get(0)));//Update entries: get path, send to Genny, add return to entries
                        }//
                        System.out.println("Run Rename element with Suffix: " + params.get(0).getValue());
                        rename.setInputEntries(processEntries);
                        rename.setSuffix(params.get(0).getValue());
                        rename.process();
                        processEntries = rename.getOutputEntries();
                    }
                    
                    
                    //loop through the elements
                    System.out.println("\n\n=-=-=-=Loop done - The output:");
                    for (Entry e : processEntries) {
                        System.out.println(e.toString());
                    }
                    System.out.println("=-=-=-=-=Done=-=-=-=");                   

                    }

                //call
                //TODO add the proccessing feature
                //run the current element
                //processingElements.get(i).process();
                //update the next element to have its input be the output of the other
                //check for out of bounds too
                //if (i != (processingElements.size() - 1)) {
                //TODO add the ablility to get the output of a processing element
                //update the next element
                //processingElements.get(i + 1).setInput_entries(processingElements.get(i).getOutput());
                //}
                //}
            }

        } else {
            System.out.println("Error: Not ready to process.\nPlease select a .JSON file to start or a new one to continue.");
        }

    }

}
