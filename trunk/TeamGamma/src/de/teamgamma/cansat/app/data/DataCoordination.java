package de.teamgamma.cansat.app.data;

import android.util.Log;
import de.teamgamma.cansat.app.database.Database;
import de.teamgamma.cansat.app.fragments_androidplot.RealtimeGraph;
import de.teamgamma.cansat.app.json.Json;
import de.teamgamma.cansat.app.main.MainActivity;
import de.teamgamma.cansat.app.options.Options;
import de.teamgamma.cansat.app.sensors.Sensor;

public class DataCoordination {

	private Json json = Json.getInstance();
	private static DataCoordination instance = null;
	private Sensor[] sensors = new Sensor[Names.names.length];
	private Options options = Options.getInstance();

	
	
	//Singleton
	public static DataCoordination getInstance() {
		if (instance == null) {
			instance = new DataCoordination();
		}
		return instance;
	}
	
	public DataCoordination(){
		// ARRAY SENSOREN MIT NAMESBELEGUNG
		for (int i = 0; i < Names.names.length; i++){
			sensors[i] = new Sensor();
			sensors[i].setName(Names.names[i]);
			
		}
	}

	public void coordinateData(String message) {
		//message wird von Socket übergeben
		//message wird von json entpackt
		Double[][] data = json.unpack(message);
		
		//Log.d("gamma", message);
		//Die Returnwerte von message aus json, werden in das Array sensors gesetzt
		for(int i = 0; i < Names.names.length; i++){
			this.sensors[i].setValues(data[i][0].longValue(), data[i][1]);
			
		}
		
		for(Sensor sensor : sensors){
			Log.d("Data","for");
			if(sensor.getName().equals(options.getActiveSensorName())){
				Log.d("Data","if1");
				if(MainActivity.getCurrentFragment().getClass().equals(RealtimeGraph.class)){
					Log.d("Data","if2");
					((RealtimeGraph)MainActivity.getCurrentFragment()).onValueChanged(sensor);
				}
			}
		}
		//RealtimeGraph.getInstance().onValueChanged(this.sensors);
	}
	
	public Sensor[] getValuesFromDatabase(){
		//Daten werden aus der Datenbank ausgelesen und zurueckgegeben
		Database database = Database.getInstance();
		this.sensors = database.getValuesFromDatabase();
		return sensors;
	}

}
