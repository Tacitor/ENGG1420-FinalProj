/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group7.FileProcessor.entries;

import java.io.File;
import java.io.FileNotFoundException;

/**
 *
 * @author shalev
 */
public abstract class Entry {
    
    
    long length; // stores length in byte formatt
    String address;
    String contents;
    
    public Entry(String address){
        this.address = address;
        
    }

    public long getLength() {
        return length;
    }

    public String getAddress() {
        return address;
    }

    protected void setLength(long length) {
        this.length = length;
    }
    
    public abstract String getContents() throws FolderDoesNotContainTextException;
    
        /**
     * a method to get the stored size of a file or folder on disk
     * @param address
     * @return 
     * @throws java.io.FileNotFoundException
     */
    public static long getLength(String address) throws FileNotFoundException{
            
            
            File file = new File(address);
            if (!file.exists()){
                throw new FileNotFoundException();
            }else{
                if (file.isFile()){
                    return file.length();
                }else{
                    File[] subFiles = file.listFiles();
                    long total = 0;
                    for(File subFile : subFiles){
                        total += getLength(subFile.getAbsolutePath());
                    }
                    return total;
                }
                
            }

    }
}