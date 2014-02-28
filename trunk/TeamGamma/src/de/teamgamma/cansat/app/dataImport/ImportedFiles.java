package de.teamgamma.cansat.app.dataImport;

import java.util.ArrayList;

public class ImportedFiles {
	private Double[][] latestFile = new Double[20][2];
	public Double[][] getLatestFile() {
		return latestFile;
	}

	public void setLatestFile(Double[][] latestFile) {
		this.latestFile = latestFile;
	}

	private static ImportedFiles instance  = null;
	
	public static ImportedFiles getInstance(){
		if(instance==null){
			instance=new ImportedFiles();
			return instance;
		}
		else{
			return instance;
		}
	}

	public static void setInstance(ImportedFiles instance) {
		ImportedFiles.instance = instance;
	}
	

	
	

}
