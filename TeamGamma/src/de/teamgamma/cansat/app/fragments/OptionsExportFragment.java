package de.teamgamma.cansat.app.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import de.teamgamma.cansat.app.R;
import de.teamgamma.cansat.app.options.Options;

public class OptionsExportFragment extends Fragment {
    public static final String ARG_SLIDEMENU_VALUES = "slidemenu_values";

    public OptionsExportFragment() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	final Options options_data = Options.getInstance();
     	final LinearLayout mLinearLayout = (LinearLayout) inflater.inflate(R.layout.fragment_options_export,
                 container, false);
     	Button button_save = (Button)mLinearLayout.findViewById(R.id.button_save);
     	final EditText exportPath = (EditText) mLinearLayout.findViewById(R.id.exportPath);
     	final EditText storagePath = (EditText) mLinearLayout.findViewById(R.id.storagePath);
     	exportPath.setText(options_data.getValueExportPath());
     	storagePath.setText(options_data.getValueStoragePath());
     	button_save.setOnClickListener(new OnClickListener() {
	        @Override
	        public void onClick(View v) {
	        	options_data.setValueExportPath(exportPath.getText().toString());
	        	options_data.setValueStoragePath(storagePath.getText().toString());
	        }
	});

        return mLinearLayout;
    }
    
    
}