/*
 * ENGG*1420
 * Group 7
 */
package elements;

import java.util.ArrayList;
import com.group7.FileProcessor.entries.Entry;
import com.group7.FileProcessor.Util;
import com.group7.FileProcessor.entries.FolderDoesNotContainTextException;
import com.group7.FileProcessor.entries.LocFile;
import com.group7.FileProcessor.entries.LocFolder;
import com.group7.FileProcessor.entries.RemFolder;

/**
 *
 * @author emj.vhart
 */
public class List extends ProcessingElement {

    //Attributes
    private int max;

    /**
     * Primary default constructor.
     */
    public List() {
        super();
    }
    
    @Override
    public void process() {
        /*
        int untilMax = 0;//creating counter
        boolean found = false;
        ArrayList<Entry> entryList = this.getInputEntries();//gets entries
        ArrayList<Entry> output = new ArrayList();//creates output entry arraylist
        for (Entry e : entryList) {
            String[] addresses = ((LocFolder) e).getSubFiles();//gets directories
            for (String s : addresses) {
                if (e.getAddress().equals(s)) {//compares addresses and entries
                    found = true;
                }
            }

            if (found == true) {
                output.add(e);
                untilMax++;
                if (untilMax == max)//stops loop if max has been hit
                {
                    break;
                }
            }
        }
         */

        ArrayList<Entry> inputEntrys = this.getInputEntries();
        ArrayList<Entry> output = new ArrayList<>();
        int maxFiles;

        //loop through the input
        for (Entry e : inputEntrys) {

            //check if this entry is a dir
            if (e instanceof LocFolder) {
                //Get all the subfiles addresses
                String[] subFiles = ((LocFolder) e).getSubFiles();

                //set the max num if files to be either all or max
                if (subFiles.length < max) {
                    maxFiles = subFiles.length;
                } else {
                    maxFiles = max;
                }

                //list max number of sub folders
                for (int i = 0; i < maxFiles; i++) {
                    //create the entry
                    Entry newEntryToAdd;

                    //figure out if this is remote or not
                    if (e instanceof LocFolder) {
                        newEntryToAdd = new LocFile(subFiles[i]);

                        //add it to the output
                        output.add(newEntryToAdd);
                    } else {
                        newEntryToAdd = null;
                        System.out.println("ERROR: Attempted list of a Remote Folder in List.java for Entry:\t" + e.getAddress() + "\tEntry ID of: " + (int)(Math.random()*25));
                    }
                }
            }
        }
        
        //pass the output
        this.setOutputEntries(output);;

    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = Util.getIntGreaterThan1(max);
    }

}
