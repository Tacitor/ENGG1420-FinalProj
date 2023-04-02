/*
 * Lukas Krampitz
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
 * @author Tacitor
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
        // Make a copy of the input of entries
        ArrayList<Entry> inputCopy = this.getInputEntries();
        ArrayList<Entry> input = new ArrayList<>();
        for (int i = 0; i < inputCopy.size(); i++) {
            input.add(inputCopy.get(i));
        }
        // Ouput of entries
        ArrayList<Entry> output = new ArrayList<>();
        
        for (int i = 0; i < input.size(); i++) {
            try {
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
                int j = 0;
                if (content.charAt(i) == check.charAt(j)) { // Checks for first char similarity between both strings
                    similarity = 0;
                    
                    // Loop through both strings to check for continuing similarites
                    while (j < checkLen && i < contentLen && content.charAt(i) == check.charAt(j)) {
                        similarity++;
                        i++;
                        j++;
                    }
                    if (similarity == checkLen) { // Check has been found
                        found = true;
                    }
                }
            }
        }
        return found;
    }

}
