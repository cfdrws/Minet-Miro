package miro;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.net.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.AbstractListModel;

public class miro {

	public static void main(String[] args) throws Exception {

		login frame = new login();
		frame.setVisible(true);
		while (login.flag) {
			// waiting for confirm
		}

		String miroPort = login.myport;// 输入客户端自己的端口,任意

		System.out.print("请输入你的p2p端口：\n");// 目前我们设置的是4444
		String p2pPort = login.p2pport;// 输入客户端自己的端口,任意

		System.out.print("请输入你的昵称：\n");// 随便起个名字
		String mironame = login.myname;

		System.out.print("请输入服务器IP：\n");
		String minetIP = login.disip;

		System.out.print("请输入服务器端口：\n");// 目前我们设置的是4444
		int minetPort = Integer.parseInt(login.disport);

		frame.setVisible(false);

		miroUI frame1 = new miroUI();
		frame1.setVisible(true);

		Socket LinkSocket = new Socket(minetIP, minetPort); // 连接服务器的套接字

		String miroIP = LinkSocket.getInetAddress().toString();// 获得客户端IP地址

		DataOutputStream miroOutStr = new DataOutputStream(
				LinkSocket.getOutputStream());
		DataInputStream miroInStr = new DataInputStream(
				LinkSocket.getInputStream());

		datePackage messagePackage = new datePackage();

		// 发送登录包
		String b = messagePackage.login(mironame, miroPort, p2pPort);
		miroOutStr.writeUTF(b);

		// 启动接收服务器消息线程
		recieve cs = new recieve(miroInStr);
		Thread ct = new Thread(cs);
		ct.start();

		// 启动发送消息给服务器线程
		send cs2 = new send(miroOutStr, mironame);
		Thread ct2 = new Thread(cs2);
		ct2.start();

		String peerPort = "";
		String peerIP = "";
		String peerName = "";

		// 启动p2p接收方线程
		p2preciever cs3 = new p2preciever(p2pPort, miroIP, mironame, peerIP,
				peerPort, peerName);
		Thread ct3 = new Thread(cs3);
		ct3.start();

		// 启动p2p发送方线程
		while (true) {
			if (p2pSend.sendflag) {
				String get1 = p2pSend.get;
				
				p2p.textField.append(get1);
				p2pSend.sendflag = false;
			}
			if (miroUI.freshflag) {
				miroUI.list.setModel(new AbstractListModel() {

					public int getSize() {
						return recieve.values.length;
					}

					public Object getElementAt(int index) {
						return recieve.values[index];
					}
				});
				miroUI.list.setSelectedIndex(0);
				miroUI.freshflag = false;
			}
			if (miroUI.p2pflag) {
				p2p frame3 = new p2p();
				frame3.setVisible(true);
				miroUI.p2pflag = false;

				String[] pipei = new String[4];
				for (int i = 0; i < 4; i++) {
					pipei[i] = miroUI.choosep2p.split(" ")[i];
				}

				for (int k = 0; k < 4; k++) {
					System.out.println(pipei[k]);
				}

				p2psender cs4 = new p2psender(p2pPort, miroIP, mironame,
						pipei[1], pipei[3], pipei[0]);
				Thread ct4 = new Thread(cs4);
				ct4.start();

			}
		}
	}
}

// CS接收线程类
class recieve implements Runnable {
	DataInputStream miroInStr;
	static String[] values = new String[100];

	recieve(DataInputStream d) {

		miroInStr = d;
	}

	public void run() {
		try {
			while (true) { // 监听服务器

				String get = miroInStr.readUTF();
				System.out.println("this is get： " + get);
				if (get.substring(0, 15).equals("wownisuangewhat")) {
					int count = 0;
					int flag = 16;
					for (int i = 16; i < get.length(); i++) {
						if (get.charAt(i) == '\r' && get.charAt(i + 1) == '\n') {

							values[count] = get.substring(flag, i);
							flag = i;
							count++;
						}

					}
					miroUI.freshflag = true;

				} else {

					miroUI.textField.append(get);
				}

			}
		} catch (Exception e) {
		}
	}
}

// CS发送线程类
class send implements Runnable {
	DataOutputStream miroOutStr;
	String mironame;
	String peerPort = "";
	String peerIP = "";
	String peerName = "";
	String p2pPort = "";
	String miroIP = "";

	send(DataOutputStream d, String mironame_) {

		miroOutStr = d;
		mironame = mironame_;
	}

	public void run() {
		try {
			datePackage messagePackage = new datePackage();
			Scanner str = new Scanner(System.in);
			while (true) {

				if (miroUI.closeflag == true) {
					String leave = messagePackage.disconnect(mironame);
					miroOutStr.writeUTF(leave);
					miroUI.closeflag = false;
				}

				if (miroUI.flag) {
					String message = miroUI.message;
					message = messagePackage.c2s_message(message, mironame);
					miroOutStr.writeUTF(message);

					miroUI.textField_1.setText("");
					miroUI.flag = false;
				}

			}

		} catch (Exception e) {
		}
	}
}

// p2p接收方线程类
class p2preciever implements Runnable {

	String mironame;
	String p2pPort;
	String miroIP;

	String peerIP;
	String peerPort;
	String peerName;

	p2preciever(String p2pPort_, String miroIP_, String mironame_,
			String peerIP_, String peerPort_, String peerName_) {

		mironame = mironame_;
		p2pPort = p2pPort_;
		miroIP = miroIP_;
		peerIP = peerIP_;
		peerPort = peerPort_;
		peerName = peerName_;

	}

	public void run() {
		try {
			ServerSocket MyListener = new ServerSocket(
					Integer.parseInt(p2pPort));
			Socket LinkSocket = null;
			LinkSocket = MyListener.accept();

			p2p frame4 = new p2p();
			frame4.setVisible(true);

			DataOutputStream OutStr = new DataOutputStream(
					LinkSocket.getOutputStream());
			DataInputStream InStr = new DataInputStream(
					LinkSocket.getInputStream());

			// 启动发送线程
			p2pSend cs5 = new p2pSend(OutStr, mironame, LinkSocket, InStr);
			Thread ct5 = new Thread(cs5);
			ct5.start();

			// 启动接收线程
			p2pRecieve cs6 = new p2pRecieve(InStr, OutStr, LinkSocket);
			Thread ct6 = new Thread(cs6);
			ct6.start();

		} catch (Exception e) {
		}
	}
}

// p2p发送方线程类
class p2psender implements Runnable {

	String mironame;
	String p2pPort;
	String miroIP;

	String peerIP;
	String peerPort;
	String peerName;

	p2psender(String p2pPort_, String miroIP_, String mironame_,
			String peerIP_, String peerPort_, String peerName_) {

		mironame = mironame_;
		p2pPort = p2pPort_;
		miroIP = miroIP_;
		peerIP = peerIP_;
		peerPort = peerPort_;
		peerName = peerName_;
	}

	public void run() {
		try {
			Socket LinkSocket = new Socket(peerIP, Integer.parseInt(peerPort));
			DataOutputStream OutStr = new DataOutputStream(
					LinkSocket.getOutputStream());
			DataInputStream InStr = new DataInputStream(
					LinkSocket.getInputStream());

			// 启动发送线程
			p2pSend cs7 = new p2pSend(OutStr, mironame, LinkSocket, InStr);
			Thread ct7 = new Thread(cs7);
			ct7.start();

			// 启动接收线程
			p2pRecieve cs8 = new p2pRecieve(InStr, OutStr, LinkSocket);
			Thread ct8 = new Thread(cs8);
			ct8.start();

		} catch (Exception e) {
		}
	}
}
