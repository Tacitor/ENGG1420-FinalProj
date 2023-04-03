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
        String[] contentsByLine;//an array containing all the lines in the entry
        String temp;
        int index=-1;

        //running through all the entries
        for (int i = 0; i < entries.size(); i++) {
            try {//using a try-catch to insure that only files are split
                Entry entry = entries.get(i);// if the entry is a folder it will go into the catch
                contents = entry.getContents();
                contentsByLine = contents.split("\n");//split the contents of the entry based off of \n's
                
                for (int j = 0; j < contentsByLine.length;) {// loop goes intil it runs out of lines
                    
                    index++;//update index
                    temp = "";
                    Entry clone = entry.clone();//creating a clone entry to perform operations on
                    for (int count = lines; count > 0 && j < contentsByLine.length; count--, j++) {
                        //count insures that the line limit is not exeeded, j is updated to avoid an out of bounds exception
                        //adding the specified number of lines to the temo string
                        temp+=contentsByLine[j]+"\n";
                    }
                    //asigning the propper address to the clone
                    clone.setAddress(entry.getAddress().split("\\.")[0]+index+".txt");
                    ((LocFile)clone).setContents(temp);// setting the contents to the temp string

                    output.add(clone);
                }
                }catch(FolderDoesNotContainTextException e){//this code is only reachable if the entry if a folder
                output.add(entries.get(i));//just passes the entry through to the output as folders should be ignored
                }
            }
        
        setOutputEntries(output);//setting the output entries
        }

    

    public int getLines() {
        return lines;
    }

    public void setLines(int lines) {
        this.lines = Util.getIntGreaterThan1(lines);
    }

}
