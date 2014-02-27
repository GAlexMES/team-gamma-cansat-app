package de.teamgamma.cansat.app.fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
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
import de.teamgamma.cansat.app.options.GenerateOptions;
import de.teamgamma.cansat.app.options.Options;
import de.teamgamma.cansat.app.options.OptionsExport;

public class OptionsSearcherFragment extends Fragment {
	
	private Options options = Options.getInstance();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final LinearLayout mLinearLayout = (LinearLayout) inflater.inflate(
				R.layout.fragment_option_searcher, container, false);
		final EditText optionsPath = (EditText) mLinearLayout
				.findViewById(R.id.editJavaSocketIPAdress);
		Button buttonGenerateOptions = (Button) mLinearLayout
				.findViewById(R.id.button_generate);
		Button buttonOptionsSave = (Button) mLinearLayout
				.findViewById(R.id.button_save);
		Button buttonBrowser = (Button) mLinearLayout
				.findViewById(R.id.button_browser);

		buttonGenerateOptions.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				OptionsExport optionsExport = new OptionsExport();
				GenerateOptions generateOptions = new GenerateOptions();
				optionsExport.writeSingle(generateOptions.getFilepath(),generateOptions.generate());
				// Create new fragment and transaction
				Fragment newFragment = new HomeFragment();
				FragmentTransaction transaction = getFragmentManager().beginTransaction();

				// Replace whatever is in the fragment_container view with this fragment,
				// and add the transaction to the back stack
				transaction.replace(R.id.content_frame, newFragment);
				transaction.addToBackStack(null);

				// Commit the transaction
				transaction.commit();

			}
		});
		buttonOptionsSave.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				options.setOptionsPath(optionsPath.getText().toString());
			}
		});
		buttonBrowser.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(	getActivity(),de.teamgamma.cansat.app.filebrowser.AndroidExplorer.class);
				startActivity(intent);
			}
		});

		return mLinearLayout;

	}
}
