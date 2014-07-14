/*
 * loadConfigPanel.java
 *
 * Created on __DATE__, __TIME__
 */

package cz.profinit.csobp.mdchecker.plugins.loadconfig.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.io.FileExistsException;
import org.jdesktop.layout.GroupLayout;
import org.jdesktop.layout.GroupLayout.ParallelGroup;
import org.jdesktop.layout.GroupLayout.SequentialGroup;

import cz.profinit.csobp.mdchecker.plugins.DialogException;
import cz.profinit.csobp.mdchecker.plugins.loadconfig.LoadConfigPlugin;
import cz.profinit.csobp.mdchecker.plugins.loadconfig.model.Configuration;

/**
 * 
 * Swing komponenta pro load plugin. Slouzi k vykreslovani frontendu nacitani
 * konfiguraci.
 *
 * @author vjinoch
 */
public class LoadConfigPanel extends javax.swing.JPanel {

	private static final long serialVersionUID = 6764774521578259216L;

	/**
	 * Relace na plugin
	 */
	private LoadConfigPlugin loadConfigPlugin;

	/**
	 * Skupina pro pridavani radku
	 */
	private ParallelGroup horizontalParallelGroup;

	/**
	 * Skupina pro pridavani radku
	 */
	private ParallelGroup verticalParallelGroup;

	/**
	 * Reference na layout
	 */
	private GroupLayout scrollPanelBodyLayout;

	private SequentialGroup sequentialGroup;
	private JButton addButton;
	private JPanel bodyPanel;
	private JPanel headerPanel;
	private JScrollPane scrollPanel;
	private JPanel scrollPanelBody;

	/**
	 * Creates new form loadConfigPanel
	 * 
	 * @param loadConfigPlugin
	 */
	public LoadConfigPanel(LoadConfigPlugin loadConfigPlugin) {
		this.loadConfigPlugin = loadConfigPlugin;
	}

	public void initComponents() {

		headerPanel = new JPanel();
		addButton = new JButton();
		bodyPanel = new JPanel();
		scrollPanel = new JScrollPane();
		scrollPanelBody = new JPanel();

		addButton.setText(loadConfigPlugin.getString("load.config.add"));
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				addButtonActionPerformed(evt);
			}
		});

		org.jdesktop.layout.GroupLayout headerPanelLayout = new org.jdesktop.layout.GroupLayout(
				headerPanel);
		headerPanel.setLayout(headerPanelLayout);
		headerPanelLayout.setHorizontalGroup(headerPanelLayout
				.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
				.add(headerPanelLayout.createSequentialGroup()
						.addContainerGap().add(addButton)
						.addContainerGap(274, Short.MAX_VALUE)));
		headerPanelLayout
				.setVerticalGroup(headerPanelLayout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(org.jdesktop.layout.GroupLayout.TRAILING,
								headerPanelLayout
										.createSequentialGroup()
										.addContainerGap(
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE).add(addButton)));

		scrollPanel
				.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPanel
				.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		scrollPanelBodyLayout = new org.jdesktop.layout.GroupLayout(
				scrollPanelBody);
		scrollPanelBody.setLayout(scrollPanelBodyLayout);

		horizontalParallelGroup = scrollPanelBodyLayout
				.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING);

		scrollPanelBodyLayout.setHorizontalGroup(horizontalParallelGroup);

		verticalParallelGroup = scrollPanelBodyLayout
				.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING);

		sequentialGroup = scrollPanelBodyLayout.createSequentialGroup();

		scrollPanelBodyLayout.setVerticalGroup(verticalParallelGroup);

		scrollPanel.setViewportView(scrollPanelBody);

		org.jdesktop.layout.GroupLayout bodyPanelLayout = new org.jdesktop.layout.GroupLayout(
				bodyPanel);
		bodyPanel.setLayout(bodyPanelLayout);
		bodyPanelLayout.setHorizontalGroup(bodyPanelLayout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(
				bodyPanelLayout
						.createSequentialGroup()
						.addContainerGap()
						.add(scrollPanel,
								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
								381, Short.MAX_VALUE).addContainerGap()));
		bodyPanelLayout.setVerticalGroup(bodyPanelLayout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(
				bodyPanelLayout
						.createSequentialGroup()
						.add(scrollPanel,
								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
								278, Short.MAX_VALUE).addContainerGap()));

		scrollPanel.getAccessibleContext().setAccessibleName("");
		scrollPanel.getAccessibleContext().setAccessibleDescription("");

		org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(
				this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
				.add(headerPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
						org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE)
				.add(bodyPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
						org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(
				layout.createSequentialGroup()
						.add(headerPanel,
								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(
								org.jdesktop.layout.LayoutStyle.RELATED)
						.add(bodyPanel,
								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));

	}

	/**
	 * Prida radek s konfiguraci do seznamu polozek
	 * 
	 * @param name
	 *            nazev konfigurace
	 * @param url
	 *            url adresa k souboru
	 */
	public void createLine(Configuration config) {
		ConfigurationLine configurationLine = new ConfigurationLine(
				loadConfigPlugin, this, config);
		addLine(configurationLine);
	}

	/**
	 * Pridava radek s konfiguraci do seznamu polozek
	 * 
	 * @param configurationLine
	 *            instance noveho radku
	 */
	private void addLine(ConfigurationLine configurationLine) {
		horizontalParallelGroup.add(scrollPanelBodyLayout
				.createSequentialGroup()
				.add(configurationLine,
						org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
						org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE).addContainerGap());
		verticalParallelGroup.add(sequentialGroup.add(configurationLine,
				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE));
	}

	/**
	 * Odstrani vybrany prvek ze seznamu zobrazenych konfiguraci
	 * 
	 * @param configurationLine
	 *            instance zobrazeneho radku
	 */
	public void removeLine(ConfigurationLine configurationLine) {
		configurationLine.setVisible(false);
		scrollPanel.remove(configurationLine);
		scrollPanelBody.revalidate();
		scrollPanel.repaint();
		try {
			loadConfigPlugin.removeConfiguration(configurationLine.getConfiguration());
		} catch (DialogException e) {
			e.showDialog(loadConfigPlugin.getComponent());
		}
	}

	/**
	 * Akce tlacitka pridat konfiguraci
	 * 
	 * @param evt
	 *            udalost tlacitka
	 * @throws FileExistsException
	 */
	public void addButtonActionPerformed(java.awt.event.ActionEvent evt) {
		JFileChooser jFileChooser = new JFileChooser();
		ConfigurationLine configurationLine;
		FileNameExtensionFilter xmlfilter = new FileNameExtensionFilter(
				"xml files (*.xml)", "xml");
		jFileChooser.setFileFilter(xmlfilter);
		jFileChooser.setDialogTitle("Open schedule file");
		// set selected filter
		jFileChooser.setFileFilter(xmlfilter);

		File file;
		int returnVal = jFileChooser.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			file = jFileChooser.getSelectedFile();
			try {
				Configuration config = new Configuration(file.getName(), file.getAbsolutePath());
				loadConfigPlugin.createConfiguration(config);
				configurationLine = new ConfigurationLine(loadConfigPlugin,
						this, config);
				addLine(configurationLine);
				scrollPanelBody.revalidate();
				scrollPanel.repaint();
			} catch (DialogException e) {
				e.showDialog(loadConfigPlugin.getComponent());
			}

		}

	}

}