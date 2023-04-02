/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elements;

import com.group7.FileProcessor.Util;
import com.group7.FileProcessor.entries.Entry;
import com.group7.FileProcessor.entries.FolderDoesNotContainTextException;
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

    @Override
    public void process() {
        ArrayList<Entry> entries = getInputEntries();
        ArrayList<Entry> output = new ArrayList<Entry>();
        String contents;
        String[] contentsByLine;
        String temp;

        for (int i = 0; i < entries.size(); i++) {
            try {
                Entry entry = entries.get(i);
                contents = entry.getContents();
                contentsByLine = contents.split("\n");
                
                for (int j = 0; j < contentsByLine.length; j++) {
                    temp = "";
                    Entry clone = entry.clone();
                    for (int count = lines; count > 0 && j < contentsByLine.length; count--) {
                        temp+=contentsByLine[count]+"\n";
                    }
                    clone.setAddress(entry.getAddress().split(".")[0]+j+".txt");
                    clone.setContents(temp);
                    output.add(clone);
                }
                }catch(FolderDoesNotContainTextException e){}
            }
        
        setOutputEntries(output);
        }

    

    public int getLines() {
        return lines;
    }

    public void setLines(int lines) {
        this.lines = Util.getIntGreaterThan1(lines);
    }

}
