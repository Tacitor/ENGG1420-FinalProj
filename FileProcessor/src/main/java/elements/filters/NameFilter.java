/*
 * Mackenzie Alec McBurney
 * Apr 1, 2023
 * Filter Processing Element that searches the names of Entries
 */
package elements.filters;

import com.group7.FileProcessor.entries.Entry;
import elements.ProcessingElement;
import java.util.ArrayList;

/**
 * A class that filters through entries based on a search key in their
 * names.
 *
 * @author kyure
 */
public class NameFilter extends ProcessingElement {

    //Attributes
    private String key;

    /**
     * Primary default constructor.
     */
    public NameFilter() {
        super();
    }

    @Override
    public void process() {
        // Make a local reference of the input of entries
        ArrayList<Entry> input = this.getInputEntries();

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
     * A method to find the name of the entry within the address
     *
     * @param address
     * @return
     */
    private String getName(String address) {
        String name = "";
        int i = address.length() - 1; // Final index of the address
        int nameLen = 0;
        char ch = address.charAt(i); // Make sure the while loop will start
        i--;
        // Cycles through address backwards to find the length of the entry name
        while (ch != 92) {
            nameLen++;
            ch = address.charAt(i);
            i--;
        }
        // Put the name of the address togther in a string
        for (int j = address.length() - nameLen; j < address.length(); j++) {
            name += address.charAt(j);
        }
        return name;
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
    private boolean contains(String check, String content) {

        boolean found = false; // Has check been found
        int contentLen = content.length();
        int checkLen = check.length();
        int similarity;
        int i = 0;

        while (found == false && i < contentLen) { // Ends when check is found
            int j = 0; // check index
            
            // Create new strings to check with case insensitivity
            String str1 = "";
            String str2 = "";
            str1 += content.charAt(i);
            str2 += check.charAt(j);
            
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
                    found = true; // Check has been found
                }
            }
            i++;
        }
        return found;
    }
}
