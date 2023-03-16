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
     * @return
     */
    public static boolean isFile(String path) {

        return path.substring(path.lastIndexOf(File.separator), path.length()).contains(".");
    }

}
