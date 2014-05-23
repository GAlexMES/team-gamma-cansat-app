package de.teamgamma.cansat.app.database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Database implements Runnable {

	private JSONArray jarray;
	private boolean download;
	private String sensor;
	private static Database instance = null;

	public static Database getInstance() {
		if (instance == null) {
			instance = new Database();
		}
		return instance;
	}

	public void getSensornames() {

		this.download = false;
		Thread databaseThread = new Thread(this);
	}

	public void getData(String sensor) {
		this.sensor = sensor;
		this.download = true;
		Thread databaseThread = new Thread(this);
	}

	@Override
	public void run() {

		this.jarray = DatabseConnection.getInstance().connection();

		if (this.download) {
			DatabaseSensorsdata.getInstance().setNamesArray(
					(JSONObject.getNames(this.jarray.getJSONObject(0))));

		} else {

			DatabaseSensorsdata.getInstance().getData(this.sensor);
		}

		this.download = false;
	}

}
