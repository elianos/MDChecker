package cz.profinit.csobp.mdchecker.plugins.loadconfig;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import cz.profinit.csobp.mdchecker.core.MDContainer;
import cz.profinit.csobp.mdchecker.plugins.DialogException;
import cz.profinit.csobp.mdchecker.plugins.ErrorDialogException;
import cz.profinit.csobp.mdchecker.plugins.MDPlugin;
import cz.profinit.csobp.mdchecker.plugins.WarningDialogException;
import cz.profinit.csobp.mdchecker.plugins.loadconfig.model.Configuration;

/**
 * 
 * Configuration manager je trida zajistujici praci s ulozenymi konfiguracemi.
 * Zajistuje jejich zapis, odmazani a dalsi obsluzne funkce.
 * 
 * @author: vjinoch
 */
public class ConfigurationManager {

	private Logger logger = Logger.getLogger(ConfigurationManager.class);

	/**
	 * Nazev property souboru pro ukladani konfiguraci.
	 */
	private static final String CONFIGURATIONS_FILE = "configs.properties";

	/**
	 * Reference na soubor s konfiguracemi.
	 */
	private File file;

	/**
	 * Reference na plugin. Slouzi ke sdileni kontextu.
	 */
	private MDPlugin mdPlugin;

	/**
	 * @param mdPlugin
	 *            reference na plugin, slouzi k predavani kontextu.
	 * @throws ErrorDialogException
	 */
	public ConfigurationManager(MDPlugin mdPlugin) throws ErrorDialogException {
		this.mdPlugin = mdPlugin;

		File folder = new File(MDContainer.DATA_FOLDER);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		file = new File(folder, CONFIGURATIONS_FILE);
		if (!file.exists()) {
			try {
				new FileOutputStream(file).close();
			} catch (IOException e) {
				ErrorDialogException ex = new ErrorDialogException(
						mdPlugin.getString("load.config.unable.init"), e);
				logger.error(ex);
				throw ex;
			}
		}
	}

	/**
	 * Vraci reader property souboru s konfiguracemi
	 * 
	 * @return reader property souboru
	 * @throws FileNotFoundException
	 */
	public Reader getReader() throws FileNotFoundException {
		logger.info("Oteviram FileReader");
		return new FileReader(file);
	}

	/**
	 * Vraci writer property souboru s konfiguracemi
	 * 
	 * @return writer property souboru
	 * @throws FileNotFoundException
	 */
	public Writer getWriter() throws IOException {
		logger.info("Oteviram FileWriter");
		return new FileWriter(file);
	}

	/**
	 * Vraci properties z property souboru s konfiguracemi.
	 * 
	 * @return properties konfiguracniho souboru
	 * @throws DialogException
	 */
	public Properties getProperties() throws DialogException {
		logger.info("Nacitam properties file s konfiguracemi.");
		Properties properties = new Properties();
		Reader reader = null;
		try {
			reader = getReader();
			properties.load(reader);

			reader.close();
		} catch (IOException e) {
			System.out.println(mdPlugin);
			WarningDialogException ex = new WarningDialogException(
					mdPlugin.getString("load.config.unable.property"), e);
			logger.warn(ex);
			throw ex;
		}
		return properties;
	}

	/**
	 * Nacte mapu konfiguraci
	 * 
	 * @return seznam konfiguraci
	 * @throws DialogException
	 */
	public List<Configuration> readConfigs() throws DialogException {
		logger.info("Ctu konfigurace.");
		Properties properties = getProperties();

		List<Configuration> result = new LinkedList<Configuration>();

		for (String key : properties.stringPropertyNames()) {
			result.add(new Configuration(properties.getProperty(key), key));
		}

		return result;
	}

	/**
	 * Ulozi do property souboru novou konfiguraci.
	 * 
	 * @param config konfigurace
	 * @throws DialogException
	 */
	public void createConfiguration(Configuration config)
			throws DialogException {
		logger.info("Pridavam konfiguraci: " + config.getName() + " -> " + config.getUrl());
		Properties properties = getProperties();
		if (properties.containsKey(config.getUrl())) {
			WarningDialogException ex = new WarningDialogException(
					mdPlugin.getString("load.config.allready.created"));
			logger.warn(ex);
			throw ex;
		}
		properties.setProperty(config.getUrl(), config.getName());
		Writer writer;
		try {
			writer = getWriter();

			properties.store(writer, "");
			writer.close();
		} catch (IOException e) {
			WarningDialogException ex = new WarningDialogException(
					mdPlugin.getString("load.config.unable.property.change"), e);
			logger.warn(ex);
			throw ex;
		}
		logger.info("Konfigurace pridana.");

	}

	/**
	 * Odstrani konfiguraci z property souboru.
	 * 
	 * @param config
	 *            konfigurace
	 * @throws DialogException
	 */
	public void removeConfiguration(Configuration config) throws DialogException {
		Properties properties = getProperties();
		properties.remove(config.getUrl());
		Writer writer;
		try {
			writer = getWriter();

			properties.store(writer, "");
			writer.close();
		} catch (IOException e) {
			WarningDialogException ex = new WarningDialogException(
					mdPlugin.getString("load.config.unable.property.change"), e);
			logger.warn(ex);
			throw ex;
		}
	}
}
