/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group7.FileProcessor.entries;

/**
 *
 * @author shalev
 */
public abstract class Entry {
    
    
    long length; // stores length in byte formatt

    

    public long getLength() {
        return length;
    }

    

    protected void setLength(long length) {
        this.length = length;
    }
    
    
}