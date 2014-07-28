package cz.profinit.csobp.mdchecker.plugins.diff;

import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import cz.profinit.csobp.mdchecker.plugins.diff.model.DiffFile;
import cz.profinit.csobp.mdchecker.plugins.diff.service.Md5Generator;

/**
 * 
 * Trida provadi porovnavani dvou slozek.
 * 
 * @author: vjinoch
 */
public class FolderDiff {

	/**
	 * Instance Md5Generatoru
	 */
	private Md5Generator md5Generator;

	public FolderDiff() {
		try {
			md5Generator = Md5Generator.getInstance();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Rekurzivni metoda generujcii rozdil dvou slozek. Pokud je nova slozka
	 * null, vraci prazdny set. Pokud je stara slozka null, vraci diff z nove
	 * slozky, jako vytvorenou novou slozku. Pokud jsou oba vstupy soubor a
	 * jejich checksum se neshoduje oznaci soubory jako diff. Pokud jsou vstupy
	 * slozky prochazim soubory v nove slozce a porovnavam se starou, pokud se
	 * soubory neshoduji oznacim je jako diff. Pokud jsou porovnavane soubory
	 * slozka a soubor, oznacuji jako diff novou variantu, bez ohledu na tu
	 * starou.
	 * 
	 * @param oldBasePath cesta ke staremu zdroji dat
	 * @param newBasePath cesta k nvoemu zdroji dat
	 * 
	 * @return vraci vis zahlavi javadoc.
	 */
	public List<DiffFile> generateFolderDiff(String oldBasePath,
			String newBasePath) {

		// Pokud neexistuje novy sobor, nedelam diff
		if (newBasePath == null) {
			return Collections.emptyList();
		}

		List<DiffFile> result = new LinkedList<DiffFile>();
		File newFile = new File(newBasePath);

		// Pokud neexistuje stary soubor automaticky tvorim diff
		if (oldBasePath == null) {
			// Pokud je novy soubor slozka, projdu ho rekurzivne
			if (newFile.isDirectory()) {
				for (File tmpFile : newFile.listFiles()) {
					result.addAll(generateFolderDiff(null, tmpFile.getPath()));
				}
				// Jinak vztvarim diff pouze souboru
			} else {
				result.add(new DiffFile(null, newFile));
			}
			return result;
		}

		File oldFile = new File(oldBasePath);

		// Oba dva jsou soubory
		if (oldFile.isFile() && newFile.isFile()) {
			String oldCheckSum = md5Generator.generateMd5(oldFile);
			String newCheckSum = md5Generator.generateMd5(newFile);
			// Pokud maji soubory rozdilnou md5 generuji diff
			if (!oldCheckSum.equals(newCheckSum)) {
				result.add(new DiffFile(oldFile, newFile));
				return result;
			}
			// Oba dva jsou slozky
		} else if (oldFile.isDirectory() && newFile.isDirectory()) {
			// Rekurzivne prochazim novou strukturu a vyhledavam, zda existuje
			// stejny soubor ve stare strukture. Pokud ano zavolam nad nimi
			// porovnani, pokud ne zavolam vytvareni pouze nad novym.
			for (String fileName : newFile.list()) {
				String oldPath = null;
				File tmpFile = new File(oldFile, fileName);
				if (tmpFile.exists()) {
					oldPath = tmpFile.getPath();
				}
				String newpath = new File(newFile, fileName).getPath();
				result.addAll(generateFolderDiff(oldPath, newpath));

			}
			// Porovnavam slozku a soubor...
		} else {
			// Pokud se jedna o typ a slozku volam porovnani pouze na nove verzi
			result.addAll(generateFolderDiff(null, newBasePath));
		}

		return result;
	}

}
