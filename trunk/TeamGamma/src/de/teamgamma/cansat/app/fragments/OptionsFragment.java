package de.teamgamma.cansat.app.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import de.teamgamma.cansat.app.R;

public class OptionsFragment extends Fragment {
    public static final String ARG_SLIDEMENU_VALUES = "slidemenu_values";

    public OptionsFragment() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
     	final LinearLayout mLinearLayout = (LinearLayout) inflater.inflate(R.layout.fargment_options,
                 container, false);        	
    	Button buttonConnection = (Button) mLinearLayout.findViewById(R.id.connection);
    	Button buttonExport = (Button) mLinearLayout.findViewById(R.id.export);
    	buttonConnection.setOnClickListener(new OnClickListener() {
    	        @Override
    	        public void onClick(View v) {
    	        	selectedOption(0);        	        	
    	        }
    	});
    	buttonExport.setOnClickListener(new OnClickListener() {
	        @Override
	        public void onClick(View v) {
	        	selectedOption(1);        	        	
	        }
	});
    	

        return mLinearLayout;
    }
    
	private void selectedOption(int position) {
    	Fragment fragment = null;
        // update the main content by replacing fragments
    	switch (position){
    	case 0:  fragment = new OptionsConnectionFragment(); break;
    	case 1: fragment = new OptionsExportFragment(); break;
    	}    	
        Bundle args = new Bundle();
        args.putInt(OptionsFragment.ARG_SLIDEMENU_VALUES, position);
        fragment.setArguments(args);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

    }
    
    
}