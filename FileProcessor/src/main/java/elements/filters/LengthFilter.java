/*
 * Mackenzie Alec McBurney
 * Apr 1, 2023
 * Filter Processing Element that searches the length (size on disk in Bytes) of Entries
 */
package elements.filters;

import com.group7.FileProcessor.Util;
import com.group7.FileProcessor.entries.Entry;
import com.group7.FileProcessor.entries.FolderDoesNotContainTextException;
import elements.ProcessingElement;
import java.util.ArrayList;

/**
 *
 * @author kyure
 */
public class LengthFilter extends ProcessingElement {

    //Attributes
    private long length;
    private String operator;

    /**
     * Primary default constructor.
     * @param operator
     * @param length
     */
    public LengthFilter(String operator, Long length) {
        super();
    }

    @Override
    public void process() {

        // Make a local reference of the input of entries
        ArrayList<Entry> input = this.getInputEntries();
        
        // Ouput of entries
        ArrayList<Entry> output = new ArrayList<>();

        for (int i = 0; i < input.size(); i++) {

            if (Util.isFile(input.get(i).getAddress()) == 1) { // Make sure entry is a file
                try {
                    String content = input.get(i).getContents(); // Make a local reference of the contents of a file
                    switch (operator) {
                        case "EQ" -> { // Equals  to opperator
                            if (content.length() == getLength()) {
                                output.add(input.get(i));
                            }
                        }

                        case "NEQ" -> { // Not equals to opperator
                            if (content.length() != getLength()) {
                                output.add(input.get(i));
                            }
                        }

                        case "GT" -> { // Greater than opperator
                            if (content.length() > getLength()) {
                                output.add(input.get(i));
                            }
                        }

                        case "GTE" -> { // Greater than or equals to opperator
                            if (content.length() >= getLength()) {
                                output.add(input.get(i));
                            }
                        }

                        case "LT" -> { // Less than oppertor
                            if (content.length() < getLength()) {
                                output.add(input.get(i));
                            }
                        }

                        case "LTE" -> { // Less than or equals to operator
                            if (content.length() <= getLength()) {
                                output.add(input.get(i));
                            }
                        }

                        default -> // Invalid operator
                            System.out.println("Invalid comparison operator.");
                    }
                } catch (FolderDoesNotContainTextException ex) {
                    System.out.println("Error in LengthFilter.java\n" + ex);
                }
            }
        }
        setOutputEntries(output);
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
