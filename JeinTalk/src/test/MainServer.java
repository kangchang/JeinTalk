package test;

import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MainServer {

	private static int PORT = 8081;
	protected static List<ObjectOutputStream> list = new ArrayList<ObjectOutputStream>();

	public static void main(String[] args) {

		try (ServerSocket server_socket = new ServerSocket(PORT);) {
			while (true) {
				try {
					System.out.println("클라이언트 접속 대기중...\n");

					Socket client_socket = server_socket.accept();
					System.out.println("접속자 : " + client_socket.getInetAddress());

					ServerInThread serverinthread = new ServerInThread(client_socket, client_socket);
					serverinthread.start();

				} catch (Exception e) {
					e.getStackTrace();
				}
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
}