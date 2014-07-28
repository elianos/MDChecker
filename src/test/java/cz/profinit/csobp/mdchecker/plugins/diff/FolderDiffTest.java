package cz.profinit.csobp.mdchecker.plugins.diff;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cz.profinit.csobp.mdchecker.plugins.diff.model.DiffFile;

public class FolderDiffTest {

	FolderDiff folderDiff;

	@Before
	public void setUp() {
		folderDiff = new FolderDiff();
	}

	@Test
	public void generateFolderDiffTest() {
		Set<String> names = new HashSet<String>();
		names.addAll(Arrays
				.asList(new String[] {
						"data/configs.properties",
						"src/main/java/cz/profinit/csobp/mdchecker/Main.java",
						"src/main/java/cz/profinit/csobp/mdchecker/core/MDContainer.java",
						"src/main/java/cz/profinit/csobp/mdchecker/plugins/DialogException.java",
						"src/main/java/cz/profinit/csobp/mdchecker/plugins/DiffPlugin.java",
						"src/main/java/cz/profinit/csobp/mdchecker/plugins/ErrorDialogException.java",
						"src/main/java/cz/profinit/csobp/mdchecker/plugins/MDPlugin.java",
						"src/main/java/cz/profinit/csobp/mdchecker/plugins/WarningDialogException.java",
						"src/main/java/cz/profinit/csobp/mdchecker/plugins/loadconfig/ConfigurationManager.java",
						"src/main/java/cz/profinit/csobp/mdchecker/plugins/loadconfig/LoadConfigPlugin.java",
						"src/main/java/cz/profinit/csobp/mdchecker/plugins/loadconfig/components/ConfigurationLine.java",
						"src/main/java/cz/profinit/csobp/mdchecker/plugins/loadconfig/components/LoadConfigPanel.java",
						"src/main/java/cz/profinit/csobp/mdchecker/plugins/loadconfig/model/Configuration.java",
						"src/main/java/cz/profinit/csobp/mdchecker/spring/xml/factory/DiffPluginBeanDefinitionPattern.java",
						"src/main/resources/META-INF/spring.handlers",
						"src/main/resources/META-INF/spring.schemas",
						"src/main/resources/appContext.xml",
						"src/main/resources/log4j.properties",
						"src/main/resources/string/text.properties" }));

		String newPath = "src/test/resources/plugins/diff/folder_new/";
		
		List<DiffFile> diffFiles = folderDiff.generateFolderDiff(
				"src/test/resources/plugins/diff/folder_old/",
				newPath);
		
		for (DiffFile df : diffFiles) {
			String tmp = df.getNewFile().getPath();
			tmp = tmp.replace("\\", "/");
			tmp = tmp.replace(newPath, "");
			
			Assert.assertTrue("Soubor neni obsazen v seznamu", names.contains(tmp));
			names.remove(tmp);
		}
		
		Assert.assertTrue("Tyto soubory byly ocekavany: " + names, names.isEmpty());
	}
}
