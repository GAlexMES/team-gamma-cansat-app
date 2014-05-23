package de.teamgamma.cansat.app.database;


public class DatabaseSensornames {
	private String[] namesArray = null;
	private static DatabaseSensornames instance = null;

	public static DatabaseSensornames getInstance() {
		if (instance == null) {
			instance = new DatabaseSensornames();
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