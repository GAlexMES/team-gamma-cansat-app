package de.teamgamma.cansat.app.savedata;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import de.teamgamma.cansat.app.options.Options;

public class Save {
	private static Save instance = null;

	Options option = Options.getInstance();
	private String filepathtemp;
	private String filepathco2;

	private String writableString = null;

	// Singleton
	public static Save getInstance() {
		if (instance == null) {
			instance = new Save();
		}
		return instance;
	}

	public void saveAll(long time, Double temp, Double co2) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd_kk/mm",
				Locale.GERMANY);
		String date = sdf.format(new Date());

		this.filepathco2 = option.getValueStoragePath() + date + "_co2";
		this.filepathtemp = option.getValueStoragePath() + date + "_temp";

		FileOutputStream outtemp;
		FileOutputStream outco2;

		try {

			if (temp != null) {
				outtemp = new FileOutputStream(new File(filepathtemp), true);
				writableString = time + ":" + Double.toString(temp) + "\n";
				outtemp.close();
			}

			if (co2 != null) {
				outco2 = new FileOutputStream(new File(filepathco2), true);
				writableString = time + ":" + Double.toString(co2) + "\n";
				outco2.write(writableString.getBytes());
				outco2.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
