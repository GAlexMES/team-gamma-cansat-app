package de.teamgamma.cansat.app.fileoperations;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import de.teamgamma.cansat.app.data.Values;
import de.teamgamma.cansat.app.options.ChartViewOptions;
import de.teamgamma.cansat.app.options.KindOfOption;
import de.teamgamma.cansat.app.options.Options;

public class Read {
	private Options option = Options.getInstance();
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

		if (filepath.endsWith("teamgamma")) {
			String[] lineArray;
			Double[] lineDouble = new Double[2];
			try {
				BufferedReader in = new BufferedReader(new FileReader(filepath));
				String zeile = null;
				int counter = 0;

				while ((zeile = in.readLine()) != null) {

					lineArray = zeile.split(":");
					if (lineArray.length > 1 && lineArray[0] != null
							&& lineArray[1] != null) {

						lineDouble[0] = Double.valueOf(lineArray[0]);
						lineDouble[1] = Double.valueOf(lineArray[1]);
						this.data.add(new Values());
						this.data.get(counter).setValues(lineDouble);

						counter++;

					}
				}
				if (Integer.valueOf(this.option.getOption(
						KindOfOption.CHARTVIEW.ordinal(),
						ChartViewOptions.NUMBEROFSHOWNVALUE)) < this.data
						.size()) {

				}
				this.data.clear();

				in.close();
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}

			return this.data;
		}

		else {
			// FEHLERBEHEBUNG fuer Alex
			return null;
		}
	}
}
