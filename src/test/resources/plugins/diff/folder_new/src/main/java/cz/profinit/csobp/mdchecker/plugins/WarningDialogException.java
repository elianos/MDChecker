package cz.profinit.csobp.mdchecker.plugins;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

/**
 * 
 * WarningDialogException je vyjimka, ktera zobrazuje error dialogove okno s
 * odpovidajici chybou. Po zobrazeni a potvrzeni dialogu aplikace pokracuje dal.
 * 
 * @author: vjinoch
 */
public class WarningDialogException extends DialogException {

	private static final long serialVersionUID = 3604522074140272760L;

	/**
	 * {@link DialogException#DialogException(String, Throwable)}
	 */
	public WarningDialogException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * {@link DialogException#DialogException(String)}
	 */
	public WarningDialogException(String message) {
		super(message);

	}

	/**
	 * {@link DialogException#showDialog(JComponent)}}
	 */
	@Override
	public void showDialog(JComponent component) {
		JOptionPane.showMessageDialog(component, getMessage(), "Warning",
				JOptionPane.WARNING_MESSAGE);
	}

}
