/*
 * Lukas Krampitz
 * Apr 1, 2023
 * Filter Processing Element that searches the contents of Entries
 */
package elements.filters;

import elements.ProcessingElement;

/**
 *
 * @author Tacitor
 */
public class ContentFilter extends ProcessingElement {

    //Attributes
    private String key;

    /**
     * Primary default constructor.
     */
    public ContentFilter() {
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

}
