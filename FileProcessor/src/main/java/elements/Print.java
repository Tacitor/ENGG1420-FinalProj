/*
 * Group 7
 * April 2, 2023
 */
package elements;

import com.group7.FileProcessor.entries.Entry;
import java.util.ArrayList;

/**
 *
 * @author keric
 */
public class Print extends ProcessingElement {

    /**
     * Primary default constructor.
     */
    public Print() {
        super();
    }

    /**
     * Input: a list of entries What it does: prints the information of the
     * entry. For local entries, this includes the name, length, and absolute
     * path of the entry. For remote entries, this includes the entryId, name,
     * length, and absolute path of the entry. Output: the same list of entries
     * as input
     */
    @Override
    public void process() {
        //Grab th e input ArrayList to make things easier
        ArrayList<Entry> inputEntries = this.getInputEntries();

        System.out.println("\n+++++Print Processing Element+++++");
        //loop through the input entries
        for (Entry e : inputEntries) {
            //print its information
            System.out.println(e.toString());
        }
        
        this.setOutputEntries(inputEntries);
    }
}
