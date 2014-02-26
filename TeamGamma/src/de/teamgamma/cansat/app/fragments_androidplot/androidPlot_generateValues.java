package de.teamgamma.cansat.app.fragments_androidplot;

import java.util.Random;

public class androidPlot_generateValues {
	public Double generateValue(int min, int max){
		Random r = new Random();
		double randomValue = min + (max - min) * r.nextDouble();
		return randomValue;
		
	}

}
