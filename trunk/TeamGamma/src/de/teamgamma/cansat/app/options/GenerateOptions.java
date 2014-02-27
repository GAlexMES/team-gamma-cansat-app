package de.teamgamma.cansat.app.options;

import java.io.FileOutputStream;

import android.os.Environment;

public class GenerateOptions {
	private static String filepath = Environment.getExternalStorageDirectory()
			.getPath() + "/teamgamma/options.txt";
	private static String newLine = "\n";

	public String generate() {
		FileOutputStream out = null;
		String writeableString = "" + newLine + "" + newLine + "1";
		return writeableString;
	}
	
	public  String getFilepath(){
		return this.filepath;
	}
}
