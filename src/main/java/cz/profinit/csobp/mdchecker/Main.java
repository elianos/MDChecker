package cz.profinit.csobp.mdchecker;

import javax.swing.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cz.profinit.csobp.mdchecker.core.MDContainer;
import cz.profinit.csobp.mdchecker.plugins.diff.DiffGenerator;
import cz.profinit.csobp.mdchecker.plugins.loadconfig.LoadConfigPlugin;
import difflib.DiffUtils;
import difflib.Patch;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by usul on 19.6.2014.
 */
public class Main {

//	private Logger logger = Logger.getLogger(Main.class);

	public static void main(String[] args) {
		final ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"applicationContext.xml");

		// ApplicationContext applicationContext = new
		// ClassPathXmlApplicationContext("appContext.xml");
		// DiffPlugin diffPlugin = (DiffPlugin)
		// applicationContext.getBean("diffPlugin");
		// Map<String, MDPlugin> map =
		// applicationContext.getBeansOfType(MDPlugin.class);
		//
		// for (String name : map.keySet()) {
		// System.out.println(map.get(name));
		// }
		//
		// System.out.println(diffPlugin.getNewWar());

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		Runnable runner = new Runnable() {
			public void run() {

				MDContainer container = new MDContainer(applicationContext);
				LoadConfigPlugin loadConfigPlugin;

				loadConfigPlugin = new LoadConfigPlugin();
				container.createPlugin(loadConfigPlugin);

			}
		};
		EventQueue.invokeLater(runner);
		
		
		
	}

}
