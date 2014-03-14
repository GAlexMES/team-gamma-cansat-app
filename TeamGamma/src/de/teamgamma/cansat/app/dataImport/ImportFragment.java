package de.teamgamma.cansat.app.dataImport;

import de.teamgamma.cansat.app.R;
import de.teamgamma.cansat.app.fragments.HomeFragment;
import de.teamgamma.cansat.app.options.Options;
import de.teamgamma.cansat.app.savedata.Read;
import android.app.Fragment;
import android.app.FragmentTransaction;
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

/**
 * 
 * 
 * @author Alexander Brennecke
 *
 *shows a fragment to select an file which should be displayed in a chart
 */

public class ImportFragment extends Fragment {
	
	//initialize a few importend variables
	Options options_data = Options.getInstance();
	String importFilepath;
	LinearLayout mLinearLayout;
	
	
	/**
	 * when the fileBrowser finished and the fragment will shown again the onResume will be called
	 * and update the TextView fields inside the fragment
	 */
	@Override
	public void onResume(){		
		final Options options_data = Options.getInstance();
		super.onResume();
		final EditText filePath = (EditText) mLinearLayout.findViewById(R.id.filePath);		
		filePath.setText(options_data.getTemporaryBrowserResultPath());
		importFilepath = options_data.getTemporaryBrowserResultPath();
		
	}
	
	/**
	 * will be called when a new ImportedFragment object is generated
	 */
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		//initialize a few importend variables
     	mLinearLayout = (LinearLayout) inflater.inflate(R.layout.fragment_import,container, false);
     	Button buttonImport = (Button) mLinearLayout.findViewById(R.id.button_import);
     	Button buttonBrowser = (Button) mLinearLayout.findViewById(R.id.button_browser);
     	
     	// ActionListener to open the fileBrowser
     	buttonBrowser.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(
						getActivity(),
						de.teamgamma.cansat.app.filebrowser.AndroidExplorer.class);
				startActivity(intent);
				
			}
		});
     	
     	// Button to import the file into a chart and display this chart
     	buttonImport.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
		     	Read reader = Read.getInstance();   		     	
		    ImportedFiles.getInstance().setLatestFile(reader.getValuefromFile(importFilepath));
		    // Create new fragment and transaction
			Fragment newFragment = new ImportSimpleXYChart();
			FragmentTransaction transaction = getFragmentManager().beginTransaction();
			
			transaction.replace(R.id.content_frame, newFragment);
			transaction.addToBackStack(null);

			// Commit the transaction
			transaction.commit();

				
			}
		});     	
     return mLinearLayout;	
	}

}
