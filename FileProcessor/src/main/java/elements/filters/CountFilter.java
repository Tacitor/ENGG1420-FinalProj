/*
 * Mackenzie Alec McBurney
 * Apr 1, 2023
 * Filter Processing Element that searches the contents of Entries for the number of occourences of a string
 */
package elements.filters;

import com.group7.FileProcessor.Util;
import com.group7.FileProcessor.entries.Entry;
import com.group7.FileProcessor.entries.FolderDoesNotContainTextException;
import elements.ProcessingElement;
import java.util.ArrayList;

/**
 *
 * @author kyure
 */
public class CountFilter extends ProcessingElement {

    //Attributes
    private String key;
    private int min;

    /**
     * Primary default constructor.
     * @param key
     * @param min
     */
    public CountFilter(String key, int min) {
        super();
    }
    
    /**
     * The process of the count filter.
     * Checks to see if an entry is a file.
     * Checks to see if the contents of the entry contains a string min number of times
     * 
     */
    @Override
    public void process() {        
        // Make a local reference of the input of entries
        ArrayList<Entry> input = this.getInputEntries();
        
        // Ouput of entries
        ArrayList<Entry> output = new ArrayList<>();

        for (int i = 0; i < input.size(); i++) {
            
            // Check to make sure entry is a file
            if (Util.isFile(input.get(i).getAddress()) == 1) {
                try {
                    
                    // Checks if the entry contains the key min number of times
                    if(contains(key, input.get(i).getContents(), min)){
                        output.add(input.get(i));
                    }
                } catch (FolderDoesNotContainTextException ex) {
                    System.out.println("Error in CountFilter.java\n" + ex);
                }
            }
        }
        // Outputs the filtered list of entries
        setOutputEntries(output);
    }
    
    /**
     * Accessor for the var Key
     * @return 
     */
    public String getKey() {
        return key;
    }
        
    /**
     * Mutator for the var Key
     * @param key 
     */
    public void setKey(String key) {
        this.key = key;
    }
    
    /**
     * Accessor for the var Min
     * @return 
     */
    public int getMin() {
        return min;
    }

    /**
     * Mutator for the min value. Ensure it is greater than 0. Default to 1 for
     * any 0 parameter. And the positive integer for any negative parameter.
     *
     * @param min
     */
    public void setMin(int min) {
        this.min = Util.getIntGreaterThan1(min);
    }
    
    /**
     * Contains method that checks for a String within a String "min" number of times
     * 
     * @param check
     * @param content
     * @param min
     * @return 
     */
    private boolean contains(String check, String content, int min) {
        
        boolean found = false; // Has check been found min times
        int contentLen = content.length();
        int checkLen = check.length();
        int similarity;
        int count = 0; // Keep track of how many times check has been found
        
        while (found == false) { // Ends when check is found min times
            for (int i = 0; i < contentLen; i++) {
                int j = 0; // check index
                if (content.charAt(i) == check.charAt(j)) { // Checks for first char similarity between both strings
                    similarity = 0;
                    
                    // Loop through both strings to check for continuing similarites
                    while (j < checkLen && i < contentLen && content.charAt(i) == check.charAt(j)) {
                        similarity++;
                        i++; // content index
                        j++; // check index
                    }
                    if (similarity == checkLen) {
                        count++; // Check has been found 
                    }
                    if (count == min) {
                        found = true; // Check has been found min number of times
                    }
                }
            }
        }
        return found;
    }
}
