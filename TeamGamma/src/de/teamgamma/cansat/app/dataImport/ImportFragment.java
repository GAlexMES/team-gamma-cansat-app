package de.teamgamma.cansat.app.dataImport;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import de.teamgamma.cansat.app.R;
import de.teamgamma.cansat.app.fileoperations.Read;
import de.teamgamma.cansat.app.fragments_androidplot.ImportSimpleXYChart;
import de.teamgamma.cansat.app.options.KindOfOption;
import de.teamgamma.cansat.app.options.PathOptions;
import de.teamgamma.cansat.app.options.Options;

/**
 * 
 * 
 * @author Alexander Brennecke
 * 
 *         shows a fragment to select an file which should be displayed in a
 *         chart
 */

public class ImportFragment extends Fragment {

	// initialize a few importend variables
	String importFilepath;
	LinearLayout mLinearLayout;

	/**
	 * when the fileBrowser finished and the fragment will shown again the
	 * onResume will be called and update the TextView fields inside the
	 * fragment
	 */
	@Override
	public void onResume() {
		final Options option = Options.getInstance();
		super.onResume();
		final EditText filePath = (EditText) mLinearLayout
				.findViewById(R.id.filePath);
		importFilepath = option.getOption(KindOfOption.PATH.ordinal(),PathOptions.TEMBROWSERRESULTPATH);
		filePath.setText(importFilepath);
		

	}

	/**
	 * will be called when a new ImportedFragment object is generated
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// initialize a few importend variables
		mLinearLayout = (LinearLayout) inflater.inflate(
				R.layout.fragment_import, container, false);
		Button buttonImport = (Button) mLinearLayout
				.findViewById(R.id.button_import);
		Button buttonBrowser = (Button) mLinearLayout
				.findViewById(R.id.button_browser);

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
				ImportedFiles.getInstance().setLatestFile(
						reader.getValuefromFile(importFilepath));
				
				if (ImportedFiles.getInstance().getLatestFile() == null) {
					Context context = mLinearLayout.getContext();
					CharSequence text = "Wrong file format ";
					int duration = Toast.LENGTH_SHORT;
					Toast toast = Toast.makeText(context, text, duration);
					toast.show();
				}
				else{
					// Create new fragment and transaction
					Fragment newFragment = new ImportSimpleXYChart();
					FragmentTransaction transaction = getFragmentManager()
							.beginTransaction();

					transaction.replace(R.id.content_frame, newFragment);
					transaction.addToBackStack(null);

					// Commit the transaction
					transaction.commit();					
				}
			}
		});
		return mLinearLayout;
	}

}
