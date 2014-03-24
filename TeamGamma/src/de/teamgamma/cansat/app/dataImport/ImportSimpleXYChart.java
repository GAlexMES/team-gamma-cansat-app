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
import de.teamgamma.cansat.app.options.Options;

import java.util.ArrayList;
import java.util.Arrays;
/**
 * 
 * @author Alexander Brennecke
 * Shows a fragment which displays a SimpleXYChart of the
 * selected import file
 */

public class ImportSimpleXYChart extends Fragment {

	private XYPlot plot;

	
	/**
	 * called when an object of this class will be created
	 */
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		// LinearLayout object, of the androitplot_xyplot.xml fragment
		final LinearLayout mLinearLayout = (LinearLayout) inflater.inflate(
				R.layout.androidplot_xyplot, container, false);

		// initialize the plot by using the XYPlot in the fragment
		plot = (XYPlot) mLinearLayout.findViewById(R.id.simpleXYPlot);
		
		// initialize a few importend variables to display the values correct
		Number[] seriesNumbers = new Number[30];
		ImportedFiles importedFile = ImportedFiles.getInstance();
		Double[][] valueArray = importedFile.getLatestFile();
		//push the datas out of the valueArray into an Number Array
		for(int i=0;i<20;i++){
			seriesNumbers[i]=valueArray[i][1];
		}
		// Make the seriesNumbers [] to an XYSeries
		XYSeries series1 = new SimpleXYSeries(Arrays.asList(seriesNumbers), 
				SimpleXYSeries.ArrayFormat.XY_VALS_INTERLEAVED, 
				""); //Name od the series

		// Configures the graph
		LineAndPointFormatter series1Format = new LineAndPointFormatter(
				Options.getInstance().getSelectedColors()[0], Options.getInstance().getSelectedColors()[1], Options.getInstance().getSelectedColors()[2], null);

		// add a new series' to the xyplot:
		plot.addSeries(series1, series1Format);

		// reduce the number of range labels
		plot.setTicksPerRangeLabel(3);
		plot.getGraphWidget().setDomainLabelOrientation(-45);

		return mLinearLayout;
	}
}