package de.teamgamma.cansat.app.dataImport;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.androidplot.xy.*;

import de.teamgamma.cansat.app.R;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A straightforward example of using AndroidPlot to plot some data.
 */
public class ImportSimpleXYChart extends Fragment {

	private XYPlot plot;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// fun little snippet that prevents users from taking screenshots
		// on ICS+ devices :-)
		final LinearLayout mLinearLayout = (LinearLayout) inflater.inflate(
				R.layout.androidplo_xyplot, container, false);

		// initialize our XYPlot reference:
		plot = (XYPlot) mLinearLayout.findViewById(R.id.simpleXYPlot);
		
		Number[] series1Numbers = new Number[30];
		ImportedFiles importedFile = ImportedFiles.getInstance();
		Double[][] valueArray = importedFile.getLatestFile();
		for(int i=0;i<20;i++){
			series1Numbers[i]=valueArray[i][1];
		}
		// Turn the above arrays into XYSeries':
		XYSeries series1 = new SimpleXYSeries(Arrays.asList(series1Numbers), // SimpleXYSeries
																				// takes
																				// a
																				// List
																				// so
																				// turn
																				// our
																				// array
																				// into
																				// a
																				// List
				SimpleXYSeries.ArrayFormat.XY_VALS_INTERLEAVED, // Y_VALS_ONLY means use
														// the element index as
														// the x value
				"Series1"); // Set the display title of the series

		// Create a formatter to use for drawing a series using
		// LineAndPointRenderer
		// and configure it from xml:
		LineAndPointFormatter series1Format = new LineAndPointFormatter(
				Color.RED, Color.GREEN, Color.BLUE, null);

		// add a new series' to the xyplot:
		plot.addSeries(series1, series1Format);

		// same as above:
		LineAndPointFormatter series2Format = new LineAndPointFormatter(
				Color.RED, Color.GREEN, Color.BLUE, null);

		// reduce the number of range labels
		plot.setTicksPerRangeLabel(3);
		plot.getGraphWidget().setDomainLabelOrientation(-45);

		return mLinearLayout;
	}
}