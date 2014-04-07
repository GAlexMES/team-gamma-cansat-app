package de.teamgamma.cansat.app.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import de.teamgamma.cansat.app.R;
import de.teamgamma.cansat.app.options.ConnectionOptions;
import de.teamgamma.cansat.app.options.KindOfOption;
import de.teamgamma.cansat.app.options.Options;
import de.teamgamma.cansat.app.socket.ServerConnection;

/**
 * 
 * @author Alexander Brennecke
 * 
 *         fragment that shows the options when a user want to change his was to
 *         connect to an database or an java socket server
 */

public class OptionsConnectionFragment extends Fragment {
	public static final String ARG_SLIDEMENU_VALUES = "slidemenu_values";
	private ServerConnection connect = null;

	public OptionsConnectionFragment() {
		// Empty constructor required for fragment subclass
	}

	/**
	 * 
	 * called when an object of this class is initialized
	 */

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// initialized objects with objects out of the fragment xml file
		final Options options = Options.getInstance();
		final LinearLayout mLinearLayout = (LinearLayout) inflater.inflate(
				R.layout.fragment_options_connection, container, false);
		final EditText java_socket_ipAdress = (EditText) mLinearLayout
				.findViewById(R.id.editJavaSocketIPAdress);
		final EditText java_socket_port = (EditText) mLinearLayout
				.findViewById(R.id.editJavaSocketPort);
		final RadioGroup itemTypeGroup = (RadioGroup) mLinearLayout
				.findViewById(R.id.Group);
		Button connectAndSave = (Button) mLinearLayout
				.findViewById(R.id.button_connect);
		Button options_save = (Button) mLinearLayout
				.findViewById(R.id.button_save);
		final RadioButton radio_database = (RadioButton) mLinearLayout
				.findViewById(R.id.radioButtonDatabase);
		final RadioButton radio_java_socket = (RadioButton) mLinearLayout
				.findViewById(R.id.radioButtonJavaSocket);
		// shows the latest chosen values
		java_socket_ipAdress.setText(options.getOption(KindOfOption.CONNECTION.ordinal(), ConnectionOptions.JAVASOCKETIP));
		java_socket_port.setText(options.getOption(KindOfOption.CONNECTION.ordinal(), ConnectionOptions.JAVASOCKETPORT));
		itemTypeGroup.clearCheck();

		// set the latest chosen radio button true
		if (Integer.valueOf(options.getOption(KindOfOption.CONNECTION.ordinal(), ConnectionOptions.JAVASOCKETMETHOD)) == R.id.radioButtonDatabase) {
			radio_database.setChecked(true);
			radio_java_socket.setChecked(false);
		} else if (Integer.valueOf(options.getOption(KindOfOption.CONNECTION.ordinal(), ConnectionOptions.JAVASOCKETMETHOD)) == R.id.radioButtonJavaSocket) {
			radio_database.setChecked(false);
			radio_java_socket.setChecked(true);

		}
		// item group for the two radio button, which displays the ways to
		// connect, to switch them
		itemTypeGroup
				.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(RadioGroup group, int checkedID) {
						itemTypeGroup.check(checkedID);
						if (checkedID == R.id.radioButtonJavaSocket) {
							radio_database.setChecked(false);
						} else if (checkedID == R.id.radioButtonDatabase) {
							radio_java_socket.setChecked(false);
						}

					}
				});

		// called when the save button was clicked
		// gives the information to the option class
		options_save.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				options.setOption(KindOfOption.CONNECTION.ordinal(), ConnectionOptions.JAVASOCKETIP,java_socket_ipAdress
						.getText().toString());
				options.setOption(KindOfOption.CONNECTION.ordinal(), ConnectionOptions.JAVASOCKETPORT,java_socket_port
						.getText().toString());
				options.setOption(KindOfOption.CONNECTION.ordinal(), ConnectionOptions.JAVASOCKETMETHOD,itemTypeGroup.getCheckedRadioButtonId());
				// a toast will show that the data was saved
				Context context = mLinearLayout.getContext();
				CharSequence text = "Saved";
				int duration = Toast.LENGTH_SHORT;
				Toast toast = Toast.makeText(context, text, duration);
				toast.show();
			}
		});

		
		//called when the connectAndSave button was clicked
		//gives the information to the option class
		// creates an new serverConnection object 
		connectAndSave.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				options.setOption(KindOfOption.CONNECTION.ordinal(), ConnectionOptions.JAVASOCKETIP,java_socket_ipAdress
						.getText().toString());
				options.setOption(KindOfOption.CONNECTION.ordinal(), ConnectionOptions.JAVASOCKETPORT,java_socket_port
						.getText().toString());
				options.setOption(KindOfOption.CONNECTION.ordinal(), ConnectionOptions.JAVASOCKETMETHOD,itemTypeGroup.getCheckedRadioButtonId());
				Context context = mLinearLayout.getContext();
				CharSequence text = "Saved";
				int duration = Toast.LENGTH_SHORT;
				Toast toast = Toast.makeText(context, text, duration);
				toast.show();
				connect = new ServerConnection();
			}
		});

		return mLinearLayout;
	}

}