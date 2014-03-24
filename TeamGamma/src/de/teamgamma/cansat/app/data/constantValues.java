package de.teamgamma.cansat.app.data;

import java.util.HashMap;

import android.util.Log;

public class constantValues {

	public static String[] names = { "time", "co2", "temp" };

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
