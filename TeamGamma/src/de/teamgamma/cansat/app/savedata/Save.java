package de.teamgamma.cansat.app.savedata;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import de.teamgamma.cansat.app.data.Names;
import de.teamgamma.cansat.app.options.Options;

public class Save {
	private static Save instance = null;

	Options option = Options.getInstance();
	private String filepath;

	private String writableString = null;

	// Singleton
	public static Save getInstance() {
		if (instance == null) {
			instance = new Save();
		}
		return instance;
	}

	public void saveAll(Double time, Double[] data) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_kk_mm",
				Locale.GERMANY);
		String date = sdf.format(new Date());

		FileOutputStream out;

		try {

			for (int i = 0; i < Names.names.length; i++) {

				if (data[i] != null) {
					this.filepath = option.getValueStoragePath() + "/" + date
							+ Names.names[i] + ".txt";
					out = new FileOutputStream(new File(this.filepath), true);
					this.writableString = time + ":" + Double.toString(data[i])
							+ "\n";
					out.write(this.writableString.getBytes());
					out.close();

				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
