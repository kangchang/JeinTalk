package test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerInThread extends Thread {

	private ObjectInputStream in = null;
	private ObjectOutputStream out = null;
	private String user;

	public ServerInThread(Socket socket) {
		try {
			this.in = new ObjectInputStream(socket.getInputStream());
			this.out = new ObjectOutputStream(socket.getOutputStream());
			MainServer.list.add(out);
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
	

	@Override
	public void run() {
		while (true) {
			try {
				user = (String) in.readObject();
				System.out.println(user);

				send();
			} catch (Exception e) {
				e.getStackTrace();
			}
		}
	}

	public void send() {
		for (int i = 0; i < (MainServer.list).size(); i++) {
			try {
				(MainServer.list.get(i)).writeObject((Object)user);
				(MainServer.list.get(i)).flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
