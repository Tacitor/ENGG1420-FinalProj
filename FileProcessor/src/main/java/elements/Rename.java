/*
 * Group 7
 * April 2, 2023
 */
package elements;

import java.util.ArrayList;
import com.group7.FileProcessor.entries.Entry;
import com.group7.FileProcessor.entries.LocFile;

/**
 *
 * @author Sheel
 */
public class Rename extends ProcessingElement {

    //Attributes
    private String suffix;

    /**
     * Primary default constructor.
     */
    public Rename() {
        super();
    }

    /**
     * Input: a list of entries, and a string value Suffix What it does: for
     * each entry in the input list, it appends the given string Suffix to the
     * name of the entry. For instance, if an input entry has the name
     * “file1.txt” and Suffix=”_copy”, then the name of the entry becomes
     * “file1_copy.txt” Output: a list of entries, including the same entries as
     * the input, with the updated names
     */
    @Override
    public void process() {
        ArrayList<Entry> sufName = this.getInputEntries();// creates array that is the input entries 
        ArrayList<Entry> output = new ArrayList(); //creates a output array 
        for (Entry e : sufName) { //created a for loop that accesses the addresses within the array to create a string, str
            String str = e.getAddress();
            Entry renamedEntry = e.clone(); // clone the input entries string 
            int index = str.lastIndexOf(".");// search through the string to find the last instance of '.'
            int index2 = str.lastIndexOf("/");// search through the string to find the last instance of '.'

            if (e instanceof LocFile) { //if its a file or a folder
                str = str.substring(0, index) + suffix + '.' + str.substring(index + 1); // seperate str into two substring, pre '.' and post '.', replace '.' with suffix +'.'
            } else {
                str = str.substring(0, index2) + suffix + '/' + str.substring(index2 + 1); // seperate str into two substring, pre '.' and post '.', replace '.' with suffix +'.'
            }
            renamedEntry.setAddress(str);// set that entries address to the new string of str
            output.add(renamedEntry); // add the entry to blank output arraylist
        }
        this.setOutputEntries(output); //output entries is set to the entries in output arrayList
    }

    /**
     * Accessor for var suffix
     *
     * @return suffix
     */
    public String getSuffix() {
        return suffix;
    }

    /**
     * Mutator for var suffix
     *
     * @param suffix
     */
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

}
