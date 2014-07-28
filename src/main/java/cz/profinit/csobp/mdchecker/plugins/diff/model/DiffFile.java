package cz.profinit.csobp.mdchecker.plugins.diff.model;

import java.io.File;

/**
 * TODO
 * 
 * @author: vjinoch
 */
public class DiffFile {
	
	private File oldFile;
	
	private File newFile;

	public DiffFile(File oldFile, File newFile) {
		super();
		this.oldFile = oldFile;
		this.newFile = newFile;
	}

	public File getOldFile() {
		return oldFile;
	}

	public void setOldFile(File oldFile) {
		this.oldFile = oldFile;
	}

	public File getNewFile() {
		return newFile;
	}

	public void setNewFile(File newFile) {
		this.newFile = newFile;
	}

	@Override
	public String toString() {
		return "DiffFile [newFile=" + newFile + "]";
	}
}
