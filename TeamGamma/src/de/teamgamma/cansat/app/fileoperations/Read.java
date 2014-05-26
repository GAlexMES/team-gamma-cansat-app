package de.teamgamma.cansat.app.fileoperations;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import de.teamgamma.cansat.app.values.ValueList;
import de.teamgamma.cansat.app.values.Values;

public class Read {

	private ValueList data;
	private static Read instance = null;

	// Singleton
	public static Read getInstance() {
		if (instance == null) {
			instance = new Read();
		}
		return instance;
	}

	public ArrayList<Values> getValuefromFile(String filepath) {
		this.data = new ValueList();
		if (filepath.endsWith("teamgamma")) {
			String[] lineArray;
			try {
				BufferedReader in = new BufferedReader(new FileReader(filepath));
				String row = null;

				while ((row = in.readLine()) != null) {

					lineArray = row.split(":");
					if (lineArray.length > 1 && lineArray[0] != null
							&& lineArray[1] != null) {

						this.data.appendData(Double.valueOf(lineArray[0]),
								Double.valueOf(lineArray[1]));
					}
				}

				in.close();
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}

			return this.data.getData();
		}

		else {
			return null;
		}
	}
}
