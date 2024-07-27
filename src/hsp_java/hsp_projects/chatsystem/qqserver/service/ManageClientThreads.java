package hsp_java.hsp_projects.chatsystem.qqserver.service;

import java.util.HashMap;
import java.util.Iterator;

//用来管理客户端与服务器端通信的线程
public class ManageClientThreads {
    //我们把多个线程放入一个Hashmap集合，key 就是用户id，value 就是线程
    private static HashMap<String, ServerConnectClientThread> hm = new HashMap<>();

    public static HashMap<String, ServerConnectClientThread> getHm() {
        return hm;
    }

    //将某个线程加入集合
    public static void addClientThread(String userId, ServerConnectClientThread serverConnectClientThread) {
        hm.put(userId, serverConnectClientThread);
    }

    //将某个线程移除集合
    public static void removeServerConnectClientThread(String userId) {
        hm.remove(userId);
    }

    //通过userId，可以得到对应线程
    public static ServerConnectClientThread getServerConnectClientThread(String userId) {
        return hm.get(userId);
    }

    //返回在线用户列表，用户之间用空格相隔
    public static String getOnlineUser() {
        String onlineUserList = "";
        //遍历集合，得到map 的 key
        Iterator<String> iterator = hm.keySet().iterator();
        while (iterator.hasNext()) {
            onlineUserList += iterator.next() + " ";
        }
        return onlineUserList;
    }
}
