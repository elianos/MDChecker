package cz.profinit.csobp.mdchecker.plugins;

import javax.swing.JComponent;
import javax.swing.JOptionPane;


/**
 * 
 * ErrorDialogException je vyjimka, ktera zobrazuje error dialogove okno s
 * odpovidajici chybou. Po zobrazeni a potvrzeni dialogu ukonci beh aplikace.
 * 
 * @author: vjinoch
 */
public class ErrorDialogException extends DialogException {

	private static final long serialVersionUID = 3604522074140272760L;
	
	/**
	 * {@link DialogException#DialogException(String, Throwable)} 
	 */
	public ErrorDialogException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * {@link DialogException#DialogException(String)}
	 */
	public ErrorDialogException(String message) {
		super(message);
		
	}

	/**
	 * {@link DialogException#showDialog(JComponent)}}
	 */
	@Override
	public void showDialog(JComponent component) {
		JOptionPane.showMessageDialog(component,
			    getMessage(),
			    "Error",
			    JOptionPane.ERROR_MESSAGE);
		
		System.exit(0);
	}

}
