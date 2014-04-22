package de.teamgamma.cansat.app.fragments_androidplot;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.androidplot.xy.*;

import de.teamgamma.cansat.app.R;
import de.teamgamma.cansat.app.data.Values;
import de.teamgamma.cansat.app.data.constantValues;
import de.teamgamma.cansat.app.dataImport.ImportedFiles;
import de.teamgamma.cansat.app.options.ChartViewOptions;
import de.teamgamma.cansat.app.options.KindOfOption;
import de.teamgamma.cansat.app.options.Options;
import de.teamgamma.cansat.app.sensors.Sensor;


import java.util.ArrayList;
import java.util.Arrays;
/**
 * 
 * @author Alexander Brennecke
 * Shows a fragment which displays a SimpleXYChart of the
 * selected import file
 */

public class ImportSimpleXYChart extends Fragment implements OnSeekBarChangeListener {

	private XYPlot plot;
	private Options options = Options.getInstance();
	private ArrayList<Values> allValues;
	private SeekBar slider;
	private XYSeries series1 = null;
	private Number[] series1Numbers = new Number[20];
	public void setValue(ArrayList<Values> values){
		allValues=values;
	}
	
	/**
	 * called when an object of this class will be created
	 */
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		// LinearLayout object, of the androitplot_xyplot.xml fragment
		final LinearLayout mLinearLayout = (LinearLayout) inflater.inflate(
				R.layout.import_androidplot_xyplot, container, false);

		slider = (SeekBar) mLinearLayout.findViewById(R.id.seekBar1);
		if(allValues.size()>30){
			Log.d("lenght",String.valueOf(allValues.size()));
			slider.setMax(allValues.size()-20);
		}
		slider.setProgress(slider.getMax());
		slider.setOnSeekBarChangeListener(this);
		
		// initialize the plot by using the XYPlot in the fragment
		plot = (XYPlot) mLinearLayout.findViewById(R.id.simpleXYPlot);
		
		// initialize a few importend variables to display the values correct
		ImportedFiles importedFile = ImportedFiles.getInstance();
		int counter = 0;
		for(int i=0; i<10;i++){
			Values b = allValues.get(allValues.size()-20+i);
			Double[] e = b.getValues();
			Double l = e[1];
			Double m = e[0];
			int c = l.intValue();
			int w = m.intValue();
			series1Numbers[counter]=c;
			counter++;
			series1Numbers[counter]=w;
			counter++;
			Log.d("numbers",String.valueOf(series1Numbers[i]));
		}
		// Make the seriesNumbers [] to an XYSeries
		series1 = new SimpleXYSeries(Arrays.asList(series1Numbers), 
				SimpleXYSeries.ArrayFormat.XY_VALS_INTERLEAVED, 
				""); //Name of the series
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
	
	

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		Log.d("SeekBar",String.valueOf(slider.getProgress()));
		int SeekBarprogress = slider.getProgress();
		for(int i=0; i<20;i++){
			((SimpleXYSeries) series1).removeFirst();
			Values b = allValues.get(SeekBarprogress+i);
			Double[] e = b.getValues();
			Double l = e[1];
			Double m = e[0];
			int c = l.intValue();
			int w = m.intValue();
			((SimpleXYSeries) series1).addLast(c,w);
		}		
		
		// redraw the Plot:
		plot.redraw();
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}
}