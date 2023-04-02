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
        
        
        
        setOutputEntries(output);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    private boolean contains(String check, String content, int min) {
        boolean found = false;
        int contentLen = content.length();
        int checkLen = check.length();
        int similarity;
        int count = 0;
        while (found == false) {
            for (int i = 0; i < contentLen; i++) {
                int j = 0;
                if (content.charAt(i) == check.charAt(j)) {
                    similarity = 0;
                    while (j < checkLen && i < contentLen && content.charAt(i) == check.charAt(j)) {
                        similarity++;
                        i++;
                        j++;
                    }
                    if (similarity == checkLen) {
                        count++;
                    }
                    if (count == min) {
                        found = true;
                    }
                }
            }
        }
        return found;
    }

}
