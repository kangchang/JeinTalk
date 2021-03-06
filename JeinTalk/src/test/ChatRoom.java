package test;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import db.DBConnection;


public class ChatRoom {
//	private static String server_IP = "192.168.0.157";
//	private static int server_PORT = 8081;
	private String server_IP;
	private int server_PORT;

	private static JFrame jFrameList = new JFrame("참여자 리스트");
	private static JTextArea jFrameListTextArea = new JTextArea();
	protected static JTextArea textArea;
	protected static JTextArea textArea_1;
	
	private static JFrame Uframe;
	private static JTextField UtextField;
	private static JLabel UlblNewLabel_2;
	private static JTextField UtextField_1;
	private static JLabel UlblNewLabel_3;
	private static JTextField UtextField_2;
	private static JLabel UlblNewLabel_4;
	private static JTextField UtextField_3;
	private static JLabel UlblNewLabel_5;
	private static JTextField UtextField_4;
	private static JLabel UlblNewLabel_6;
	
	String getUserNameFromDb = null;
	String getIdFromDb = null;
	String getMailFromDb = null;
	String getPwFromDb = null;
	
	private String id;
	private String name;
	private static String loginDate;
	
	static DBConnection dbconn = new DBConnection();
	static Connection conn = dbconn.getConnection();
	static PreparedStatement pstmt = null;
	StringBuffer sql = new StringBuffer();
	int result;
	
	public ChatRoom(String id, String name, String loginDate, String ip, int port ) {
		this.id = id;
		this.name = name;
		this.server_IP = ip;
		this.server_PORT = port;
		
		this.loginDate = loginDate;
	}
	
