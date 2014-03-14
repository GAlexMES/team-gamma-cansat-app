package de.teamgamma.cansat.app.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import de.teamgamma.cansat.app.R;
import de.teamgamma.cansat.app.options.Options;

/**
 * 
 * @author Alexander Brennecke
 * ragment that shows the options when a user want to change the path for file export
 * or value storing
 *
 */
public class OptionsExportFragment extends Fragment {
	//initialize some variables
	public static final String ARG_SLIDEMENU_VALUES = "slidemenu_values";
	LinearLayout mLinearLayout;

	public OptionsExportFragment() {
		// Empty constructor
	}

	/**
	 * when the fileBrowser finished and the fragment will shown again the onResume will be called
	 * and update the TextView fields inside the fragment
	 */
	@Override
	public void onResume() {

		final Options options_data = Options.getInstance();
		super.onResume();

		// update the TextViews
		final EditText exportPath = (EditText) mLinearLayout
				.findViewById(R.id.exportPath);
		final EditText storagePath = (EditText) mLinearLayout
				.findViewById(R.id.storagePath);

		boolean browserButton[] = options_data.getBrowsButtons();
		for (int i = 1; i < browserButton.length; i++) {
			if (browserButton[i]) {
				options_data.setSingleBrowsButtons(i, false);
				switch (i) {
				case 1:
					exportPath.setText(Options.getInstance()
							.getTempValueExportPath());
					break;
				case 2:
					storagePath.setText(Options.getInstance()
							.getTempValueStoragePath());
					break;
				}
			} else {
				switch (i) {
				case 1:
					exportPath.setText(Options.getInstance().getValueExportPath());
					break;
				case 2:
					storagePath.setText(Options.getInstance().getValueStoragePath());
					break;
				}
			}
		}
	}
	
	/**
	 * called when the fragment is shown the first time
	 */

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		// initialized variables and create object out of fragment xml objects
		final Options options_data = Options.getInstance();
		mLinearLayout = (LinearLayout) inflater.inflate(
				R.layout.fragment_options_export, container, false);
		Button button_save = (Button) mLinearLayout
				.findViewById(R.id.button_save);
		Button button_browser_export = (Button) mLinearLayout
				.findViewById(R.id.button_browser_export);
		Button button_browser_storage = (Button) mLinearLayout
				.findViewById(R.id.button_browser_storage);
		final EditText exportPath = (EditText) mLinearLayout
				.findViewById(R.id.exportPath);
		final EditText storagePath = (EditText) mLinearLayout
				.findViewById(R.id.storagePath);
		
		// set text to TextViews
		exportPath.setText(options_data.getValueExportPath());
		storagePath.setText(options_data.getValueStoragePath());
		
		//called when the save button was clicked
		button_save.setOnClickListener(new OnClickListener() {
			/**
			 * gives the options to the option class
			 */
			@Override
			public void onClick(View v) {
				options_data
						.setValueExportPath(exportPath.getText().toString());
				options_data.setValueStoragePath(storagePath.getText()
						.toString());
			}

		});

		//called when the browser button was clicked
		button_browser_export.setOnClickListener(new OnClickListener() {
			/**
			 * opens a new activity with the file browser
			 */
			@Override
			public void onClick(View v) {
				options_data.setSingleBrowsButtons(1, true);
				Intent intent = new Intent(
						getActivity(),
						de.teamgamma.cansat.app.filebrowser.AndroidExplorer.class);
				// starts the activity
				startActivity(intent);
			}
		});
		
		// //called when the browser button was clicked
		button_browser_storage.setOnClickListener(new OnClickListener() {
			/**
			 * opens a new activity with the file browser
			 */
			@Override
			public void onClick(View v) {
				options_data.setSingleBrowsButtons(2, true);
				Intent intent = new Intent(
						getActivity(),
						de.teamgamma.cansat.app.filebrowser.AndroidExplorer.class);
				// starts the activity
				startActivity(intent);
			}
		});

		return mLinearLayout;
	}
}