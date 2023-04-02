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
public class RemFolder extends LocFolder {

    String accessKey;
    String repositoryId;
    static int downloadnum = 0;

    public RemFolder(String accessKey, String repositoryId) {
        File fold = new File("C:\\ENG1420Group7FileProccessor\\folderdownload" + downloadnum);
        boolean dir = true;
        if (!fold.exists()) {
            dir = fold.mkdirs();
        }
        setAddress(fold.getAbsolutePath());
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

    @Override
    public Entry clone() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
