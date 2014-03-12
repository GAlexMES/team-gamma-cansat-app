package de.teamgamma.cansat.app.sensors;

public class Sensor {
	private double[][] values = new double[10][2];
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double[][] getValues() {
		return values;
	}

	public void setValues(long time,Double value) {
		if (value != null) {

			newValue();
			values[9][0] = time;
			values[9][1] = value;
		}
	}
	
	public void setFirstValue(long time,Double value){
		if (value != null) {
			newValue();
			values[0][0] = time;
			values[0][1] = value;
		}
		
	}

	private void newValue() {
		for (int i = 0; i < 9; i++) {
			values[i][1] = values[i + 1][1];
		}
	}

}
