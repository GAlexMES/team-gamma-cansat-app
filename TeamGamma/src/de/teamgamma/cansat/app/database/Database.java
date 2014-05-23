package de.teamgamma.cansat.app.database;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import de.teamgamma.cansat.app.values.ValueList;
import de.teamgamma.cansat.app.values.Values;

public class Database {
	private ValueList data;
	private static Database instance = null;
	private String[] namesArray;

	public String[] getSensornames() {
		return namesArray;
	}

	public void setNamesArray(String[] namesArray) {
		this.namesArray = namesArray;
	}

	public static Database getInstance() {
		if (instance == null) {
			instance = new Database();
		}
		return instance;
	}

	public Database() {
		// SENSORENARRAY MIT NAMESBELEGUNG DER SENSOREN WIRD ERZEUGT
		data = new ValueList();

	}


	public void setSensornames(JSONObject jdata) {
		
		Thread connectionThread = new Thread(new ConnectionThread());
		connectionThread.start();

	}

	public ArrayList<Values> getData(String sensor) {
		ValueList data = new ValueList();

		JSONObject jdata = null;
		JSONArray jarray = null;

		try {
			if (jdata == null){
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