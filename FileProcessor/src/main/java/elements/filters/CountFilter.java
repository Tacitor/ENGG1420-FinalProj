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
 * A class to filter through entries based on a search key and a minimum number
 * of appearances in their contents
 *
 * @author kyure
 */
public class CountFilter extends ProcessingElement {

    //Attributes
    private String key;
    private int min;

    /**
     * Primary default constructor.
     */
    public CountFilter() {
        super();
    }

    /**
     * The process of the count filter. Checks to see if an entry is a file.
     * Checks to see if the contents of the entry contains a string min number
     * of times
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
                    if (contains(key, input.get(i).getContents(), min)) {
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
     *
     * @return key
     */
    public String getKey() {
        return key;
    }

    /**
     * Mutator for the var Key
     *
     * @param key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Accessor for the var Min
     *
     * @return min
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
     * Contains method that checks for a String within a String "min" number of
     * times Is case sensitive
     *
     * @param check
     * @param content
     * @param min
     * @return
     */
    private boolean contains(String check, String content, int min) {
        //System.out.println("Started.");
        boolean found = false; // Has check been found min times
        int contentLen = content.length();
        int checkLen = check.length();
        int similarity;
        int count = 0; // Keep track of how many times check has been found
        int i = 0;
        while (found == false && i < contentLen) { // Ends when check is found min times
            int j = 0; // check index 
            // Create new strings to check with case insensitivity
            String str1 = "";
            String str2 = "";
            str1 += content.charAt(i);
            str2 += check.charAt(j);
            
            //System.out.println("Comparing: " + str1 +" to " + str2);
            
            if (str1.equalsIgnoreCase(str2)) { // Checks for first char similarity between both strings
                similarity = 0;

                // Loop through both strings to check for continuing similarites
                while (j < checkLen && i < contentLen && str1.equalsIgnoreCase(str2)) {
                    similarity++;
                    i++; // content index
                    j++; // check index
                    
                    if (j < checkLen && i < contentLen) {
                        // Create new strings to check with case insensitivity
                        String str3 = "";
                        String str4 = "";
                        str3 += content.charAt(i);
                        str4 += check.charAt(j);
                        str1 = str3;
                        str2 = str4;
                    }
                }
                if (similarity == checkLen) {
                    count++; // Check has been found 
                }
                if (count == min) {
                    //System.out.println("Count: " + count);
                    found = true; // Check has been found min number of times
                }
            }
            i++;
        }
        return found;
    }
}
