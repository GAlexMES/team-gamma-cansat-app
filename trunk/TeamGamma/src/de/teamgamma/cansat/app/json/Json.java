package de.teamgamma.cansat.app.json;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import de.teamgamma.cansat.app.data.Names;

public class Json {

	private static Json instance = null;
	private Double[][] sensors = new Double[Names.names.length][2];

	public static Json getInstance() {
		if (instance == null) {
			instance = new Json();
		}
		return instance;
	}

	public Double[][] unpack(String json) {

		JSONObject data;
		try {
			data = new JSONObject(json);
			Long time;
			time = data.getLong("time");
			for (int i = 0; i < Names.names.length; i++) {
				// Sensoren mit Werten belegen
				
				this.sensors[i][1] = data.getDouble(Names.names[i]);
				this.sensors[i][0] = Double.valueOf(time);
			}
			

		} catch (JSONException e) {

			e.printStackTrace();
		}

		return sensors;
	}

}