package de.teamgamma.cansat.app.fragments.androidplot;

import java.util.Arrays;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.androidplot.xy.BarFormatter;
import com.androidplot.xy.BarRenderer;
import com.androidplot.xy.BoundaryMode;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;

import de.teamgamma.cansat.app.R;

 
// Monitor the phone's orientation sensor and plot the resulting azimuth pitch and roll values.
// See: http://developer.android.com/reference/android/hardware/SensorEvent.html
public class realtime_xy_example extends Fragment
{
	private XYPlot aprLevelsPlot = null;
    private XYPlot aprHistoryPlot = null;
    private SimpleXYSeries aprLevelsSeries = null;
    private androidPlot_generateValues generator = new androidPlot_generateValues();

 
    /** Called when the activity is first created. */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	final LinearLayout mLinearLayout = (LinearLayout) inflater.inflate(R.layout.androidplot_realtime_dynamic_plot,
                container, false);
        
 
        // setup the APR Levels plot:
        Number[] series1Numbers = {1, 8, 5, 2, 7, 4};
        aprLevelsPlot = (XYPlot)mLinearLayout.findViewById(R.id.aprLevelsPlot); 
        aprLevelsSeries = new SimpleXYSeries(Arrays.asList(series1Numbers),SimpleXYSeries.ArrayFormat.Y_VALS_ONLY,"Graph Beschriftung");
        aprLevelsSeries.useImplicitXVals();
        aprLevelsPlot.addSeries(aprLevelsSeries,new BarFormatter(Color.argb(100, 0, 200, 0), Color.rgb(0, 80, 0)));
        aprLevelsPlot.setDomainStepValue(3);
        aprLevelsPlot.setTicksPerRangeLabel(3);
 
        // per the android documentation, the minimum and maximum readings we can get from
        // any of the orientation sensors is -180 and 359 respectively so we will fix our plot's
        // boundaries to those values.  If we did not do this, the plot would auto-range which
        // can be visually confusing in the case of dynamic plots.
        aprLevelsPlot.setRangeBoundaries(-180, 359, BoundaryMode.FIXED);
 
        // use our custom domain value formatter:
       // aprLevelsPlot.setDomainValueFormat(new APRIndexFormat());
 
        // update our domain and range axis labels:
        aprLevelsPlot.setDomainLabel("Field One");
        aprLevelsPlot.getDomainLabelWidget().pack();
        aprLevelsPlot.setRangeLabel("Y- BEschriftung");
        aprLevelsPlot.getRangeLabelWidget().pack();
        aprLevelsPlot.setGridPadding(15, 0, 15, 0);
 
        // setup the APR History plot:
        aprHistoryPlot = (XYPlot) mLinearLayout.findViewById(R.id.aprHistoryPlot);
        aprHistoryPlot.addSeries(aprLevelsSeries, new BarFormatter(Color.argb(100, 0, 200, 0), Color.rgb(0, 80, 0)));
        aprHistoryPlot.setRangeBoundaries(-180, 359, BoundaryMode.FIXED);
        aprHistoryPlot.setDomainBoundaries(0, 30, BoundaryMode.FIXED);
        aprHistoryPlot.setDomainStepValue(5);
        aprHistoryPlot.setTicksPerRangeLabel(3);
        aprHistoryPlot.setDomainLabel("Field Two");
        aprHistoryPlot.getDomainLabelWidget().pack();
        aprHistoryPlot.setRangeLabel("y-Beschriftung");
        aprHistoryPlot.getRangeLabelWidget().pack();
 
       
 
 
        // get a ref to the BarRenderer so we can make some changes to it:
        BarRenderer barRenderer = (BarRenderer) aprLevelsPlot.getRenderer(BarRenderer.class);
        if(barRenderer != null) {
            // make our bars a little thicker than the default so they can be seen better:
            barRenderer.setBarWidth(25);
        }
		return mLinearLayout;
 
        // register for orientation sensor events:
        
}}