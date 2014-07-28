package cz.profinit.csobp.mdchecker.plugins.diff;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JComponent;

import org.apache.commons.io.FileUtils;

import jdiff.APIComparator;
import cz.profinit.csobp.mdchecker.plugins.MDPlugin;
import difflib.DiffUtils;
import difflib.Patch;

/**
 * Diff Plugin slouzi k vytvorezeni diffu mezi dvemi dodavkami.
 * 
 * @author: vjinoch
 */
public class DiffPlugin extends MDPlugin {

	private String oldWar;

	private String newWar;

	public DiffPlugin(String oldWar, String newWar) {
		this.oldWar = oldWar;
		this.newWar = newWar;
	}

	public void afterCreate() {
		System.out.println(APIComparator.docChanged(oldWar, newWar));
		System.out.println(APIComparator.changedInheritance(oldWar, newWar));
		// API api = jdiff.XMLToAPI.readFile("data/configs.properties", false,
		// "test");

		List<String> original = null;
		List<String> revised = null;
		try {
			original = FileUtils.readLines(new File("data/configs.properties"));
			revised = FileUtils.readLines(new File("data/configs2.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Patch patch = DiffUtils.diff(original, revised);

		

	};


	public String getOldWar() {
		return oldWar;
	}

	public void setOldWar(String oldWar) {
		this.oldWar = oldWar;
	}

	public String getNewWar() {
		return newWar;
	}

	public void setNewWar(String newWar) {
		this.newWar = newWar;
	}

	public String getValue() {
		return toString();
	}

	@Override
	public String toString() {
		return "DiffPlugin [oldWar=" + oldWar + ", newWar=" + newWar + "]";
	}

	@Override
	public JComponent getComponent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}

}
