package miro;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

// p2p发送线程（注：发送方接收方都可调用）
public class p2pSend implements Runnable {

	public static String get;
	public static boolean sendflag = false;
	/*
	 * SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss");//设置日期格式 Date now
	 * = new Date(); String time = df.format(now);
	 */

	Socket LinkSocket;
	DataOutputStream OutStr;
	String mironame;
	DataInputStream InStr;
	int flag = 0;

	// flag=1; //发送文件按钮触发
	p2pSend(DataOutputStream OutStr_, String mironame_, Socket LinkSocket_,
			DataInputStream InStr_) {
		LinkSocket = LinkSocket_;
		OutStr = OutStr_;
		mironame = mironame_;
		InStr = InStr_;

	}

	public void run() {
		try {
			datePackage messagePackage = new datePackage();
			Scanner str = new Scanner(System.in);

			while (true) {
				if (p2p.fileflag) {
					flag = 1;
					p2p.fileflag = false;

				}
				if (flag == 0) {
					if (p2p.chatflag == true) {

						/**/String message = p2p.message; // 这句话就是用户输入的地方

						get = mironame + "说： "
								+ "\r\n" + "            " + message + "\r\n";

						System.out.print(get);

						/*
						 * if (p2p.count2 != 3) { p2p.record2[p2p.count2] = get;
						 * p2p.count2++; } else { p2p.record2[0] =
						 * p2p.record2[1]; p2p.record2[1] = p2p.record2[2];
						 * p2p.record2[2] = get; }
						 * 
						 * 
						 * String record = p2p.record2[0]; for (int i = 1; i <
						 * 3; i++) { if (p2p.record2[i] != null) record +=
						 * p2p.record2[i]; }
						 * 
						 * // System.out.print("66666");
						 * p2p.textArea.setText(get); //
						 * System.out.print("77777");
						 */

						message = messagePackage.p2p_message(message, mironame);
						System.out.print(message);
						OutStr.writeUTF(message);
						sendflag = true;
						p2p.textField_1.setText("");
						p2p.chatflag = false;
					}
				}

				else {

					OutStr.writeUTF("mimamimamimayoushimima");// 发这个消息告诉对方我要向你传文件了，快开启接收线程

					// String filepass=str.nextLine();
					/**/String filepass = p2p.fileString; // 输入文件地址地址如c:\\FoxitReader_CHS.rar

					String str1 = new String(filepass.getBytes(), "GBK");
				//	str1 = str.nextLine();
					File file = new File(str1);
					System.out.println("成功get");
					FileInputStream fis = new FileInputStream(file);
					// OutputStr eam fileoutStr = LinkSocket.getOutputStream();
					String fileName = file.getName();
					OutStr.flush();
					OutStr.write(fileName.getBytes()); // 发送文件名
					System.out.println("成功get123");
					{
						byte[] bufFile = new byte[1024];
						int len = 0;
						while (true) {
							len = fis.read(bufFile);
							OutStr.flush();
							if (len != -1) {
								OutStr.write(bufFile, 0, len); // 将从硬盘上读取的字节数据写入socket输出流
							} else {
								break;
							}
						}
					}
					flag = 0;
					// LinkSocket.shutdownOutput();
					fis.close();
					// OutStr.close();

				}
			}
		} catch (Exception e) {
		}
	}
}
