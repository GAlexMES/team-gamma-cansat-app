package de.teamgamma.cansat.app.json;

import org.json.JSONException;
import org.json.JSONObject;

import de.teamgamma.cansat.app.data.constantValues;
import de.teamgamma.cansat.app.options.KindOfOption;
import de.teamgamma.cansat.app.options.MapsOptions;
import de.teamgamma.cansat.app.options.Options;

public class Json {

	private Double[][] sensors;
	private static Json instance = null;

	public static Json getInstance() {
		if (instance == null) {
			instance = new Json();
		}
		return instance;
	}

	private Json() {
		sensors = new Double[constantValues.names.length][2];
	}

	public Double[][] unpack(String json) {
		// the data are read out with the help of a "JsonObject" and added to
		// the respective "Sensor" class
		JSONObject data;
		try {
			data = new JSONObject(json);
			Long time;
			time = data.getLong("time");
			for (int i = 0; i < constantValues.names.length; i++) {
				this.sensors[i][0] = Double.valueOf(time
						- constantValues.firstTimestamp);
				try {
					if (data.getDouble(constantValues.names[i]) != 0) {
						this.sensors[i][1] = data.getDouble(constantValues.names[i]);
						if (constantValues.names[i].equals("longitude") || constantValues.names[i].equals("latitude"))

							if (constantValues.names[i].equals("longitude")) {
								Options.getInstance().setOption(KindOfOption.MAPS.ordinal(),MapsOptions.LONGITUDE,this.sensors[i][1]);
							} else if (constantValues.names[i].equals("latitude")) {
								Options.getInstance().setOption(KindOfOption.MAPS.ordinal(),MapsOptions.LATITUDE,this.sensors[i][1]);
							}
					}
				} catch (JSONException e) {
				}

			}
		} catch (JSONException e) {

			e.printStackTrace();
		}

		return this.sensors;
	}

}
