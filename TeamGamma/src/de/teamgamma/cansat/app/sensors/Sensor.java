package de.teamgamma.cansat.app.sensors;

import android.util.Log;
import de.teamgamma.cansat.app.options.ChartViewOptions;
import de.teamgamma.cansat.app.options.KindOfOption;
import de.teamgamma.cansat.app.options.Options;

public class Sensor {
	private int numberOfValues;
	private double[][] values;
	private String name;
	
	
	public Sensor(){
		Options options = Options.getInstance();
		if(options.getOption(KindOfOption.CHARTVIEW.ordinal(), ChartViewOptions.NUMBEROFSHOWNVALUE)==null){
			numberOfValues=20;
		}
		else{
			this.numberOfValues = Integer.valueOf(options.getOption(KindOfOption.CHARTVIEW.ordinal(), ChartViewOptions.NUMBEROFSHOWNVALUE));
			
		}
		values = new double[numberOfValues][2];
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double[][] getValues() {
		return values;
	}

	public void setValues(long time, Double value) {
		if (value != null) {
			newValue();
			if (numberOfValues > 0) {
				values[numberOfValues - 1][0] = time;
				values[numberOfValues - 1][1] = value;
			}
		}
	}

	public void setFirstValue(long time, Double value) {
		if (value != null) {
			newValue();
			values[0][0] = time;
			values[0][1] = value;
		}

	}

	private void newValue() {
		for (int i = 0; i < numberOfValues - 1; i++) {
			values[i][1] = values[i + 1][1];
		}
	}

}
