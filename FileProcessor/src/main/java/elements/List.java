/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elements;
import java.util.ArrayList;
import com.group7.FileProcessor.entries.Entry;
import com.group7.FileProcessor.Util;
import com.group7.FileProcessor.entries.LocFolder;

/**
 *
 * @author emj.vhart
 */
public class List extends ProcessingElement {

    //Attributes
    private int max;

    /**
     * Primary default constructor.
     */
    public List() {
        super();
    }

    @Override
    public void process() {
       int untilMax = 0;//creating counter
       boolean found = false;
       ArrayList<Entry> entryList = this.getInputEntries();//gets entries
       ArrayList<Entry> output = new ArrayList();//creates output entry arraylist
       for (Entry e : entryList) {
        String[] addresses = ((LocFolder) e).getSubFiles();//gets directories
        for (String s : addresses) {
            if (e.getAddress().equals(s)) {//compares addresses and entries
               found = true; 
            }
        }
        
        if (found == true)
        {
        output.add(e);
        untilMax++;
            if(untilMax == max)//stops loop if max has been hit
            {
                break;
            }
        }
    }
       
       
    }
    
    public int getMax()
    {
        return max;
    }

    public void setMax(int max)
    {
        this.max = Util.getIntGreaterThan1(max);
    }

}
