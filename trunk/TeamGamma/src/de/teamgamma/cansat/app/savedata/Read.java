package de.teamgamma.cansat.app.savedata;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import de.teamgamma.cansat.app.options.Options;

public class Read {
	Options option = Options.getInstance();

	private Double[][] outputArrayList = new Double[20][2];
	private static Read instance = null;

	// Singleton
	public static Read getInstance() {
		if (instance == null) {
			instance = new Read();
		}
		return instance;
	}

	public Double[][] getValuefromFile(String filepath) {
		refreshOutputArray();
		String[] lineArray;
		try {
			BufferedReader in = new BufferedReader(new FileReader(filepath));
			String zeile = null;
			while ((zeile = in.readLine()) != null) {
				lineArray = zeile.split(":");
				for (int i = 0; i < 20; i += 1) {
					if (lineArray.length > 20){

						int index = lineArray.length/20 * i;

						if (index+1 <= lineArray.length){
						outputArrayList[i][0] = (double) Long.parseLong(lineArray[index]);
						outputArrayList[i][1] = (double) Long.parseLong(lineArray[index+1]);
						}
					}
				}
				in.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return outputArrayList;
	}

	private void refreshOutputArray() {
		for (int i = 0; i < 20; i++) {
			for (int b = 0; b < 2; b++) {
				outputArrayList[i][b] = 0.0;
			}
		}
	}



}
