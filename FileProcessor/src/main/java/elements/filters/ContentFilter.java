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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    
    private boolean contains(String check, String content) {
        
        boolean found = false;// Has check been found
        int contentLen = content.length();
        int checkLen = check.length();
        int similarity;
        
        while (found == false) { // Ends when check is found
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
                        found = true; // Check has been found
                    }
                }
            }
        }
        return found;
    }

}
