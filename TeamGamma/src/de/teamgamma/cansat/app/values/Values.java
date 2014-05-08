package de.teamgamma.cansat.app.values;

public class Values {
	private Double[] values = new Double[2];

	public Double[] getValues() {
		return values;
	}

	public void setValues(Double time, Double value) {
		this.values[0] = time;
		this.values[1] = value;
	}

}
