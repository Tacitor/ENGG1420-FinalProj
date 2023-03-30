/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group7.FileProcessor.entries;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author shale
 */
public class LocFolder extends Local {

    String[] contents;//coltains the filepaths of all subFiles and subfolders, the contents of subfolders is also stored

    /**
     * constructor for a local folder
     * @param address address of the folder
     */
    public LocFolder(String address) {
        super(address);
        updateContents();
    }

    /**
     * a method to update the stored contents of the folder
     */
    public void updateContents() {
        try {
            
            List<String> tempList;

            Path path = Paths.get(address);

            Stream<Path> subPaths = Files.walk(path);

            tempList = subPaths.map(p->p.toString()).collect(Collectors.toList());
            
            contents = tempList.toArray(String[]::new);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   
    public String[] getContents() {
        return contents;
    }
    
}