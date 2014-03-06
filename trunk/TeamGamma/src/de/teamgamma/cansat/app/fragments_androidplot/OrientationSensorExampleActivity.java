package de.teamgamma.cansat.app.fragments_androidplot;

/*
* Copyright 2012 AndroidPlot.com
*
*    Licensed under the Apache License, Version 2.0 (the "License");
*    you may not use this file except in compliance with the License.
*    You may obtain a copy of the License at
*
*        http://www.apache.org/licenses/LICENSE-2.0
*
*    Unless required by applicable law or agreed to in writing, software
*    distributed under the License is distributed on an "AS IS" BASIS,
*    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*    See the License for the specific language governing permissions and
*    limitations under the License.
*/
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.Arrays;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.androidplot.util.PlotStatistics;
import com.androidplot.xy.BarFormatter;
import com.androidplot.xy.BarRenderer;
import com.androidplot.xy.BoundaryMode;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;

import de.teamgamma.cansat.app.R;
 
// Monitor the phone's orientation sensor and plot the resulting azimuth pitch and roll values.
// See: http://developer.android.com/reference/android/hardware/SensorEvent.html
public class OrientationSensorExampleActivity extends Fragment implements SensorEventListener
{
 
    /**
     * A simple formatter to convert bar indexes into sensor names.
     */
    private class APRIndexFormat extends Format {
        @Override
        public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
            Number num = (Number) obj;
 
            // using num.intValue() will floor the value, so we add 0.5 to round instead:
            int roundNum = (int) (num.floatValue() + 0.5f);
            switch(roundNum) {
                case 0:
                    toAppendTo.append("Azimuth");
                    break;
                case 1:
                    toAppendTo.append("Pitch");
                    break;
                case 2:
                    toAppendTo.append("Roll");
                    break;
                default:
                    toAppendTo.append("Unknown");
            }
            return toAppendTo;
        }
 
        @Override
        public Object parseObject(String source, ParsePosition pos) {
            return null;  // We don't use this so just return null for now.
        }
    }
 
             // number of points to plot in history
    private SensorManager sensorMgr = null;
    private Sensor orSensor = null;
 
    private XYPlot aprLevelsPlot = null;
 
    
 
    private CheckBox hwAcceleratedCb;
    private CheckBox showFpsCb;
    private SimpleXYSeries aprLevelsSeries = null;
 

    /** Called when the activity is first created. */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final LinearLayout mLinearLayout = (LinearLayout) inflater.inflate(R.layout.androidplot_realtime_dynamic_plot,
                container, false);
 
        // setup the APR Levels plot:
        aprLevelsPlot = (XYPlot)mLinearLayout.findViewById(R.id.aprLevelsPlot);
 
        aprLevelsSeries = new SimpleXYSeries("APR Levels");
        aprLevelsSeries.useImplicitXVals();
        aprLevelsPlot.addSeries(aprLevelsSeries,
                new BarFormatter(Color.argb(100, 0, 200, 0), Color.rgb(0, 80, 0)));
        aprLevelsPlot.setDomainStepValue(3);
        aprLevelsPlot.setTicksPerRangeLabel(3);
 
        // per the android documentation, the minimum and maximum readings we can get from
        // any of the orientation sensors is -180 and 359 respectively so we will fix our plot's
        // boundaries to those values.  If we did not do this, the plot would auto-range which
        // can be visually confusing in the case of dynamic plots.
        aprLevelsPlot.setRangeBoundaries(-180, 359, BoundaryMode.FIXED);
 
        // use our custom domain value formatter:
        aprLevelsPlot.setDomainValueFormat(new APRIndexFormat());
 
        // update our domain and range axis labels:
        aprLevelsPlot.setDomainLabel("Axis");
        aprLevelsPlot.getDomainLabelWidget().pack();
        aprLevelsPlot.setRangeLabel("Angle (Degs)");
        aprLevelsPlot.getRangeLabelWidget().pack();
        aprLevelsPlot.setGridPadding(15, 0, 15, 0);
 
        // setup the APR History plot:
        
        // setup checkboxes:
        hwAcceleratedCb = (CheckBox)mLinearLayout.findViewById(R.id.hwAccelerationCb);
        final PlotStatistics levelStats = new PlotStatistics(1000, false);
        final PlotStatistics histStats = new PlotStatistics(1000, false);
 
        aprLevelsPlot.addListener(levelStats);
        hwAcceleratedCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    aprLevelsPlot.setLayerType(View.LAYER_TYPE_NONE, null);
               
                } else {
                    aprLevelsPlot.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
                   
                    
                }
            }
        });

 
        // get a ref to the BarRenderer so we can make some changes to it:
        BarRenderer barRenderer = (BarRenderer) aprLevelsPlot.getRenderer(BarRenderer.class);
        if(barRenderer != null) {
            // make our bars a little thicker than the default so they can be seen better:
            barRenderer.setBarWidth(25);
        }
 
        // register for orientation sensor events:
        sensorMgr = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        for (Sensor sensor : sensorMgr.getSensorList(Sensor.TYPE_ORIENTATION)) {
            if (sensor.getType() == Sensor.TYPE_ORIENTATION) {
                orSensor = sensor;
            }
        }
 
        // if we can't access the orientation sensor then exit:
        if (orSensor == null) {
            System.out.println("Failed to attach to orSensor.");
            cleanup();
        }
 
        sensorMgr.registerListener(this, orSensor, SensorManager.SENSOR_DELAY_UI);
		return mLinearLayout;
 
    }
 
    private void cleanup() {
        // aunregister with the orientation sensor before exiting:
        sensorMgr.unregisterListener(this);
        //finish();
    }
 
    // Called whenever a new orSensor reading is taken.
    @Override
    public synchronized void onSensorChanged(SensorEvent sensorEvent) {
 
        // update instantaneous data:
        Number[] series1Numbers = {sensorEvent.values[0], sensorEvent.values[1], sensorEvent.values[2]};
        aprLevelsSeries.setModel(Arrays.asList(series1Numbers), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY);
 
        // get rid the oldest sample in history:
       
        // add the latest history sample:

 
        // redraw the Plots:
        aprLevelsPlot.redraw();
      
    }
 
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        // Not interested in this event
    }
}