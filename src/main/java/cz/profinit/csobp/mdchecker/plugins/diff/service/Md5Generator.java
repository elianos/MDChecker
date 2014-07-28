package cz.profinit.csobp.mdchecker.plugins.diff.service;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

/**
 * 
 * Generator MD5 checksum pro porovnani souboru
 * 
 * @author: vjinoch
 */
public class Md5Generator {

	/**
	 * Instance
	 */
	private static Md5Generator instance = null;

	private Logger logger = Logger.getLogger(Md5Generator.class);

	/**
	 * Typ hashovaci funkce
	 */
	private static final String HASH_TYPE = "md5";

	private MessageDigest md;

	/**
	 * private constructor inicializujici generator
	 * 
	 * @throws NoSuchAlgorithmException
	 */
	private Md5Generator() throws NoSuchAlgorithmException {
		md = MessageDigest.getInstance(HASH_TYPE);
	}

	/**
	 * Ziskani instance generatoru
	 * 
	 * @return instanci generatoru
	 * 
	 * @throws NoSuchAlgorithmException
	 */
	public static Md5Generator getInstance() throws NoSuchAlgorithmException {
		if (instance == null) {
			instance = new Md5Generator();
		}
		return instance;
	}

	/**
	 * Provede vygenerovani md5 checksum souboru.
	 * 
	 * @param file
	 *            soubor ze ktereho ma byt generovana checksum.
	 * 
	 * @return vraci checksum. Pokud soubor neexistuje, nebo ho neni mozne cist
	 *         vraci null.
	 * 
	 * @throws IOException
	 */
	public String generateMd5(File file) {
		
		if (file == null || !file.exists() || !file.canRead()) {
			return null;
		}
		
		byte[] hash = null;
		try {
			hash = md.digest(FileUtils.readFileToByteArray(file));
			StringBuilder sb = new StringBuilder(2 * hash.length);
			for (byte b : hash) {
				sb.append(String.format("%02x", b & 0xff));
			}
			return sb.toString();
		} catch (IOException e) {
			logger.error("Soubor nebylo mozne zpracovat", e);
			return null;
		}
	}

}
