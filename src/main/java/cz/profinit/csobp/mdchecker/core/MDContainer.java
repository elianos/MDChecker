package cz.profinit.csobp.mdchecker.core;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.MessageFormat;
import java.util.Properties;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import cz.profinit.csobp.mdchecker.Main;
import cz.profinit.csobp.mdchecker.plugins.MDPlugin;

/**
 * 
 * Container obsluhuje vytvoreni zakladniho okna a spravu zobrazeni jednotlivych
 * pluginu.
 * 
 * @author: vjinoch
 */
public class MDContainer {

	private Logger logger = Logger.getLogger(MDContainer.class);
	
	/**
	 * Hlavni okno aplikace
	 */
	private final JFrame mainFrame;

	/**
	 * Zasobnik aktivnich pluginu
	 */
	private Stack<MDPlugin> plugins = new Stack<MDPlugin>();

	/**
	 * Properties file obsahujici retezce
	 */
	private final Properties string;

	public MDContainer(ApplicationContext applicationContext) {
		logger.info("Nacitam string properties:");
		this.string = (Properties) applicationContext.getBean("string");
		logger.info(string);
		
		logger.info("Inicializuji hlavni behove okno");
		mainFrame = new JFrame(getString("main.title"));
		mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		mainFrame.addWindowListener(new ContainerWindowListener());

		Dimension dimension = new Dimension();
		dimension.setSize(800, 600);
		mainFrame.setMinimumSize(dimension);
		mainFrame.setResizable(true);
		mainFrame.setVisible(true);
		mainFrame.setLocationRelativeTo(null);
	}

	/**
	 * Metoda nastartuje novy plugin. Skryje stary plugin a zavesi za nej novy,
	 * ktery nasledne zobrazi.
	 * 
	 * @param plugin
	 *            plugin k zobrazeni
	 */
	public void createPlugin(MDPlugin plugin) {
		logger.info("Spoustim plugin: " + plugin.getClass().getName());
		
		if (!plugins.isEmpty()) {
			MDPlugin mdPlugin = plugins.peek();
			if (mdPlugin != null) {
				logger.info("Deaktivuji soucasny plugin:" + mdPlugin.getClass().getName());
				mainFrame.remove(mdPlugin.getComponent());
				mdPlugin.getComponent().setVisible(false);
			}
		}

		MDPlugin parent = null;
		if (!plugins.isEmpty()) {
			parent = plugins.peek();
		}
		plugin.create(parent, this);

		plugins.add(plugin);

		logger.info("Aktivuji novy plugin:" + plugin.getClass().getName());
		mainFrame.add(plugin.getComponent());
		plugin.getComponent().setVisible(true);
	}

	/**
	 * Metoda ukonci aktualne zobrazeny plugin. Skryje jej a zobrazy jeho
	 * rodice, pokud existuje.
	 */
	public void finishPlugin() {
		MDPlugin plugin = plugins.pop();
		logger.info("Ukoncuji bezici plugin:" + plugin.getClass().getName());
		
		plugin.getComponent().setVisible(false);
		mainFrame.remove(plugin.getComponent());

		if (!plugins.isEmpty()) {
			plugin = plugins.peek();
			if (plugin != null) {
				logger.info("Aktivuji rodicovsky plugin:" + plugin.getClass().getName());
				mainFrame.add(plugin.getComponent());
				plugin.getComponent().setVisible(true);
			}
		}
	}

	/**
	 * Metoda vraci text definovany v property souboru
	 * 
	 * @param name
	 *            nazev textu
	 * 
	 * @return zpracovany text
	 */
	public String getString(String name) {
		if (string.containsKey(name)) {
			return string.getProperty(name);
		}
		return name;
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
		if (string.contains(name)) {
			String text = string.getProperty(name);
			if (text.isEmpty())
				return null;

			return MessageFormat.format(text, params);
		}
		return name;
	}

	/**
	 * 
	 * Listener pro zobrazeni dialogu pri stisknuti krizku pro zavreni okna.
	 * 
	 * @author: vjinoch
	 */
	private class ContainerWindowListener implements WindowListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.WindowListener#windowOpened(java.awt.event.WindowEvent
		 * )
		 */
		public void windowOpened(WindowEvent e) {
			// EMPTY
		}

		/*
		 * Vytvori dialogove okno ktere predpoklada potvrzeni, pro uzavreni
		 * 
		 * @see
		 * java.awt.event.WindowListener#windowClosing(java.awt.event.WindowEvent
		 * )
		 */
		public void windowClosing(WindowEvent e) {
			String[] buttons = new String[] { getString("main.yes"),
					getString("main.no") };
			int result = JOptionPane.showOptionDialog(null,
					getString("main.exit.message"),
					getString("main.exit.title"), JOptionPane.DEFAULT_OPTION,
					JOptionPane.WARNING_MESSAGE, null, buttons, buttons[0]);
			if (result == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.WindowListener#windowClosed(java.awt.event.WindowEvent
		 * )
		 */
		public void windowClosed(WindowEvent e) {
			// EMPTY
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.WindowListener#windowIconified(java.awt.event.WindowEvent
		 * )
		 */
		public void windowIconified(WindowEvent e) {
			// EMPTY
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.event.WindowListener#windowDeiconified(java.awt.event.
		 * WindowEvent)
		 */
		public void windowDeiconified(WindowEvent e) {
			// EMPTY
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.WindowListener#windowActivated(java.awt.event.WindowEvent
		 * )
		 */
		public void windowActivated(WindowEvent e) {
			// EMPTY
		}

		public void windowDeactivated(WindowEvent e) {
			// EMPTY
		}

	}

}
