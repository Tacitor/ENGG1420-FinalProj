/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group7.FileProcessor.entries;

import com.laserfiche.api.client.model.AccessKey;
import com.laserfiche.repository.api.RepositoryApiClient;
import com.laserfiche.repository.api.RepositoryApiClientImpl;
import com.laserfiche.repository.api.clients.impl.model.Entry;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfEntry;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.function.Consumer;
import java.io.IOException;

/**
 *
 * @author shale
 */
public class RemFile extends LocFile {

    private int entryId;
    private String repositoryId;
    private static String servicePrincipalKey = "z6N-YywpamJ5Dr227qKb";
    private static String accessKeyBase64 = "ewoJImN1c3RvbWVySWQiOiAiMTQwMTM1OTIzOCIsCgkiY2xpZW50SWQiOiAiY2M4NWRlZGUtMGIzZS00ZGIyLTkyNzYtOWY5N2EzMGY2ZWZlIiwKCSJkb21haW4iOiAibGFzZXJmaWNoZS5jYSIsCgkiandrIjogewoJCSJrdHkiOiAiRUMiLAoJCSJjcnYiOiAiUC0yNTYiLAoJCSJ1c2UiOiAic2lnIiwKCQkia2lkIjogInh0UFBLcl8zbGVSa1dFQi1QaU9yNkFDMzJETjRRdDd0VmI4amE4VHNwRTQiLAoJCSJ4IjogImtsZl9IOWgxMTN6dmRHcjZ0Rk5MRC1wYk15YWJWUjZzTk5Fb2l1d2NmSTgiLAoJCSJ5IjogInVWcUR4NlRSV0JBaWZTbXJ6NG9uX2NXeEZlTDVPc29xM2V6U0ZQbWtkRk0iLAoJCSJkIjogInJfMHZXYnB6Q2Z2YUYzYXZQeERhNzdKTzItUkRrSWctRnpHWENrbFRNQzgiLAoJCSJpYXQiOiAxNjc3Mjk3NTA4Cgl9Cn0=";
    //inputing our group acces key and converting it into an AccessKey object
    private static AccessKey accessKey = AccessKey.createFromBase64EncodedAccessKey(accessKeyBase64);

    public RemFile(int entryid, String repositoryId) throws IOException {

        //instantiating client
        RepositoryApiClient client = RepositoryApiClientImpl.createFromAccessKey(
                servicePrincipalKey, accessKey);

        // getting entry name
        String name = client.getEntriesClient().getEntry(repositoryId, entryid, null).join().getName();

        // creating a folder off the C drive to write files to
        File fold = new File("C:\\ENG1420Group7FileProccessor");
        boolean dir = true;
        if (!fold.exists()) {
            dir = fold.mkdir();
        }

        setAddress(fold.getAbsolutePath() + "\\" + name + ".txt");

        //downloading the entry of of the lazerfiche servers
        Consumer<InputStream> consumer = inputStream -> {
            File exportedFile = new File(getAddress());
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
                .exportDocument(repositoryId, entryid, null, consumer)
                .join();

        client.close();
        updateContents();
        this.entryId = entryid;
        this.repositoryId = repositoryId;
    }

    public int getEntryId() {
        return entryId;
    }

    public void setEntrId(int entryid) {
        this.entryId = entryid;
    }

    public String getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
    }

    @Override
    public RemFile clone() {
        //create the new remote file
        RemFile newRemFile;

        //try catch the new file creation
        try {
            newRemFile = new RemFile(entryId, repositoryId);

            //add the other entry information
            newRemFile.setAddress(address);
            newRemFile.setLength(length);
            newRemFile.setContents(contents);

        } catch (IOException e) {
            System.out.println("IOException in RemFile using clone(). Unable to create new Remote Entry. Returning null.\n" + e);

            //return it as a null object because of the exception
            newRemFile = null;
        }

        return newRemFile;
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

        return "Remote File:\tEntryID: " + entryId + "\tName: " + address.substring(0, index - 1)
                + "\tLength: " + length + "\tAbsolute path: [ERROR]";
    }

}
