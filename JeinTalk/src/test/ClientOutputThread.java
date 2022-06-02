package test;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class ClientOutputThread extends Thread {

	private Socket socket;
	private String name;
	private String message;
	private ObjectOutputStream thr_out_socket = null;
	private USER user = new USER();
	private SimpleDateFormat date = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
	String loginDate = date.format(System.currentTimeMillis());

	public ClientOutputThread(Socket socket, String name) {
		this.socket = socket;
		this.name = name;
		try {
			thr_out_socket = new ObjectOutputStream(this.socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		Scanner scan = new Scanner(System.in);
		System.out.println("My name : " + name + " / 접속한 시간 : " + loginDate);
		System.out.print("보내고싶은 Message 입력 : ");
		while (true) {
			try {
				message = scan.nextLine();
				user.setMessage(message);
				user.setName(name);
				thr_out_socket.writeObject((Object)(user.toString()));
				thr_out_socket.flush();
			} catch (Exception e) {

			}
		}
	}
}