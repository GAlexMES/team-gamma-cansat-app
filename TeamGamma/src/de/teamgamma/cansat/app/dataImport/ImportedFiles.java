package de.teamgamma.cansat.app.dataImport;

import java.util.ArrayList;

/**
 * 
 * @author Alexander Brennecke
 * Shows 20 values from an imported file
 * files can be opened by the file browser
 *
 */
public class ImportedFiles {
	private Double[][] latestFile = new Double[20][2];
	private static ImportedFiles instance  = null;	
	/**
	 * 
	 * @return instance for singelton pattern
	 */
	public static ImportedFiles getInstance(){
		if(instance==null){
			instance=new ImportedFiles();
			return instance;
		}
		else{
			return instance;
		}
	}
	
	public Double[][] getLatestFile() {
		return latestFile;
	}

	public void setLatestFile(Double[][] latestFile) {
		this.latestFile = latestFile;
	}

	public static void setInstance(ImportedFiles instance) {
		ImportedFiles.instance = instance;
	}
	

	
	

}
