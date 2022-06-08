package test;

import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.Socket;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;





public class ChatRoom {
	private static String server_IP = "192.168.0.227";
	private static int server_PORT = 8081;
	
	
	private static JFrame jFrameList = new JFrame("참여자 리스트");
	private static JTextArea jFrameListTextArea = new JTextArea();
	
	private String name;	
	private static String loginDate;
	
	public ChatRoom (String name, String loginDate) {
		this.name = name;
		this.loginDate = loginDate;
	}
	
	public void runChatRoom() {
		try {
			// =====================================Swing=====================================
			
			JFrame jframe = new JFrame(name + " " + loginDate);
			jframe.setBounds(100, 100, 1120, 600);
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
			textArea1.setEditable(false);

			JScrollPane scrollPane = new JScrollPane(textArea1);
			scrollPane.setBounds(45, 80, 690, 340);
			scrollPane.revalidate();
			scrollPane.repaint();

			jframe.getContentPane().add(scrollPane);
			jframe.getContentPane().add(scrollPane);

			// 입력창
			JTextArea textArea2 = new JTextArea();
			textArea2.setBounds(45, 440, 479, 93);
			jframe.getContentPane().add(textArea2);
			textArea2.setLineWrap(true);
			textArea2.revalidate();
			textArea2.repaint();

			JButton btnNewButton = new JButton("SEND");
			btnNewButton.setFont(new Font("굴림", Font.BOLD, 18));
			btnNewButton.setBounds(555, 440, 180, 92);

			jframe.getContentPane().add(btnNewButton);

			btnNewButton.revalidate();
			btnNewButton.repaint();

			JTextArea textArea = new JTextArea();
			textArea.setBounds(773, 82, 323, 204);
			jframe.getContentPane().add(textArea);

			JTextArea textArea_1 = new JTextArea();
			textArea_1.setBounds(773, 329, 323, 204);
			jframe.getContentPane().add(textArea_1);

			JButton btnNewButton_1 = new JButton("회원 정보 수정");
			btnNewButton_1.setBounds(45, 7, 131, 23);
			jframe.getContentPane().add(btnNewButton_1);

			JButton btnNewButton_1_1 = new JButton("로그아웃");
			btnNewButton_1_1.setBounds(188, 7, 131, 23);
			jframe.getContentPane().add(btnNewButton_1_1);

			JLabel lblNewLabe01 = new JLabel("온라인");
			lblNewLabe01.setFont(new Font("굴림", Font.PLAIN, 14));
			lblNewLabe01.setBounds(773, 55, 81, 17);
			jframe.getContentPane().add(lblNewLabe01);

			JLabel lblNewLabe02 = new JLabel("오프라인");
			lblNewLabe02.setFont(new Font("굴림", Font.PLAIN, 14));
			lblNewLabe02.setBounds(773, 304, 81, 15);
			jframe.getContentPane().add(lblNewLabe02);

			JButton btnNewButton_2 = new JButton("+");
			btnNewButton_2.setFont(new Font("굴림", Font.BOLD, 16));
			btnNewButton_2.setBounds(689, 51, 46, 23);
			jframe.getContentPane().add(btnNewButton_2);

			JButton btnNewButton_3 = new JButton("채팅방1");
			btnNewButton_3.setBounds(45, 57, 91, 23);
			jframe.getContentPane().add(btnNewButton_3);

			JButton btnNewButton_4 = new JButton("X");
			btnNewButton_4.setFont(new Font("굴림", Font.PLAIN, 7));
			btnNewButton_4.setBounds(135, 57, 38, 23);
			jframe.getContentPane().add(btnNewButton_4);

			JToggleButton tglbtnNewToggleButton = new JToggleButton("참여 리스트");
			tglbtnNewToggleButton.setFont(new Font("굴림", Font.PLAIN, 12));
			tglbtnNewToggleButton.setBounds(566, 50, 111, 29);
			jframe.getContentPane().add(tglbtnNewToggleButton);
			tglbtnNewToggleButton.revalidate();
			tglbtnNewToggleButton.repaint();

			ItemListener itemListener = new ItemListener() {
				// itemStateChanged() method is nvoked automatically
				// whenever you click or unlick on the Button.
				public void itemStateChanged(ItemEvent itemEvent) {

					// event is generated in button
					int state = itemEvent.getStateChange();

					// if selected print selected in console
					if (state == ItemEvent.SELECTED) {
						jFrameList.setVisible(true);
						jFrameList.setBounds(100, 100, 300, 300);
						jFrameList.setLocationRelativeTo(null);
						jFrameList.setResizable(false);
						
						jFrameListTextArea.setBounds(10, 10, 100, 200);
						jFrameList.getContentPane().add(jFrameListTextArea);
						jFrameListTextArea.setEditable(false);
						
					} else {
						jFrameList.setVisible(false);
						
					}
				}
			};

			tglbtnNewToggleButton.addItemListener(itemListener);
			
			// =====================================Swing=====================================

			Socket client = new Socket(server_IP, server_PORT);
			textArea1.append("서버와 연결 되었습니다.\n");
			ClientOutputThread threadout = new ClientOutputThread(client, name, textArea1, textArea2,
					btnNewButton);
			threadout.start();
			ClientInputThread threadin = new ClientInputThread(client, textArea1, textArea2, btnNewButton, scrollPane, jFrameListTextArea);
			threadin.start();
		} catch (Exception e) {
			e.getStackTrace();
		} finally {

		}
	}
	
	
	
}
