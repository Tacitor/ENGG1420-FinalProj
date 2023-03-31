/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group7.FileProcessor.entries;

import java.io.File;
import java.io.FileNotFoundException;

/**
 *
 * @author shale
 */
public class testmain {
    
    public static void main(String[] args) throws FileNotFoundException{
        Entry fold = new LocFolder("C:\\Users\\shale\\OneDrive\\Desktop\\testFolder");
        Entry file = new LocFile("C:\\Users\\shale\\OneDrive\\Desktop\\text.txt");
        File testFile = new File("C:\\Users\\shale\\OneDrive\\Desktop\\text.txt");

        try{
        System.out.println(file.getContents());
        System.out.println(fold.getContents());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
