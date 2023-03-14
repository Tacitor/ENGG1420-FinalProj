/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group7.fileprocessingsystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author shale
 */
public class LocFile extends Local{//couldn't name file or it would conflict with the File built in class
    
    File file;
    Scanner scanner;
    
    
    public LocFile(String address) throws FileNotFoundException{
        super(address);
        file = new File(address);
        scanner = new Scanner(file);
        //setting contents and contentsByLine
        String temp;
        int index = 0;
        for(int i = 0; scanner.hasNext();i++){
            temp = scanner.nextLine();
            setContents(getContents() + temp);
            index = i;
        }
        scanner = new Scanner(file);
        setContentsByLineLength(index+1);
        for(int i = 0; scanner.hasNext();i++){
            setContentsByLine(i,scanner.nextLine());
        }
        //still need to fill in name and length
            
    }
    
    
}
