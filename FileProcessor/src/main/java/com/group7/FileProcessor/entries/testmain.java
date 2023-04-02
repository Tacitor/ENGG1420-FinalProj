/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group7.FileProcessor.entries;

import java.io.IOException;
import java.util.Arrays;

/**
 *
 * @author shale
 */
public class testmain {
    public static void main(String[] args) throws IOException{
        RemFolder file = new RemFolder(1,"r-0001d410ba56");
        System.out.println(Arrays.toString(file.getSubFiles()));
    }
}
