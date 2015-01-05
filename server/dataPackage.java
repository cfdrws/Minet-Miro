Enter file contents here
package minet;
import java.text.SimpleDateFormat;
import java.util.Date;

public class datePackage {
	SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss");//设置日期格式  
	Date now = new Date(); 
    String time = df.format(now);    
    
    public String  login(String name,String port,String p2pPort){    
        String login;
        login = "CS1.0 LOGIN " + name +"\r\n"
                + "Port " + port +" "+p2pPort+"\r\n";
        return login;
    }
    
    public String status(int i){
        String status=null;
        if (i==1)
            status = "(" + time +")"+"\r\n"
                    + "【系统通知】您已成功登录，输入信息即可发送给所有在线用户。\r\n";
                    
      return status;

    }
 
    public String leave(String name){
       
        String underline = "CS1.0 LEAVE " + name +"\r\n"
        		 + "(" + time +")"+"\r\n";
        return underline;
    }
    
    public String update(String name,int state){
    	 String update;
       if(state==1)
        update ="(" + time +")"+"\r\n"+"【系统通知】用户 "+ name +" 上线！\r\n";
       else   update ="(" + time +")"+"\r\n"+"【系统通知】用户 "+ name +" 下线！\r\n";
        return update;
    }
    
    public String list(String list){
     
        String onlinelist = null;
        
        int count = list.split(";").length;
        for (int i = 0;i < count;i++){
        	if(i==0)
        		 onlinelist = list.split(";")[i];
        	else
            onlinelist = onlinelist + list.split(";")[i];
            if ((i+1)%4 == 0) onlinelist = onlinelist + "\r\n";
            else onlinelist = onlinelist + " ";
        }      
        onlinelist =  "wownisuangewhat\r\n"
             + onlinelist+"\r\n";
        return onlinelist;
    }
    
    public String  s2c_message(String message) {
    	  String message_ = message.split(" ")[1];    
    	  String name = message.split(" ")[2];    
    	  //int length = (name + "说： " + "(" + time +")" + "\r\n").length();
    	 // String space=null;
    	  //for(int i=0;i<=length;i++)space=space+" ";
        String m =        		
        		 "(" + time +")" +name + "说： " + "\r\n"
             +"            "
             + message_+ "\r\n";
        return m;
    }
    
    public String c2s_message(String message,String name) {
        String save ="mess"+" "+message+" "+name;
        return save;
    }
    public String disconnect(String name){
        String leave = "CS1.0 LEAVE " + name +"\r\n"
             +  "(" + time +")"  +"\r\n";
            
        return leave;
    }
    
    public String p2p_message(String message,String name){
        String message_ ="(" + time +")" +name + "说： " + "\r\n"
                +"            "
                + message+ "\r\n";
            
        return message_;
    }
}
