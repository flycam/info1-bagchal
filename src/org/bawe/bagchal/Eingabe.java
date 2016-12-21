package org.bawe.bagchal;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Hilfeklasse zum Einlesen von Zeichenketten und Zahlen von der Konsole.
 * 
 * @author pape
 */
public class Eingabe {
	
	private BufferedReader console =   new BufferedReader( new InputStreamReader( System.in ) ); 
	
	/**
	 * Gibt die nächste Eingabezeile als String zurück.
	 */
	public String readLine() {
		try {
			return console.readLine();
		} catch (IOException e) {
			return "\n";
		}
	}

	/**
	 * Gibt die nächste Eingabezeile als int-Wert zurück. Es werden
	 * nur die ersten Ziffer inklusive Vorzeichen berücksichtigt.
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
