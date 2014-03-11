package de.teamgamma.cansat.app.data;

import de.teamgamma.cansat.app.json.Json;
import de.teamgamma.cansat.app.savedata.Read;
import de.teamgamma.cansat.app.savedata.Save;
import de.teamgamma.cansat.app.sensors.Sensor;

public class DataCoordination {

	private Json json = Json.getInstance();
	private static DataCoordination instance = null;
	private Sensor[] sensors = new Sensor[Names.names.length];

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
		Double[][] data = json.unpack(message);
		for(int i = 0; i < Names.names.length; i++){
			this.sensors[i].setValues(data[i][1].longValue(), data[i][2]);
		}
		
		
		
		
		
	}
	
	public Sensor[] getValuesFromDatabase(){
		// Database...
		
		return sensors;
	}

}
