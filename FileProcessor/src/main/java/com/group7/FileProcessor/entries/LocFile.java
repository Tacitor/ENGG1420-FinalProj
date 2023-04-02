/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group7.FileProcessor.entries;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author shalev
 */
public class LocFile extends Entry {//couldn't name file or it would conflict with the File built in class

    File file;
    Scanner scanner;

    public LocFile(String address) {
        super(address);
        // updating the stored contents using the address
        updateContents();
    }

    protected LocFile() {
    }//default protected constructor to be used bythe remote classes since they generate their own addresses 

    @Override
    public String getContents() throws FolderDoesNotContainTextException {
        return contents;
    }

    protected void setContents(String contents) {
        this.contents = contents;
    }

    /**
     * a method to update the stored contents, works similar to a refresh to
     * check for any changes to the file
     */
    public void updateContents() {
        try {
            file = new File(address);

            scanner = new Scanner(file);

            //setting contents
            StringBuilder temp = new StringBuilder();
            while (scanner.hasNext()) {
                temp.append(scanner.nextLine()); // running through all the lines in the file and adding them to temp
                temp.append("\n");
            }
            setContents(temp.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * a method to create a clone of a locFile instance
     *
     * @return returns the clone
     */
    @Override
    public LocFile clone() {
        LocFile newLocFile = new LocFile();

        newLocFile.setAddress(address);
        newLocFile.setContents(contents);
        newLocFile.setLength(length);

        return newLocFile;
    }

    /**
     * Return a string representation of the Local File that complies with the
     * requirements of the Print Processing Element in the Project Description.
     *
     * @return
     */
    @Override
    public String toString() {

        int index = address.lastIndexOf(".");

        //If there is an issue with finding where the name of the file is just length to the whole file
        if (index == -1) {
            index = address.length();
        }

        return "Local File:\tName: " + address.substring(0, index - 1)
                + "\tLength: " + length + "\tAbsolute path: " + address;
    }

}
