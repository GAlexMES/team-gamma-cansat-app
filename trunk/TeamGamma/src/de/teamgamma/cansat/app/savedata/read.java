package de.teamgamma.cansat.app.savedata;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import android.os.Environment;

public class read {
	private String filepath = Environment.getExternalStorageDirectory()
			.getPath() + "/teamgamma/Values.txt";
	private final String[] names = new String[3];

	private long[] time;
	private double[] temp;
	private double[] co2;

	public read() {
		names[0] = "time";
		names[1] = "temp";
		names[2] = "cos2";
	}

	public void getValue(int positionLine) {
		String[] line = null;
		int counter = 0;
		int arrayCounter = 1;
		try {
			BufferedReader in = new BufferedReader(new FileReader(filepath));
			for (int i = 0; i < positionLine + 1; i++) {
				counter++;
				line = in.readLine().split(":");

				time[i] = Long.parseLong(line[0]);
				temp[i] = Long.parseLong(line[1]);
				co2[i] = Long.parseLong(line[3]);
				

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
