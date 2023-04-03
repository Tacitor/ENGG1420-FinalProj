/*
 * Group 7
 * April 2, 2023
 */
package com.group7.FileProcessor.entries;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.io.File;
import java.util.Arrays;

/**
 *
 * @author shale
 */
public class LocFolder extends Entry {

    String[] subFiles;//contains the filepaths of all subFiles and subfolders, the contents of subfolders is also stored

    /**
     * constructor for a local folder
     *
     * @param address address of the folder
     */
    public LocFolder(String address) {
        super(address);
        //updates the array of subfiles using the address stored in memory
        updateSubFiles();
    }

    protected LocFolder() {
    }//default protected constructor to be used bythe remote classes since they generate their own addresses

    /**
     * a method to update the stored contents of the folder
     */
    public void updateSubFiles() {
        try {

            List<String> tempList;

            Path path = Paths.get(address);

            Stream<Path> subPaths = Files.walk(path);

            tempList = subPaths.map(p -> p.toString()).collect(Collectors.toList());

            tempList.remove(0);

            subFiles = tempList.toArray(String[]::new);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Accessor for var subFiles
     *
     * @return
     */
    public String[] getSubFiles() {
        return subFiles;
    }

    /**
     * Mutator for var subFiles
     *
     * s* @param subFiles
     */
    public void setSubFiles(String[] subFiles) {
        this.subFiles = subFiles;
    }

    /**
     * Accessor for var contents
     *
     * @return
     * @throws FolderDoesNotContainTextException
     */
    @Override
    public String getContents() throws FolderDoesNotContainTextException {
        throw new FolderDoesNotContainTextException();
    }

    /**
     *
     * @return
     */
    @Override
    public Entry clone() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Mutator for var contents
     *
     * @param temp
     */
    public void setContents(String temp) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * Return a string representation of the Local Folder that complies with the
     * requirements of the Print Processing Element in the Project Description.
     *
     * @return
     */
    @Override
    public String toString() {

        int index = address.lastIndexOf(File.separator);

        //If there is an issue with finding where the name of the file is just length to the whole file
        if (index == -1) {
            index = address.length();
        }

        return "Local Folder:\tName: " + address.substring(index + 1)
                + "\tLength: [ERROR]" + "\tAbsolute path: " + address;
    }
}
