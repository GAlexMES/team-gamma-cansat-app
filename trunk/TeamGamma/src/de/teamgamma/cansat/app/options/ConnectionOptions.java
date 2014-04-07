package de.teamgamma.cansat.app.options;

import android.util.Log;

public class ConnectionOptions implements OptionsInterface {
	
	public static final int JAVASOCKETPORT = 0;
	public static final int JAVASOCKETIP = 1;
	public static final int JAVASOCKETMETHOD= 2;
	public String[] values = new String[3];
	
	/*
	 * 0: Java Socket port
	 * 1: Java Socket Ip Adress
	 * 2: Method to connect
	 */

	public String[] getValues() {
		for(String b:values){
		Log.d("return",String.valueOf(b));
		}
		return values;
	}

	public void setValues(String[] values) {
		this.values = values;
	}

	@Override
	public int[] getColors() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setColors(int[] colors) {
		// TODO Auto-generated method stub
		
	}
	
	

}
