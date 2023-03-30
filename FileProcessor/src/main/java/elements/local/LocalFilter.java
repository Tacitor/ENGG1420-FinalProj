/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elements.local;

import com.group7.FileProcessor.pojo.EntriesPOJO;
import elements.Filter;
import java.util.ArrayList;

/**
 *
 * @author keric
 */
public class LocalFilter extends Filter {

    public LocalFilter(ArrayList<EntriesPOJO> inputEntries) {
        super(inputEntries);
    }

    @Override
    public void nameFilter(ArrayList<EntriesPOJO> inputEntries, String Key) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void lengthFilter(ArrayList<EntriesPOJO> inputEntries, long Length, String Operator) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void contentFilter(ArrayList<EntriesPOJO> inputEntries, String Key) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void countFilter(ArrayList<EntriesPOJO> inputEntries, String Key, int Min) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
