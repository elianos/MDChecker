package cz.profinit.csobp.mdchecker.plugins.diff;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import cz.profinit.csobp.mdchecker.core.MDContainer;
import cz.profinit.csobp.mdchecker.plugins.loadconfig.LoadConfigPlugin;
import difflib.DiffUtils;
import difflib.Patch;

public class DiffGeneratorTest {
	
	public DiffGenerator diffGenerator;
	
	@Before
	public void setUp() {
		diffGenerator = new DiffGenerator();
	}
	
	@Test
	public void generateHtmlTest() throws IOException {
		List<String> original = FileUtils.readLines(new File("src/test/resources/plugins/diff/ClassOld.txt"), "UTF8");
		List<String> revised = FileUtils.readLines(new File("src/test/resources/plugins/diff/ClassNew.txt"), "UTF8");

		Patch patch = DiffUtils.diff(original, revised);
		String result = diffGenerator.generateHtml(original, patch, "data/configs.properties");
		System.out.println(result);

	}
	
}