	public void runChatRoom() throws SQLException {
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

			//온라인
			textArea = new JTextArea();
			textArea.setBounds(773, 82, 323, 204);
			jframe.getContentPane().add(textArea);
			textArea.setEditable(false);

			//오프라인
			textArea_1 = new JTextArea();
			textArea_1.setBounds(773, 329, 323, 204);
			jframe.getContentPane().add(textArea_1);
			textArea_1.setEditable(false);
			
			JButton btnNewButton_1 = new JButton("회원 정보 수정");
			btnNewButton_1.setBounds(45, 7, 131, 23);
			jframe.getContentPane().add(btnNewButton_1);
			//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
			//회원정보수정
			btnNewButton_1.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						sql.setLength(0);
						sql.append("SELECT id, pw, username, email FROM user WHERE id=?");
						pstmt = conn.prepareStatement(sql.toString());
						pstmt.setString(1, id);
						ResultSet result1 = pstmt.executeQuery();
						
						while(result1.next()) {
							getIdFromDb = result1.getString("id");
							getPwFromDb = result1.getString("pw");
							getUserNameFromDb = result1.getString("username");
							getMailFromDb = result1.getString("email");
						}
						
						Uframe = new JFrame();
						Uframe.setBounds(100, 100, 720, 555);
						Uframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						Uframe.getContentPane().setLayout(null);
						Uframe.setResizable(false);
						Uframe.setVisible(true);
						
						JLabel UlblNewLabel = new JLabel("회원정보수정");
						UlblNewLabel.setFont(new Font("굴림", Font.PLAIN, 18));
						UlblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
						UlblNewLabel.setBounds(278, 50, 120, 60);
						Uframe.getContentPane().add(UlblNewLabel);
						
						JLabel UlblNewLabel_1 = new JLabel("이름");
						UlblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 14));
						UlblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
						UlblNewLabel_1.setBounds(188, 140, 133, 30);
						Uframe.getContentPane().add(UlblNewLabel_1);
						
						UtextField = new JTextField(getUserNameFromDb);
						UtextField.setBounds(333, 145, 125, 21);
						UtextField.setColumns(10);
						Uframe.getContentPane().add(UtextField);
						
						UlblNewLabel_2 = new JLabel("ID");
						UlblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
						UlblNewLabel_2.setFont(new Font("굴림", Font.PLAIN, 14));
						UlblNewLabel_2.setBounds(188, 175, 133, 30);
						Uframe.getContentPane().add(UlblNewLabel_2);
						
						UtextField_1 = new JTextField(getIdFromDb);
						UtextField_1.setColumns(10);
						UtextField_1.setBounds(333, 180, 125, 21);
						Uframe.getContentPane().add(UtextField_1);
						UtextField_1.setEditable(false);
						
						UlblNewLabel_3 = new JLabel("E-Mail");
						UlblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
						UlblNewLabel_3.setFont(new Font("굴림", Font.PLAIN, 14));
						UlblNewLabel_3.setBounds(188, 218, 133, 30);
						Uframe.getContentPane().add(UlblNewLabel_3);
						
						UtextField_2 = new JTextField(getMailFromDb);
						UtextField_2.setColumns(10);
						UtextField_2.setBounds(333, 223, 125, 21);
						Uframe.getContentPane().add(UtextField_2);
						
						UlblNewLabel_4 = new JLabel("Password");
						UlblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
						UlblNewLabel_4.setFont(new Font("굴림", Font.PLAIN, 14));
						UlblNewLabel_4.setBounds(188, 257, 133, 30);
						Uframe.getContentPane().add(UlblNewLabel_4);
						
						UtextField_3 = new JTextField(getPwFromDb);
						UtextField_3.setColumns(10);
						UtextField_3.setBounds(333, 262, 125, 21);
						Uframe.getContentPane().add(UtextField_3);
						
						UlblNewLabel_5 = new JLabel("Password 확인");
						UlblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
						UlblNewLabel_5.setFont(new Font("굴림", Font.PLAIN, 14));
						UlblNewLabel_5.setBounds(188, 306, 133, 30);
						Uframe.getContentPane().add(UlblNewLabel_5);
						
						UtextField_4 = new JTextField(getPwFromDb);
						UtextField_4.setColumns(10);
						UtextField_4.setBounds(333, 311, 125, 21);
						Uframe.getContentPane().add(UtextField_4);
	
						UlblNewLabel_6 = new JLabel("");
						UlblNewLabel_6.setBounds(333, 205, 133, 15);
						Uframe.getContentPane().add(UlblNewLabel_6);
						
						JButton UbtnNewButton = new JButton("중복확인");
						UbtnNewButton.setBounds(503, 179, 91, 23);
						Uframe.getContentPane().add(UbtnNewButton);
						UbtnNewButton.setEnabled(false);
						
						JButton UbtnNewButton_1 = new JButton("수정완료");
						UbtnNewButton_1.setBounds(230, 386, 91, 23);
						Uframe.getContentPane().add(UbtnNewButton_1);
						UbtnNewButton_1.setEnabled(false);
						
						//PW 리스너
						UtextField_3.getDocument().addDocumentListener(new DocumentListener() {

							@Override
							public void insertUpdate(DocumentEvent e) {
								Check();
							}

							@Override
							public void removeUpdate(DocumentEvent e) {
								Check();
							}

							@Override
							public void changedUpdate(DocumentEvent e) {
								Check();
							}
							
							public void Check() {
								if(UtextField_3.getText().equals(UtextField_4.getText()) && !UtextField.getText().equals("") && !UtextField_2.getText().equals("") && !UtextField_3.getText().equals("") && !UtextField_4.getText().equals("")) {
									UbtnNewButton_1.setEnabled(true);
								}else if(!UtextField_3.getText().equals(UtextField_4.getText()) && !UtextField.getText().equals("") && !UtextField_2.getText().equals("") && !UtextField_3.getText().equals("") && !UtextField_4.getText().equals("")) {
									UbtnNewButton_1.setEnabled(false);
								}
							}
						});
						
						//PW 확인 리스너
						UtextField_4.getDocument().addDocumentListener(new DocumentListener() {

							@Override
							public void insertUpdate(DocumentEvent e) {
								Check();
							}

							@Override
							public void removeUpdate(DocumentEvent e) {
								Check();
							}

							@Override
							public void changedUpdate(DocumentEvent e) {
								Check();
							}
							
							public void Check() {
								if(UtextField_3.getText().equals(UtextField_4.getText()) && !UtextField.getText().equals("") && !UtextField_2.getText().equals("") && !UtextField_3.getText().equals("") && !UtextField_4.getText().equals("")) {
									UbtnNewButton_1.setEnabled(true);
								}else if(!UtextField_3.getText().equals(UtextField_4.getText()) && !UtextField.getText().equals("") && !UtextField_2.getText().equals("") && !UtextField_3.getText().equals("") && !UtextField_4.getText().equals("")) {
									UbtnNewButton_1.setEnabled(false);
								}
							}
						});
						
						// 등록 리스너
						UbtnNewButton_1.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								try {
									if(!UtextField.equals(getUserNameFromDb)) {
										sql.setLength(0);
										sql.append("UPDATE user SET username=? WHERE id=?");
										pstmt = conn.prepareStatement(sql.toString());
										pstmt.setString(1, UtextField.getText());
										pstmt.setString(2, id);
										result = pstmt.executeUpdate();
									}
									if(!UtextField_2.equals(getMailFromDb)) {
										sql.setLength(0);
										sql.append("UPDATE user SET email=? WHERE id=?");
										pstmt = conn.prepareStatement(sql.toString());
										pstmt.setString(1, UtextField_2.getText());
										pstmt.setString(2, id);
										result = pstmt.executeUpdate();
									}
									if(!UtextField_3.equals(getMailFromDb)) {
										sql.setLength(0);
										sql.append("UPDATE user SET pw=? WHERE id=?");
										pstmt = conn.prepareStatement(sql.toString());
										pstmt.setString(1, UtextField_3.getText());
										pstmt.setString(2, id);
										result = pstmt.executeUpdate();
									}
									Uframe.dispose();
								} catch (SQLException exc) {
									exc.printStackTrace();
								}
							}
				            
				        });
						
						/*
						//중복확인 리스너
						UbtnNewButton.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								try {
									sql.setLength(0);
									sql.append("SELECT id FROM user WHERE id=?");
									
									pstmt = conn.prepareStatement(sql.toString());
									
									pstmt.setString(1, UtextField_1.getText());
									
									ResultSet result = pstmt.executeQuery();
									CheckResult = 0;
									
									while(result.next()) {
										CheckResult = 1;
									}
									if(CheckResult == 1) {
										UtextField_1.setForeground(Color.red);
										UlblNewLabel_6.setText("이미 등록된 ID입니다.");
									}else if(CheckResult == 0) {
										UtextField_1.setForeground(Color.green);
										UlblNewLabel_6.setText("사용가능한 ID입니다.");
									}
									
									if(CheckResult == 0 && UtextField_3.getText().equals(UtextField_4.getText()) && !UtextField.getText().equals("") && !UtextField_1.getText().equals("") && !UtextField_2.getText().equals("") && !UtextField_3.getText().equals("") && !UtextField_4.getText().equals("")) {
										UbtnNewButton_1.setEnabled(true);
									}else if(CheckResult == 1 || !UtextField_3.getText().equals(UtextField_4.getText()) && !UtextField.getText().equals("") && !UtextField_1.getText().equals("") && !UtextField_2.getText().equals("") && !UtextField_3.getText().equals("") && !UtextField_4.getText().equals("")) {
										UbtnNewButton_1.setEnabled(false);
									}
								} catch (SQLException exc) {
									exc.printStackTrace();
								}
							}
				            
				        });
						*/
						
						JButton UbtnNewButton_1_1 = new JButton("취소");
						UbtnNewButton_1_1.setBounds(379, 386, 91, 23);
						Uframe.getContentPane().add(UbtnNewButton_1_1);
						
						//취소
						UbtnNewButton_1_1.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
									Uframe.dispose();
							}
				            
				        });
					} catch (SQLException exc) {
						exc.printStackTrace();
					}
				}
	            
	        });
			//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
			JButton btnNewButton_1_1 = new JButton("로그아웃");
			btnNewButton_1_1.setBounds(188, 7, 131, 23);
			jframe.getContentPane().add(btnNewButton_1_1);
			
			btnNewButton_1_1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						sql.append("UPDATE user SET flag=0 WHERE id=?");
						pstmt = conn.prepareStatement(sql.toString());
						pstmt.setString(1, id);
						result = pstmt.executeUpdate();
						jframe.dispose();
						Client.textField.setText("");
						Client.textField_1.setText("");
						Client.frame.setVisible(true);
					} catch (SQLException exc) {
						exc.printStackTrace();
					}
				}
			});

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
			
			/*
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
			*/
			
			// =====================================Swing=====================================
			
			Socket client = new Socket(server_IP, server_PORT);
			textArea1.append("서버와 연결 되었습니다.\n");
			ClientOutputThread threadout = new ClientOutputThread(client, name, textArea1, textArea2, btnNewButton);
			threadout.start();
			ClientInputThread threadin = new ClientInputThread(client, textArea1, textArea2, btnNewButton, scrollPane,
					jFrameListTextArea);
			threadin.start();
			UserOnOffline useronoffline = new UserOnOffline();
			useronoffline.start();
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
//			conn.close();
//			pstmt.close();
		}
	}
}
