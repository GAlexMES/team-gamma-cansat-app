package de.teamgamma.cansat.app.socket;
import de.teamgamma.cansat.app.options.Options;

public class AndroidSocketConnect {
	Options options = Options.getInstance();
	public AndroidSocketConnect(){
		new Thread(new Runnable() {
			
			@Override
			public void run(){
				new AndroidClient(options.getJava_socket_ipAdress(), Integer.parseInt(options.getJava_socket_port()), new MessageAdapter() {
					
					@Override
					public void messageArrived(String message) {
						
					}
				});
			}
		}).start();
	}
}
