/*
 * Group 7
 * April 2, 2023
 */
package com.group7.FileProcessor.pojo;

/**
 *
 * @author keric
 */
public class ParametersPOJO {

    private String name; // Name of parameter
    private String value; // Value of parameter

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
     * Accessor for var value
     *
     * @return value
     */
    public String getValue() {
        return value;
    }

    /**
     * Mutator for var value
     *
     * @param value
     */
    public void setValue(String value) {
        this.value = value;
    }

}
