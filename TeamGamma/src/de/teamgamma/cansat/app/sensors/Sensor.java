package de.teamgamma.cansat.app.sensors;

public class Sensor {
	private double[][] values = new double[10][2];
	
	public double[][] getValues() {
		// TODO Auto-generated method stub
		return values;
	}

	public void setValues(long time, Double value) {
		if (value != null){
			
		
		newValue();
		values[9][0] = time;
		values[9][1] = value;
		}
	}

	
	private void newValue(){
		for(int i = 0; i<9;i++){
			values[i][1]=values[i+1][1];
		}
	}
	
}
