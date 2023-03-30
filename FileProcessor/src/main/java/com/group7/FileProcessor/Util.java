/*
 * Lukas Krampitz and Group 7
 * Mar 12, 2023
 * A utility helper class.
 */
package com.group7.FileProcessor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

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

}
