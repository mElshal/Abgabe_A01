import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;

public class Login extends JPanel{
	private JPanel contentPane;
	
	private JTextField textUser;
	private JPasswordField textPw;
	private JTextField textHost;
	private JTextField textPort;
	
	public Login() {
		JFrame login = new JFrame("Login");
		login.setAlwaysOnTop(true);
		login.setResizable(false);
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		login.setBounds(100, 100, 370, 276);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		login.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDatenbankInfoEingeben = new JLabel("Datenbank Info eingeben:");
		lblDatenbankInfoEingeben.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblDatenbankInfoEingeben.setBounds(10, 11, 348, 33);
		contentPane.add(lblDatenbankInfoEingeben);
		
		JLabel lblUser = new JLabel("User:");
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUser.setBounds(101, 55, 52, 20);
		contentPane.add(lblUser);
		
		JLabel lblPw = new JLabel("Pw:");
		lblPw.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPw.setBounds(101, 86, 46, 14);
		contentPane.add(lblPw);
		
		JLabel lblHost = new JLabel("Host:");
		lblHost.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblHost.setBounds(101, 111, 46, 14);
		contentPane.add(lblHost);
		
		JLabel lblPort = new JLabel("Port:");
		lblPort.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPort.setBounds(101, 136, 46, 14);
		contentPane.add(lblPort);
		
		textUser = new JTextField();
		textUser.setBounds(147, 55, 86, 20);
		contentPane.add(textUser);
		textUser.setColumns(10);
		
		textPw = new JPasswordField();
		textPw.setBounds(147, 85, 86, 20);
		contentPane.add(textPw);
		textPw.setColumns(10);
		
		textHost = new JTextField();
		textHost.setBounds(147, 110, 86, 20);
		contentPane.add(textHost);
		textHost.setColumns(10);
		
		textPort = new JTextField();
		textPort.setBounds(147, 136, 86, 20);
		contentPane.add(textPort);
		textPort.setColumns(10);
		
		JButton searchBtn = new JButton("Suchen");
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = textUser.getText() ;
				String pw = textPw.getText();
				String host = textHost.getText();
				int port = Integer.parseInt(textPort.getText());
				FlugModel flug = new FlugModel(user,pw,host,port);
				if(flug.getConnected()) {
					//System.out.println("True");
				}
				
			}
		});
		searchBtn.setBounds(192, 185, 89, 23);
		contentPane.add(searchBtn);
		
		JButton clearBtn = new JButton("Clear");
		clearBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textUser.setText("");
				textPw.setText("");
				textHost.setText("");
				textPort.setText("");
			}
		});
		clearBtn.setBounds(93, 185, 89, 23);
		contentPane.add(clearBtn);
		login.setVisible(true);
	}
	public static void main(String[]args) {
		Login l = new Login();
	}
		
		
}
