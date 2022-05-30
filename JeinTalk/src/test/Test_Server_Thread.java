package test;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Test_Server_Thread{
	
	private static String server_IP = "192.168.0.242";
	private static int server_PORT = 9998;
	private static String message;
	private static String name;
	private static String server_message;
	private static String server_name;
	private static String message01;
	
	public static void main(String[] args) {
		while (true) {
			try {
				System.out.println("클라이언트 접속 대기중...");
				try (ServerSocket server_socket = new ServerSocket(server_PORT)) {
					Socket client_socket = server_socket.accept();
					
					System.out.println("접속자 : " + client_socket.getInetAddress());
					
					InputStream input_st = client_socket.getInputStream();
					ObjectInputStream obj_input_st = new ObjectInputStream(input_st);
					
					USER user = new USER();
					
					user = (USER)obj_input_st.readObject();
					
					message = user.getMessage();
					name = user.getName();
					
					System.out.println(message + "   " + name);
					System.out.println("client 가 보낸 값");
					
					// server -> client message 보내기
					
					OutputStream out_st = client_socket.getOutputStream();
					ObjectOutputStream obj_out_st = new ObjectOutputStream(out_st);
					
					user.setMessage("제인");
					obj_out_st.writeObject(user);
					
				}
			} catch(Exception e) {
				e.getStackTrace();
			}
		}
	}
}
