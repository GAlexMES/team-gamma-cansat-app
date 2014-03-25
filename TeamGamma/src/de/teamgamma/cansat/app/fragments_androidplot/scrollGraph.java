package de.teamgamma.cansat.app.fragments_androidplot;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import de.teamgamma.cansat.app.R;
import de.teamgamma.cansat.app.data.constantValues;
import de.teamgamma.cansat.app.options.Options;
import android.app.Fragment;

import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.FloatMath;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.androidplot.xy.BoundaryMode;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.LineAndPointRenderer;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;

public class scrollGraph extends Fragment implements OnTouchListener {
	
	
    private XYPlot mySimpleXYPlot;
    private SimpleXYSeries mySeries;
    private PointF minXY;
    private PointF maxXY;
    private LinearLayout mLinearLayout;
 
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		// LinearLayout object, of the androitplot_xyplot.xml fragment
		mLinearLayout = (LinearLayout) inflater.inflate(R.layout.androidplot_xyplot, container, false);
		
        mySimpleXYPlot = (XYPlot) mLinearLayout.findViewById(R.id.simpleXYPlot);
        mySimpleXYPlot.setOnTouchListener(this);
 
        //Plot layout configurations
        mySimpleXYPlot.getGraphWidget().setTicksPerRangeLabel(1);
        mySimpleXYPlot.getGraphWidget().setTicksPerDomainLabel(1);
        mySimpleXYPlot.getGraphWidget().setRangeValueFormat(
                new DecimalFormat("#####.##"));
        mySimpleXYPlot.getGraphWidget().setDomainValueFormat(
                new DecimalFormat("#####.##"));
        mySimpleXYPlot.getGraphWidget().setRangeLabelWidth(25);
        mySimpleXYPlot.setRangeLabel("");
        mySimpleXYPlot.setDomainLabel("");
       
 
        //Creation of the series
        Vector<Double> vector=new Vector<Double>();
        for (double x=0.0;x<Math.PI*5;x+=Math.PI/20){
            vector.add(x);
            vector.add(Math.sin(x));
        }
        
        Number[] seriesNumbers = {1,5,7,8,9,5,6,8,7,7,1,5,7,8,9,5,6,8,7,7,1,5,7,8,9,5,6,8,7,7,1,5,7,8,9,5,6,8,7,7};
		mySeries = new SimpleXYSeries(Arrays.asList(seriesNumbers ), 
				SimpleXYSeries.ArrayFormat.XY_VALS_INTERLEAVED, 
				"");
 
		LineAndPointFormatter series1Format = new LineAndPointFormatter(
				constantValues.selectableColors[Options.getInstance().getSelectedColors()[1]], //Line color
				constantValues.selectableColors[Options.getInstance().getSelectedColors()[0]], //Point color
				constantValues.selectableColors[Options.getInstance().getSelectedColors()[2]], //Area color
				null);

		// add a new series' to the xyplot:
		mySimpleXYPlot.addSeries(mySeries, series1Format);
 
        mySimpleXYPlot.redraw();
 
        //Set of internal variables for keeping track of the boundaries
        mySimpleXYPlot.calculateMinMaxVals();
        minXY=new PointF(mySimpleXYPlot.getCalculatedMinX().floatValue(),mySimpleXYPlot.getCalculatedMinY().floatValue());
        maxXY=new PointF(mySimpleXYPlot.getCalculatedMaxX().floatValue(),mySimpleXYPlot.getCalculatedMaxY().floatValue());
        return mLinearLayout;
    }
 
    // Definition of the touch states
    static final int NONE = 0;
    static final int ONE_FINGER_DRAG = 1;
    static final int TWO_FINGERS_DRAG = 2;
    int mode = NONE;
 
    PointF firstFinger;
    float lastScrolling;
    float distBetweenFingers;
    float lastZooming;
 
    @Override
    public boolean onTouch(View arg0, MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
        case MotionEvent.ACTION_DOWN: // Start gesture
            firstFinger = new PointF(event.getX(), event.getY());
            mode = ONE_FINGER_DRAG;
            break;
        case MotionEvent.ACTION_UP:
        case MotionEvent.ACTION_POINTER_UP:
            //When the gesture ends, a thread is created to give inertia to the scrolling and zoom
            Timer t = new Timer();
                t.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        while(Math.abs(lastScrolling)>1f || Math.abs(lastZooming-1)<1.01){
                        lastScrolling*=.8;
                        scroll(lastScrolling);
                        lastZooming+=(1-lastZooming)*.2;
                        zoom(lastZooming);
                        mySimpleXYPlot.setDomainBoundaries(minXY.x, maxXY.x, BoundaryMode.AUTO);
                        mySimpleXYPlot.redraw();
                       
                        // the thread lives until the scrolling and zooming are imperceptible
                    }
                    }
                }, 0);
 
        case MotionEvent.ACTION_POINTER_DOWN: // second finger
            distBetweenFingers = spacing(event);
            // the distance check is done to avoid false alarms
            if (distBetweenFingers > 5f) {
                mode = TWO_FINGERS_DRAG;
            }
            break;
        case MotionEvent.ACTION_MOVE:
            if (mode == ONE_FINGER_DRAG) {
                PointF oldFirstFinger=firstFinger;
                firstFinger=new PointF(event.getX(), event.getY());
                lastScrolling=oldFirstFinger.x-firstFinger.x;
                scroll(lastScrolling);
                lastZooming=(firstFinger.y-oldFirstFinger.y)/mySimpleXYPlot.getHeight();
                if (lastZooming<0)
                    lastZooming=1/(1-lastZooming);
                else
                    lastZooming+=1;
                zoom(lastZooming);
                mySimpleXYPlot.setDomainBoundaries(minXY.x, maxXY.x, BoundaryMode.AUTO);
                mySimpleXYPlot.redraw();
 
            } else if (mode == TWO_FINGERS_DRAG) {
                float oldDist =distBetweenFingers;
                distBetweenFingers=spacing(event);
                lastZooming=oldDist/distBetweenFingers;
                zoom(lastZooming);
                mySimpleXYPlot.setDomainBoundaries(minXY.x, maxXY.x, BoundaryMode.AUTO);
                mySimpleXYPlot.redraw();
            }
            break;
        }
        return true;
    }
 
    private void zoom(float scale) {
        float domainSpan = maxXY.x    - minXY.x;
        float domainMidPoint = maxXY.x        - domainSpan / 2.0f;
        float offset = domainSpan * scale / 2.0f;
        minXY.x=domainMidPoint- offset;
        maxXY.x=domainMidPoint+offset;
    }
 
    private void scroll(float pan) {
        float domainSpan = maxXY.x    - minXY.x;
        float step = domainSpan / mySimpleXYPlot.getWidth();
        float offset = pan * step;
        minXY.x+= offset;
        maxXY.x+= offset;
    }
 
    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return FloatMath.sqrt(x * x + y * y);
    }
}