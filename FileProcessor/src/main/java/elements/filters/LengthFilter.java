/*
 * Mackenzie Alec McBurney
 * Apr 1, 2023
 * Filter Processing Element that searches the length (size on disk in Bytes) of Entries
 */
package elements.filters;

import com.group7.FileProcessor.Util;
import com.group7.FileProcessor.entries.Entry;
import elements.ProcessingElement;
import java.util.ArrayList;

/**
 * A class to filter through entries based on their size in Bytes
 *
 * @author kyure
 */
public class LengthFilter extends ProcessingElement {

    //Attributes
    private long length;
    private String operator;

    /**
     * Primary default constructor.h
     */
    public LengthFilter() {
        super();
    }

    /**
     * The method that performs the function of the LengthFilter class.
     */
    @Override
    public void process() {

        // Make a local reference of the input of entries
        ArrayList<Entry> input = this.getInputEntries();

        // Ouput of entries
        ArrayList<Entry> output = new ArrayList<>();

        for (int i = 0; i < input.size(); i++) {

            if (Util.isFile(input.get(i).getAddress()) == 1) { // Make sure entry is a file
                switch (operator) {
                    case "EQ" -> { // Equals  to opperator
                        if (input.get(i).getLength() == getLength()) {
                            output.add(input.get(i));
                        }
                    }

                    case "NEQ" -> { // Not equals to opperator
                        if (input.get(i).getLength() != getLength()) {
                            output.add(input.get(i));
                        }
                    }

                    case "GT" -> { // Greater than opperator
                        if (input.get(i).getLength() > getLength()) {
                            output.add(input.get(i));
                        }
                    }

                    case "GTE" -> { // Greater than or equals to opperator
                        if (input.get(i).getLength() >= getLength()) {
                            output.add(input.get(i));
                        }
                    }

                    case "LT" -> { // Less than oppertor
                        if (input.get(i).getLength() < getLength()) {
                            output.add(input.get(i));
                        }
                    }

                    case "LTE" -> { // Less than or equals to operator
                        if (input.get(i).getLength() <= getLength()) {
                            output.add(input.get(i));
                        }
                    }

                    default -> // Invalid operator
                        System.out.println("Invalid comparison operator.");
                }
            }
            setOutputEntries(output);
        }
    }

    /**
     * Accessor for var length
     *
     * @return length
     */
    public long getLength() {
        return length;
    }

    /**
     * Mutator for var length
     *
     * @param length
     */
    public void setLength(long length) {
        this.length = length;
    }

    /**
     * Accessor for var operator
     *
     * @return operator
     */
    public String getOperator() {
        return operator;
    }

    /**
     * Mutator for var operator
     *
     * @param operator
     */
    public void setOperator(String operator) {
        this.operator = operator;
    }
}
