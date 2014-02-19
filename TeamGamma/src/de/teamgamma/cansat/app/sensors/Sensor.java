package de.teamgamma.cansat.app.sensors;

public class Sensor {
	private int[][] values = new int[10][2];
	
	public int[][] getValues() {
		// TODO Auto-generated method stub
		return values;
	}

	public void setValues(int time, int value) {
		newValue();
		values[9][0] = time;
		values[9][1] = value;
	}
	
	private void newValue(){
		for(int i = 0; i<9;i++){
			values[i]=values[i+1];
		}
	}

}
