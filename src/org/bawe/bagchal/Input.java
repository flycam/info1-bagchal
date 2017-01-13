package org.bawe.bagchal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Hilfeklasse zum Einlesen von Zeichenketten und Zahlen von der Konsole.
 *
 * @author pape
 */
public class Input {

    private BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Gibt die n�chste Eingabezeile als String zur�ck.
     */
    public String readLine() {
        try {
            return console.readLine();
        } catch (IOException e) {
            return "\n";
        }
    }

    /**
     * Gibt die n�chste Eingabezeile als int-Wert zur�ck. Es werden
     * nur die ersten Ziffer inklusive Vorzeichen ber�cksichtigt.
     */
    public int readInt() {
        return parseInt(readLine());
    }

    /**
     * Wandelt <code>zahl</code> in einen int-Wert.
     */
    public int parseInt(String zahl) {
        try {
            return Integer.parseInt(zahl);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
