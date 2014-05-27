package de.teamgamma.cansat.app.database;

import org.json.JSONArray;
import org.json.JSONException;

import android.app.Fragment;
import de.teamgamma.cansat.app.fragments_androidplot.ImportSimpleXYChart;
import de.teamgamma.cansat.app.options.ChartViewOptions;
import de.teamgamma.cansat.app.options.KindOfOption;
import de.teamgamma.cansat.app.options.Options;

public class Database implements Runnable {

	private JSONArray jarray;
	private boolean download;
	private String sensor;
	private static Database instance = null;
	private Fragment fragment;

	public static Database getInstance() {
		if (instance == null) {
			instance = new Database();
		}
		return instance;
	}

	public void setSensornames() {

		this.download = false;
		Thread databaseThread = new Thread(this);
		databaseThread.start();
	}

	public void getData(String sensor, Fragment fragment) {
		this.fragment = fragment;
		this.sensor = sensor;
		this.download = true;
		Thread databaseThread = new Thread(this);
		databaseThread.start();
	}

	@Override
	public void run() {

		this.jarray = DatabseConnection.connection();

		if (this.download) {

			if (this.sensor.equals(Options.getInstance().getOption(KindOfOption.CHARTVIEW.ordinal(),
					ChartViewOptions.ACTIVESENSORNAME))) {
				((ImportSimpleXYChart) fragment).setValue(DatabaseSensordata.getInstance().getData(this.sensor, this.jarray));
			}

		} else {

			try {
				String[] names = new String[this.jarray.getJSONObject(0).names().length()];

				for (int i = 0; i < names.length; i++) {
					names[i] = (String) this.jarray.getJSONObject(0).names().get(i);
				}
				
				DatabaseSensornames.getInstance().setNamesArray(names);

			} catch (JSONException e) {
				e.printStackTrace();
			}

		}

		this.download = false;
	}

}