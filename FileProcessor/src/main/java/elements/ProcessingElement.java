/*
 * Lukas Krampitz
 * Apr 1, 2023
 * Defines the pattern of behavious for all processing elements
 */
package elements;

import com.group7.FileProcessor.entries.Entry;
import java.util.ArrayList;

/**
 *
 * @author Tacitor
 */
public abstract class ProcessingElement {

    //Attributes
    private ArrayList<Entry> inputEntries;
    private ArrayList<Entry> outputEntries;

    /**
     * Primary Default constructor. Does not take any parameters because the
     * input entries might not be known at the time of construction.
     */
    public ProcessingElement() {

    }

    /**
     * The method that runs and computes the output ArrayList of Entries. Does
     * not return it. Must store in into outputEntries attribute.
     */
    public abstract void process();

    /**
     * Accessor for the inputEntries
     *
     * @return - inputEntries
     */
    public ArrayList<Entry> getInputEntries() {
        return inputEntries;
    }

    /**
     * Mutator for the inputEntries
     *
     * @param inputEntries
     */
    public void setInputEntries(ArrayList<Entry> inputEntries) {
        this.inputEntries = inputEntries;
    }

    /**
     * Accessor for the outputEntries.
     *
     * @return
     */
    public ArrayList<Entry> getOutputEntries() {
        return outputEntries;
    }

    /**
     * Mutator for the outputEntries. Please note there is no public Mutator for
     * this attribute because the output should only be Processing Element
     * defined. The subclasses may of course make use of the protected mutator.
     *
     * @param outputEntries
     */
    protected void setOutputEntries(ArrayList<Entry> outputEntries) {
        this.outputEntries = outputEntries;
    }

}
