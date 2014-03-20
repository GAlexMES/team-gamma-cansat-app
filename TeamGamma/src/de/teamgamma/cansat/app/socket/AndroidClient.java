package de.teamgamma.cansat.app.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import android.util.Log;

public class AndroidClient {
	private Socket clientSocket = null;

	class CommunicationThread implements Runnable {

		private BufferedReader in;
		private MessageAdapter messageAdapter;


		public CommunicationThread(MessageAdapter messageAdapter) {
			this.messageAdapter = messageAdapter;

			try {
				// BufferedReader zum lesen erzeugen
				in = new BufferedReader(new InputStreamReader(
						clientSocket.getInputStream()));

				Log.d("socket_test", "Buffered reader created!");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void run() {
			String message = null;

			while (clientSocket.isConnected()) {
				try {
					if (in.ready()) {
						message = in.readLine();
						Log.d("values",message);
						messageAdapter.messageArrived(message);
						
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
				clientSocket = new Socket(inetAddress, dstPort);
				Log.d("socket_test", "Socket created, everything fine!");
				commThread = new Thread(new CommunicationThread(messageAdapter));
				commThread.start();
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
		commThread.start();
	}

	public void stopStreaming() {
		commThread.interrupt();
	}

	public void disconnect() {
		try {
			clientSocket.close();
		} catch (IOException e) {
			Log.d("socket_test", "Exception while closing the socket!");
		}
	}
}
