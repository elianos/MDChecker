package cz.profinit.csobp.mdchecker.plugins;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


public class LoadConfigPlugin extends MDPlugin {

		
	public LoadConfigPlugin() {
		
		JButton jButton = new JButton();
		jButton.setText("text");
		jButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				finish();
			}
		});
		component = jButton;
	}

	public JButton getJButtonComponent() {
		return (JButton) getComponent();
	}
	
	@Override
	public Component getComponent() {
		return component;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
