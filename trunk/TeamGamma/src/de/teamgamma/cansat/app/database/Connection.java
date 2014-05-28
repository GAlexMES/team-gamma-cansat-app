package de.teamgamma.cansat.app.database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * @author Teamgamma
 * 
 * this class connects to our database and is returned the data as an "JSONArray"
 * 
 * 
 */

public class Connection {

	public static JSONArray connection() {
		//here the connection to the database will start
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

			try {
				//The data from our database will be cast to an ("JSONArray") and the JsonArray is returned
				JSONArray jarray = new JSONObject(reader.readLine()).getJSONArray("data");

				return jarray;

			} catch (JSONException e) {
				e.printStackTrace();
			}
			reader.close();

		} catch (IOException e) {
			e.printStackTrace();

		}
		return null;
	}

}
