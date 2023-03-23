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
public class ScenarioPOJO {
    private String name;//variables must have same name as .Json
    private List<ProcessingElementPOJO> processing_elements;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProcessingElementPOJO> getProcessing_elements() {
        return processing_elements;
    }

    public void setProcessing_elements(List<ProcessingElementPOJO> processing_elements) {
        this.processing_elements = processing_elements;
    }
    
}
