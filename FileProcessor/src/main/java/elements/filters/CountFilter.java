/*
 * Lukas Krampitz
 * Apr 1, 2023
 * Filter Processing Element that searches the contents of Entries for the number of occourences of a string
 */
package elements.filters;

import com.group7.FileProcessor.Util;
import com.group7.FileProcessor.entries.Entry;
import com.group7.FileProcessor.entries.FolderDoesNotContainTextException;
import elements.ProcessingElement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tacitor
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

    @Override
    public void process() {
        ArrayList<Entry> output = new ArrayList<>();
        
        // In case min input is out of range set to 1
        if (min <= 0) {
            min = 1;
        }
        
        // Make a copy of the input of entries
        ArrayList<Entry> inputCopy = this.getInputEntries();
        ArrayList<Entry> input = new ArrayList<>();
        for (int i = 0; i < inputCopy.size(); i++) {
            input.add(inputCopy.get(i));
        }
        
        for (int i = 0; i < input.size(); i++) {
            //Check to make sure entry is a file
            if (Util.isFile(input.get(i).getAddress()) == 1) {
                try {
                    if(contains(key, input.get(i).getContents(), min)){
                        output.add(input.get(i));
                    }
                } catch (FolderDoesNotContainTextException ex) {
                    Logger.getLogger(CountFilter.class.getName()).log(Level.SEVERE, null, ex);
                }
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
