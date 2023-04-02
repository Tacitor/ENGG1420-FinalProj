/*
 * Lukas Krampitz
 * Apr 1, 2023
 * Filter Processing Element that searches the names of Entries
 */
package elements.filters;

import com.group7.FileProcessor.entries.Entry;
import elements.ProcessingElement;
import java.util.ArrayList;

/**
 *
 * @author Tacitor
 */
public class NameFilter extends ProcessingElement {

    //Attributes
    private String key;

    /**
     * Primary default constructor.
     *
     * @param key
     */
    public NameFilter(String key) {
        super();
        setKey(key);
    }

    @Override
    public void process() {
        // Make a copy of the input of entries
        ArrayList<Entry> inputCopy = this.getInputEntries();
        ArrayList<Entry> input = new ArrayList<>();
        for (int i = 0; i < inputCopy.size(); i++) {
            input.add(inputCopy.get(i));
        }
        // Ouput of entries
        ArrayList<Entry> output = new ArrayList<>();

        for (int i = 0; i < input.size(); i++) {
            String name = getName(input.get(i).getAddress()); // Finds the name of the entry
            if (contains(key, name)) {
                output.add(input.get(i)); // Adds the entry to the ouput if the name contains the key
            }
        }

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
      * A method to find the name of the entry within the address
      * 
      * @param address
      * @return 
      */
    private String getName(String address) {
        String name = "";
        int i = address.length() - 1; // Final index of the address
        int nameLen = 0; 
        char ch = address.charAt(0); // Make sure the while loop will start
        i--;
        while (ch != 92) { // Get the length of the name of the entry
            nameLen++;
            ch = address.charAt(i);
            i--;
        }
        // Put the name of the address togther in a string
        for (int j = address.length() - nameLen; j < address.length(); j++) {
            name += address.charAt(i);
        }
        return name;
    }
    /**
     * A method that checks for a String within a String
     * 
     * @param check
     * @param content
     * @return 
     */
    private boolean contains(String check, String content) {
        
        boolean found = false; // Has check been found
        int contentLen = content.length();
        int checkLen = check.length();
        int similarity;
        
        while (found == false) { // Ends when check is found
            for (int i = 0; i < contentLen; i++) {
                int j = 0;
                if (content.charAt(i) == check.charAt(j)) { // Checks for first char similarity between both strings
                    similarity = 0;
                    
                    // Loop through both strings to check for continuing similarites
                    while (j < checkLen && i < contentLen && content.charAt(i) == check.charAt(j)) {
                        similarity++;
                        i++;
                        j++;
                    }
                    if (similarity == checkLen) {
                        found = true; // Check has been found
                    }
                }
            }
        }
        return found;
    }
}
