package minet;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

public class serverUI extends JFrame {

	private JPanel contentPane;
    public static JTextArea textField;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public serverUI() {
		setTitle("\u670D\u52A1\u5668");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextArea();
		textField.setSelectedTextColor(new Color(255, 255, 255));
		textField.setBackground(new Color(255, 255, 255));
		textField.setEditable(false);
		textField.setBounds(10, 10, 414, 195);
		textField.setBorder(BorderFactory.createTitledBorder(""));
		JScrollPane jsp = new JScrollPane(textField);
		jsp.setLocation(10, 10);
		jsp.setSize(414, 228);
		contentPane.add(jsp);
		textField.setColumns(10);
	}
}

