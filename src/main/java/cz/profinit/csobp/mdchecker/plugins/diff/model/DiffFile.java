package cz.profinit.csobp.mdchecker.plugins.diff.model;

import java.io.File;
import java.util.Collections;
import java.util.Set;

import org.apache.commons.io.FilenameUtils;

/**
 * Model nesouci informace o rozdilu mezi soubory
 * 
 * @author: vjinoch
 */
public class DiffFile {

	public static final String CLASS_EXTENSION = "class";

	/**
	 * Reference na stary soubor
	 */
	private File oldFile;

	/**
	 * Reference na novy soubor
	 */
	private File newFile;
	
	/**
	 * Podcasti hlavniho class souboru.
	 */
	private Set<DiffFile> slaveClasses = Collections.emptySet();

	public DiffFile(File oldFile, File newFile) {
		super();
		this.oldFile = oldFile;
		this.newFile = newFile;
	}

	/**
	 * Jedna se o class soubor?
	 * 
	 * @return pokud se jedna o class soubor vraci true, jinak false.
	 */
	public Boolean isClassFile() {
		return FilenameUtils.getExtension(newFile.getName()).equals(
				CLASS_EXTENSION);
	}

	/**
	 * Jedna se o hlavni class soubor? (Pokud soubor neobsahuje separator $
	 * jedna se o hlavni class soubor).
	 * 
	 * @return pokud se jedna o class soubor a zaroven o hlavni class soubor vraci true, jinak false;
	 */
	public Boolean isMasterClassFile() {
		return (isClassFile() && !newFile.getName().contains("$"));
	}
	
	
	/**
	 * @return vraci nazev diff souboru vcetne pripony.
	 */
	public String getFilename() {
		return newFile.getName();
	}
	
	/**
	 * @return vraci nazev diff souboru bez pripony.
	 */
	public String getFilenameWithoutExtension() {
		return FilenameUtils.getBaseName(getFilename());
	}
	
	/**
	 * @return vraci nazev hlavniho class souboru bez pripony.
	 */
	public String getMasterFilename() {
		String[] nameParts = getFilenameWithoutExtension().split("\\$");
		return nameParts[0];
	}
	
	/**
	 * {@link DiffFile#slaveClasses}
	 */
	public Set<DiffFile> getSlaveClasses() {
		return slaveClasses;
	}

	/**
	 * {@link DiffFile#slaveClasses}
	 */
	public void setSlaveClasses(Set<DiffFile> slaveClasses) {
		this.slaveClasses = slaveClasses;
	}

	/**
	 * {@link DiffFile#oldFile}
	 */
	public File getOldFile() {
		return oldFile;
	}

	/**
	 * {@link DiffFile#oldFile}
	 */
	public void setOldFile(File oldFile) {
		this.oldFile = oldFile;
	}

	/**
	 * {@link DiffFile#newFile}
	 */
	public File getNewFile() {
		return newFile;
	}

	/**
	 * {@link DiffFile#newFile}
	 */
	public void setNewFile(File newFile) {
		this.newFile = newFile;
	}

	@Override
	public String toString() {
		return "DiffFile [newFile=" + newFile + "]";
	}
}
