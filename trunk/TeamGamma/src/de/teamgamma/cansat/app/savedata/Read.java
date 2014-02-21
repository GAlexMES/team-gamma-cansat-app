package de.teamgamma.cansat.app.savedata;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import de.teamgamma.cansat.app.R;
import de.teamgamma.cansat.app.options.Options;



public class Read {
	Options option = Options.getInstance();
	private String filepathtime = option.getValueStoragePath()
			+ R.string.value_time;
	private String filepathtemp = option.getValueStoragePath()
			+ R.string.value_temp;
	private String filepathco2 = option.getValueStoragePath()
			+ R.string.value_co2;

	private long[] time;
	private double[][] temp;
	private double[][] co2;
	private static Read instance = null;

	// Singletion
	public static Read getInstance() {
		if (instance == null) {
			instance = new Read();
		}
		return instance;

	}

	public long[] getTime() {
		int counter = 1;
		String[] lineArray;
		try {
			BufferedReader in = new BufferedReader(new FileReader(filepathtime));

			String zeile = null;
			while ((zeile = in.readLine()) != null) {
				lineArray = zeile.split(":");
				for (String i : lineArray) {
					time[counter] = Long.parseLong(i);
				}
				counter++;

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return time;
	}

	public double[][] getTemp() {
		int counter = 0;
		String[] lineArray;
		try {
			BufferedReader in = new BufferedReader(new FileReader(filepathtemp));

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

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return temp;
	}

	public double[][] getCo2() {
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

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return co2;
	}

	// public void getValue(int positionLine) {
	// String[] line = null;
	// int counter = 0;
	// int arrayCounter = 1;
	// try {
	// BufferedReader in = new BufferedReader(new FileReader(filepathtime));
	// for (int i = 0; i < positionLine + 1; i++) {
	// counter++;
	// line = in.readLine().split(":");
	//
	// time[i] = Long.parseLong(line[0]);
	// temp[i] = Long.parseLong(line[1]);
	// co2[i] = Long.parseLong(line[3]);
	//
	// }
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }

}
