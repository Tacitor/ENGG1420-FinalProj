/*
 * Group 7
 * April 2, 2023

 */
package com.group7.FileProcessor.entries;

import com.group7.FileProcessor.Util;
import static com.group7.FileProcessor.entries.RemFolder.accessKey;
import static com.group7.FileProcessor.entries.RemFolder.servicePrincipalKey;
import com.group7.FileProcessor.pojo.EntriesPOJO;
import com.laserfiche.repository.api.RepositoryApiClient;
import com.laserfiche.repository.api.RepositoryApiClientImpl;
import java.io.IOException;

/**
 *
 * @author keric
 */
public class EntryGenny {

    private String type;
    private String path;
    private String repositoryId;
    private int entryId;

    public Entry addEntry(EntriesPOJO entry) {
        if (entry.getType().equalsIgnoreCase("remte")) {
            //Grab the LaserFiche info
            repositoryId = entry.getRepositoryId();
            entryId = Integer.parseInt(entry.getEntryId());

            System.out.println("Remote entry.");

            //test for type
            RepositoryApiClient client = RepositoryApiClientImpl.createFromAccessKey(
                    servicePrincipalKey, accessKey);
            if (client.getEntriesClient().getEntry(repositoryId, entryId, null).join().getEntryType().toString().equals("Folder")) {
                System.out.println("REMOTE FOLDER");

                //create the Entry
                RemFolder remFolder = new RemFolder(entryId, repositoryId);
                return remFolder;

            } else {
                System.out.println("REMOTE FILE");

                try {
                    //create the Entry
                    RemFile remFile = new RemFile(entryId, repositoryId);
                    return remFile;
                } catch (IOException ex) {
                    System.out.println("IOException in EntryGenny.java when creating a new REMOTE FILE Entry\n" + ex);
                }
            }

        } else {//assume local
            path = entry.getPath();
            System.out.println("Local entry.");
            //path = Paths.get(entry.getPath());//get path as string use Paths.get to convert string to path
            if (Util.isFile(path) == 1) {//If path is valid file make entry object
                LocFile locFile = new LocFile(path);
                System.out.println("LocFile created");
                return locFile;
            } else if (Util.isFile(path) == 0) {
                LocFolder locFolder = new LocFolder(path);

                System.out.println("LocFolder created");
                return locFolder;
            }

        }
        return null;

    }
}
