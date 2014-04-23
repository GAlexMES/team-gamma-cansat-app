package de.teamgamma.cansat.app.fragments_androidplot;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
	private XYSeries highestValueSeries = null;
	private Number[] series1Numbers = new Number[20];
	private Number[] highestValue = {0,0};
	public void setValue(ArrayList<Values> values){
		allValues=values;
	}
	
	/**
	 * called when an object of this class will be created
	 */
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		// LinearLayout object, of the androitplot_xyplot.xml fragment
		final RelativeLayout mLinearLayout = (RelativeLayout) inflater.inflate(
				R.layout.import_androidplot_xyplot, container, false);

		slider = (SeekBar) mLinearLayout.findViewById(R.id.seekBar1);
		if(allValues.size()>30){
			Log.d("lenght",String.valueOf(allValues.size()));
			slider.setMax(allValues.size()-10);
		}
		slider.setProgress(slider.getMax());
		slider.setOnSeekBarChangeListener(this);
		
		// initialize the plot by using the XYPlot in the fragment
		plot = (XYPlot) mLinearLayout.findViewById(R.id.simpleXYPlot);
		
		
		for(int i = 0; i<allValues.size();i++){
			if(allValues.get(i).getValues()[1]>highestValue[1].intValue()){
				highestValue[1]=allValues.get(i).getValues()[1].intValue();
			}
		}
		
				
		int counter = 0;
		for(int i=0; i<10;i++){
			Double[] values = allValues.get(allValues.size()-10+i).getValues();
			int value = values[1].intValue();
			int time = values[0].intValue();
			if(counter==0){
				highestValue[0]=time;
			}
			series1Numbers[counter]=value;
			counter++;
			series1Numbers[counter]=time;
			counter++;
		}

		
		// Make the seriesNumbers [] to an XYSeries
		series1 = new SimpleXYSeries(Arrays.asList(series1Numbers), 
				SimpleXYSeries.ArrayFormat.XY_VALS_INTERLEAVED, 
				""); //Name of the series
		
		// Make the highestValue to an XYSeries
		highestValueSeries = new SimpleXYSeries(Arrays.asList(highestValue), 
				SimpleXYSeries.ArrayFormat.XY_VALS_INTERLEAVED, 
				""); //Name of the series
		
		// Configures the graph
		LineAndPointFormatter series1Format = new LineAndPointFormatter(				
				constantValues.selectableColors[options.getOptions()[KindOfOption.CHARTVIEW.ordinal()].getColors()[1]], //Line color
				constantValues.selectableColors[options.getOptions()[KindOfOption.CHARTVIEW.ordinal()].getColors()[0]], //Point color
				constantValues.selectableColors[options.getOptions()[KindOfOption.CHARTVIEW.ordinal()].getColors()[2]], //Area color
				null);
		LineAndPointFormatter series2Format = new LineAndPointFormatter(				
				Color.TRANSPARENT,Color.TRANSPARENT,Color.TRANSPARENT,
				null);

		// add a new series' to the xyplot:
		plot.addSeries(series1, series1Format);
		plot.addSeries(highestValueSeries, series2Format);

		// reduce the number of range labels
		plot.setTicksPerRangeLabel(3);
		plot.getGraphWidget().setDomainLabelOrientation(-45);

		return mLinearLayout;
	}
	
	

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		int SeekBarprogress = slider.getProgress();

		
		for(int i=0; i<10;i++){
			((SimpleXYSeries) series1).removeFirst();
			Values b = allValues.get(SeekBarprogress+i);
			Double[] e = b.getValues();
			Double l = e[0];
			Double m = e[1];
			int c = l.intValue();
			int w = m.intValue();
			Number value = highestValue[1];
			((SimpleXYSeries) highestValueSeries).removeLast();
			((SimpleXYSeries) highestValueSeries).addLast(c,value);
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