package de.teamgamma.cansat.app.database;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import de.teamgamma.cansat.app.values.ValueList;
import de.teamgamma.cansat.app.values.Values;

public class DatabaseSensordata {
	private ValueList data;
	private static DatabaseSensordata instance = null;

	public static DatabaseSensordata getInstance() {
		if (instance == null) {
			instance = new DatabaseSensordata();
		}
		return instance;
	}


	public ArrayList<Values> getData(String sensor, JSONArray jarray) {
		this.data = new ValueList();

		if (jarray == null) {
			return null;
		}
		JSONObject jvalue;
		for (int i = 0; i < jarray.length(); i++) {

			try {
				jvalue = jarray.getJSONObject(i);
				this.data.appendData(
						Double.valueOf(jvalue.getLong("utc_time")),
						jvalue.getDouble(sensor));

			} catch (JSONException e) {
				e.printStackTrace();
			}

		}

		return this.data.getData();

	}
}