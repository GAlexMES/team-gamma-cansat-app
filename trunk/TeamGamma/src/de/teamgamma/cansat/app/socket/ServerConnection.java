package de.teamgamma.cansat.app.socket;



import de.teamgamma.cansat.app.data.DataCoordination;
import de.teamgamma.cansat.app.options.ConnectionOptions;
import de.teamgamma.cansat.app.options.KindOfOption;
import de.teamgamma.cansat.app.options.Options;

public class ServerConnection {

	private DataCoordination datatransfer = DataCoordination.getInstance();

	public ServerConnection() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				Options options = Options.getInstance();
				AndroidClient client = new AndroidClient(
						options.getOption(KindOfOption.CONNECTION.ordinal(),ConnectionOptions.JAVASOCKETIP),
						
						Integer.parseInt(options.getOption(KindOfOption.CONNECTION.ordinal(),ConnectionOptions.JAVASOCKETPORT)),
						new MessageAdapter() {

							@Override
							public void messageArrived(String message) {
							
								datatransfer.coordinateData(message);
							}
						});

			}
		}
		).start();

	}
}
