package test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientInputThread extends Thread {

	private Socket socket;
	private ObjectInputStream thr_in_socket = null;
	private String userto;

	public ClientInputThread(Socket socket) {
		this.socket = socket;
		try {
			thr_in_socket = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				userto = (String)thr_in_socket.readObject();
				System.out.println("\n"+userto);

			} catch (ClassNotFoundException e) {
				
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
	}
}
