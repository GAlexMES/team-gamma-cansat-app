package de.teamgamma.cansat.app.json;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import de.teamgamma.cansat.app.data.Names;
import de.teamgamma.cansat.app.savedata.Save;

public class Json {

	private static Json instance = null;
	private Double[][] sensors = new Double[Names.names.length][2];
	private Save save = Save.getInstance();
	private Double[] dataToSave;

	public static Json getInstance() {
		if (instance == null) {
			instance = new Json();
		}
		return instance;
	}

	public Double[][] unpack(String json) {

		JSONObject data;
		try {
			data = new JSONObject(json);
			Long time;
			time = data.getLong("time");
			for (int i = 0; i < Names.names.length; i++) {
				// Sensoren mit Werten belegen
				Log.d("Json","for1");
				
				this.sensors[i][1] = data.getDouble(Names.names[i]);
				Log.d("Json","for2");
				this.sensors[i][0] = Double.valueOf(time);
				Log.d("Json","for3");
//				if (i > 1) {
//					this.dataToSave[i - 1] = this.sensors[i][1];
//				}
				//save.saveAll(this.sensors[0][0], dataToSave);
			}
			
			
		} catch (JSONException e) {

			e.printStackTrace();
		}

		return sensors;
	}

}
