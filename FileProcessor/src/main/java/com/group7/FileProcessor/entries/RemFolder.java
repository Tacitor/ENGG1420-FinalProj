/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group7.FileProcessor.entries;

/**
 *
 * @author shale
 */
public class RemFolder extends LocFile{
    
    String accessKey;
    String repositoryId;
    
    public RemFolder(String address,String accessKey,String repositoryId){
        super(address);
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
    

