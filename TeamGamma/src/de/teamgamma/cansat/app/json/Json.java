package de.teamgamma.cansat.app.json;

import org.json.JSONException;
import org.json.JSONObject;

import de.teamgamma.cansat.app.data.Names;
import de.teamgamma.cansat.app.sensors.Sensor;

public class Json {

	private static Json instance = null;
	private Sensor[] sensors = new Sensor[Names.names.length];
	


	public static Json getInstance() {
		if (instance == null) {
			instance = new Json();
		}
		return instance;
	}
	
	
	public Json(){
		// ARRAY SENSOREN MIT NAMESBELEGUNG
		for (int i = 0; i < Names.names.length; i++){
			sensors[i] = new Sensor();
			sensors[i].setName(Names.names[i]);
			
		}
	}

	public Sensor[] unpack(String json) {

		JSONObject data;
		try {
			data = new JSONObject(json);
			long time;
			time = data.getLong("time");
			for (int i = 0; i < Names.names.length; i++){
				// Sensoren mit Werten belegen
				this.sensors[i].setValues(time,data.getDouble(Names.names[i]));	
				
			}return sensors;

		} catch (JSONException e) {
			e.printStackTrace();
		}return sensors;
	}

}
