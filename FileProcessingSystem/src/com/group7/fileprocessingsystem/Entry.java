/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group7.fileprocessingsystem;

/**
 *
 * @author shale
 */
public abstract class Entry {
    
    String location;
    String contents;
    String []contentsByLine;
    String name;
    int length;

    public Entry(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public String[] getContents() {
        return contentsByLine;
    }
    public String getLine(int index) {
        return contentsByLine[index];
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setContents(String[] contents) {
        this.contentsByLine = contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    protected void setContentsByLineLength(int length) {
            contentsByLine = new String[length];
        
    }
    
    public void setContentsByLine(String[] contentsByLine) {
        for (int i = 0; i < this.contentsByLine.length; i++) {
            this.contentsByLine[i] = contentsByLine[i];
        }
        
    }
    public void setContentsByLine(int index, String line) {
        this.contentsByLine[index] = line;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLength(int length) {
        this.length = length;
    }
    
    
}
