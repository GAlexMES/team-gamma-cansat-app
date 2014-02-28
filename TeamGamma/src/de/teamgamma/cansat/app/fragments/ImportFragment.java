package de.teamgamma.cansat.app.fragments;

import de.teamgamma.cansat.app.R;
import de.teamgamma.cansat.app.options.Options;
import de.teamgamma.cansat.app.savedata.Read;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

public class ImportFragment extends Fragment {
	Options options_data = Options.getInstance();
	String importFilepath;
	LinearLayout mLinearLayout;
	
	@Override
	public void onResume(){
		
		final Options options_data = Options.getInstance();
		super.onResume();
		final EditText filePath = (EditText) mLinearLayout.findViewById(R.id.filePath);		
		filePath.setText(options_data.getTemporaryBrowserResultPath());
		importFilepath = options_data.getTemporaryBrowserResultPath();
		
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
     	mLinearLayout = (LinearLayout) inflater.inflate(R.layout.fragment_import,container, false);
     	final EditText filepath = (EditText)mLinearLayout.findViewById(R.id.filePath);
     	Button buttonImport = (Button) mLinearLayout.findViewById(R.id.button_import);
     	Button buttonBrowser = (Button) mLinearLayout.findViewById(R.id.button_browser);
     	
     	buttonBrowser.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(
						getActivity(),
						de.teamgamma.cansat.app.filebrowser.AndroidExplorer.class);
				startActivity(intent);
				
			}
		});
     	
     	buttonImport.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
		     	Read reader = Read.getInstance();   
		     	
		    Log.d("output", String.valueOf(reader.getCo2(importFilepath)[0][0]));
				
			}
		});
     	

     	
     	
     	
     return mLinearLayout;	
	}

}
