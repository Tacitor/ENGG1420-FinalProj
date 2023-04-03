/*
 * Group 7
 * April 2, 2023
 */
package com.group7.FileProcessor.entries;

import static com.group7.FileProcessor.entries.Entry.getLength;
import com.laserfiche.api.client.model.AccessKey;
import com.laserfiche.repository.api.RepositoryApiClient;
import com.laserfiche.repository.api.RepositoryApiClientImpl;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
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

    public RemFile(int entryid, String repositoryId, boolean doUpdateContents) throws IOException {

        //instantiating client
        RepositoryApiClient client = RepositoryApiClientImpl.createFromAccessKey(
                servicePrincipalKey, accessKey);

        //check for rate limiting the laserfiche API
        if (doUpdateContents) {
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

        }

        client.close();

        //check is the contents should be updates upon construction. This switch exists so that we don't get rate limited by the LaserFiche API
        if (doUpdateContents) {
            updateContents();

            //update the length
            try {
                this.setLength(getLength(address));
            } catch (FileNotFoundException e) {
                System.out.println("ERROR");
                this.setLength(-2);
            }
        }
        this.entryId = entryid;
        this.repositoryId = repositoryId;

    }

    /**
     * An accessor for the var entryId
     *
     * @return
     */
    public int getEntryId() {
        return entryId;
    }

    /**
     * A mutator for the var entryId
     *
     * @param entryid
     */
    public void setEntrId(int entryid) {
        this.entryId = entryid;
    }

    /**
     * An accessor for the var entryId
     *
     * @return
     */
    public String getRepositoryId() {
        return repositoryId;
    }

    /**
     * A mutator for the var repositoryId
     *
     * @param repositoryId
     */
    public void setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
    }

    /**
     * A method that returns a copy of a file.
     *
     * @return
     */
    @Override
    public RemFile clone() {
        //create the new remote file
        RemFile newRemFile;

        //try catch the new file creation
        try {
            newRemFile = new RemFile(entryId, repositoryId, false);

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
     * Return a string representation of the Remote File that complies with the
     * requirements of the Print Processing Element in the Project Description.
     *
     * @return
     */
    @Override
    public String toString() {

        int index = address.lastIndexOf(File.separator);

        //If there is an issue with finding where the name of the file is just length to the whole file
        if (index == -1) {
            System.out.println("ERROR In RemFile.java toString()");
            index = 0;
        }

        return "Remote File:\tEntryID: " + entryId + "\tName: " + address.substring(index + 1)
                + "\tLength: " + length + "\tAbsolute path: [ERROR]";
    }

}
