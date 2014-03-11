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
		this.sensors = json.unpack(message);
		
		
		
		
		
	}
	
	public Sensor[] getValuesFromDatabase(){
		// Database...
		
		return sensors;
	}

}
