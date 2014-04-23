package de.teamgamma.cansat.app.fileoperations;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import de.teamgamma.cansat.app.data.constantValues;
import de.teamgamma.cansat.app.options.KindOfOption;
import de.teamgamma.cansat.app.options.PathOptions;
import de.teamgamma.cansat.app.options.Options;

public class Save {
	private static Save instance = null;

	private Options option = Options.getInstance();
	private String filepath;
	private String writableString = null;
	private String exportTime;

	// an instance of the class is created, so there is no recursive call
	public static Save getInstance() {
		if (instance == null) {
			instance = new Save();
		}
		return instance;
	}

	private Save() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_kk_mm",
				Locale.GERMANY);
		this.exportTime = sdf.format(new Date());

	}

	public void saveAll(Double[][] data) {

		FileOutputStream out;

		try {
			Double time = data[0][0];

			for (int i = 1; i < constantValues.names.length; i++) {
				// the values ​​of individual sensors are each written to a
				// separate Sensor file
				// the name of the file consists of the date, time and name of
				// the sensor
				if (data[i][1] != null) {
					this.filepath = option.getOption(
							KindOfOption.PATH.ordinal(),
							PathOptions.VALUESTORAGEPATH)
							+ "/"
							+ this.exportTime
							+ constantValues.names[i]
							+ ".teamgamma";
					out = new FileOutputStream(new File(this.filepath), true);

					this.writableString = String.valueOf(time) + ":"
							+ Double.toString(data[i][1]) + "\n";
					out.write(this.writableString.getBytes());
					out.close();

				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
