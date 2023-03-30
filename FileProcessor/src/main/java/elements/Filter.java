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
public abstract class Filter {

    private ArrayList<EntriesPOJO> inputEntries;
    private ArrayList<EntriesPOJO> outputEntries;

    public abstract void nameFilter(ArrayList<EntriesPOJO> inputEntries, String Key);

    public abstract void lengthFilter(ArrayList<EntriesPOJO> inputEntries, long Length, String Operator);

    public abstract void contentFilter(ArrayList<EntriesPOJO> inputEntries, String Key);

    public abstract void countFilter(ArrayList<EntriesPOJO> inputEntries, String Key, int Min);

    public Filter(ArrayList<EntriesPOJO> inputEntries) {
        this.inputEntries = inputEntries;
    }

}
