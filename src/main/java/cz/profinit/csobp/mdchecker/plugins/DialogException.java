package cz.profinit.csobp.mdchecker.plugins;

import javax.swing.JComponent;

/**
 * 
 * DialogException je vyjimka, ktera obaluje standardni chyby a umoznuje
 * zobrazit adekvatni dialog.
 * 
 * @author: vjinoch
 */
public abstract class DialogException extends Exception {

	private static final long serialVersionUID = -5695488773525425950L;

	/**
	 * {@link Exception#Exception(String, Throwable)}
	 */
	public DialogException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * {@link Exception#Exception(String)}
	 */
	public DialogException(String message) {
		super(message);
	}

	/**
	 * Metoda provadi zobrazeni samotneho dialogoveho okna vyjimky vyjimky. 
	 * 
	 * @param component relace na komponentu ze ktere ma byt dialogove okno zobrazeno.
	 */
	public abstract void showDialog(JComponent component);
}
