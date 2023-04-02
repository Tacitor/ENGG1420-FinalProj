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
import java.io.File;

/**
 *
 * @author shale
 */
public class LocFolder extends Entry {

    String[] subFiles;//contains the filepaths of all subFiles and subfolders, the contents of subfolders is also stored

    /**
     * constructor for a local folder
     * @param address address of the folder
     */
    public LocFolder(String address) {
        super(address);
        //updates the array of subfiles using the address stored in memory
        updateSubFiles();
    }
    protected LocFolder(){}//default protected constructor to be used bythe remote classes since they generate their own addresses

    /**
     * a method to update the stored contents of the folder
     */
    public void updateSubFiles() {
        try {
            
            List<String> tempList;

            Path path = Paths.get(address);

            Stream<Path> subPaths = Files.walk(path);

            tempList = subPaths.map(p->p.toString()).collect(Collectors.toList());
            
            subFiles = tempList.toArray(String[]::new);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String[] getSubFiles() {
        return subFiles;
    }

    public void setSubFiles(String[] subFiles) {
        this.subFiles = subFiles;
    }
    
    

   
    @Override
    public String getContents() throws FolderDoesNotContainTextException{
        throw new FolderDoesNotContainTextException();
    }

    @Override
    public Entry clone() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setContents(String temp) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    /**
     * Return a string representation of the Local Folder that complies with the
     * requirements of the Print Processing Element in the Project Description.
     *
     * @return
     */
    @Override
    public String toString() {

        int index = address.lastIndexOf(File.pathSeparator);

        //If there is an issue with finding where the name of the file is just length to the whole file
        if (index == -1) {
            index = address.length();
        }

        return "Local Folder:\tName: " + address.substring(index - 1)
                + "\tLength: [ERROR]" + "\tAbsolute path: " + address;
    }
    
}
