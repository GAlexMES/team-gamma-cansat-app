package de.teamgamma.cansat.app.fragments_androidplot;

import android.R.color;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.androidplot.xy.*;

import de.teamgamma.cansat.app.R;
import de.teamgamma.cansat.app.data.Values;
import de.teamgamma.cansat.app.data.constantValues;
import de.teamgamma.cansat.app.options.KindOfOption;
import de.teamgamma.cansat.app.options.Options;



import java.util.ArrayList;

/**
 * 
 * @author Alexander Brennecke Shows a fragment which displays a SimpleXYChart
 *         of the selected import file
 */

public class ImportSimpleXYChart extends Fragment implements
		OnSeekBarChangeListener {

	private XYPlot plot;
	private Options options = Options.getInstance();
	private ArrayList<Values> allValues;
	private SeekBar timeSlider;
	private SeekBar numberOfShownValueSlider;
	private XYSeries series1 = null;
<<<<<<< .mine
	private XYSeries highestPoint = null;
	private int numberOfShownValues;
	int highestValue;
	int lowestValue;
	public void setValue(ArrayList<Values> values) {
		allValues = values;
		numberOfShownValues = allValues.size();
		lowestValue=allValues.get(0).getValues()[1].intValue();
		highestValue=allValues.get(0).getValues()[1].intValue();
		for(int i =0; i<allValues.size();i++){
			if(allValues.get(i).getValues()[1].intValue()>highestValue){
				highestValue=allValues.get(i).getValues()[1].intValue();
			}
			if(allValues.get(i).getValues()[1].intValue()<lowestValue){
				lowestValue=allValues.get(i).getValues()[1].intValue();
			}
		}
=======
	private XYSeries highestValueSeries = null;
	private Number[] series1Numbers = new Number[20];
	private Number[] highestValue = {0,0};
	public void setValue(ArrayList<Values> values){
		allValues=values;
>>>>>>> .r110
	}

	/**
	 * called when an object of this class will be created
	 */
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// LinearLayout object, of the androitplot_xyplot.xml fragment
		final RelativeLayout mLinearLayout = (RelativeLayout) inflater.inflate(
				R.layout.import_androidplot_xyplot, container, false);

		numberOfShownValueSlider = (SeekBar) mLinearLayout
				.findViewById(R.id.numberOfShownValueSeekBar);
		numberOfShownValueSlider.setMax(allValues.size());
		numberOfShownValueSlider.setProgress(numberOfShownValueSlider.getMax());
		numberOfShownValueSlider.setOnSeekBarChangeListener(this);

		timeSlider = (SeekBar) mLinearLayout.findViewById(R.id.seekBar1);
		timeSlider.setMax(allValues.size() - numberOfShownValues);
		timeSlider.setProgress(timeSlider.getMax());
		timeSlider.setOnSeekBarChangeListener(this);

		// initialize the plot by using the XYPlot in the fragment
		plot = (XYPlot) mLinearLayout.findViewById(R.id.simpleXYPlot);

<<<<<<< .mine
		// initialize a few importend variables to display the values correct

=======
		
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
>>>>>>> .r110

		
		// Make the seriesNumbers [] to an XYSeries
		series1 = new SimpleXYSeries(null,
				SimpleXYSeries.ArrayFormat.XY_VALS_INTERLEAVED, ""); // Name of
																		// the
																		// series
		highestPoint = new SimpleXYSeries(null,
				SimpleXYSeries.ArrayFormat.XY_VALS_INTERLEAVED, "");
		((SimpleXYSeries) highestPoint).addFirst(0, highestValue);
		((SimpleXYSeries) highestPoint).addFirst(0, lowestValue);

		updateSeries(numberOfShownValues, 0);
		
		// Make the highestValue to an XYSeries
		highestValueSeries = new SimpleXYSeries(Arrays.asList(highestValue), 
				SimpleXYSeries.ArrayFormat.XY_VALS_INTERLEAVED, 
				""); //Name of the series
		
		// Configures the graph
		LineAndPointFormatter series1Format = new LineAndPointFormatter(
				constantValues.selectableColors[options.getOptions()[KindOfOption.CHARTVIEW
						.ordinal()].getColors()[1]], // Line color
				constantValues.selectableColors[options.getOptions()[KindOfOption.CHARTVIEW
						.ordinal()].getColors()[0]], // Point color
				constantValues.selectableColors[options.getOptions()[KindOfOption.CHARTVIEW
						.ordinal()].getColors()[2]], // Area color
				null);
<<<<<<< .mine
		
		LineAndPointFormatter highestPointFormatter = new LineAndPointFormatter(
				color.transparent,color.transparent,color.transparent,null);
=======
		LineAndPointFormatter series2Format = new LineAndPointFormatter(				
				Color.TRANSPARENT,Color.TRANSPARENT,Color.TRANSPARENT,
				null);
>>>>>>> .r110

		// add a new series' to the xyplot:
		plot.addSeries(series1, series1Format);
<<<<<<< .mine
		plot.addSeries(highestPoint, highestPointFormatter);
=======
		plot.addSeries(highestValueSeries, series2Format);

>>>>>>> .r110
		// reduce the number of range labels
		plot.setTicksPerRangeLabel(3);
		plot.getGraphWidget().setDomainLabelOrientation(-45);

		return mLinearLayout;
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
<<<<<<< .mine
		updateSeries(numberOfShownValueSlider.getProgress(), timeSlider.getProgress());
		numberOfShownValues=numberOfShownValueSlider.getProgress();

	}

	private void updateSeries(int numberOfShownValues, int timeSliderProgress) {
		int timeSliderMax=timeSliderProgress;
		if (numberOfShownValues != this.numberOfShownValues) {			
			while (numberOfShownValues + timeSliderMax != allValues.size()) {
				if (numberOfShownValues + timeSliderMax > allValues.size()) {
					timeSliderMax = timeSliderMax - 1;
				} else if (numberOfShownValues + timeSliderMax < allValues
						.size()) {
					timeSliderMax = timeSliderMax + 1;
				}
			}
			timeSlider.setMax(timeSliderMax);
		}
		if(timeSliderProgress+numberOfShownValues>allValues.size()){
			timeSliderProgress=timeSliderMax;
		}
		int arraySize = ((SimpleXYSeries) series1).size();
		for (int i = 0; i < arraySize; i++) {
=======
		int SeekBarprogress = slider.getProgress();

		
		for(int i=0; i<10;i++){
>>>>>>> .r110
			((SimpleXYSeries) series1).removeFirst();
<<<<<<< .mine
		}
		for (int i = 0; i < numberOfShownValues; i++) {
			((SimpleXYSeries) series1).addLast(
					allValues.get(timeSliderProgress + i).getValues()[0]
							.intValue(), allValues.get(timeSliderProgress + i)
							.getValues()[1].intValue());
		}
		for(int i = 0; i<2;i++){
		((SimpleXYSeries) highestPoint).removeFirst();
		}
		((SimpleXYSeries) highestPoint).addFirst(timeSliderProgress,highestValue);
		((SimpleXYSeries) highestPoint).addFirst(timeSliderProgress,lowestValue);
=======
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
>>>>>>> .r110
		
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