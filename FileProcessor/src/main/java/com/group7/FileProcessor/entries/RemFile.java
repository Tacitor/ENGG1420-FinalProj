/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group7.FileProcessor.entries;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author shale
 */
public class RemFile extends LocFile{
    
    String accessKey;
    String repositoryId;
    static int downloadnum=0;
    
    public RemFile(String accessKey,String repositoryId) throws IOException{
        File fold = new File("C:\\ENG1420Group7FileProccessor");
        boolean dir = true;
        if (!fold.exists()){
            dir = fold.mkdir();
        }
        setAddress(fold.getAbsolutePath()+"\\filedownload"+downloadnum+".txt");
        downloadnum++;
        File newFile = new File(getAddress());
        newFile.createNewFile();
        this.accessKey = accessKey;
        this.repositoryId = repositoryId;
    }
    

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
    }
    
}
