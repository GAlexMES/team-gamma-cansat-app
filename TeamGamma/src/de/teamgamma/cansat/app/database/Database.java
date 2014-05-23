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

public class Database {
	private ValueList data;
	private static Database instance = null;

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

	private JSONObject connection() {
		try {

			URL url = new URL("http://gammaweb.noodle-net.de/read.php");

			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setUseCaches(false);
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));

			JSONObject jdata = null;
			try {
				jdata = new JSONObject(reader.readLine());
			} catch (JSONException e) {
				e.printStackTrace();
			}
			reader.close();
			return jdata;

		} catch (IOException e) {
			e.printStackTrace();

		}
		return null;

	}

	public String[] getSensornames() {
		
		
		
		JSONObject jdata = this.connection();
		JSONArray jarray = jdata.getJSONArray("data");
		
		
		try {
			
			return JSONObject.getNames(jarray.getJSONObject(0));	
			
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return null;

	}

	public ArrayList<Values> connect(String sensor) {
		ValueList data = new ValueList();

		JSONObject jdata = null;
		JSONArray jarray = null;

		try {
			jdata = this.connection();
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