package de.teamgamma.cansat.app.data;

import de.teamgamma.cansat.app.database.Database;
import de.teamgamma.cansat.app.fileoperations.SaveThread;
import de.teamgamma.cansat.app.fragments_androidplot.RealtimeGraph;
import de.teamgamma.cansat.app.json.Json;
import de.teamgamma.cansat.app.main.MainActivity;
import de.teamgamma.cansat.app.options.ChartViewOptions;
import de.teamgamma.cansat.app.options.KindOfOption;
import de.teamgamma.cansat.app.options.newOptions;
import de.teamgamma.cansat.app.sensors.Sensor;

public class DataCoordination {

	private Json json = Json.getInstance();
	private static DataCoordination instance = null;
	private Sensor[] sensors = new Sensor[constantValues.names.length];
	private newOptions options = newOptions.getInstance();

	// Singleton
	public static DataCoordination getInstance() {
		if (instance == null) {
			instance = new DataCoordination();
		}
		return instance;
	}

	public DataCoordination() {
		// ARRAY SENSOREN MIT NAMESBELEGUNG
		for (int i = 0; i < constantValues.names.length; i++) {
			sensors[i] = new Sensor();
			sensors[i].setName(constantValues.names[i]);

		}
	}

	public void coordinateData(String message) {
		// message wird von Socket �bergeben
		// message wird von json entpackt
		Double[][] data = json.unpack(message);
		// Die Returnwerte von message aus json, werden in das Array sensors
		// gesetzt
		for (int i = 0; i < constantValues.names.length; i++) {
			this.sensors[i].setValues(data[i][0].longValue(), data[i][1]);
		}

		for (Sensor sensor : sensors) {
			if (sensor.getName().equals(options.getOption(KindOfOption.CHARTVIEW.ordinal(), ChartViewOptions.ACTIVESENSORNAME))) {
				if (MainActivity.getCurrentFragment().getClass()
						.equals(RealtimeGraph.class)) {
					((RealtimeGraph) MainActivity.getCurrentFragment())
							.onValueChanged(sensor);
				}
			}
		}
		SaveThread saveData = new SaveThread();
		saveData.setData(data);
		Thread saveThread = new Thread(saveData);
		saveThread.start();

		// RealtimeGraph.getInstance().onValueChanged(this.sensors);
	}

	public Sensor[] getValuesFromDatabase() {
		// Daten werden aus der Datenbank ausgelesen und zurueckgegeben
		Database database = Database.getInstance();
		this.sensors = database.getValuesFromDatabase();
		return sensors;
	}

}
