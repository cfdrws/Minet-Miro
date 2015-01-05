package miro;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.Font;

public class p2p extends JFrame {
	java.net.URL imgURL1 = miro.class.getResource("icon\\444.jpg");
	java.net.URL imgURL2 = miro.class.getResource("icon\\333.png");
	java.net.URL imgURL3 = miro.class.getResource("icon\\999.jpg");
	private JPanel contentPane;
	static JTextArea textField;
	static JTextArea textField_1;
	static JTextArea textArea;
	static boolean flag = false;
	static boolean chatflag = false;
	static boolean fileflag = false;
	static String fileString;
	static int count2 = 0;
	static String message;
	static String[] record2 = new String[3];

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public p2p() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(imgURL2));
		setTitle("\u53E8\u53E8-P2P");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 446, 434);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextArea();
		textField.setForeground(new Color(102, 102, 102));
		textField.setFont(new Font("微软雅黑", Font.BOLD, 14));
		textField.setSelectedTextColor(new Color(255, 255, 255));
		textField.setBackground(new Color(255, 255, 255));
		textField.setEditable(false);
		textField.setBounds(20, 10, 394, 184);
		textField.setBorder(BorderFactory.createTitledBorder(""));
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextArea();
		textField_1.setFont(new Font("微软雅黑", Font.BOLD, 14));
		textField_1.setBackground(new Color(255, 240, 245));
		textField.setTabSize(4);
		textField_1.setAlignmentY(Component.TOP_ALIGNMENT);
		textField_1.setAlignmentX(Component.LEFT_ALIGNMENT);
		textField_1.setBounds(20, 222, 394, 129);
		textField_1.setBorder(BorderFactory.createTitledBorder(""));
		contentPane.add(textField_1);

		JButton btnSend = new JButton("Send");
		btnSend.setBackground(new Color(255, 105, 108));
		btnSend.setForeground(new Color(255, 204, 255));
		btnSend.setFont(new Font("幼圆", Font.ITALIC, 21));
		btnSend.setBounds(321, 361, 93, 25);
		btnSend.addActionListener(new send());
		contentPane.add(btnSend);

		JButton button_1 = new JButton("");
		button_1.setIcon(new ImageIcon(imgURL3));
		button_1.setBounds(266, 361, 38, 25);
		button_1.addActionListener(new open());
		contentPane.add(button_1);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(imgURL1));
		lblNewLabel.setBounds(0, 0, 437, 396);
		contentPane.add(lblNewLabel);

	}

	private class send implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			message = textField_1.getText();
			flag = true;
			chatflag = true;
		}
	}

	private class open implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			JFileChooser jc = new JFileChooser();
			int returnValue = jc.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File selectedFile = jc.getSelectedFile();
				if (selectedFile != null) {
					fileString = selectedFile.getAbsolutePath();
					// fileString = JOptionPane.showInputDialog(null,"请输入文件路径");
					fileflag = true;

					// System.out.print("hehehe"+fileString);
				}
			}

		}
	}
}
