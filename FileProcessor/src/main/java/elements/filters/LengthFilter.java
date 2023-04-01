/*
 * Lukas Krampitz
 * Apr 1, 2023
 * Filter Processing Element that searches the length (size on disk in Bytes) of Entries
 */
package elements.filters;

import elements.ProcessingElement;

/**
 *
 * @author Tacitor
 */
public class LengthFilter extends ProcessingElement {

    //Attributes
    private long length;
    private String operator;

    /**
     * Primary default constructor.
     */
    public LengthFilter() {
        super();
    }

    @Override
    public void process() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

}
