/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elements;

import com.group7.FileProcessor.Util;
import com.group7.FileProcessor.entries.Entry;
import com.group7.FileProcessor.entries.FolderDoesNotContainTextException;
import com.group7.FileProcessor.entries.LocFile;
import com.group7.FileProcessor.entries.LocFolder;
import java.util.ArrayList;
import java.util.Arrays;

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
        int num=-1;

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
                        temp+=contentsByLine[j]+"\n";
                    }
                    clone.setAddress(entry.getAddress().split("\\.")[0]+num+".txt");
                    ((LocFile)clone).setContents(temp);

                    output.add(clone);
                }
                }catch(FolderDoesNotContainTextException e){
                output.add(entries.get(i));
                }
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
