/*
 * Lukas Krampitz
 * Apr 1, 2023
 * Filter Processing Element that searches the contents of Entries for the number of occourences of a string
 */
package elements.filters;

import com.group7.FileProcessor.Util;
import elements.ProcessingElement;

/**
 *
 * @author Tacitor
 */
public class CountFilter extends ProcessingElement {

    //Attributes
    private String key;
    private int min;

    /**
     * Primary default constructor.
     */
    public CountFilter() {
        super();
    }

    @Override
    public void process() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getMin() {
        return min;
    }

    /**
     * Mutator for the min value. Ensure it is greater than 0. Default to 1 for
     * any 0 parameter. And the positive integer for any negative parameter.
     *
     * @param min
     */
    public void setMin(int min) {
        this.min = Util.getIntGreaterThan1(min);
    }

}
