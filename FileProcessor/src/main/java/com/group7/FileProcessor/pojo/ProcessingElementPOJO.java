/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group7.FileProcessor.pojo;

import java.util.ArrayList;

/**
 *
 * @author keric
 */
public class ProcessingElementPOJO {
    private String type;
    private ArrayList<EntriesPOJO> input_entries;//Maybe shange to array list
    private ArrayList<ParametersPOJO> parameters;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<EntriesPOJO> getInput_entries() {
        return input_entries;
    }

    public void setInput_entries(ArrayList<EntriesPOJO> input_entries) {
        this.input_entries = input_entries;
    }

    public ArrayList<ParametersPOJO> getParameters() {
        return parameters;
    }

    public void setParameters(ArrayList<ParametersPOJO> parameters) {
        this.parameters = parameters;
    }


}
