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
	private String apiKey = ""; // Options.getStringApiKey oderso noch nicht
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
		// Verbindung zur Datenbank wird aufgebaut
		// Die erhaltenen Daten werden zurueckgegeben
		try {
//			String body = "key=" + URLEncoder.encode(this.apiKey, "UTF-8") + "&" + "action="
//			+ URLEncoder.encode("get_data", "UTF-8");

			URL url = new URL("http://gammaweb.team-gamma.de/read.php");

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setUseCaches(false);
			connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
//			connection.setRequestProperty("Content-Length",String.valueOf(body.length()));
//			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
//			writer.write(body);
//			writer.flush();
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			JSONArray jdata = null;
			
			try {
				jdata = new JSONArray(reader.readLine());
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
			JSONObject jvalue;
			for (int i = 0; i < jdata.length(); i++) {
				
				try {
					jvalue = jdata.getJSONObject(i);
					this.data.appendData(Double.valueOf(jvalue.getLong("time")), jvalue.getDouble(sensor));


				} catch (JSONException e) {
					e.printStackTrace();
				}

			}

//			writer.close();
			reader.close();

		} catch (IOException e) {
			e.printStackTrace();

		}
		return this.data.getData();

	}

}
