package cz.profinit.csobp.mdchecker;

import javax.swing.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cz.profinit.csobp.mdchecker.plugins.DiffPlugin;
import cz.profinit.csobp.mdchecker.plugins.MDPlugin;

import java.awt.*;
import java.util.Map;

/**
 * Created by usul on 19.6.2014.
 */
public class Main {

    public static void main(String[] args) {
    	
    	ApplicationContext applicationContext = new ClassPathXmlApplicationContext("appContext.xml");
    	DiffPlugin diffPlugin = (DiffPlugin) applicationContext.getBean("diffPlugin");
    	Map<String, MDPlugin> map = applicationContext.getBeansOfType(MDPlugin.class);
    	
    	for (String name : map.keySet()) {
    		System.out.println(map.get(name));
    	}
    	
    	System.out.println(diffPlugin.getNewWar());
//        Runnable runner = new Runnable() {
//            public void run() {
//                JFrame jFrame = new JFrame();
//                jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//                JButton jButton = new JButton("Test One");
//                jButton.setVisible(true);
//
//                jFrame.add(jButton, BorderLayout.NORTH);
//                Dimension dimension = new Dimension();
//                dimension.setSize(800, 600);
//                jFrame.setMinimumSize(dimension);
//                jFrame.setResizable(true);
//                jFrame.setVisible(true);
//            }
//        };
//        EventQueue.invokeLater(runner);

    }
}
