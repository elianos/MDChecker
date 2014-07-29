package cz.profinit.csobp.mdchecker.utils;

import java.io.File;
import java.net.URL;

public class FileManipulatorUtil {
	
	public static File getResourceFile(String path) {
		return new File(getResourcePath(path));
	}
	
	public static String getResourcePath(String path) {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		URL url = classLoader.getResource(path);
		return url.getPath();
	}
}
