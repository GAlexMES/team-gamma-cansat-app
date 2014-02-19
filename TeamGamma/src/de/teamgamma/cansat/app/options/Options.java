package de.teamgamma.cansat.app.options;




public class Options {
	private String java_socket_ipAdress = null;;
	private String java_socket_port = null;;
	private OptionsExport optionsExport = new OptionsExport();
	private int methodToConnect = 0;
	private String valueExportPath = null;
	private String valueStoragePath = null;
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
		java_socket_ipAdress=optionsExport.getValue(0);
		java_socket_port=optionsExport.getValue(1);
		methodToConnect=Integer.parseInt(optionsExport.getValue(2));
		valueExportPath = optionsExport.getValue(3);
		valueStoragePath = optionsExport.getValue(4);
		}
	
	public String getJava_socket_ipAdress() {
		return java_socket_ipAdress;
	}
	public String getJava_socket_port() {
		return java_socket_port;
	}
	public void setJava_socket_ipAdress(String java_socket_ipAdress) {
		this.java_socket_ipAdress = java_socket_ipAdress;
		writeAllToFile();
	}
	public void setJava_socket_port(String java_socket_port) {
		this.java_socket_port = java_socket_port;
		writeAllToFile();
	}
	
	public void setAllConnectionOptions(String ipAdress, String port, int checkedRadioButton){
		java_socket_ipAdress = 	ipAdress;
		java_socket_port	= 	port;
		methodToConnect		=	checkedRadioButton;
		writeAllToFile();
	}
	
	private void writeAllToFile(){
		optionsExport.writeAll(java_socket_ipAdress, java_socket_port, methodToConnect, valueExportPath, valueStoragePath );
	}
}
