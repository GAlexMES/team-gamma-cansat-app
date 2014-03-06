package de.teamgamma.cansat.app.json;

import org.json.JSONException;
import org.json.JSONObject;

import de.teamgamma.cansat.app.data.Names;

public class Json {

	private static Json instance = null;
	private Double[] data = new Double[Names.names.length];
	private long time;
	
	public long getTime() {
		return this.time;
	}

	public Double getTemp() {
		return data[0];
	}

	public Double getCo2() {
		return data[1];
	}

	public Double[] getData() {
		return data;
	}

	public void setData(Double[] data) {
		this.data = data;
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
			int counter = 0;
			this.time = data.getLong("time");
			for (String s : Names.names){
				this.data[counter] = data.getDouble(s);
				counter++;
				
				
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
