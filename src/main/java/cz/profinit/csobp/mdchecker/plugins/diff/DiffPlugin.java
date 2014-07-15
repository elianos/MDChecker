package cz.profinit.csobp.mdchecker.plugins.diff;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JComponent;

import com.sun.tools.javadoc.RootDocImpl;

import jdiff.API;
import jdiff.APIComparator;
import jdiff.JDiff;
import cz.profinit.csobp.mdchecker.plugins.MDPlugin;
import difflib.Delta;
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

	private static List<String> fileToLines(String filename) {
		List<String> lines = new LinkedList<String>();
		String line = "";
		try {
			BufferedReader in = new BufferedReader(new FileReader(filename));
			while ((line = in.readLine()) != null) {
				lines.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}

	public void afterCreate() {
		System.out.println(APIComparator.docChanged(oldWar, newWar));
		System.out.println(APIComparator.changedInheritance(oldWar, newWar));
		// API api = jdiff.XMLToAPI.readFile("data/configs.properties", false,
		// "test");

		List<String> original = fileToLines("data/configs.properties");
		List<String> revised = fileToLines("data/configs2.properties");

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
