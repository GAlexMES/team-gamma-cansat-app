package de.teamgamma.cansat.app.data;

import java.util.HashMap;

/**
 * @author Teamgamma
 * 
 * This class consists of static types of data.
 * The Application adapt to this Class and change it self easily, if there are changes
 * 
 * 
 */

import android.graphics.Color;
import android.util.Log;

public class constantValues {

	public static String[] names = { "temp", "pressure", "humidity", "gyro", "longitude", "latitude" };
	
	public static String[] notAllowedKeys = { "id", "ID", "time" , "longitude", "latitude"};
	
	public static long firstTimestamp;
	
	public static String exportTime;

	public static final int[] selectableColors = { Color.TRANSPARENT,
			Color.BLUE, Color.BLACK, Color.CYAN, Color.GREEN, Color.RED,
			Color.MAGENTA, Color.YELLOW, Color.WHITE };
	
	public static final String head = "Team-Gamma_Android-App";
	
	private static HashMap<String, String> namesDic = new HashMap<String, String>();

	public static void generateMap(String[] stringArray) {
		for (int i = 0; i < names.length; i++) {
			namesDic.put(names[i], stringArray[i + 1]);
			Log.d("names", namesDic.get(names[i]));
		}
	}

	public static String getStringFromHashmap(String key) {
		return namesDic.get(key);
	}

}
