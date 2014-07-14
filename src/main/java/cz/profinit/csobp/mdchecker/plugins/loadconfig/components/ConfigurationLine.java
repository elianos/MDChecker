/*
 * ConfigurationLine.java
 *
 * Created on __DATE__, __TIME__
 */

package cz.profinit.csobp.mdchecker.plugins.loadconfig.components;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import cz.profinit.csobp.mdchecker.plugins.loadconfig.LoadConfigPlugin;
import cz.profinit.csobp.mdchecker.plugins.loadconfig.model.Configuration;

/**
 * 
 * Komponenta radku popisujici konfiguraci. Nese data o jedne konkretni
 * konfiguraci.
 * 
 * @author: vjinoch
 */
public class ConfigurationLine extends javax.swing.JPanel {

	private static final long serialVersionUID = -8583036673843567439L;

	/**
	 * Reference na plugin
	 */
	private LoadConfigPlugin loadConfigPlugin;

	/**
	 * Reference na nadrazeny prvek
	 */
	private LoadConfigPanel loadConfigPanel;

	/**
	 * Reference datoveho modelu konfigurace
	 */
	private Configuration config;

	private JButton deleteButton;
	private JLabel label;
	private JButton openButton;
	
	/**
	 * Creates new form ConfigurationLine
	 * 
	 * @param loadConfigPlugin
	 */
	public ConfigurationLine(LoadConfigPlugin loadConfigPlugin,
			LoadConfigPanel loadConfigPanel, Configuration config) {
		this.loadConfigPlugin = loadConfigPlugin;
		this.loadConfigPanel = loadConfigPanel;
		this.config = config;
		initComponents();
	}

	private void initComponents() {

		openButton = new javax.swing.JButton();
		label = new javax.swing.JLabel();
		deleteButton = new javax.swing.JButton();

		openButton.setText(loadConfigPlugin.getString("load.config.open"));
		openButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				openButtonActionPerformed(evt);
			}
		});

		label.setText(config.getName());

		deleteButton.setText("X");
		deleteButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				deleteButtonActionPerformed(evt);
			}
		});

		org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(
				this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(
				layout.createSequentialGroup()
						.addContainerGap()
						.add(openButton)
						.addPreferredGap(
								org.jdesktop.layout.LayoutStyle.RELATED)
						.add(label)
						.addPreferredGap(
								org.jdesktop.layout.LayoutStyle.RELATED, 210,
								Short.MAX_VALUE).add(deleteButton)
						.addContainerGap()));
		layout.setVerticalGroup(layout
				.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
				.add(layout
						.createSequentialGroup()
						.addContainerGap(
								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.add(layout
								.createParallelGroup(
										org.jdesktop.layout.GroupLayout.BASELINE)
								.add(openButton).add(label).add(deleteButton))));
	}

	/**
	 * Akce odstraneni konfigurace
	 * 
	 * @param evt prijmuta udalost
	 */
	private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {
		String[] buttons = new String[] {
				loadConfigPlugin.getString("main.yes"),
				loadConfigPlugin.getString("main.no") };
		int result = JOptionPane.showOptionDialog(null,
				loadConfigPlugin.getString("main.exit.message"),
				loadConfigPlugin.getString("main.exit.title"),
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				buttons, buttons[0]);
		if (result == JOptionPane.YES_OPTION) {
			loadConfigPanel.removeLine(this);
		}
	}

	/**
	 * Akce otevreni konfigurace.
	 * Nastavi hodnotu pluginu a vyvola jeho vykonani.
	 * 
	 * @param evt prijmuta akce
	 */
	private void openButtonActionPerformed(java.awt.event.ActionEvent evt) {
		loadConfigPlugin.setConfigurationUrl(config.getUrl());
		loadConfigPlugin.execute();
	}

	/**
	 * {@link Configuration#getName()}
	 */
	public String getName() {
		return config.getName();
	}

	/**
	 * {@link Configuration#getUrl()}
	 */
	public String getUrl() {
		return config.getUrl();
	}
	
	/**
	 * {@link ConfigurationLine#config}
	 */
	public Configuration getConfiguration() {
		return config;
	}


}