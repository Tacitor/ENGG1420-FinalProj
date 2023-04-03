/*
 * Group 7
 * April 2, 2023
 */
package com.group7.FileProcessor.entries;

import java.io.File;
import java.io.FileNotFoundException;

/**
 *
 * @author shalev
 */
public abstract class Entry {

    protected long length; // stores length in byte formatt
    protected String address;
    protected String contents;

    public Entry(String address) {
        this.address = address;
        
    }

    protected Entry() {
    }//default protected constructor to be used bythe remote classes since they generate their own addresses

    /**
     * Accessor for var length
     *
     * @return
     */
    public long getLength() {
        return length;
    }

    /**
     * Accessor for var address
     *
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     * Public Mutator for the address of an Entry. This method is public because
     * it needs to be accessed by other classes such as Split and Rename.
     *
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Mutator for var length
     *
     * @param length
     */
    protected void setLength(long length) {
        this.length = length;
    }

    public abstract String getContents() throws FolderDoesNotContainTextException;

    /**
     * a method to get the stored size of a file or folder on disk
     *
     * @param address
     * @return
     * @throws java.io.FileNotFoundException
     */
    public static long getLength(String address) throws FileNotFoundException {

        File file = new File(address);
        if (!file.exists()) {
            throw new FileNotFoundException();
        } else {
            if (file.isFile()) {
                return file.length();
            } else {
                File[] subFiles = file.listFiles();
                long total = 0;
                for (File subFile : subFiles) {
                    total += getLength(subFile.getAbsolutePath());
                }
                return total;
            }
        }
    }

    @Override
    public abstract Entry clone();
}
