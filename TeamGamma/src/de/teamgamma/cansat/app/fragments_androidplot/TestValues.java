package de.teamgamma.cansat.app.fragments_androidplot;

import java.util.Random;

import android.util.Log;
import de.teamgamma.cansat.app.sensors.Sensor;

public class TestValues extends Thread {
	Sensor sensor = new Sensor();
	Double i = 0.1;

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
			Log.d("gamma", String.valueOf(i));
			sensor.setFirstValue(i.longValue(),i);
			i = i + 10.0;
			RealtimeGraph.getInstance().onValueChanged(sensor);
			
		}
	}

}
