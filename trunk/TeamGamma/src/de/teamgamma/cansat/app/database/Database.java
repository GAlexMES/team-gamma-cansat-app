package de.teamgamma.cansat.app.database;

import org.json.*;

import de.teamgamma.cansat.app.fragments_androidplot.ImportSimpleXYChart;
import de.teamgamma.cansat.app.fragments_androidplot.RealtimeGraph;
import de.teamgamma.cansat.app.main.MainActivity;
import de.teamgamma.cansat.app.options.ChartViewOptions;
import de.teamgamma.cansat.app.options.KindOfOption;
import de.teamgamma.cansat.app.options.Options;

public class Database implements Runnable {

	private JSONArray jarray;
	private boolean download;
	private String sensor;
	private static Database instance = null;

	public static Database getInstance() {
		if (instance == null) {
			instance = new Database();
		}
		return instance;
	}

	public void getSensornames() {

		this.download = false;
		Thread databaseThread = new Thread(this);
		databaseThread.start();
	}

	public void getData(String sensor) {
		this.sensor = sensor;
		this.download = true;
		Thread databaseThread = new Thread(this);
		databaseThread.start();
	}

	@Override
	public void run() {

		this.jarray = DatabseConnection.connection();

		if (this.download) {
			// sagt noch nicht wohin mit den Daten.
			// DIE FOLGENDE ZEILE LIEFERT DIE ARRAYLIST ZURUECK; MIT DER ALEXS
			// GRAPH AUFGERUFEN WERDEN SOLL!!!
			// DatabaseSensordata.getInstance().getData(this.sensor,
			// this.jarray);

			if (this.sensor.equals(Options.getInstance().getOption(
					KindOfOption.CHARTVIEW.ordinal(),
					ChartViewOptions.ACTIVESENSORNAME))) {
				if (MainActivity.getCurrentFragment().getClass()
						.equals(RealtimeGraph.class)) {
					((ImportSimpleXYChart) MainActivity.getCurrentFragment())
							.setValue(DatabaseSensordata.getInstance().getData(
									this.sensor, this.jarray));
				}
			}

		} else {

			try {
				String[] names = new String[this.jarray.getJSONObject(0)
						.names().length()];

				for (int i = 0; i < names.length; i++) {
					names[i] = (String) this.jarray.getJSONObject(0).names()
							.get(i);
				}
				DatabaseSensornames.getInstance().setNamesArray(names);

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		this.download = false;
	}

}
