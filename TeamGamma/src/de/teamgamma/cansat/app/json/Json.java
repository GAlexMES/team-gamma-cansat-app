package de.teamgamma.cansat.app.json;

import org.json.JSONObject;

import de.teamgamma.cansat.app.savedata.Save;

public class Json {

	private final String[] names = new String[3];
	private static Json instance = null;

	public static Json getInstance() {
		if (instance == null) {
			instance = new Json();
		}
		return instance;

	}
	public Json(){
		this.names[0] = "time";
		this.names[1] = "temp";
		this.names[2] = "co2";
	}

	public void unpack(String json) {

		JSONObject data = new JSONObject(json);

		/*
		 * Daten an Save und Values uebergeben.
		 */

		Save.getInstance().writeAll(data.getLong(names[0]),
				data.getDouble(names[1]), data.getDouble(names[2]));

		data.getLong(this.names[0]);
		data.getDouble(names[2]);
		data.getDouble(names[2]);

	}

}
