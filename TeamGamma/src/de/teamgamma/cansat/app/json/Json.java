package de.teamgamma.cansat.app.json;

import org.json.JSONException;
import org.json.JSONObject;

import de.teamgamma.cansat.app.data.constantValues;


public class Json {

	private static Json instance = null;
	private Double[][] sensors = new Double[constantValues.names.length][2];

	public static Json getInstance() {
		if (instance == null) {
			instance = new Json();
		}
		return instance;
	}

	public Double[][] unpack(String json) {
		// the data are read out with the help of a "JsonObject" and added to the respective "Sensor" class
		JSONObject data;
		try {
			data = new JSONObject(json);
			Long time;
			time = data.getLong("time");
			for (int i = 0; i < constantValues.names.length; i++) {
				this.sensors[i][1] = data.getDouble(constantValues.names[i]);
				this.sensors[i][0] = Double.valueOf(time);

			}
		} catch (JSONException e) {

			e.printStackTrace();
		}

		return sensors;
	}

}
