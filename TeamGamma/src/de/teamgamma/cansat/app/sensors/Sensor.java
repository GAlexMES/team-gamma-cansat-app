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
			this.numberOfValues=20;
		}
		else{
			this.numberOfValues = Integer.valueOf(options.getOption(KindOfOption.CHARTVIEW.ordinal(), ChartViewOptions.NUMBEROFSHOWNVALUE));
			
		}
		this.values = new double[numberOfValues][2];
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double[][] getValues() {
		return this.values;
	}

	public void setValues(long time, Double value) {
		if (value != null) {
			newValue();
			if (this.numberOfValues > 0) {
				this.values[this.numberOfValues - 1][0] = time;
				this.values[this.numberOfValues - 1][1] = value;
			}
		}
	}

	public void setFirstValue(long time, Double value) {
		if (value != null) {
			newValue();
			this.values[0][0] = time;
			this.values[0][1] = value;
		}

	}

	private void newValue() {
		for (int i = 0; i < this.numberOfValues - 1; i++) {
			this.values[i][1] = this.values[i + 1][1];
		}
	}

}
