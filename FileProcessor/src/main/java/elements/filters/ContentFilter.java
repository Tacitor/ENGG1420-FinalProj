/*
 * Mackenzie Alec McBurney
 * Apr 1, 2023
 * Filter Processing Element that searches the contents of Entries
 */
package elements.filters;

import com.group7.FileProcessor.entries.Entry;
import com.group7.FileProcessor.entries.FolderDoesNotContainTextException;
import elements.ProcessingElement;
import java.util.ArrayList;

/**
 * A class that filters through entries based on a search key in their contents
 *
 * @author kyure
 */
public class ContentFilter extends ProcessingElement {

    //Attributes
    private String key;

    /**
     * Primary default constructor.
     */
    public ContentFilter() {
        super();
    }

    @Override
    public void process() {
        // Make a local reference of the input of entries
        ArrayList<Entry> input = this.getInputEntries();

        // Ouput of entries
        ArrayList<Entry> output = new ArrayList<>();

        for (int i = 0; i < input.size(); i++) {
            try {
                // Checks if the key appears within the contents of the entry
                if (contains(key, input.get(i).getContents())) {
                    output.add(input.get(i));
                }
            } catch (FolderDoesNotContainTextException ex) {
                System.out.println("Error in ContentFilter.java\n" + ex);
            }
        }

        setOutputEntries(output);
    }

    /**
     * Accessor for var key
     *
     * @return key
     */
    public String getKey() {
        return key;
    }

    /**
     * Mutator for var key
     *
     * @param key
     */
    public void setKey(String key) {
        this.key = key;
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

        boolean found = false;// Has check been found
        int contentLen = content.length();
        int checkLen = check.length();
        int similarity;
        int i = 0;

        while (found == false && i < contentLen) { // Ends when check is found
            int j = 0; // check index
            String str1 = "";
            String str2 = "";
            str1 += content.charAt(i);
            str2 += check.charAt(j);
            if (str1.equalsIgnoreCase(str2)) { // Checks for first char similarity between both strings
                similarity = 0;

                // Loop through both strings to check for continuing similarites
                while (j < checkLen && i < contentLen && content.charAt(i) == check.charAt(j)) {
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
