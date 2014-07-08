package cz.profinit.csobp.mdchecker;

import javax.swing.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

import cz.profinit.csobp.mdchecker.core.MDContainer;
import cz.profinit.csobp.mdchecker.plugins.DiffPlugin;
import cz.profinit.csobp.mdchecker.plugins.LoadConfigPlugin;
import cz.profinit.csobp.mdchecker.plugins.MDPlugin;

import java.awt.*;
import java.util.Map;
import java.util.Properties;

/**
 * Created by usul on 19.6.2014.
 */
public class Main {

    public static void main(String[] args) {
    	ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
    	final Properties property = (Properties) applicationContext.getBean("string");
    	
//    	ApplicationContext applicationContext = new ClassPathXmlApplicationContext("appContext.xml");
//    	DiffPlugin diffPlugin = (DiffPlugin) applicationContext.getBean("diffPlugin");
//    	Map<String, MDPlugin> map = applicationContext.getBeansOfType(MDPlugin.class);
//    	
//    	for (String name : map.keySet()) {
//    		System.out.println(map.get(name));
//    	}
//    	
//    	System.out.println(diffPlugin.getNewWar());
    	
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
                MDContainer container = new MDContainer(property);
                LoadConfigPlugin loadConfigPlugin = new LoadConfigPlugin();
                container.createPlugin(loadConfigPlugin);
            }
        };
        EventQueue.invokeLater(runner);

    }
}
