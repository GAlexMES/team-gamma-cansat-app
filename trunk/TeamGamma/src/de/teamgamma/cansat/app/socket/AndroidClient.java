package de.teamgamma.cansat.app.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.json.JSONObject;

import android.util.Log;
import de.teamgamma.cansat.app.data.constantValues;

public class AndroidClient {
	private Socket clientSocket = null;

	class CommunicationThread implements Runnable {

		private BufferedReader in;
		private MessageAdapter messageAdapter;

		public CommunicationThread(MessageAdapter messageAdapter) {
			this.messageAdapter = messageAdapter;

			try {
				// BufferedReader zum lesen erzeugen
				this.in = new BufferedReader(new InputStreamReader(
						clientSocket.getInputStream()));

				Log.d("socket_test", "Buffered reader created!");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void run() {
			String message = null;
			int counter = 0;
			while (clientSocket.isConnected()) {
				try {
					if (this.in.ready()) {
						message = this.in.readLine();
						Log.d("values", message);
						if (counter == 0) {
							JSONObject jsonmessage = new JSONObject(message);
							constantValues.firstTimestamp = jsonmessage
									.getLong("time");
						}
						this.messageAdapter.messageArrived(message.toString());
						counter++;

					}
				} catch (IOException e) {
					e.printStackTrace();
					Log.d("socket_test", "IOException while receiving message!");
				} catch (Exception e) {
					e.printStackTrace();
					Log.d("socket_test", "Exception while receiving message!");
				}
			}
		}
	}

	private Thread commThread;

	public AndroidClient(String dst, int dstPort, MessageAdapter messageAdapter) {
		try {

			InetAddress inetAddress = InetAddress.getByName(dst);

			try {
				this.clientSocket = new Socket(inetAddress, dstPort);
				Log.d("socket_test", "Socket created, everything fine!");
				this.commThread = new Thread(new CommunicationThread(messageAdapter));
				this.commThread.start();
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_kk_mm",
						Locale.GERMANY);
				constantValues.exportTime = sdf.format(new Date());
				
			} catch (IOException e) {
				e.printStackTrace();
				Log.d("socket_test", "Socket created, IO Exception!");
			} catch (Exception e) {
				Log.d("socket_test", "Socket created, Exception!");
				e.printStackTrace();
			}
		} catch (UnknownHostException e1) {
			Log.d("socket_test", "Error while get Internet Address");
			e1.printStackTrace();
		}
	}

	public void startStreaming() {
		this.commThread.start();

	}

	public void stopStreaming() {
		this.commThread.interrupt();
	}

	public void disconnect() {
		try {
			this.clientSocket.close();
		} catch (IOException e) {
			Log.d("socket_test", "Exception while closing the socket!");
		}
	}
}
