package de.teamgamma.cansat.app.dataImport;

import android.util.Log;


/**
 * 
 * @author Alexander Brennecke
 * Shows 20 values from an imported file
 * files can be opened by the file browser
 *
 */
public class ImportedFiles {
	private Double[][] latestFile;
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
		for(int i=0; i<latestFile.length;i++){
			Log.d("import", "ImportedFile:"+ String.valueOf(latestFile[i][0]));
		}
	}
}
