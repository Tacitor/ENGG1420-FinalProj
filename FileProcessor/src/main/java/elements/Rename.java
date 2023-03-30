/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elements;

import com.group7.FileProcessor.pojo.EntriesPOJO;
import java.util.ArrayList;

/**
 *
 * @author keric
 */
public abstract class Rename {
    private ArrayList<EntriesPOJO> inputEntries;
    private ArrayList<EntriesPOJO> outputEntries;
    
    public abstract void rename(ArrayList<EntriesPOJO> inputEntries,String Suffix);

    public Rename(ArrayList<EntriesPOJO> inputEntries) {
        this.inputEntries = inputEntries;
    }
    
}
