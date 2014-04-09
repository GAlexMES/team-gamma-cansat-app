package de.teamgamma.cansat.app.fragments_androidplot;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.androidplot.xy.*;

import de.teamgamma.cansat.app.R;
import de.teamgamma.cansat.app.data.constantValues;
import de.teamgamma.cansat.app.dataImport.ImportedFiles;
import de.teamgamma.cansat.app.options.KindOfOption;
import de.teamgamma.cansat.app.options.Options;


import java.util.Arrays;
/**
 * 
 * @author Alexander Brennecke
 * Shows a fragment which displays a SimpleXYChart of the
 * selected import file
 */

public class ImportSimpleXYChart extends Fragment {

	private XYPlot plot;
	private Options options = Options.getInstance();

	
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
		ImportedFiles importedFile = ImportedFiles.getInstance();
		Double[][] valueArray = importedFile.getLatestFile();
		Number[] seriesNumbers = new Number[valueArray.length*2];
		//push the datas out of the valueArray into an Number Array
		for(int i=0;i<valueArray.length;i++){
			seriesNumbers[2*i]=valueArray[i][0];
			seriesNumbers[2*i+1]=valueArray[i][1];
		}
		// Make the seriesNumbers [] to an XYSeries
		XYSeries series1 = new SimpleXYSeries(Arrays.asList(seriesNumbers), 
				SimpleXYSeries.ArrayFormat.XY_VALS_INTERLEAVED, 
				""); //Name od the series
		// Configures the graph
		LineAndPointFormatter series1Format = new LineAndPointFormatter(				
				constantValues.selectableColors[options.getOptions()[KindOfOption.CHARTVIEW.ordinal()].getColors()[1]], //Line color
				constantValues.selectableColors[options.getOptions()[KindOfOption.CHARTVIEW.ordinal()].getColors()[0]], //Point color
				constantValues.selectableColors[options.getOptions()[KindOfOption.CHARTVIEW.ordinal()].getColors()[2]], //Area color
				null);

		// add a new series' to the xyplot:
		plot.addSeries(series1, series1Format);

		// reduce the number of range labels
		plot.setTicksPerRangeLabel(3);
		plot.getGraphWidget().setDomainLabelOrientation(-45);

		return mLinearLayout;
	}
}