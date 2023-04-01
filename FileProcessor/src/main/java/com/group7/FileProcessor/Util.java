/*
 * Lukas Krampitz and Group 7
 * Mar 12, 2023
 * A utility helper class.
 */
package com.group7.FileProcessor;

import java.io.File;

/**
 *
 * @author Tacitor
 */
public abstract class Util {

    /**
     * Tests to see if a path is to a file or directory. Checks if the last
     * portion of the address contains a .
     *
     * @param path
     * @return an int. Return 1 if it is a file. 0 if it is not a file. and -1
     * for any error
     */
    public static int isFile(String path) {

        File file = new File(path);

        if (file.exists()) {
            return file.isFile() ? 1 : 0;
        } else {
            return -1;
        }

        //return 
        //return path.substring(path.lastIndexOf(File.separator), path.length()).contains(".");
    }

    /**
     * Ensure a given int is greater than 0. Default to 1 for any 0 parameter.
     * And the positive integer for any negative parameter.
     *
     * @param n
     * @return
     */
    public static int getIntGreaterThan1(int n) {
        //test for 0
        if (n == 0) {
            //set it to 1 to ensuer a value greater than 0
            return 1;
        } else {
            //set the provided value. Ensure it is positive
            return Math.abs(n);
        }
    }

}
