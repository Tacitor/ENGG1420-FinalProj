/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group7.FileProcessor.entries;

import java.io.File;

/**
 *
 * @author shale
 */
public class RemFile extends LocFile{
    
    String accessKey;
    String repositoryId;
    static int downloadnum=0;
    
    public RemFile(String address,String accessKey,String repositoryId){
        super(new File("fileDownload"+downloadnum+".txt").getAbsolutePath());
        downloadnum++;
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
