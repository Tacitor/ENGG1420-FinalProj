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
public class ProcessingElementPOJO {

    private String type; // Type of processing element to be used
    private ArrayList<EntriesPOJO> input_entries; // The entries received
    private ArrayList<ParametersPOJO> parameters; // Parameters for the processing elements

    /**
     * Accessor for var type
     *
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * Mutator for var type
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Accessor for var input_entries
     *
     * @return
     */
    public ArrayList<EntriesPOJO> getInput_entries() {
        return input_entries;
    }

    /**
     * Mutator for var input_entries
     *
     * @param input_entries
     */
    public void setInput_entries(ArrayList<EntriesPOJO> input_entries) {
        this.input_entries = input_entries;
    }

    /**
     * Accessor for var parameters
     *
     * @return
     */
    public ArrayList<ParametersPOJO> getParameters() {
        return parameters;
    }

    /**
     * Mutator for var parameters
     *
     * @param parameters
     */
    public void setParameters(ArrayList<ParametersPOJO> parameters) {
        this.parameters = parameters;
    }
}
