/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group7.FileProcessor.entries;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author shalev
 */
public class LocFile extends Entry {//couldn't name file or it would conflict with the File built in class

    File file;
    Scanner scanner;
    

    public LocFile(String address) {
        super(address);
        updateContents();
    }
    protected LocFile(){}
    
    @Override
    public String getContents() throws FolderDoesNotContainTextException {
        return contents;
    }
    
    private void setContents(String contents) {
        this.contents = contents;
    }
    
    /**
     * a method to update the stored contents, works similar to a refresh to check for any changes to the file
     */
    public void updateContents(){
        try {
            file = new File(address);
            
            scanner = new Scanner(file);
            
            
            //setting contents
            StringBuilder temp = new StringBuilder();
            while (scanner.hasNext()) {
                temp.append(scanner.nextLine()); // running through all the lines in the file and adding them to temp
                temp.append("\n");
            }
            setContents(temp.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    

    

}
