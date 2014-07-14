package cz.profinit.csobp.mdchecker.plugins;

import javax.swing.JComponent;

import cz.profinit.csobp.mdchecker.core.MDContainer;

/**
 * 
 * Rozhrani vsech MDChecker pluginu. Slouzi pro volani zobrazeni a obecnou
 * manipulaci s pluginy.
 * 
 * @author: vjinoch
 */
public abstract class MDPlugin {
	
	/**
	 * Reference na rodicovsky plugin
	 */
	protected MDPlugin parent;
	
	/**
	 * Reference na container
	 */
	protected MDContainer container;
	
	/**
	 * 
	 * Metoda create slouzi k vytvoreni pluginu. Stara se o zobrazeni 
	 * 
	 * @param parent
	 */
	public void create(MDPlugin parent, MDContainer mdContainer) {
		this.parent = parent;
		this.container = mdContainer;
		
		afterCreate();
	}
	
	/**
	 * After create je operace, ktera je provadena po vytvoreni pluginu
	 */
	public void afterCreate() {
		
	}
	
	/**
	 * Akce provede ukonceni pluginu
	 */
	public void finish() {
		container.finishPlugin();
	}
	
	/**
	 * Vykonani samotne akce pluginu
	 */
	public abstract void execute();
	
	/**
	 * Vraci referenci na hlavni componentu ktera reprezentuje plugin
	 * 
	 * @return komponenta reprezentujici plugin
	 */
	public abstract JComponent getComponent();
	
	/**
	 * Metoda vraci text definovany v property souboru
	 * 
	 * @param name
	 *            nazev textu
	 * 
	 * @return zpracovany text
	 */
	public String getString(String name) {
		return container.getString(name);
	}

	/**
	 * Metoda vraci text definovany v property souboru a vlozi do nej zvolene
	 * parametry. Vyuziva MessageFormatter.
	 * 
	 * @param name
	 *            nazev textu
	 * @param params
	 *            parametry ktere maji byt vlozeny do textu
	 * 
	 * @return zpracovany text
	 */
	public String getString(String name, Object... params) {
		return container.getString(name, params);
	}
}
