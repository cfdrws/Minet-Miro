package miro;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.Socket;

// p2p接收线程（注：发送方接收方都可调用）
public class p2pRecieve implements Runnable {

	 public static String get;
	Socket LinkSocket;
	// String peerName;
	DataInputStream InStr;
	DataOutputStream OutStr;
	int flag = 0;

	p2pRecieve(DataInputStream InStr_, DataOutputStream OutStr_,
			Socket LinkSocket_) {

		LinkSocket = LinkSocket_;
		InStr = InStr_;
		OutStr = OutStr_;

	}

	public void run() {
		try {
			while (true) {
							 
				get = InStr.readUTF();
				if (get.equals("mimamimamimayoushimima")) {
					// InputStream sockIn=
					// LinkSocket.getInputStream();//定义socket输入流,接收客户端的信息
					byte[] bufName = new byte[1024];
					int lenInfo = 0;
					lenInfo = InStr.read(bufName); // 获取文件名
					String fileName = new String(bufName, 0, lenInfo);
					File dir = new File("d:\\"); // 存到D盘根目录
					File file = new File(dir + fileName);
					if (file.createNewFile())
						OutStr.writeUTF("FileSendNow"); // 告诉客户端,开始传送数据吧

					FileOutputStream fos = new FileOutputStream(file); // 用来写入硬盘
					byte[] bufFile = new byte[1024 * 1024]; // 接收数据的缓存
					int len = 0;
					while (true) {
						len = InStr.read(bufFile); // 接收数据
						if (len != -1) {
							fos.write(bufFile, 0, len); // 写入硬盘文件
						} else {
							break;
						}
					}
					bufName = null;
					System.out.print("文件已经接收完毕！");
				}
				if (!get.equals("mimamimamimayoushimima")){
					System.out.println(get); // 打印输出的地方							     
				p2p.textField.append(get);												  					
													
				}
				
				}
			
		} catch (Exception e) {
		}
	}
}
