/*
 * Group 7
 * April 2, 2023
 */
package elements;

import com.group7.FileProcessor.Util;
import com.group7.FileProcessor.entries.Entry;
import com.group7.FileProcessor.entries.FolderDoesNotContainTextException;
import com.group7.FileProcessor.entries.LocFile;
import java.util.ArrayList;

/**
 *
 * @author keric
 */
public class Split extends ProcessingElement {

    //Attributes
    private int lines;

    /**
     * Primary default constructor.
     */
    public Split() {
        super();
    }

    /**
     * Input: a list of entries, and an integer value Lines greater than 0 What
     * it does: for each entry in the input, if it is a file, then it splits the
     * file on a line by line basis, based on the given value Lines. The
     * original file is kept unchanged. If the entry is a directory, it is
     * ignored. Output: the list of created entries resulted from splitting the
     * input entries.
     */
    @Override
    public void process() {
        ArrayList<Entry> entries = getInputEntries();
        ArrayList<Entry> output = new ArrayList();
        String contents;
        String[] contentsByLine;
        String temp;
        int num = -1;

        for (int i = 0; i < entries.size(); i++) {
            try {
                Entry entry = entries.get(i);
                contents = entry.getContents();
                contentsByLine = contents.split("\n");

                for (int j = 0; j < contentsByLine.length;) {

                    num++;
                    temp = "";
                    Entry clone = entry.clone();
                    for (int count = lines; count > 0 && j < contentsByLine.length; count--, j++) {
                        temp += contentsByLine[j] + "\n";
                    }
                    clone.setAddress(entry.getAddress().split("\\.")[0] + num + ".txt");
                    ((LocFile) clone).setContents(temp);

                    output.add(clone);
                }
            } catch (FolderDoesNotContainTextException e) {
                output.add(entries.get(i));
            }
        }
        setOutputEntries(output);
    }
    
    /**
     * Accessor for var lines
     * @return 
     */
    public int getLines() {
        return lines;
    }
    
    /**
     * Mutator for var lines
     * @param lines 
     */
    public void setLines(int lines) {
        this.lines = Util.getIntGreaterThan1(lines);
    }

}
