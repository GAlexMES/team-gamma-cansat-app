package de.teamgamma.cansat.app.database;


public class Sensornames {
	private String[] namesArray = null;
	private static Sensornames instance = null;

	public static Sensornames getInstance() {
		if (instance == null) {
			instance = new Sensornames();
		}
		return instance;
	}
	
	public String[] getSensornames() {
		return namesArray;
	}

	public void setNamesArray(String[] namesArray) {
		this.namesArray = namesArray;
	}
}