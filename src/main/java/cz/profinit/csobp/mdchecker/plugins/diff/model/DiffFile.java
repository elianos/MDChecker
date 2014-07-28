package cz.profinit.csobp.mdchecker.plugins.diff.model;

import java.io.File;

/**
 * Model nesouci informace o rozdilu mezi soubory
 * 
 * @author: vjinoch
 */
public class DiffFile {
	
	/**
	 * Reference na stary soubor
	 */
	private File oldFile;
	
	/**
	 * Reference na novy soubor
	 */
	private File newFile;

	public DiffFile(File oldFile, File newFile) {
		super();
		this.oldFile = oldFile;
		this.newFile = newFile;
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
