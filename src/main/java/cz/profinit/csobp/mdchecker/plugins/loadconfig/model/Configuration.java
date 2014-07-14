package cz.profinit.csobp.mdchecker.plugins.loadconfig.model;

/**
 * 
 * Datovy model konfigurace.
 * 
 * @author: vjinoch
 */
public class Configuration {

	/**
	 * Nazev konfigurace. Pouziva se nazev souboru.
	 */
	private String name;
	
	/**
	 * Url adresa konfigurace
	 */
	private String url;

	/**
	 * @param name {@link Configuration#name}
	 * @param url {@link Configuration#url}
	 */
	public Configuration(String name, String url) {
		super();
		this.name = name;
		this.url = url;
	}

	/**
	 * {@link Configuration#name}
	 */
	public String getName() {
		return name;
	}

	/**
	 * {@link Configuration#name}
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * {@link Configuration#url}
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * {@link Configuration#url}
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
}
