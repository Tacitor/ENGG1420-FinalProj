/*
 * Group 7
 * April 2, 2023
 */
package com.group7.FileProcessor.pojo;

import java.util.ArrayList;

/**
 *
 * @author keric
 */
public class ScenarioPOJO {

    private String name;//variables must have same name as .Json
    private ArrayList<ProcessingElementPOJO> processing_elements; // List of processing elements to execute

    /**
     * Accessor for var name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Mutator for var name
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Accessor for var processing_elements
     *
     * @return processing_elements
     */
    public ArrayList<ProcessingElementPOJO> getProcessing_elements() {
        return processing_elements;
    }

    /**
     * Mutator for var processing_elements
     *
     * @param processing_elements
     */
    public void setProcessing_elements(ArrayList<ProcessingElementPOJO> processing_elements) {
        this.processing_elements = processing_elements;
    }

    /**
     * A method that prints information about entries
     */
    public void print() {
        //Print the date in the Scenario
        System.out.println("Scenario name: " + getName());

        for (ProcessingElementPOJO element : getProcessing_elements()) {
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
    }
}
