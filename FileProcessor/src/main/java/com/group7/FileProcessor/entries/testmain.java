/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group7.FileProcessor.entries;

import java.io.IOException;

/**
 *
 * @author shale
 */
public class testmain {
    public static void main(String[] args) throws IOException{
        RemFile file = new RemFile(25,"r-0001d410ba56");
        System.out.println(file.contents);
    }
}
