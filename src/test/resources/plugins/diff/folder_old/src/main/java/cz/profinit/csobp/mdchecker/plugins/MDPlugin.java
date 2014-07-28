package cz.profinit.csobp.mdchecker.plugins;

import java.awt.Component;

import scala.annotation.meta.param;
import cz.profinit.csobp.mdchecker.core.MDContainer;

/**
 * 
 * Rozhrani vsech MDChecker pluginu. Slouzi pro volani zobrazeni a obecnou
 * manipulaci s pluginy.
 * 
 * @author: vjinoch
 */
public abstract class MDPlugin {
	
	protected MDPlugin parent;
	
	protected MDContainer container;
	
	protected Component component;
	
	/**
	 * 
	 * Metoda create slouzi k vytvoreni pluginu. Stara se o zobrazeni 
	 * 
	 * @param parent
	 */
	public void create(MDPlugin parent, MDContainer mdContainer) {
		this.parent = parent;
		this.container = mdContainer;
	}
	
	
	/**
	 * 
	 */
	public void finish() {
		container.finishPlugin();
	}
	
	
	public abstract Component getComponent();
	
	/**
	 * 
	 * Testovaci metoda
	 * 
	 * @return toString value
	 */
	public abstract String getValue();
}
