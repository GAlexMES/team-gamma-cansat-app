package de.teamgamma.cansat.app.fileoperations;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import android.util.Log;
import de.teamgamma.cansat.app.data.Values;
import de.teamgamma.cansat.app.options.Options;

public class Read {
	private Options option = Options.getInstance();
	private ArrayList<Values> data = new ArrayList<Values>();
	private Double[][] output;

	private static Read instance = null;

	// Singleton
	public static Read getInstance() {
		if (instance == null) {
			instance = new Read();
		}
		return instance;
	}

	public Double[][] getValuefromFile(String filepath) {

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
				int index = 0;
				if (this.option.getNumberOfValues() < this.data.size()) {
					this.output = new Double[this.option.getNumberOfValues()][2];
					for (int i = 0; i < this.output.length; i++) {
						index = (int) (this.data.size() / this.output.length * i);

						this.output[i][0] = this.data.get(index).getValues()[0];
						this.output[i][1] = this.data.get(index).getValues()[1];

						Log.d("getValues", String.valueOf(output[i][0]));

					}
				}else{
					this.output = new Double[this.data.size()][2];
					for (int i = 0; i < this.data.size(); i++){
						this.output[i][0] = this.data.get(i).getValues()[0];
						this.output[i][1] = this.data.get(i).getValues()[1];
						Log.d("getValues", String.valueOf(output[i][0]));
					}
					
				}
				this.data.clear();

				in.close();
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}

			return output;
		}

		else {
			// FEHLERBEHEBUNG fuer Alex
			return null;
		}
	}
}
