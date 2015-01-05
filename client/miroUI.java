package miro;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class miroUI extends JFrame {
	java.net.URL imgURL1 = miro.class.getResource("icon\\444.jpg");
	java.net.URL imgURL2 = miro.class.getResource("icon\\333.png");
	java.net.URL imgURL3 = miro.class.getResource("icon\\p2pchat222.jpg");
	java.net.URL imgURL4 = miro.class.getResource("icon\\leave.jpg");
	private static JPanel contentPane;
	static JTextArea textField;
	static JTextArea textField_1;
	static boolean flag = false;
	static boolean closeflag = false;
	static boolean p2pflag=false;
	static boolean freshflag=false;
	static int count = 0;
	static String message;
	static String choosep2p;
	static int p2plength;
	static String[] record = new String[3];
  
	static JList list=new JList();

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public miroUI() {
		setTitle("\u53E8\u53E8-\u804A\u5929\u5BA4");
		setIconImage(Toolkit.getDefaultToolkit().getImage(imgURL2));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 731, 504);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 153, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextArea();
		textField.setForeground(new Color(105, 105, 105));
		textField.setFont(new Font("微软雅黑", Font.BOLD, 14));
		textField.setSelectedTextColor(new Color(255, 255, 255));
		textField.setBackground(new Color(255, 255, 255));
		textField.setEditable(false);
		textField.setBounds(272, 9, 470, 358);
		textField.setBorder(BorderFactory.createTitledBorder(""));
		JScrollPane jsp = new JScrollPane(textField);
		jsp.setLocation(10, 26);
		jsp.setSize(422, 258);
		contentPane.add(jsp);
		textField.setColumns(10);

		textField_1 = new JTextArea();
		textField_1.setFont(new Font("微软雅黑", Font.BOLD, 14));
		textField_1.setForeground(new Color(0, 0, 0));
		textField_1.setBackground(new Color(255, 240, 245));
		textField.setTabSize(4);
		
				JButton btnSend = new JButton("Send");
				btnSend.setBackground(new Color(255, 105, 180));
				btnSend.setForeground(new Color(255, 204, 255));
				btnSend.setFont(new Font("幼圆", Font.ITALIC, 21));
				btnSend.setBounds(339, 422, 93, 23);
				btnSend.addActionListener(new send());
				contentPane.add(btnSend);
		textField_1.setAlignmentY(Component.TOP_ALIGNMENT);
		textField_1.setAlignmentX(Component.LEFT_ALIGNMENT);
		textField_1.setBounds(10, 307, 422, 108);
		textField_1.setBorder(BorderFactory.createTitledBorder(""));
		contentPane.add(textField_1);

		JButton button1 = new JButton("\u79BB\u7EBF");
		button1.setIcon(new ImageIcon(imgURL4));
		button1.setBounds(596, 377, 93, 68);
		button1.addActionListener(new close());
		contentPane.add(button1);
		list.setToolTipText("");

		//JList list = new JList();
		list.setBounds(454, 26, 251, 341);

		list.setVisibleRowCount(50);

		list.setValueIsAdjusting(true);
		list.setSelectionForeground(new Color(148, 0, 211));
		list.setSelectionBackground(new Color(240, 248, 255));
		list.setModel(new AbstractListModel() {

			public int getSize() {
				return recieve.values.length;
			}

			public Object getElementAt(int index) {
				return recieve.values[index];
			}
		});

		list.setSelectedIndex(0);
		list.setLayoutOrientation(JList.VERTICAL_WRAP);
		list.setForeground(new Color(255, 255, 255));
		list.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		list.setFixedCellWidth(200);
		list.setFixedCellHeight(20);
		list.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		list.setBorder(BorderFactory.createTitledBorder(""));
		list.setBackground(new Color(255, 240, 245));
		
		contentPane.add(list);

		JButton btnPp = new JButton("P2P\u804A\u5929");
		btnPp.setIcon(new ImageIcon(imgURL3));
		btnPp.setBounds(464, 377, 87, 68);
		btnPp.addActionListener(new p2p());
		contentPane.add(btnPp);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(imgURL1));
		lblNewLabel.setBounds(-12, -11, 743, 490);
		contentPane.add(lblNewLabel);

	}

	private class send implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			message = textField_1.getText();
			flag = true;
		}
	}

	private class close implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			closeflag = true;
		}
	}

	
		

		
	private class p2p implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String ss = list.getSelectedValue().toString();
			System.out.println(ss);
			choosep2p=ss;
			p2plength=choosep2p.length();
			p2pflag=true;
			
		}

	}
	

	public static void freshlist(final String value[]) {

	}
}
