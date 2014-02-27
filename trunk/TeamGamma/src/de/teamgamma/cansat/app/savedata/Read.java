package de.teamgamma.cansat.app.savedata;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import de.teamgamma.cansat.app.options.Options;

public class Read {
	Options option = Options.getInstance();

	private String filepathtemp;
	private String filepathco2;

	private double[][] temp;
	private double[][] co2;
	private static Read instance = null;

	// Singleton
	public static Read getInstance() {
		if (instance == null) {
			instance = new Read();
		}
		return instance;
	}

	public double[][] getTemp(String filepathtemp) {
		this.filepathtemp = filepathtemp;
		int counter = 0;
		String[] lineArray;
		try {
			BufferedReader in = new BufferedReader(new FileReader(
					this.filepathtemp));
			String zeile = null;

			while ((zeile = in.readLine()) != null) {
				lineArray = zeile.split(":");
				for (String i : lineArray) {
					if (counter % 2 == 0) {
						temp[counter][0] = Long.parseLong(i);
					} else {
						temp[counter][1] = Double.parseDouble(i);
						counter++;
					}
				}
				in.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return temp;
	}

	public double[][] getCo2(String filepathco2) {
		this.filepathco2 = filepathco2;
		int counter = 0;
		String[] lineArray;
		try {
			BufferedReader in = new BufferedReader(new FileReader(filepathco2));
			String zeile = null;

			while ((zeile = in.readLine()) != null) {
				lineArray = zeile.split(":");
				for (String i : lineArray) {
					if (counter % 2 == 0) {
						co2[counter][0] = Long.parseLong(i);

					} else {
						co2[counter][1] = Double.parseDouble(i);
						counter++;
					}
				}
				in.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return co2;
	}
}
