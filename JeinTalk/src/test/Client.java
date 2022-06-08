package test;

import java.awt.Color;
import java.awt.EventQueue;
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

import db.DBConnection;

public class Client {
	
	private static String client_name;
	private static JFrame frame;
	private static JTextField textField;
	private static JTextField textField_1;
	
	private static SimpleDateFormat date = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
	private static String loginDate = date.format(System.currentTimeMillis());

	
	
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

		
		//회원가입
		btnSign.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SignUp Sign = new SignUp();
				Sign.Singup();
			}
        });
		
		
		//회원정보찾기
		btnIdPassFind.addActionListener(event->{
        	new FindInfo();
        });
		
		//로그인성공시
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					
					DBConnection dbconn = new DBConnection();
					Connection conn = dbconn.getConnection();
					PreparedStatement pstmt = null;
					
					StringBuffer sql = new StringBuffer();
					sql.append("SELECT id, pw, username FROM user WHERE id=?");

					pstmt = conn.prepareStatement(sql.toString());
					
					pstmt.setString(1, textField.getText());
					
					ResultSet result = pstmt.executeQuery();
					String getIdFromDb = null;
					String getPwFromDb = null;
					String getUserNameFromeDb = null;
					while(result.next()) {
						getIdFromDb = result.getString("id");
						getPwFromDb = result.getString("pw");
						getUserNameFromeDb = result.getString("username");
					}
					
					if(textField.getText().equals(getIdFromDb) && textField_1.getText().equals(getPwFromDb)) {
						ChatRoom chatroom = new ChatRoom(getUserNameFromeDb, loginDate);
						chatroom.runChatRoom();
						frame.setVisible(false);
					} else {
						System.out.println("로그인 실패");
						JFrame jframe3 = new JFrame();
						
						
					}
					
				} catch(Exception e1) {
					e1.printStackTrace();
				} finally {
					
				}
			}
            
        });
		// =====================================Swing=====================================

		

		
		


		
	}
	
}