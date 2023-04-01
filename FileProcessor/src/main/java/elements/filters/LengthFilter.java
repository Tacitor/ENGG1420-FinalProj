/*
 * Lukas Krampitz
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
 * @author Tacitor
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
        setOperator(operator);
        setLength(length);
    }

    @Override
    public void process() {

        // Make a copy of the input of entries
        ArrayList<Entry> inputCopy = this.getInputEntries();
        ArrayList<Entry> input = new ArrayList<>();
        for (int i = 0; i < inputCopy.size(); i++) {
            input.add(inputCopy.get(i));
        }
        // Ouput of entries
        ArrayList<Entry> output = new ArrayList<>();

        for (int i = 0; i < input.size(); i++) {

            if (Util.isFile(input.get(i).getAddress()) == 1) {
                try {
                    String content = input.get(i).getContents();
                    switch (operator) {
                        case "EQ" -> {
                            if (content.length() == getLength()) {
                                output.add(input.get(i));
                            }
                        }

                        case "NEQ" -> {
                            if (content.length() != getLength()) {
                                output.add(input.get(i));
                            }
                        }

                        case "GT" -> {
                            if (content.length() > getLength()) {
                                output.add(input.get(i));
                            }
                        }

                        case "GTE" -> {
                            if (content.length() >= getLength()) {
                                output.add(input.get(i));
                            }
                        }

                        case "LT" -> {
                            if (content.length() < getLength()) {
                                output.add(input.get(i));
                            }
                        }

                        case "LTE" -> {
                            if (content.length() <= getLength()) {
                                output.add(input.get(i));
                            }
                        }

                        default ->
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
