package de.teamgamma.cansat.app.options;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import android.os.Environment;
import android.util.Log;



public class OptionsExport {
	private String newLine = "\n";
	private String filepath =Environment.getExternalStorageDirectory().getPath()+ "/teamgamma/options.txt";
	private String writableString = null;
	
	public void writeAll(String java_socket_ipAdress, String java_socket_port, int methodToConnect, String valueExportPath, String valueStoragePath){
	FileOutputStream out = null;
	writableString = java_socket_ipAdress + newLine + java_socket_port+ newLine + methodToConnect+ newLine+ valueExportPath + newLine + valueStoragePath;
	try {
		out = new FileOutputStream(new File(filepath));
	} catch (FileNotFoundException e2) {
		// TODO Auto-generated catch block
		e2.printStackTrace();
	}
	try {
		out.write(writableString.getBytes());
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	try {
		out.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  
	}
	
	public String getValue (int positionLine){
		String zeile = null;
		try {
			BufferedReader in = new BufferedReader(new FileReader(filepath));			
			for(int i =0;i<positionLine+1;i++){
				zeile=in.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return zeile;
	}

}

