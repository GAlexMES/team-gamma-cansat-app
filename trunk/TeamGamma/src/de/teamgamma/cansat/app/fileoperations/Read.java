package de.teamgamma.cansat.app.fileoperations;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import de.teamgamma.cansat.app.data.Values;

public class Read {
	private ArrayList<Values> data = new ArrayList<Values>();

	private static Read instance = null;

	// Singleton
	public static Read getInstance() {
		if (instance == null) {
			instance = new Read();
		}
		return instance;
	}

	public ArrayList<Values> getValuefromFile(String filepath) {
		this.data.clear();

		if (filepath.endsWith("teamgamma")) {
			String[] lineArray;
			Double[] lineDouble = new Double[2];
			try {
				BufferedReader in = new BufferedReader(new FileReader(filepath));
				String row = null;
				int index = 0;

				while ((row = in.readLine()) != null) {

					lineArray = row.split(":");
					if (lineArray.length > 1 && lineArray[0] != null
							&& lineArray[1] != null) {

						lineDouble[0] = Double.valueOf(lineArray[0]);
						lineDouble[1] = Double.valueOf(lineArray[1]);
						this.data.add(new Values());
						this.data.get(index).setValues(lineDouble);
						if (this.data.get(index) == null) {
							this.data.remove(index);
							index--;
						}

						index++;

					}
				}

				in.close();
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}

			return this.data;
		}

		else {
			return null;
		}
	}
}
