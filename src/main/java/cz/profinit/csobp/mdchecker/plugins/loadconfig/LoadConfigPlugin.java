package cz.profinit.csobp.mdchecker.plugins.loadconfig;

import java.util.List;

import javax.swing.JComponent;

import cz.profinit.csobp.mdchecker.plugins.DialogException;
import cz.profinit.csobp.mdchecker.plugins.MDPlugin;
import cz.profinit.csobp.mdchecker.plugins.loadconfig.components.LoadConfigPanel;
import cz.profinit.csobp.mdchecker.plugins.loadconfig.model.Configuration;

/**
 * 
 * Plugin Load Config umoznuje uzivateli nacist konfiguraci, ktera bude v
 * aplikaci ulozena pro pripadne dalsi pouziti. Nasledne umoznuje spustit tvorbu
 * male dodavky
 * 
 * @author: vjinoch
 */
public class LoadConfigPlugin extends MDPlugin {

	/**
	 * Instance zobrazovaneho prostredi
	 */
	private LoadConfigPanel loadConfigPanel;

	/**
	 * Manager spravujici konfigurace
	 */
	private ConfigurationManager configurationManager;

	/**
	 * Konfigurace URL je promenna, ktera urcuje podle jake konfigurace ma byt
	 * vytvorena mala dodavka. Tat promena musi byt pred spustenim
	 * {@link LoadConfigPlugin#execute()} inicializovana a nastavena na
	 * pozadovanou hodnotu.
	 */
	private String configurationUrl;
	
	public LoadConfigPlugin() {
		loadConfigPanel = new LoadConfigPanel(this);
	}

	/**
	 * {@link MDPlugin#afterCreate()}
	 */
	@Override
	public void afterCreate() {
		loadConfigPanel.initComponents();

		try {
			configurationManager = new ConfigurationManager(this);

			List<Configuration> configs = configurationManager.readConfigs();
			for (Configuration config : configs) {
				loadConfigPanel.createLine(config);
			}
		} catch (DialogException e) {
			e.showDialog(getComponent());
		}

	}

	/**
	 * {@link MDPlugin#getComponent()}
	 */
	@Override
	public JComponent getComponent() {
		return loadConfigPanel;
	}

	/**
	 * Metoda vyvola vytvoreni reference na vlozenou konfiguraci
	 * 
	 * @param config konfigurace
	 * @throws DialogException
	 */
	public void createConfiguration(Configuration config)
			throws DialogException {
		configurationManager.createConfiguration(config);

	}

	/**
	 * Metoda vyvola odstraneni reference na konfigurace
	 * 
	 * @param config konfigurace
	 * @throws DialogException
	 */
	public void removeConfiguration(Configuration config)
			throws DialogException {
		configurationManager.removeConfiguration(config);

	}

	/**
	 * {@link LoadConfigPlugin#configurationUrl}
	 */
	public String getConfigurationUrl() {
		return configurationUrl;
	}

	/**
	 * {@link LoadConfigPlugin#configurationUrl}
	 */
	public void setConfigurationUrl(String configurationUrl) {
		this.configurationUrl = configurationUrl;
	}

	/**
	 * {@link MDPlugin#execute(}}
	 */
	@Override
	public void execute() {
		System.out.println(configurationUrl);

	}

}
