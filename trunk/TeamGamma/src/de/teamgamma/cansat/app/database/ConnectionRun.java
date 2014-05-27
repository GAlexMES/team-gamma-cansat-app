package de.teamgamma.cansat.app.database;

import org.json.JSONArray;
import org.json.JSONException;

public class ConnectionRun implements Runnable {

	@Override
	public void run() {

		JSONArray jarray = Connection.connection();
		DatabaseCoordination.getInstance().setJsonArray(jarray);

		try {
			String[] names = new String[jarray.getJSONObject(0).names()
					.length()];

			for (int i = 0; i < names.length; i++) {
				names[i] = (String) jarray.getJSONObject(0).names().get(i);
			}

			Sensornames.getInstance().setNamesArray(names);

		} catch (JSONException e) {
			e.printStackTrace();
		}


	}

}
