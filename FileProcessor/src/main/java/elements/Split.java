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
        // Initialize ArrayList entries
        ArrayList<Entry> entries = getInputEntries();
        ArrayList<Entry> output = new ArrayList();
        String contents;
        String[] contentsByLine;//an array containing all the lines in the entry
        String temp;

        int num = -1;

        //running through all the entries
        for (int i = 0; i < entries.size(); i++) {
            try {//using a try-catch to insure that only files are split
                Entry entry = entries.get(i);// if the entry is a folder it will go into the catch
                contents = entry.getContents();

                contentsByLine = contents.split("\n");//split the contents of the entry based off of \n's

                for (int j = 0; j < contentsByLine.length;) {// loop goes intil it runs out of lines

                    num++;//update index

                    temp = "";
                    Entry clone = entry.clone();//creating a clone entry to perform operations on
                    for (int count = lines; count > 0 && j < contentsByLine.length; count--, j++) {

                        //count insures that the line limit is not exeeded, j is updated to avoid an out of bounds exception
                        //adding the specified number of lines to the temo string
                        temp += contentsByLine[j] + "\n";
                    }
                    //asigning the propper address to the clone
                    clone.setAddress(entry.getAddress().split("\\.")[0] + num + ".txt");
                    ((LocFile) clone).setContents(temp);// setting the contents to the temp string

                    output.add(clone);
                }
            } catch (FolderDoesNotContainTextException e) {//this code is only reachable if the entry if a folder
                output.add(entries.get(i));//just passes the entry through to the output as folders should be ignored
            }
        }

        setOutputEntries(output);//setting the output entries

    }

    /**
     * Accessor for var lines
     *
     * @return lines
     */
    public int getLines() {
        return lines;
    }

    /**
     * Mutator for var lines
     *
     * @param lines
     */
    public void setLines(int lines) {
        this.lines = Util.getIntGreaterThan1(lines);
    }

}
