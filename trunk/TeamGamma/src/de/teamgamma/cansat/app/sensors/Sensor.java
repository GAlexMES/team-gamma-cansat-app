package de.teamgamma.cansat.app.sensors;

import de.teamgamma.cansat.app.options.Options;
import android.util.Log;

public class Sensor {
	private int numberOfValues = Options.getInstance().getNumberOfValues();
	private double[][] values = new double[numberOfValues][2];
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
			values[numberOfValues-1][0] = time;
			values[numberOfValues-1][1] = value;
		}
		Log.d("sensor",String.valueOf(time));
		Log.d("Sensor",String.valueOf(values[numberOfValues-1][1]));
	}
	
	public void setFirstValue(long time,Double value){
		if (value != null) {
			newValue();
			values[0][0] = time;
			values[0][1] = value;
		}
		
	}

	private void newValue() {
		for (int i = 0; i < numberOfValues-1; i++) {
			values[i][1] = values[i + 1][1];
		}
	}

}
