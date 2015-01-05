package minet;
import java.net.Socket;
import java.util.List;


//date struct -share date
class shareList {
	String miroSocketInfList; // 在线用户信息列表，形式：username + ";" + miroIP + ";" + port + ";"
	static List<Socket> socketList;// socket list

	shareList(String m, List<Socket> s) {
		miroSocketInfList = m;
		socketList = s;
	}
}
