package de.teamgamma.cansat.app.options;




public class Options {
	private String javaSocketIpAdress = null;;
	private String javaSocketPort = null;;
	private OptionsExport optionsExport = new OptionsExport();
	private int methodToConnect = 0;
	private String valueExportPath = null;
	private String valueStoragePath = null;
	private String optionsPath = null;
	private String tempValueExportPath = null;
	private String tempValueStoragePath = null;
	private String tempOptionsPath = null;

	public String getTempValueExportPath() {
		return tempValueExportPath;
	}


	public void setTempValueExportPath(String tempValueExportPath) {
		this.tempValueExportPath = tempValueExportPath;
	}


	public String getTempValueStoragePath() {
		return tempValueStoragePath;
	}


	public void setTempValueStoragePath(String tempValueStoragePath) {
		this.tempValueStoragePath = tempValueStoragePath;
	}


	public String getTempOptionsPath() {
		return tempOptionsPath;
	}


	public void setTempOptionsPath(String tempOptionsPath) {
		this.tempOptionsPath = tempOptionsPath;
	}


	public String getOptionsPath() {
		return optionsPath;
	}


	public void setOptionsPath(String optionsPath) {
		this.optionsPath = optionsPath;
	}

	private static Options instance = null;

	
	
	public String getValueStoragePath() {
		return valueStoragePath;
	}


	public void setValueStoragePath(String valueStoragePath) {
		this.valueStoragePath = valueStoragePath;
		writeAllToFile();
	}


	public String getValueExportPath() {
		return valueExportPath;
	}


	public void setValueExportPath(String valueExportPath) {
		this.valueExportPath = valueExportPath;
		writeAllToFile();
	}


	public static Options getInstance(){
		if(instance==null){
			instance = new Options();	
			
		}
		return instance;
	}
	
	
	public int getMethodToConnect() {
		return methodToConnect;
	}


	public void setMethodToConnect(int methodToConnect) {
		this.methodToConnect = methodToConnect;
		writeAllToFile();
	}


	public void getValuesFromFile(){
		javaSocketIpAdress=optionsExport.getValue(0);
		javaSocketPort=optionsExport.getValue(1);
		if(optionsExport.getValue(2)!=null){
			methodToConnect=Integer.parseInt(optionsExport.getValue(2));
		}
	
		
		valueExportPath = optionsExport.getValue(3);
		valueStoragePath = optionsExport.getValue(4);
		}
	
	public String getJavaSocketIpAdress() {
		return javaSocketIpAdress;
	}
	public String getJavaSocketPort() {
		return javaSocketPort;
	}
	public void setJavaSocketIpAdress(String javaSocketIpAdress) {
		this.javaSocketIpAdress = javaSocketIpAdress;
		writeAllToFile();
	}
	public void setJavaSocketPort(String javaSocketPort) {
		this.javaSocketPort = javaSocketPort;
		writeAllToFile();
	}
	
	public void setAllConnectionOptions(String ipAdress, String port, int checkedRadioButton){
		javaSocketIpAdress = 	ipAdress;
		javaSocketPort	= 	port;
		methodToConnect		=	checkedRadioButton;
		writeAllToFile();
	}
	
	private void writeAllToFile(){
		optionsExport.writeAll(javaSocketIpAdress, javaSocketPort, methodToConnect, valueExportPath, valueStoragePath );
	}
}
