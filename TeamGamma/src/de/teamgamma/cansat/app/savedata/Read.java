package de.teamgamma.cansat.app.savedata;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import de.teamgamma.cansat.app.data.Names;
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
			int counter = 0;
			while ((zeile = in.readLine()) != null) {

				if (counter > 0) {
					lineArray = zeile.split(":");
					if (lineArray.length > 20) {
						for (int i = 0; i < 20; i += 1) {

							int index = lineArray.length / 20 * i;
							if (index % 2 != 0 && index > 1) {
								index--;
							}
							outputArrayList[i][0] = (double) Long
									.parseLong(lineArray[index]);
							outputArrayList[i][1] = (double) Long
									.parseLong(lineArray[index + 1]);

						}
					} else {
						for (int i = 0; i < lineArray.length; i++) {
							outputArrayList[i][0] = (double) Long
									.parseLong(lineArray[i]);
							outputArrayList[i][1] = (double) Long
									.parseLong(lineArray[i + 1]);
						}
					}
					in.close();
				} else {
					// testen ob \n mit uebergeben wird... auch beim auslesen der werte
					if (zeile != Names.head) {
						// Fehelerausgabe.....Falsche Datei
						break;
					}
				}
				counter++;
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
