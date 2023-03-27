/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group7.FileProcessor.entries;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 *
 * @author shalev
 */
public class Local extends Entry{
    
    String address;
    
    public Local(String address){
        this.address = address;
        
    }

    public String getAddress() {
        return address;
    }
    /**
     * a method to update the stored size of the file
     */
    public void updateSize() {
        try {
            //using built in nio.file.Files and nio.file.Paths to fill in length
            Path path = Paths.get(getAddress());
            setLength(Files.size(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}