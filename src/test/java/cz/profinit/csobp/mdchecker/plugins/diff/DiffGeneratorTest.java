package cz.profinit.csobp.mdchecker.plugins.diff;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import difflib.DiffUtils;
import difflib.Patch;

public class DiffGeneratorTest {
	
	public DiffGenerator diffGenerator;
	
	@Before
	public void setUp() {
		diffGenerator = new DiffGenerator();
	}
	
	@Test
	public void generateHtmlTest() {
		List<String> original = fileToLines("src/test/resources/plugins/diff/ClassOld.txt");
		List<String> revised = fileToLines("src/test/resources/plugins/diff/ClassNew.txt");

		Patch patch = DiffUtils.diff(original, revised);
		String result = diffGenerator.generateHtml(original, patch, "data/configs.properties");
		System.out.println(result);
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
	
}
