/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group7.FileProcessor.entries;

import java.io.File;
import com.laserfiche.api.client.model.AccessKey;
import com.laserfiche.repository.api.RepositoryApiClient;
import com.laserfiche.repository.api.RepositoryApiClientImpl;
import com.laserfiche.repository.api.clients.impl.model.Entry;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfEntry;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.function.Consumer;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author shale
 */
public class RemFolder extends LocFolder {

    
    int entryId;
    String repositoryId;
    ArrayList<Integer> entryIds = new ArrayList<Integer>();
    ArrayList<String> names = new ArrayList<String>();
    
    static String servicePrincipalKey = "z6N-YywpamJ5Dr227qKb";
    static String accessKeyBase64 = "ewoJImN1c3RvbWVySWQiOiAiMTQwMTM1OTIzOCIsCgkiY2xpZW50SWQiOiAiY2M4NWRlZGUtMGIzZS00ZGIyLTkyNzYtOWY5N2EzMGY2ZWZlIiwKCSJkb21haW4iOiAibGFzZXJmaWNoZS5jYSIsCgkiandrIjogewoJCSJrdHkiOiAiRUMiLAoJCSJjcnYiOiAiUC0yNTYiLAoJCSJ1c2UiOiAic2lnIiwKCQkia2lkIjogInh0UFBLcl8zbGVSa1dFQi1QaU9yNkFDMzJETjRRdDd0VmI4amE4VHNwRTQiLAoJCSJ4IjogImtsZl9IOWgxMTN6dmRHcjZ0Rk5MRC1wYk15YWJWUjZzTk5Fb2l1d2NmSTgiLAoJCSJ5IjogInVWcUR4NlRSV0JBaWZTbXJ6NG9uX2NXeEZlTDVPc29xM2V6U0ZQbWtkRk0iLAoJCSJkIjogInJfMHZXYnB6Q2Z2YUYzYXZQeERhNzdKTzItUkRrSWctRnpHWENrbFRNQzgiLAoJCSJpYXQiOiAxNjc3Mjk3NTA4Cgl9Cn0=";
    static AccessKey accessKey = AccessKey.createFromBase64EncodedAccessKey(accessKeyBase64);

    
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
    
    
    
    /*
    public RemFolder(int entryId, String repositoryId) {

        this.entryId = entryId;
        this.repositoryId = repositoryId;
        
        RepositoryApiClient client = RepositoryApiClientImpl.createFromAccessKey(
                servicePrincipalKey, accessKey);
        String name = client.getEntriesClient().getEntry(repositoryId, entryId, null).join().getName();

        File fold = new File("C:\\ENG1420Group7FileProccessor\\" + name);
        boolean dir = true;
        if (!fold.exists()) {
            dir = fold.mkdirs();
        }
        setAddress(fold.getAbsolutePath());

        download(entryId,client);
        
        client.close();

        
    }*/

    private void download(int entryId,RepositoryApiClient client,File folder) {

        

        String name;
        System.out.println(entryId);
        ODataValueContextOfIListOfEntry result = client
                .getEntriesClient()
                .getEntryListing(repositoryId, entryId, true, null, null, null, null, null, "name", null, null, null)
                .join();
        List<Entry> entries = result.getValue();
        for (Entry childEntry : entries) {
            if (client.getEntriesClient().getEntry(repositoryId, childEntry.getId(), null).join().getEntryType().toString().equals("Folder")) {
                name = client.getEntriesClient().getEntry(repositoryId, childEntry.getId(), null).join().getName();
                File subFolder =  new File(folder.getAbsolutePath()+"\\"+name);
                download(childEntry.getId(),client,subFolder);
            } else {
                
                name = client.getEntriesClient().getEntry(repositoryId, childEntry.getId(), null).join().getName();
                
                String str = folder.getAbsolutePath()+"\\"+name+".txt";
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

    public int getEntryId() {
        return entryId;
    }

    public void setentryId(int entryId) {
        this.entryId = entryId;
    }

    public String getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
    }

}
