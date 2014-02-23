package de.teamgamma.cansat.app.json;

import java.util.jar.Attributes.Name;

import org.json.JSONObject;

import de.teamgamma.cansat.app.savedata.Save;

public class Json {

	private static Json instance = null;

	public static Json getInstance() {
		if (instance == null) {
			instance = new Json();
		}
		return instance;

	}


	public void unpack(String json) {

		JSONObject data = new JSONObject(json);

		/*
		 * Daten an Save und Values uebergeben.
		 */
		for (String s : Names.names){
			if (s == "time"){
				data.getLong(s);
				
			}else{
				data.getDouble(s);
			}
			
		}
		Save.getInstance().writeAll(data.getLong(Names.names[0]),
				data.getDouble(Names.names[1]), data.getDouble(Names.names[2]));



	}

}
