package de.teamgamma.cansat.app.fragments;

import java.util.ArrayList;

import android.R.color;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import de.teamgamma.cansat.app.R;
import de.teamgamma.cansat.app.data.constantValues;
import de.teamgamma.cansat.app.database.Database;
import de.teamgamma.cansat.app.database.DatabaseSensornames;
import de.teamgamma.cansat.app.fileoperations.Read;
import de.teamgamma.cansat.app.fragments_androidplot.ImportSimpleXYChart;
import de.teamgamma.cansat.app.fragments_androidplot.RealtimeGraph;
import de.teamgamma.cansat.app.options.ChartViewOptions;
import de.teamgamma.cansat.app.options.ConnectionOptions;
import de.teamgamma.cansat.app.options.KindOfOption;
import de.teamgamma.cansat.app.options.Options;
import de.teamgamma.cansat.app.options.PathOptions;
import de.teamgamma.cansat.app.socket.ServerConnection;
import de.teamgamma.cansat.app.values.Values;

public class DatabaseSensorsFragment extends Fragment {

	// initialize a few importend variables
	LinearLayout mLinearLayout;
	private Options options = Options.getInstance();
	private ArrayList<Button> buttons = new ArrayList<Button>();

	/**
	 * will be called when a new ImportedFragment object is generated
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// initialize a few importend variables
		mLinearLayout = (LinearLayout) inflater.inflate(
				R.layout.fragment_database_sensor, container, false);
		
		
		Database.getInstance().setSensornames();
		while(DatabaseSensornames.getInstance().getSensornames()==null){
		}
			
		for(int i = 0; i < DatabaseSensornames.getInstance().getSensornames().length;i++){
			AddAll(DatabaseSensornames.getInstance().getSensornames()[i],i);
		}
		
		
		return mLinearLayout;
	}
		
	private void AddAll(String text, int id) {	    
	    Button btn = new Button(mLinearLayout.getContext());
	    btn.setBackgroundColor(Color.rgb(51, 181, 229));
	    btn.setText(text); 
	    btn.setId(id);
	    buttons.add(btn);
	    btn.setY(buttons.size()*(btn.getHeight()+10));
	    btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				for(int i = 0; i<buttons.size();i++){
					if(buttons.get(i).getId() == v.getId()){
						//options.setOption(KindOfOption.CHARTVIEW.ordinal(),ChartViewOptions.ACTIVESENSORNAME,constantValues.names[i]);
						// maybe wrong only testing
						options.setOption(KindOfOption.CHARTVIEW.ordinal(),ChartViewOptions.ACTIVESENSORNAME,(String) buttons.get(v.getId()).getText());
						break;
					}
				}
				
				
				Fragment fragment = new ImportSimpleXYChart();
				
				Bundle args = new Bundle();
		        args.putInt(OptionsFragment.ARG_SLIDEMENU_VALUES, v.getId());
		        fragment.setArguments(args);

		        FragmentManager fragmentManager = getFragmentManager();
		        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
		        
		        //Database.getInstance().getData(options.getOption(KindOfOption.CHARTVIEW.ordinal(), ChartViewOptions.ACTIVESENSORNAME), fragment);
		        Database.getInstance().getData(options.getOption(KindOfOption.CHARTVIEW.ordinal(), ChartViewOptions.ACTIVESENSORNAME), fragment);

				
			}
		});
	    mLinearLayout.addView(btn); 
	    }
	
	

}

