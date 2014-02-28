package de.teamgamma.cansat.app.savedata;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import android.util.Log;
import de.teamgamma.cansat.app.options.Options;

public class Read {
	Options option = Options.getInstance();

	private String filepathtemp;
	private String filepathco2;

	private double[][] temp;
	private Double[][] outputArrayList = new Double[20][2];
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
			BufferedReader in = new BufferedReader(new FileReader(
					this.filepathco2));
			String zeile = null;

			while ((zeile = in.readLine()) != null) {
				// counter = 0; vielleicht, vielleicht auch nicht
				lineArray = zeile.split(":");
				for (String i : lineArray) {
					if (counter % 2 == 0) {
						Log.d("i", i); // wird nur einmal aufgerufen, dann
										// nullPointer in der nächsten Zeile!
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

	public Double[][] getValuefromFile(String filepath) {
		refreshOutputArray();
		String[] lineArray;
		int counter = 0;
		try {
			BufferedReader in = new BufferedReader(new FileReader(filepath));
			String zeile = null;
			while ((zeile = in.readLine()) != null) {
				lineArray = zeile.split(":");
				for (int i = 0; i < lineArray.length; i=i+2) {
					if (i < 20) {						
						outputArrayList[counter][0] = ((double) Long.parseLong(lineArray[i]));				
						outputArrayList[counter][1] = ((double) Long.parseLong(lineArray[i+1]));
						counter ++;
					}
					else{
						pushOutputArrayList();
						if (i % 2 == 0) {
							outputArrayList[19][0] = ((double) Long.parseLong(lineArray[i]));

						} else {
							outputArrayList[19][1] = ((double) Long.parseLong(lineArray[i]));
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
	
	private void refreshOutputArray(){
		for(int i=0;i<20;i++){
			for(int b=0;b<2;b++){
				outputArrayList[i][b]=0.0;
			}			
		}
	}
	
	private void pushOutputArrayList(){
		for(int i=0;i<19;i++){
			outputArrayList[i][0]=outputArrayList[i+1][0];
			outputArrayList[i][1]=outputArrayList[i+1][1];
		}
	}


}
