/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elements;

import java.util.ArrayList;
import com.group7.FileProcessor.entries.Entry;

/**
 *
 * @author keric
 */
public class Rename extends ProcessingElement {

    //Attributes
    private String suffix;

    /**
     * Primary default constructor.
     */
    public Rename() {
        super();
    }

    @Override
    public void process() {
        ArrayList<Entry> sufName = this.getInputEntries();
        ArrayList<Entry> output = new ArrayList();
        for (Entry e : sufName) {
            String str = e.getAddress();
            Entry renamedEntry = e.clone();
            int index = str.lastIndexOf(".");
            if (index != -1) {
                str = str.substring(0, index - 1) + suffix + '.' + str.substring(index + 1);
            }
            renamedEntry.setAddress(str);
            output.add(renamedEntry);
        }

        this.setOutputEntries(output);
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

}
