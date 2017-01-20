package org.bawe.bagchal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Helper Class for reading String and Integers from standard console input.
 *
 * @author pape
 * @conributer bawe
 */
public class Input {

    private BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Reads standard input and returns the last line.
     */
    public String readLine() {
        try {
            return console.readLine();
        } catch (IOException e) {
            return "\n";
        }
    }

    /**
     * Reads line from standard input and parses it as an integer.
     */
    public int readInt() {
        return parseInt(readLine());
    }

    /**
     * Converts a String input to integer. Returns -1 if input cannot be parsed to integer. (0 is a valid input)
     */
    public int parseInt(String zahl) {
        try {
            return Integer.parseInt(zahl);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Accepts a String and tries to parse digits as integer array. Fills with -1 if string position was not a digit.
     */
    public int[] parseIntArray(String zahl){
        int[] result = new int[zahl.length()];
        for(int i = 0; i < zahl.length(); i++){
            try{
                result[i] = Integer.parseInt(zahl.substring(i,i+1));
            }catch (NumberFormatException e){
                result[i] = -1;
            }
        }
        return result;
    }
}
