package de.teamgamma.cansat.app.fileoperations;


public class SaveThread implements Runnable {
	private Save save = Save.getInstance();
	private Double[][] data;

	@Override
	public void run() {
		save.saveAll(data);
	}
	public void setData(Double[][] data){
		this.data = data;
		
	}

}
