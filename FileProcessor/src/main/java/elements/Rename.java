/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elements;

import java.util.ArrayList;
import com.group7.FileProcessor.entries.Entry;
import com.group7.FileProcessor.entries.LocFile;
import com.group7.FileProcessor.entries.LocFolder;

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
                    str = str.substring(0, index - 1) + suffix + '.' + str.substring(index + 1); // seperate str into two substring, pre '.' and post '.', replace '.' with suffix +'.'
                } else {
                    str = str.substring(0, index2 - 1) + suffix + '/' + str.substring(index2 + 1); // seperate str into two substring, pre '.' and post '.', replace '.' with suffix +'.'
                }
                renamedEntry.setAddress(str);// set that entries address to the new string of str
                output.add(renamedEntry); // add the entry to blank output arraylist
        }
        this.setOutputEntries(output); //output entries is set to the entries in output arrayList
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

}
