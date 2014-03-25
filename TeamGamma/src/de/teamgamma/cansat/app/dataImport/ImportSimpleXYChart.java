package de.teamgamma.cansat.app.dataImport;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.androidplot.xy.*;

import de.teamgamma.cansat.app.R;
import de.teamgamma.cansat.app.data.constantValues;
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
		Number[] seriesNumbers = new Number[Options.getInstance().getNumberOfValues()*2];
		ImportedFiles importedFile = ImportedFiles.getInstance();
		Double[][] valueArray = importedFile.getLatestFile();
		for(int i=0;i<valueArray.length;i++){
			Log.d("import",String.valueOf(valueArray[i][0]));
			Log.d("import",String.valueOf(valueArray[i][1]));
		}
		Log.d("import","valueArray:"+String.valueOf(valueArray.length));
		Log.d("import","seriesNumbers:"+String.valueOf(seriesNumbers.length));
		//push the datas out of the valueArray into an Number Array
		for(int i=0;i<valueArray.length;i++){
			Log.d("import","2*i"+String.valueOf(2*i));
			seriesNumbers[2*i]=valueArray[i][0];
			Log.d("import",String.valueOf(seriesNumbers[2*i]));
			seriesNumbers[2*i+1]=valueArray[i][1];
			Log.d("import","2*i+1"+String.valueOf(2*i+1));
			Log.d("import",String.valueOf(seriesNumbers[2*i+1]));
		}
		Log.d("import","end of for");
		// Make the seriesNumbers [] to an XYSeries
		XYSeries series1 = new SimpleXYSeries(Arrays.asList(seriesNumbers), 
				SimpleXYSeries.ArrayFormat.XY_VALS_INTERLEAVED, 
				""); //Name od the series
		Log.d("import","xyseries");
		// Configures the graph
		LineAndPointFormatter series1Format = new LineAndPointFormatter(
				constantValues.selectableColors[Options.getInstance().getSelectedColors()[1]],
				constantValues.selectableColors[Options.getInstance().getSelectedColors()[0]],
				constantValues.selectableColors[Options.getInstance().getSelectedColors()[2]],
				null);

		// add a new series' to the xyplot:
		plot.addSeries(series1, series1Format);

		// reduce the number of range labels
		plot.setTicksPerRangeLabel(3);
		plot.getGraphWidget().setDomainLabelOrientation(-45);

		return mLinearLayout;
	}
}