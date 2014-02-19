package de.teamgamma.cansat.app.json;

import org.json.JSONObject;

public class Json {
	
	private final String[] names = new String[3];
	
	
	public Json(){
		this.names[0] = "Time:";
		this.names[1] = "Temp";
		this.names[2] = "Co2";
	}
	
	
	
	
	public void unpack(String json){
		
		JSONObject data = new JSONObject(json);
		
		/*
		 * Daten an Save und Values uebergeben.
		 */
		data.getLong(this.names[0]);
		data.getDouble(names[2]);
		data.getDouble(names[2]);
		
		
		
		
		
	}
	
	
	
	
	
	

}
