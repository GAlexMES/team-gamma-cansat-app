package de.teamgamma.cansat.app.data;

import de.teamgamma.cansat.app.json.Json;
import de.teamgamma.cansat.app.savedata.Read;
import de.teamgamma.cansat.app.savedata.Save;
import de.teamgamma.cansat.app.sensors.Sensor;

public class DataCoordination {

	private Json json = Json.getInstance();
	private Save save = Save.getInstance();
	private Sensor temp = new Sensor();
	private Sensor co2 = new Sensor();

	public void coordinateData(String message) {
		json.unpack(message);
		save.saveAll(json.getTime(), json.getTemp(), json.getCo2());
		temp.setValues(json.getTime(), json.getTemp());
		co2.setValues(json.getTime(), json.getCo2());

	}

}
