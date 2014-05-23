package de.teamgamma.cansat.app.database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ConnectionThread implements Runnable{

	@Override
	public void run() {
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

			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			JSONObject jdata = null;
			try {
				
				jdata = new JSONObject(reader.readLine());
				
				JSONArray jarray = jdata.getJSONArray("data");
				
				Database.getInstance().setSensornames(JSONObject.getNames(jarray.getJSONObject(0)));	
				
				
				
				
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
			reader.close();
			
		
		} catch (IOException e) {
			e.printStackTrace();

		}		
	}

}
