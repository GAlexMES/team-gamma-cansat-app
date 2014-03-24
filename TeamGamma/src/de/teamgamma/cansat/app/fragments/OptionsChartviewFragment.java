package de.teamgamma.cansat.app.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import de.teamgamma.cansat.app.R;
import de.teamgamma.cansat.app.data.constantValues;
import de.teamgamma.cansat.app.options.Options;

/**
 * 
 * @author Alexander Brennecke
 * ragment that shows the options when a user want to change the path for file export
 * or value storing
 *
 */
public class OptionsChartviewFragment extends Fragment implements OnSeekBarChangeListener {
	//initialize some variables
	public static final String ARG_SLIDEMENU_VALUES = "slidemenu_values";
	LinearLayout mLinearLayout;
	TextView currentPointColor,currentLineColor,currentAreaColor;
	SeekBar pointSeekBar,lineSeekBar,areaSeekBar;
	SeekBar[] seekBarArray = {pointSeekBar,lineSeekBar,areaSeekBar};
	int[] seekBarValue = new int[3];
	int[] seekBarIdArray = {R.id.pointSeekBar,R.id.lineSeekBar,R.id.areaSeekBar};
	TextView[] textViewArray = {currentPointColor,currentLineColor,currentAreaColor};
	int[] textViewIdArray = {R.id.selectedPointColor,R.id.selectedLinesColor,R.id.selectedAreaColor};

	public OptionsChartviewFragment() {
		// Empty constructor
	}

	
	/**
	 * called when the fragment is shown the first time
	 */

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		// initialized variables and create object out of fragment xml objects
		final Options options = Options.getInstance();
		mLinearLayout = (LinearLayout) inflater.inflate(
				R.layout.fragment_options_chartview, container, false);
		Button button_save = (Button) mLinearLayout
				.findViewById(R.id.button_save);
		final EditText numberOfShownPoints = (EditText)mLinearLayout.findViewById(R.id.selectedNumber);
		numberOfShownPoints.setText(options.getNumberOfValues());
		for(int i = 0; i<seekBarIdArray.length;i++){
			seekBarArray[i] = (SeekBar)mLinearLayout.findViewById(seekBarIdArray[i]);
			seekBarArray[i].setOnSeekBarChangeListener(this);
			textViewArray[i]= (TextView)mLinearLayout.findViewById(textViewIdArray[i]);
		}
		//called when the save button was clicked
		button_save.setOnClickListener(new OnClickListener() {
			/**
			 * gives the options to the option class
			 */
			@Override
			public void onClick(View v) {
				options.setSelectedColors(seekBarValue);
				options.setNumbersOfValues(Integer.valueOf(numberOfShownPoints.getText().toString()));
			}

		});

		return mLinearLayout;
	}
	
	private void updateColor(){
		for(int i = 0; i<seekBarIdArray.length;i++){
			seekBarValue[i]=seekBarArray[i].getProgress();
			textViewArray[i].setBackgroundColor(constantValues.selectableColors[seekBarValue[i]]);
		}

		
	}


	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		updateColor();

		
	}


	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}
}