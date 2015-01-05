package miro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Toolkit;
import java.awt.Font;

import javax.swing.ImageIcon;



public class login extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	java.net.URL imgURL1 = miro.class.getResource("icon\\111.png");
	java.net.URL imgURL2 = miro.class.getResource("icon\\222.jpg");
	java.net.URL imgURL3 = miro.class.getResource("icon\\333.png");
	private JPanel contentPane;
	private JTextField txtMyPort;
	private JTextField txtMyName;
	private JTextField txtServerIp;
	private JTextField txtServePort;
	
	public static String myport;
	public static String myname;
	public static String disip;
	public static String disport;
	public static String p2pport;
	public static boolean flag=true;
	private JTextField txtpp;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	
				
	
	/**
	 * Create the frame.
	 */
	public login() {
		setTitle("\u53E8\u53E8");
		setIconImage(Toolkit.getDefaultToolkit().getImage(imgURL3));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtMyPort = new JTextField();
		txtMyPort.setForeground(new Color(102, 0, 102));
		txtMyPort.setFont(new Font("微软雅黑", Font.BOLD, 14));
		txtMyPort.setBackground(new Color(255, 255, 51));
		txtMyPort.setText("My Port");
		txtMyPort.setBounds(63, 103, 210, 32);
		contentPane.add(txtMyPort);
		txtMyPort.setColumns(10);
		
		txtMyName = new JTextField();
		txtMyName.setForeground(new Color(102, 0, 102));
		txtMyName.setFont(new Font("微软雅黑", Font.BOLD, 14));
		txtMyName.setBackground(new Color(255, 255, 51));
		txtMyName.setText("My Name");
		txtMyName.setColumns(10);
		txtMyName.setBounds(63, 159, 210, 32);
		contentPane.add(txtMyName);
		
		txtServerIp = new JTextField();
		txtServerIp.setForeground(new Color(102, 0, 102));
		txtServerIp.setFont(new Font("微软雅黑", Font.BOLD, 14));
		txtServerIp.setBackground(new Color(255, 255, 51));
		txtServerIp.setText("Server IP");
		txtServerIp.setColumns(10);
		txtServerIp.setBounds(63, 224, 210, 32);
		contentPane.add(txtServerIp);
		
		txtServePort = new JTextField();
		txtServePort.setFont(new Font("微软雅黑", Font.BOLD, 14));
		txtServePort.setForeground(new Color(102, 0, 102));
		txtServePort.setBackground(new Color(255, 255, 51));
		txtServePort.setText("Serve Port(4444)");
		txtServePort.setColumns(10);
		txtServePort.setBounds(63, 288, 210, 32);
		contentPane.add(txtServePort);
		
		JButton button = new JButton("\u767B\u5F55");
		button.setIcon(new ImageIcon(imgURL2));
		button.setBounds(105, 406, 122, 32);
		contentPane.add(button);
		
		lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(imgURL1));
		lblNewLabel_1.setBounds(140, 10, 50, 50);
		contentPane.add(lblNewLabel_1);
		
		txtpp = new JTextField();
		txtpp.setForeground(new Color(102, 0, 102));
		txtpp.setFont(new Font("微软雅黑", Font.BOLD, 14));
		txtpp.setBackground(new Color(255, 255, 51));
		txtpp.setText("My P2Pport");
		txtpp.setColumns(10);
		txtpp.setBounds(63, 345, 210, 32);
		contentPane.add(txtpp);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBackground(new Color(255, 255, 153));
		lblNewLabel.setBounds(38, 80, 270, 316);
		contentPane.add(lblNewLabel);
		button.addActionListener(new confirm());
	}
	
	private class confirm implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			myport=txtMyPort.getText();
			myname=txtMyName.getText();
			disip=txtServerIp.getText();
			disport=txtServePort.getText();
			p2pport=txtpp.getText();
			flag=false;
		}
	}
}
