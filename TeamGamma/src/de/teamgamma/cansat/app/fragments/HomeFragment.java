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

public class HomeFragment extends Fragment {
    public static final String ARG_SLIDEMENU_VALUES = "slidemenu_values";

    public HomeFragment() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
     	final LinearLayout mLinearLayout = (LinearLayout) inflater.inflate(R.layout.fragment_home,
                 container, false);
    	

        return mLinearLayout;
    }
   
    
}