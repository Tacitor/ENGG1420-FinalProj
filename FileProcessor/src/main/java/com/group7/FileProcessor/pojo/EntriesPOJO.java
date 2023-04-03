/*
 * Group 7
 * April 2, 2023
 */
package com.group7.FileProcessor.pojo;

/**
 *
 * @author keric
 */
public class EntriesPOJO {

    private String type; // Local or remote
    private String path; // Local file or folder path
    private String repositoryId; // Unique identifier for the Laserfiche
    private String entryId; // Unique identifier for entry

    /**
     * Accessor for var type
     *
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * Mutator for var type
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Accessor for var path
     *
     * @return path
     */
    public String getPath() {
        return path;
    }

    /**
     * Mutator for var path
     *
     * @param path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Accessor for var repositoryId
     *
     * @return repositoryId
     */
    public String getRepositoryId() {
        return repositoryId;
    }

    /**
     * Mutator for var repositoryId
     *
     * @param repositoryId
     */
    public void setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
    }

    /**
     * Accessor for var entryId
     *
     * @return entryId
     */
    public String getEntryId() {
        return entryId;
    }

    /**
     * Mutator for var entryId
     *
     * @param entryId
     */
    public void setEntryId(String entryId) {
        this.entryId = entryId;
    }

}
