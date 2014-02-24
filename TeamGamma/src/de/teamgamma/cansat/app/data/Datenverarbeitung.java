package de.teamgamma.cansat.app.data;

import de.teamgamma.cansat.app.json.Json;
import de.teamgamma.cansat.app.savedata.Save;
import de.teamgamma.cansat.app.sensors.Sensor;

public class Datenverarbeitung {
	
	public Datenverarbeitung(String message){
		Json j = Json.getInstance();
		Save save = Save.getInstance();
		Sensor temp = new Sensor();
		Sensor co2 = new Sensor();
		
		j.unpack(message);
		
		save.saveAll(j.getTime(), j.getTemp(), j.getCo2());
		
		
		
		// Alex seine klasse Sensor muss ein Doublearray sein
		//temp.setValues(j.getTime(), j.getTemp());
		//co2.setValues(j.getTime(), j.getCo2());
		
		
		
	}
	
	
	

}
