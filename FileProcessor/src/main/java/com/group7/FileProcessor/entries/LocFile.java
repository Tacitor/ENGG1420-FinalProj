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
 * @author shale
 */
public class LocFile extends Local {//couldn't name file or it would conflict with the File built in class

    File file;
    Scanner scanner;

    public LocFile(String address) {
        super(address);

        updateContents();

    }
    
    public void updateContents(){
        try {
            file = new File(address);
            
            scanner = new Scanner(file);
            
            updateSize();// updating the stored size of the file
            
            //setting contents
            String temp = "";
            while (scanner.hasNext()) {
                temp += scanner.nextLine() + "\n"; // running through all the lines in the file and adding them to temp
            }
            setContents(temp);

        } catch (FileNotFoundException e) {
            System.out.println("File Not Found, adress: " + address);
        }
    }

    

}
