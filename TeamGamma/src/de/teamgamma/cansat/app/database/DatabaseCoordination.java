package de.teamgamma.cansat.app.database;

import org.json.JSONArray;

import android.app.Fragment;
import de.teamgamma.cansat.app.fragments_androidplot.ImportSimpleXYChart;
import de.teamgamma.cansat.app.options.ChartViewOptions;
import de.teamgamma.cansat.app.options.KindOfOption;
import de.teamgamma.cansat.app.options.Options;

public class DatabaseCoordination {


	private static DatabaseCoordination instance = null;
	private JSONArray jarray;


	public void setJsonArray(JSONArray jarray) {
		this.jarray = jarray;
	}

	private ConnectionRun connectionrun = new ConnectionRun();

	public static DatabaseCoordination getInstance() {
		if (instance == null) {
			instance = new DatabaseCoordination();
		}
		return instance;
	}

	public void connectToDatabase() {
		Thread databaseThread = new Thread(this.connectionrun);
		databaseThread.start();
	}

	public void getData(String sensor, Fragment fragment) {

		if (sensor.equals(Options.getInstance().getOption(
				KindOfOption.CHARTVIEW.ordinal(),
				ChartViewOptions.ACTIVESENSORNAME))) {
			((ImportSimpleXYChart) fragment).setValue(Sensordata.getInstance()
					.getData(sensor, this.jarray));
		}
	}

}