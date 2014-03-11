package de.teamgamma.cansat.app.database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import de.teamgamma.cansat.app.data.Names;
import de.teamgamma.cansat.app.sensors.Sensor;

public class Database {
	private Sensor[] sensors = new Sensor[Names.names.length];
	private String apiKey = ""; // Options.getStringApiKey oderso noch nicht
								// da!!!!

	private static Database instance = null;

	public static Database getInstance() {
		if (instance == null) {
			instance = new Database();
		}
		return instance;
	}

	public Database() {
		// SENSORENARRAY MIT NAMESBELEGUNG DER SENSOREN WIRD ERZEUGT
		for (int i = 0; i < Names.names.length; i++) {
			sensors[i] = new Sensor();
			sensors[i].setName(Names.names[i]);
		}
	}

	public Sensor[] getValuesFromDatabase() {
		// Daten werden aus der Datenbank gelesen und zurueckgegeben
		this.getData(this.connect());
		return this.sensors;
	}

	private String connect() {
		// Verbindung zur Datenbank wird aufgebaut
		// Die erhaltenen Daten werden zurueckgegeben
		try {
			String body = "key=" + URLEncoder.encode(this.apiKey, "UTF-8")
					+ "&" + "action=" + URLEncoder.encode("get_data", "UTF-8");

			URL url = new URL("http://team-gamma.de");// URL Noch nicht
														// Richtig

			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setUseCaches(false);
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			connection.setRequestProperty("Content-Length",
					String.valueOf(body.length()));
			OutputStreamWriter writer = new OutputStreamWriter(
					connection.getOutputStream());
			writer.write(body);
			writer.flush();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			StringBuilder stringBuilder = new StringBuilder();

			for (String line; (line = reader.readLine()) != null;) {
				System.out.println(line);
				stringBuilder.append(line);
			}

			writer.close();
			reader.close();

			return stringBuilder.toString();

		} catch (IOException e) {
			e.printStackTrace();

		}
		return null;
	}

	private void getData(String param) {
		// Daten werden in die jeweiligen Sensoren des SensorArrays geschrieben
		JSONObject response;
		try {
			response = new JSONObject(param);

			if (response.getBoolean("error") == false) {
				JSONArray data = response.getJSONArray("data");

				JSONObject item = null;

				if (response.length() > 20) {
					long time = 0;

					int index = 0;
					for (int i = 0; i < 20; i++) {

						index = response.length() / 20 * i;
						item = data.getJSONObject(index);

						index -= 1;
						// Zeit lesen
						time = item.getLong("time");

						// Zugriff auf einzelne Sensoren

						for (int counter = 0; counter < Names.names.length; counter++) {
							this.sensors[counter].setValues(time,
									item.getDouble(Names.names[counter]));

						}
					}

				} else {
					long time;
					for (int i = response.length(); i > 0; i++) {
						item = data.getJSONObject(i);
						time = data.getJSONObject(i).getLong("time");
						for (int counter = 0; counter < Names.names.length; counter++) {
							this.sensors[counter].setValues(time,
									item.getDouble(Names.names[counter]));
						}
					}
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
