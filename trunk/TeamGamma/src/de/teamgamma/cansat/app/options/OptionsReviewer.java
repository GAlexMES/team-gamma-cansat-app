package de.teamgamma.cansat.app.options;

public class OptionsReviewer {

	public boolean checkBetween(int Begin, int End, int value) {
		if (value >= Begin && value <= End) {
			return true;
		} else {
			return false;
		}
	}

}
