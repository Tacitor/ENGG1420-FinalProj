/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group7.FileProcessor.entries;

import com.group7.FileProcessor.Util;
import com.group7.FileProcessor.pojo.EntriesPOJO;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author keric
 */
public class EntryGenny {

    private String type;
    private String path;
    private String repositoryId;
    private String entryId;

    public Entry addEntry(EntriesPOJO entry) {
        if (entry.getType().equalsIgnoreCase("remte")) {
            System.out.println("Remote entry.");
        } else {//assume local
            path = entry.getPath();
            System.out.println("Local entry.");
            //path = Paths.get(entry.getPath());//get path as string use Paths.get to convert string to path
            if (Util.isFile(path) > 0) {//If path is valid file make entry object
                LocFile locFile = new LocFile(path);
                System.out.println("LocFile created");
                return locFile;
            } else if (Util.isDirectory(path) > 0) {
                LocFolder locFolder = new LocFolder(path);
                System.out.println("LocFolder created");
                return locFolder;
            }

        }
        return null;

    }
}
