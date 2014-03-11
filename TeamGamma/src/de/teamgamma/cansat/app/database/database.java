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

public class database {
	private final String key = "ae635cd72df";

	// fertig!!!!
	private String apiKey = ""; // Options.getStringApiKey oderso noch nicht
								// da!!!!

	public void connect() {
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

			JSONObject response = new JSONObject(stringBuilder.toString());

			Sensor[] sensors = new Sensor[Names.names.length];
			for (int i = 0; i <= Names.names.length; i++) {

				sensors[i] = new Sensor();
			}

			if (response.getBoolean("error") == false) {
				JSONArray data = response.getJSONArray("data");

				JSONObject item = null;

				if (response.length() > 20) {
					long time = 0;
					int counter = 0;

					int index = 0;
					for (int i = 0; i < 20; i++) {

						index = response.length() / 20 * i;
						item = data.getJSONObject(index);

						index -= 1;
						// Zeit lesen
						time = item.getLong("time");

						// Zugriff auf einzelne Sensoren

						for (String s : Names.names) {
							sensors[counter].setValues(time, item.getDouble(s));

							counter++;
						}
					}

				} else {

				}

			} else {
				// Fehlerbehandlung
			}

		} catch (IOException e) {
			e.printStackTrace();

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
