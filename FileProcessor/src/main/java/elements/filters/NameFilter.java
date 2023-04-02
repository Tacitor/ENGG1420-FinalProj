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
            String name = getName(input.get(i).getAddress());
            if (contains(key, name)) {
                output.add(input.get(i));
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

    private String getName(String address) {
        String name = "";
        int i = address.length() - 1;
        // Find start of file or folder name
        int nameLen = 0;
        char ch = address.charAt(0);
        i--;
        while (ch != 92) {
            nameLen++;
            ch = address.charAt(i);
            i--;
        }
        for (int j = address.length() - nameLen; j < address.length(); j++) {
            name += address.charAt(i);
        }
        return name;
    }

    private boolean contains(String check, String content) {
        boolean found = false;
        int contentLen = content.length();
        int checkLen = check.length();
        int similarity;
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
                        found = true;
                    }
                }
            }
        }
        return found;
    }
}
