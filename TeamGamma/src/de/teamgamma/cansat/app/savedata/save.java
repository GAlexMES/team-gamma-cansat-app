package de.teamgamma.cansat.app.savedata;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import de.teamgamma.cansat.app.R;
import de.teamgamma.cansat.app.options.Options;

public class save {
	Options option = Options.getInstance();
	private String filepathtime = option.getValueStoragePath()
			+ R.string.value_time;
	private String filepathtemp = option.getValueStoragePath()
			+ R.string.value_temp;
	private String filepathco2 = option.getValueStoragePath()
			+ R.string.value_co2;
	private String writableString = null;
	private final String[] names = new String[3];

	public void save() {
		names[0] = "time";
		names[1] = "temp";
		names[2] = "cos";

	}

	public void writeAll(long time, Double temp, Double co2) {

		FileOutputStream outtime = null;
		FileOutputStream outtemp = null;
		FileOutputStream outco2 = null;

		try {

			outtime = new FileOutputStream(new File(filepathtime), true);
			writableString = Long.toString(time);
			outtime.write(writableString.getBytes());
			outtime.close();

			if (temp != null) {
				outtemp = new FileOutputStream(new File(filepathtemp), true);
				writableString = Double.toString(temp);
				outtemp.write(writableString.getBytes());
				outtemp.close();

			}

			if (co2 != null) {
				outco2 = new FileOutputStream(new File(filepathco2), true);
				writableString = Double.toString(co2);
				outco2.write(writableString.getBytes());
				outco2.close();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
