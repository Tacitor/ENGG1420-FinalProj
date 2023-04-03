/*
 * Group 7
 * April 2, 2023
 */
package com.group7.FileProcessor.entries;

import java.io.File;
import com.laserfiche.api.client.model.AccessKey;
import com.laserfiche.repository.api.RepositoryApiClient;
import com.laserfiche.repository.api.RepositoryApiClientImpl;
import com.laserfiche.repository.api.clients.impl.model.Entry;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfEntry;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.function.Consumer;
import java.io.IOException;

/**
 *
 * @author shale
 */
public class RemFolder extends LocFolder {

    private int entryId;
    private String repositoryId;
    private ArrayList<Integer> entryIds = new ArrayList<Integer>();
    private ArrayList<String> names = new ArrayList<String>();

    static String servicePrincipalKey = "z6N-YywpamJ5Dr227qKb";
    static String accessKeyBase64 = "ewoJImN1c3RvbWVySWQiOiAiMTQwMTM1OTIzOCIsCgkiY2xpZW50SWQiOiAiY2M4NWRlZGUtMGIzZS00ZGIyLTkyNzYtOWY5N2EzMGY2ZWZlIiwKCSJkb21haW4iOiAibGFzZXJmaWNoZS5jYSIsCgkiandrIjogewoJCSJrdHkiOiAiRUMiLAoJCSJjcnYiOiAiUC0yNTYiLAoJCSJ1c2UiOiAic2lnIiwKCQkia2lkIjogInh0UFBLcl8zbGVSa1dFQi1QaU9yNkFDMzJETjRRdDd0VmI4amE4VHNwRTQiLAoJCSJ4IjogImtsZl9IOWgxMTN6dmRHcjZ0Rk5MRC1wYk15YWJWUjZzTk5Fb2l1d2NmSTgiLAoJCSJ5IjogInVWcUR4NlRSV0JBaWZTbXJ6NG9uX2NXeEZlTDVPc29xM2V6U0ZQbWtkRk0iLAoJCSJkIjogInJfMHZXYnB6Q2Z2YUYzYXZQeERhNzdKTzItUkRrSWctRnpHWENrbFRNQzgiLAoJCSJpYXQiOiAxNjc3Mjk3NTA4Cgl9Cn0=";
    //inputing our group acces key and converting it into an AccessKey object
    static AccessKey accessKey = AccessKey.createFromBase64EncodedAccessKey(accessKeyBase64);

    /*unsed construtor took to much time to make to have the heart to delete it
    
    public RemFolder(int entryId, String repositoryId) {
        this.entryId = entryId;
        this.repositoryId = repositoryId;
        
        
        
        RepositoryApiClient client = RepositoryApiClientImpl.createFromAccessKey(
                servicePrincipalKey, accessKey);
        String name = client.getEntriesClient().getEntry(repositoryId, entryId, null).join().getName();
        
        ODataValueContextOfIListOfEntry result = client
                .getEntriesClient()
                .getEntryListing(repositoryId, entryId, true, null, null, null, null, null, "name", null, null, null)
                .join();
        List<Entry> entries = result.getValue();
        for (Entry childEntry : entries) {
            entryIds.add(childEntry.getId());
            names.add(childEntry.getName());
        }
        
        client.close();

    }*/
    public RemFolder(int entryId, String repositoryId) {

        this.entryId = entryId;
        this.repositoryId = repositoryId;

        //instantiating client
        RepositoryApiClient client = RepositoryApiClientImpl.createFromAccessKey(
                servicePrincipalKey, accessKey);
        String name = client.getEntriesClient().getEntry(repositoryId, entryId, null).join().getName();        
        
        ODataValueContextOfIListOfEntry result = client
                .getEntriesClient()
                .getEntryListing(repositoryId, entryId, true, null, null, null, null, null, "name", null, null, null)
                .join();
        List<Entry> entries = result.getValue();
        
        for (Entry childEntry : entries) {
            entryIds.add(childEntry.getId());
            names.add(childEntry.getName());
        }        

        File fold;
        //only the root folder has an empty name, with my code it'll just put all the files in the Folders folder
        //this is a bit of a workaround
        if (name.equals("")) {
            fold = new File("C:\\ENG1420Group7FileProccessor\\Folders\\root");
        } else {
            fold = new File("C:\\ENG1420Group7FileProccessor\\Folders\\" + name);
        }

        //creating a folder off the C drive to write files to
        boolean dir = true;
        if (!fold.exists()) {
            dir = fold.mkdirs();
        }
        setAddress(fold.getAbsolutePath());
        updateSubFiles();

        download(entryId, client, fold);// actually downloading the contents of the entry

        client.close();

    }

