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
    private String name;
    private List<ProcessingElementPOJO> elements;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProcessingElementPOJO> getElements() {
        return elements;
    }

    public void setElements(List<ProcessingElementPOJO> elements) {
        this.elements = elements;
    }
    
}
