package de.teamgamma.cansat.app.database;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import de.teamgamma.cansat.app.values.ValueList;
import de.teamgamma.cansat.app.values.Values;

public class Sensordata {
	private ValueList data;
	private static Sensordata instance = null;

	public static Sensordata getInstance() {
		if (instance == null) {
			instance = new Sensordata();
		}
		return instance;
	}


	public ArrayList<Values> getData(String sensor, JSONArray jarray) {
		Log.d("database", sensor);
		this.data = new ValueList();

		if (jarray == null) {
			return null;
		}
		JSONObject jvalue;
		Long firstTimestamp = Long.valueOf(0);
		try {
			jvalue = jarray.getJSONObject(0);
			firstTimestamp = jvalue.getLong("time");
			
			
			
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		for (int i = 0; i < jarray.length(); i++) {

			try {
				jvalue = jarray.getJSONObject(i);
				this.data.appendData(
						Double.valueOf(jvalue.getLong("time") - firstTimestamp),
						jvalue.getDouble(sensor));
				Log.d("database", String.valueOf(jvalue.getDouble(sensor)));

			} catch (JSONException e) {
				e.printStackTrace();
			}

		}
		return this.data.getData();

	}
}