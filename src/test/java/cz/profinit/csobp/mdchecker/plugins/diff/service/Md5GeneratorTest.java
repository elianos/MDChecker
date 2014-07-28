package cz.profinit.csobp.mdchecker.plugins.diff.service;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cz.profinit.csobp.mdchecker.utils.FileManipulatorUtil;

public class Md5GeneratorTest {

	public Md5Generator md5Generator;
	
	@Before
	public void setUp() throws NoSuchAlgorithmException {
		md5Generator = Md5Generator.getInstance();
	}
	
	@Test
	public void generateMd5Test() throws IOException {
		String md5 = md5Generator.generateMd5(FileManipulatorUtil.getResourceFile("plugins/diff/ClassOld.txt"));
		String excepted = "821ae7262793725b71e914c5932c80ec";
		Assert.assertEquals(excepted, md5);
		
		md5 = md5Generator.generateMd5(FileManipulatorUtil.getResourceFile("plugins/diff/ClassNew.txt"));
		excepted = "f61078cb0c3073c5e719e6305c62981b";
		Assert.assertEquals(excepted, md5);
		
	}
}
