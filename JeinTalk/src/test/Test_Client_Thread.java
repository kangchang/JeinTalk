package test;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Test_Client_Thread {

	private static String server_IP = "192.168.0.227";
	private static int server_PORT = 8081;
	
	private static ObjectInputStream in;
	private static ObjectOutputStream out;
	
	private static String input;
	
	private static String client_message;
	private static String client_name;

	public static void main(String[] args) {
		while(true) {
			try {
				Socket client = new Socket(server_IP, server_PORT);
				out = new ObjectOutputStream(client.getOutputStream());
				
				Scanner scan = new Scanner(System.in);
				System.out.print("입력해봐 : ");
				input = scan.nextLine();
				
				USER user = new USER();
				user.setMessage(input);
				out.writeObject(user);
				
				
				in = new ObjectInputStream(client.getInputStream());
				user = (USER) in.readObject();
				System.out.println(user.toString());
				
				
			} catch (Exception e) {
				e.getStackTrace();
			}
		}
	}
}
