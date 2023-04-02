/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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

    @Override
    public void process() {
        //Grab th e input ArrayList to make things easier
        ArrayList<Entry> inputEntries = this.getInputEntries();

        //loop through the input entries
        for (Entry e : inputEntries) {
            //print its information
            System.out.println("Gimme that verifiable info");
            System.out.println(e.toString());
        }
    }

}
