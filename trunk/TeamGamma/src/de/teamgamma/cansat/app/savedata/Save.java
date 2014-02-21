package de.teamgamma.cansat.app.savedata;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import de.teamgamma.cansat.app.R;
import de.teamgamma.cansat.app.options.Options;



public class Save {
	private static Save instance = null;

	Options option = Options.getInstance();
	private String filepathtime = option.getValueStoragePath()
			+ R.string.value_time;
	private String filepathtemp = option.getValueStoragePath()
			+ R.string.value_temp;
	private String filepathco2 = option.getValueStoragePath()
			+ R.string.value_co2;
	private String writableString = null;

	
	//Singletion
	public static Save getInstance() {
		if (instance == null) {
			instance = new Save();
		}
		return instance;

	}

	public void writeAll(long time, Double temp, Double co2) {

		FileOutputStream outtime = null;
		FileOutputStream outtemp = null;
		FileOutputStream outco2 = null;

		try {

			outtime = new FileOutputStream(new File(filepathtime), true);
			writableString = Long.toString(time);
			outtime.write(writableString.getBytes());
			outtime.write(":".getBytes());
			outtime.close();

			if (temp != null) {
				outtemp = new FileOutputStream(new File(filepathtemp), true);
				writableString = time + ":" +Double.toString(temp) + "\n";
				outtemp.close();

			}

			if (co2 != null) {
				outco2 = new FileOutputStream(new File(filepathco2), true);
				writableString =time + ":" + Double.toString(co2) + "\n";
				outco2.write(writableString.getBytes());
				outco2.close();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
