package minet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class minet {
    
	public static void main(String[] args) throws Exception {

		ServerSocket MyListener = new ServerSocket(4444);
		ArrayList<Socket> miroSocketList = new ArrayList<Socket>();	
		shareList shareList_ = new shareList("", miroSocketList);
		
		serverUI frame = new serverUI();
		frame.setVisible(true);
		
		while (true) {
			Socket LinkSocket = MyListener.accept();//该Socket对象绑定了客户程序的IP地址或端口号。
			miroSocketList.add(LinkSocket);		
			ThreadClass ClassObject = new ThreadClass( LinkSocket,shareList_);					
			Thread ClassThread = new Thread(ClassObject); 
			ClassThread.start();
		}
	}
}

class ThreadClass implements Runnable {

	int miroNum ;
	Socket LinkSocket ;
	shareList shareList_;
	String mironame = "";
	String port = "";
	String p2pPort = "";

	ThreadClass( Socket s, shareList l) {
		
		LinkSocket = s;
		shareList_ = l;
	}

	public void run() {
		
		try {
			String miroIP = LinkSocket.getInetAddress().toString(); 
			DataInputStream minetInStr = new DataInputStream(
					LinkSocket.getInputStream()); // 服务器获得的输入流
			DataOutputStream minetOutStr = new DataOutputStream(
					LinkSocket.getOutputStream()); // 服务器放出的输出流			
			while (true) {
				String minetInMess = minetInStr.readUTF(); // 读接收的消息
				  serverUI.textField.append(minetInMess);
				  serverUI.textField.append("\n");
				  System.out.println(minetInMess); 
				datePacketProcess(minetInMess,minetOutStr,miroIP.split("/")[1],shareList_,LinkSocket);
			}			
		} catch (Exception e) {
		}           
	}


void datePacketProcess(String minetInMess,DataOutputStream minetOutStr,String miroIP,
		                                  shareList shareList_,Socket miroSocket) throws IOException 
{
	 String first = minetInMess.split(" ")[0];   
     String second = minetInMess.split(" ")[1];  
     datePackage message = new datePackage();      
 
     if (first.equals("CS1.0")) {  
    	 
         if (second.equals("LOGIN")){                        
             mironame = minetInMess.split(" |\\\r\\\n")[2];   
             port = minetInMess.split(" |\\\r\\\n")[4];
             p2pPort =minetInMess.split(" |\\\r\\\n")[5];
             shareList_.miroSocketInfList = shareList_.miroSocketInfList + mironame + ";" + miroIP + ";" + port + ";"+p2pPort + ";";
            
             minetOutStr.writeUTF(message. status(1));                                 
             minetOutStr.writeUTF(message. list(shareList_.miroSocketInfList));
             
             for(int i = 0 , len= shareList_.socketList.size();i<len;++i){           
                   if (shareList_.socketList.get(i) != miroSocket) {               
                       DataOutputStream otherMiroGetMess = new DataOutputStream(shareList.socketList.get(i).getOutputStream());
                       otherMiroGetMess.writeUTF(message. update(mironame,1)); //发送更新信息
                       otherMiroGetMess.writeUTF(message. list(shareList_.miroSocketInfList));
                   }
             }
         }
         
         
         if (second.equals("LEAVE")) {        
             String thisclient = mironame + ";" + miroIP + ";" + port + ";"+p2pPort+";";
             String newlist;
             if (shareList_.miroSocketInfList.split(thisclient).length == 1)
            	 newlist = shareList_.miroSocketInfList.split(thisclient)[0];   
             else
            	 newlist = shareList_.miroSocketInfList.split(thisclient)[0] + shareList_.miroSocketInfList.split(thisclient)[1]; //这个地方不判断的话数组越界就直接抛出错误了        
             shareList_.miroSocketInfList= newlist;
             serverUI.textField.append(newlist);
             System.out.print(newlist);
             for(int i = 0 , len= shareList_.socketList.size();i<len;++i){   //减少
               if(shareList_.socketList.get(i) == miroSocket){  
            	   shareList_.socketList.remove(i);
            	   --len;
            	   --i;
               }  
               else;
             }
             System.out.print(shareList_.socketList.size());
             for(int i = 0 , len= shareList_.socketList.size();i<len;++i) {      //广播更新信息
                   DataOutputStream otherMiroGetMess = new DataOutputStream((shareList_.socketList.get(i)).getOutputStream());
                   System.out.print("55555");
                   otherMiroGetMess.writeUTF(message. update(mironame,0));
                   System.out.print("66666");
                   System.out.print(message. update(mironame,0));
                   serverUI.textField.append(message. update(mironame,0));
                   otherMiroGetMess.writeUTF(message. list(shareList_.miroSocketInfList));
                   System.out.print(message. list(shareList_.miroSocketInfList));
                   serverUI.textField.append(message. list(shareList_.miroSocketInfList));
                   //otherMiroGetMess.writeUTF(shareList_.socketList.size()+"");
             }
             miroSocket.close();
         }         
     }
     if (first.equals("mess")) {   
        
         for(int i = 0 , len= shareList_.socketList.size();i<len;++i){         
               DataOutputStream otherMiroGetMess = new DataOutputStream(shareList_.socketList.get(i).getOutputStream());
               otherMiroGetMess.writeUTF(message.s2c_message(minetInMess));               
         }
     }        
     else;
 }	
}
