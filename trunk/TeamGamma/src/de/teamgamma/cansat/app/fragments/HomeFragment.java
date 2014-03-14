package de.teamgamma.cansat.app.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import de.teamgamma.cansat.app.R;

/**
 * 
 * 
 * @author Alexander Brennecke
 *
 *shows the home screen when an object is initialized
 *initialized when app opened
 *
 */

public class HomeFragment extends Fragment {
    public static final String ARG_SLIDEMENU_VALUES = "slidemenu_values";

    public HomeFragment() {
        // Empty constructor
    }

    /**
     * 
     * called when an object of this class was created
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
     	final LinearLayout mLinearLayout = (LinearLayout) inflater.inflate(R.layout.fragment_home,
                 container, false);
    	

        return mLinearLayout;
    }
   
    
}