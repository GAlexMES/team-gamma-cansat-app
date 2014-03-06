package de.teamgamma.cansat.app.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;
import de.teamgamma.cansat.app.R;
import de.teamgamma.cansat.app.options.Options;
import de.teamgamma.cansat.app.socket.AndroidClient;
import de.teamgamma.cansat.app.socket.ServerConnection;


public class OptionsConnectionFragment extends Fragment {
	public static final String ARG_SLIDEMENU_VALUES = "slidemenu_values";

	private ServerConnection connect = null;


	public OptionsConnectionFragment() {
		// Empty constructor required for fragment subclasses
	}

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	final Options option_data= Options.getInstance();
    	option_data.getValuesFromFile();
     	final LinearLayout mLinearLayout = (LinearLayout) inflater.inflate(R.layout.fragment_options_connection,container, false);         	
     	final EditText java_socket_ipAdress = (EditText)mLinearLayout.findViewById(R.id.editJavaSocketIPAdress);
     	final EditText java_socket_port = (EditText)mLinearLayout.findViewById(R.id.editJavaSocketPort);
     	final RadioGroup itemTypeGroup = (RadioGroup)mLinearLayout.findViewById(R.id.Group);
     	Button connectAndSave = (Button) mLinearLayout.findViewById(R.id.button_connect);
     	Button options_save = (Button)mLinearLayout.findViewById(R.id.button_save);
     	java_socket_ipAdress.setText(option_data.getJavaSocketIpAdress());
     	java_socket_port.setText(option_data.getJavaSocketPort());
     	itemTypeGroup.clearCheck();
    	final RadioButton radio_database = (RadioButton)mLinearLayout.findViewById(R.id.radioButtonDatabase);
    	final RadioButton radio_java_socket = (RadioButton)mLinearLayout.findViewById(R.id.radioButtonJavaSocket);
    	
    	if(option_data.getMethodToConnect() == R.id.radioButtonDatabase){
    		radio_database.setChecked(true);
    		radio_java_socket.setChecked(false);
    	}
    	else if (option_data.getMethodToConnect()==R.id.radioButtonJavaSocket){
    		radio_database.setChecked(false);
    		radio_java_socket.setChecked(true);
    	}
     	
     	itemTypeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
            	

            	itemTypeGroup .check(checkedId);
                if (checkedId == R.id.radioButtonJavaSocket) {                	
                	radio_database.setChecked(false);
                } else if (checkedId == R.id.radioButtonDatabase) {
                	radio_java_socket.setChecked(false);                	
                   
                }
            }

        });
     	
     	
     	options_save.setOnClickListener(new OnClickListener() {
	        @Override
	        public void onClick(View v) {
	        	option_data.setAllConnectionOptions(java_socket_ipAdress.getText().toString(), java_socket_port.getText().toString(),itemTypeGroup.getCheckedRadioButtonId());        	
	        	Context context = mLinearLayout.getContext();
	        	CharSequence text = "Saved";
	        	int duration = Toast.LENGTH_SHORT;
	        	Toast toast = Toast.makeText(context, text, duration);
	        	toast.show();
	        }
	});
	
     	connectAndSave.setOnClickListener(new OnClickListener() {
	        @Override
	        public void onClick(View v) {
	        	option_data.setAllConnectionOptions(java_socket_ipAdress.getText().toString(), java_socket_port.getText().toString(),itemTypeGroup.getCheckedRadioButtonId());
	        	Context context = mLinearLayout.getContext();
	        	CharSequence text = "Saved";
	        	int duration = Toast.LENGTH_SHORT;
	        	Toast toast = Toast.makeText(context, text, duration);
	        	toast.show();
	        	connect =  new ServerConnection();
	        }
	});
     	

        return mLinearLayout;
    }
}