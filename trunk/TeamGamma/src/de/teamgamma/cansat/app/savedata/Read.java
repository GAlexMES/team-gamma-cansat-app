package de.teamgamma.cansat.app.savedata;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import android.util.Log;
import de.teamgamma.cansat.app.options.Options;

public class Read {
	Options option = Options.getInstance();
	ArrayList<Values> data = new ArrayList<Values>();
	Double[][] output;

	private static Read instance = null;

	// Singleton
	public static Read getInstance() {
		if (instance == null) {
			instance = new Read();
		}
		return instance;
	}

	public Double[][] getValuefromFile(String filepath) {
		String[] lineArray;
		Double[] lineDouble = new Double[2];
		try {
			BufferedReader in = new BufferedReader(new FileReader(filepath));
			String zeile = null;
			int counter = 0;
			
			while ((zeile = in.readLine()) != null) {
				
				lineArray = zeile.split(":");
				Log.d("row", zeile);
				if (lineArray.length > 1){
				lineDouble[0] = Double.valueOf(lineArray[0]);
				lineDouble[1] = Double.valueOf(lineArray[1]);
				this.data.add(new Values());
				this.data.get(counter).setValues(lineDouble);

				
				counter++;
				}
			}
			for (int i = 0; i < this.data.size(); i++) {
				output = new Double[this.data.size()][2];
				output[i][0] = this.data.get(i).getValues()[0];
				output[i][1] = this.data.get(i).getValues()[1];
			
				
			}
			for (Double[] d : output){

				Log.d("DATA", String.valueOf(d[0]));
				
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return output;
	}

}
