package de.teamgamma.cansat.app.database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import de.teamgamma.cansat.app.values.ValueList;
import de.teamgamma.cansat.app.values.Values;

public class DatabaseSensorsdata {
	private ValueList data;
	private static DatabaseSensorsdata instance = null;

	public static DatabaseSensorsdata getInstance() {
		if (instance == null) {
			instance = new DatabaseSensorsdata();
		}
		return instance;
	}

	public DatabaseSensorsdata() {
		// SENSORENARRAY MIT NAMESBELEGUNG DER SENSOREN WIRD ERZEUGT
		data = new ValueList();

	}

	public ArrayList<Values> getData(String sensor) {
		ValueList data = new ValueList();

		JSONObject jdata = null;
		JSONArray jarray = null;

		try {
			if (jdata == null) {
				return null;
			}
			jarray = jdata.getJSONArray("data");

		} catch (JSONException e1) {
			e1.printStackTrace();
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