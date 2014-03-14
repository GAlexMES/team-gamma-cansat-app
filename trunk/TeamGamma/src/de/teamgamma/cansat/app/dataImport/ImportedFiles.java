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
	
	/**
	 * 
	 * 
	 * @return a double[][] out of the latest file
	 */
	public Double[][] getLatestFile() {
		return latestFile;
	}
	
	/**
	 * 
	 * @param latestFile a double[][] of the latest import
	 */

	public void setLatestFile(Double[][] latestFile) {
		this.latestFile = latestFile;
	}
}
