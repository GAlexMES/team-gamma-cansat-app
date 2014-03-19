package de.teamgamma.cansat.app.sensors;

import android.util.Log;

public class Sensor {
	private double[][] values = new double[20][2];
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
			values[19][0] = time;
			values[19][1] = value;
		}
		Log.d("sensor",String.valueOf(time));
		Log.d("Sensor",String.valueOf(values[19][1]));
	}
	
	public void setFirstValue(long time,Double value){
		if (value != null) {
			newValue();
			values[0][0] = time;
			values[0][1] = value;
		}
		
	}

	private void newValue() {
		for (int i = 0; i < 19; i++) {
			values[i][1] = values[i + 1][1];
		}
	}

}
