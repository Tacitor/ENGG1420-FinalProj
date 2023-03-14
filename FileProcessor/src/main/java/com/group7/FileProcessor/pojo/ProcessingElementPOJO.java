/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group7.FileProcessor.pojo;

import java.util.List;

/**
 *
 * @author keric
 */
public class ProcessingElementPOJO {
    private String type;
    private List<EntriesPOJO> entries;
    private List<ParametersPOJO> parameters;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<EntriesPOJO> getEntries() {
        return entries;
    }

    public void setEntries(List<EntriesPOJO> entries) {
        this.entries = entries;
    }

    public List<ParametersPOJO> getParameters() {
        return parameters;
    }

    public void setParameters(List<ParametersPOJO> parameters) {
        this.parameters = parameters;
    }
    
}
