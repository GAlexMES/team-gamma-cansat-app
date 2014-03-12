package de.teamgamma.cansat.app.fragments_androidplot;

import java.util.Arrays;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;

import de.teamgamma.cansat.app.R;
import de.teamgamma.cansat.app.data.Names;
import de.teamgamma.cansat.app.sensors.Sensor;

public class RealtimeGraph extends Fragment {
	private XYPlot plot;
	private static final int HISTORY_SIZE = 30;
	private XYSeries series1 = null;
	private String sensorName = "";
	private int sensorPosition;
	private static RealtimeGraph instance = null;

	public static final RealtimeGraph getInstance() {
		if (instance == null) {
			instance = new RealtimeGraph();
			return instance;
		} else {
			return instance;
		}
	}

	public void onSensorChange(String sensorName) {
		if (this.sensorName.equals("")) {
		} 
		else if (this.sensorName.equals(sensorName)) {
		} 
		else if (!this.sensorName.equals(sensorName)) {
			int position = 0;
			for (String name : Names.names) {
				if (sensorName.equals(name)) {
					this.sensorName = sensorName;
					this.sensorPosition = position;
					plot.clear();
					break;
				}
				position++;
			}

		}

	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// fun little snippet that prevents users from taking screenshots
		// on ICS+ devices :-)
		final LinearLayout mLinearLayout = (LinearLayout) inflater.inflate(
				R.layout.androidplot_simple_xy_plot_example, container, false);

		// initialize our XYPlot reference:
		plot = (XYPlot) mLinearLayout.findViewById(R.id.mySimpleXYPlot);

		// Create a couple arrays of y-values to plot:
		Number[] series1Numbers = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

		// Turn the above arrays into XYSeries':
		series1 = new SimpleXYSeries(Arrays.asList(series1Numbers), // SimpleXYSeries
																	// takes a
																	// List so
																	// turn our
																	// array
																	// into a
																	// List
				SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, // Y_VALS_ONLY means use
														// the element index as
														// the x value
				sensorName); // Set the display title of the series

		// Create a formatter to use for drawing a series using
		// LineAndPointRenderer
		// and configure it from xml:
		LineAndPointFormatter series1Format = new LineAndPointFormatter(
				Color.BLUE, Color.YELLOW, Color.WHITE, null);

		// add a new series' to the xyplot:
		plot.addSeries(series1, series1Format);

		// reduce the number of range labels
		plot.setTicksPerRangeLabel(3);
		plot.getGraphWidget().setDomainLabelOrientation(-45);

		return mLinearLayout;
	}

	public synchronized void onValueChanged(Sensor[] sensor) {
		// get rid the oldest sample in history:
		if (series1.size() > HISTORY_SIZE) {
			((SimpleXYSeries) series1).removeFirst();
		}
		// add the latest history sample:
		((SimpleXYSeries) series1).addLast(null,
				sensor[sensorPosition].getValues()[0][0]);

		// redraw the Plots:
		plot.redraw();

	}
}
