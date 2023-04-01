/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elements;

import com.group7.FileProcessor.Util;

/**
 *
 * @author keric
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = Util.getIntGreaterThan1(max);
    }

}
