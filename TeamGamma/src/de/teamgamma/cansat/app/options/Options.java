package de.teamgamma.cansat.app.options;


public class Options {

	private OptionsInterface[] options = new OptionsInterface[3];

	private OptionsReviewer check = new OptionsReviewer();
	
	
	private static Options instance = null;

	public static Options getInstance() {
		if (instance == null) {
			instance = new Options();
			return instance;
		} else {
			return instance;
		}
	}

	private Options() {
		options[0] = new ConnectionOptions();
		options[1] = new PathOptions();
		options[2] = new ChartViewOptions();
	}

	public OptionsInterface[] getOptions() {
		return options;
	}

	public boolean setOption(int kindOfOption, int nameOfOption, Object value) {
		OptionsExport exporter = new OptionsExport();
		if (check.checkBetween(0, options.length - 1, kindOfOption)) {
			if (check.checkBetween(0,
					options[kindOfOption].getValues().length - 1, nameOfOption)) {
				String[] currentValues = options[kindOfOption].getValues();
				currentValues[nameOfOption] = String.valueOf(value);
				options[kindOfOption].setValues(currentValues);
				exporter.newWrite();
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public void readAll(){
		OptionsExport exporter = new OptionsExport();
		exporter.newReadFile();
	}

	public String getOption(int kindOfOption, int nameOfOption) {
		if (check.checkBetween(0, options.length - 1, kindOfOption)) {
			if (check.checkBetween(0,
					options[kindOfOption].getValues().length - 1, nameOfOption)) {
				return options[kindOfOption].getValues()[nameOfOption];
			}
			return "";
		}
		return "";
	}
}
	
