package de.teamgamma.cansat.app.json;

import org.json.JSONException;
import org.json.JSONObject;

import de.teamgamma.cansat.app.data.Names;

public class Json {

	private static Json instance = null;
	private long time;
	private Double temp;
	private Double co2;

	public long getTime() {
		return time;
	}

	public Double getTemp() {
		return temp;
	}

	public Double getCo2() {
		return co2;
	}

	public static Json getInstance() {
		if (instance == null) {
			instance = new Json();
		}
		return instance;
	}

	public void unpack(String json) {

		JSONObject data;
		try {
			data = new JSONObject(json);
			this.time = data.getLong(Names.names[0]);
			this.temp = data.getDouble(Names.names[1]);
			this.co2 = data.getDouble(Names.names[2]);
			

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