    /**
     * a recursive method to download all subFolders of the entry to the correct
     * location on disc
     *
     * @param entryId id of the entry
     * @param client the client object being used
     * @param folder the folder that you want the contents to be downloaded to
     */
    private void download(int entryId, RepositoryApiClient client, File folder) {

        String name;
        //creating a list of all subEntries
        ODataValueContextOfIListOfEntry result = client
                .getEntriesClient()
                .getEntryListing(repositoryId, entryId, true, null, null, null, null, null, "name", null, null, null)
                .join();

        List<Entry> entries = result.getValue();

        for (Entry childEntry : entries) {
            // the lazerfiche ipa cannot download folders so if the entry is a folder more actions must be preformed
            if (client.getEntriesClient().getEntry(repositoryId, childEntry.getId(), null).join().getEntryType().toString().equals("Folder")) {

                //getting the name of the subfolder
                name = client.getEntriesClient().getEntry(repositoryId, childEntry.getId(), null).join().getName();
                //creating a subfolder of that name in the given folder
                File subFolder = new File(folder.getAbsolutePath() + "\\" + name);
                boolean dir = true;
                if (!subFolder.exists()) {
                    dir = subFolder.mkdirs();
                }
                //calling the function on the subEntry to download its contents
                download(childEntry.getId(), client, subFolder);
            } else {

                //getting the name of the file
                name = client.getEntriesClient().getEntry(repositoryId, childEntry.getId(), null).join().getName();

                //downloading the file
                String str = folder.getAbsolutePath() + "\\" + name + ".txt";
                Consumer<InputStream> consumer = inputStream -> {

                    File exportedFile = new File(str);
                    try (FileOutputStream outputStream = new FileOutputStream(exportedFile)) {
                        byte[] buffer = new byte[1024];
                        while (true) {
                            int length = inputStream.read(buffer);
                            if (length == -1) {
                                break;
                            }
                            outputStream.write(buffer, 0, length);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                };

                client.getEntriesClient()
                        .exportDocument(repositoryId, childEntry.getId(), null, consumer)
                        .join();

            }
        }
    }

    /**
     * Accessor for var entryId
     *
     * @return entryId
     * @return
     */
    public int getEntryId() {
        return entryId;
    }

    /**
     * Mutator for var entryId
     *
     * @param entryId
     */
    public void setentryId(int entryId) {
        this.entryId = entryId;
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

    @Override
    public com.group7.FileProcessor.entries.Entry clone() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Return a string representation of the Remote Folder that complies with
     * the requirements of the Print Processing Element in the Project
     * Description.
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

        return "Local Folder:\tEntryID: " + entryId + "\tName: " + address.substring(index + 1)
                + "\tLength: [ERROR]" + "\tAbsolute path: [ERROR]";
    }

    public ArrayList<Integer> getEntryIds() {
        return entryIds;
    }

    public void setEntryIds(ArrayList<Integer> entryIds) {
        this.entryIds = entryIds;
    }

    public ArrayList<String> getNames() {
        return names;
    }

    public void setNames(ArrayList<String> names) {
        this.names = names;
    }

}
