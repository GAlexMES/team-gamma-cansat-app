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

import android.util.Log;
import de.teamgamma.cansat.app.options.KindOfOption;
import de.teamgamma.cansat.app.options.MapsOptions;
import de.teamgamma.cansat.app.options.Options;
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

	public ArrayList<Values> connect(String sensor) {
		ValueList data = new ValueList();
		try {
			// String body = "key=" + URLEncoder.encode(this.apiKey, "UTF-8") +
			// "&" + "action="
			// + URLEncoder.encode("get_data", "UTF-8");

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
			JSONArray jarray = null;

			// System.out.println(reader.readLine());

			try {
				jdata = new JSONObject(reader.readLine());
				jarray = jdata.getJSONArray("data");

			} catch (JSONException e1) {
				e1.printStackTrace();
			}
			JSONObject jvalue;
			for (int i = 0; i < jarray.length(); i++) {

				try {
					jvalue = jarray.getJSONObject(i);
					this.data.appendData(Double.valueOf(jvalue.getString("utc_time")), Double.valueOf(jvalue.getString(sensor)));
					

				} catch (JSONException e) {
					e.printStackTrace();
				}

			}
			
//			try {
//				Options.getInstance().setOption(KindOfOption.MAPS.ordinal(),MapsOptions.LONGITUDE, jarray.getJSONObject(jarray.length()-1).getString("longitude"));
//				Options.getInstance().setOption(KindOfOption.MAPS.ordinal(),MapsOptions.LATITUDE, jarray.getJSONObject(jarray.length()-1).getString("latitude"));
//
//				
//			} catch (JSONException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

			reader.close();

		} catch (IOException e) {
			e.printStackTrace();

		}
		return this.data.getData();

	}
}