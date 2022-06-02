package test;

import java.net.Socket;
import java.util.Scanner;

public class Client {

	private static String server_IP = "192.168.0.227";
	private static int server_PORT = 8081;
	private static String client_name;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		System.out.print("(최초실행 1회)당신의 이름을 입력해주세요 : ");
		client_name = scan.nextLine();

		try {
			Socket client = new Socket(server_IP, server_PORT);
			System.out.println("서버와 연결 되었습니다.");

			ClientOutputThread threadout = new ClientOutputThread(client, client_name);
			threadout.start();
			
			ClientInputThread threadin = new ClientInputThread(client);
			threadin.start();

		} catch (Exception e) {
			e.getStackTrace();
		}
	}
}