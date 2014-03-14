package de.teamgamma.cansat.app.options;

import java.io.FileOutputStream;

import android.os.Environment;

/**
 * 
 * @author Alexander Brennecke
 * generate a options.txt if there doens't exists one
 *
 */
public class GenerateOptions {
	//generale filepath for this app
	private static String filepath = Environment.getExternalStorageDirectory()
			.getPath() + "/teamgamma";
	// to type om 
	private static String newLine = "\n";

	/**
	 * called when a new options.txt should be generated
	 * @return a writable string with importend informations for data saving
	 */
	public String generate() {
		String writeableString = "" + newLine + "" + newLine + "1" + newLine+ filepath + newLine + filepath ;
		return writeableString;
	}
	
	/**
	 * 
	 * @return the general options.txt filepath
	 */
	public  String getOptionsFilepath(){
		return this.filepath+"/options.txt";
	}
}
