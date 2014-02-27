package de.teamgamma.cansat.app.fragments;

import de.teamgamma.cansat.app.R;
import de.teamgamma.cansat.app.options.Options;
import de.teamgamma.cansat.app.savedata.Read;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class ImportFragment extends Fragment {
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
     	final LinearLayout mLinearLayout = (LinearLayout) inflater.inflate(R.layout.fragment_import,container, false);
     	
     	Read reader = Read.getInstance();
     	reader.
     	
     	
     	
     return mLinearLayout;	
	}

}
