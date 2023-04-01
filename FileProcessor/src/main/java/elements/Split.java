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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getLines() {
        return lines;
    }

    public void setLines(int lines) {
        this.lines = Util.getIntGreaterThan1(lines);
    }

}
