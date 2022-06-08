package test;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Client {
	private static String server_IP = "192.168.0.242";
	private static int server_PORT = 8089;
	private static String client_name;
	private static JFrame frame;
	private static JTextField textField;
	private static JTextField textField_1;
	private SimpleDateFormat date = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
	String loginDate = date.format(System.currentTimeMillis());

	public static void main(String[] args) {
		// =====================================Swing=====================================
		int x = 120;
		int y = 120;
		int height = 500;
		int width = 400;

		frame = new JFrame();
		frame.setBounds(x, y, height, width);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);

		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 20));
		lblNewLabel.setBounds(180, 30, 80, 40);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(50, 88, 108, 28);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("굴림", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(50, 120, 108, 28);
		frame.getContentPane().add(lblNewLabel_1_1);

		textField = new JTextField();
		textField.setBounds(190, 86, 129, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		textField.revalidate();
		textField.repaint();

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(190, 126, 129, 21);
		frame.getContentPane().add(textField_1);
		textField_1.revalidate();
		textField_1.repaint();

		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("굴림", Font.PLAIN, 24));
		btnLogin.setBounds(331, 86, 110, 62);
		frame.getContentPane().add(btnLogin);
		btnLogin.revalidate();
		btnLogin.repaint();

		JButton btnSign = new JButton("회원가입");
		btnSign.setBounds(125, 189, 100, 23);
		frame.getContentPane().add(btnSign);
		btnSign.revalidate();
		btnSign.repaint();

		JButton btnIdPassFind = new JButton("ID/Pw 찾기");
		btnIdPassFind.setBounds(240, 189, 100, 23);
		frame.getContentPane().add(btnIdPassFind);
		btnIdPassFind.revalidate();
		btnIdPassFind.repaint();

		// =====================================Swing=====================================

//        Scanner scan = new Scanner(System.in);
//        System.out.print("(최초실행 1회)당신의 이름을 입력해주세요 : ");
//        client_name = scan.nextLine();

//        client_name = textField.getText();
//        System.out.println(client_name);

		client_name = "주지찬";
		try {

			// =====================================Swing=====================================
			JFrame jframe = new JFrame();
			jframe = new JFrame();
			jframe.setBounds(100, 100, 800, 600);
			jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jframe.getContentPane().setLayout(null);
			jframe.setLocationRelativeTo(null);
			jframe.setResizable(false);
			jframe.setVisible(true);

			// 채팅창
			JTextArea textArea1 = new JTextArea();
			textArea1.setLineWrap(true);
			textArea1.setBounds(45, 10, 690, 387);
			textArea1.revalidate();
			textArea1.repaint();

			textArea1.setLineWrap(true);
			textArea1.setWrapStyleWord(true);

			JScrollPane scrollPane = new JScrollPane(textArea1);
			scrollPane.setBounds(45, 10, 690, 387);
			scrollPane.revalidate();
			scrollPane.repaint();

			jframe.getContentPane().add(scrollPane);
			jframe.add(scrollPane);

			// 입력창
			JTextArea textArea2 = new JTextArea();
			textArea2.setBounds(45, 422, 479, 93);
			jframe.getContentPane().add(textArea2);
			textArea2.revalidate();
			textArea2.repaint();

			JButton btnNewButton = new JButton("SEND");
			btnNewButton.setFont(new Font("굴림", Font.BOLD, 18));
			btnNewButton.setBounds(555, 423, 180, 92);

			jframe.getContentPane().add(btnNewButton);
			btnNewButton.revalidate();
			btnNewButton.repaint();

			// =====================================Swing=====================================

			Socket client = new Socket(server_IP, server_PORT);
			textArea1.append("서버와 연결 되었습니다.\n");
			ClientOutputThread threadout = new ClientOutputThread(client, client_name, textArea1, textArea2,
					btnNewButton);
			threadout.start();
			ClientInputThread threadin = new ClientInputThread(client, textArea1, textArea2, btnNewButton, scrollPane);
			threadin.start();
		} catch (Exception e) {
			e.getStackTrace();
		}

	}
}