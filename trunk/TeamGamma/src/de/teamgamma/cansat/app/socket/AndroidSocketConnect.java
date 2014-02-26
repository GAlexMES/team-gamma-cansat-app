package de.teamgamma.cansat.app.socket;
import de.teamgamma.cansat.app.options.Options;

public class AndroidSocketConnect {
	Options options = Options.getInstance();
	public AndroidSocketConnect(){
		new Thread(new Runnable() {
			
			@Override
			public void run(){
				new AndroidClient(options.getJavaSocketIpAdress(), Integer.parseInt(options.getJavaSocketPort()), new MessageAdapter() {
					
					@Override
					public void messageArrived(String message) {
						
					}
				});
			}
		}).start();
	}
}
