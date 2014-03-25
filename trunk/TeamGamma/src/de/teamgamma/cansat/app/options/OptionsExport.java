package de.teamgamma.cansat.app.options;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import android.util.Log;

public class OptionsExport {
	private Options options = Options.getInstance();
	private String newLine = "\n";
	private String filepath = options.getOptionsPath();
	private String writeableString = null;

	public void writeAll( //writes data into options.txt
			String java_socket_ipAdress, //JavaSocketIPAdress
			String java_socket_port,//JavaSocketPort
			int methodToConnect, //MethodToConnect
			String valueExportPath, //Path for valueExport
			String valueStoragePath, //Path for value storage
			int numberOfShownPoints, //Numbers of Points, shown in a chart
			int [] selectedChartColors // selected colors for points/lines/areas
			){
		
		writeableString = java_socket_ipAdress + newLine 
						+ java_socket_port + newLine
						+ methodToConnect + newLine
						+ valueExportPath + newLine
						+ valueStoragePath + newLine
						+ numberOfShownPoints + newLine
						+ selectedChartColors[0]+ newLine
						+ selectedChartColors[1]+ newLine
						+ selectedChartColors[2];
		writeSingle(this.filepath, writeableString);
	}

	public void writeSingle(String filepath, String message) {
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(new File(filepath));
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			out.write(message.getBytes());
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
	public String getValue(int positionLine) {
		String zeile = null;
		try {
			BufferedReader in = new BufferedReader(new FileReader(filepath));
			for (int i = 0; i < positionLine + 1; i++) {
				zeile = in.readLine();
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return zeile;
	}

}
