package cz.profinit.csobp.mdchecker.plugins.diff;

import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import cz.profinit.csobp.mdchecker.utils.FileManipulatorUtil;
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
		List<String> original = FileUtils.readLines(FileManipulatorUtil.getResourceFile("plugins/diff/ClassOld.txt"), "UTF8");
		List<String> revised = FileUtils.readLines(FileManipulatorUtil.getResourceFile("plugins/diff/ClassNew.txt"), "UTF8");

		Patch patch = DiffUtils.diff(original, revised);
		String result = diffGenerator.generateHtml(original, patch, "file name");
		System.out.println(result);

	}
	
}
