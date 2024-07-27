package hsp_java.hsp_projects.chatsystem.qqserver.service;

import hsp_java.hsp_projects.chatsystem.qqcommon.Message;
import hsp_java.hsp_projects.chatsystem.qqcommon.MessageType;
import hsp_java.hsp_projects.chatsystem.qqserver.utils.Utility;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

public class SendNewsToAllClients implements Runnable{

    @Override
    public void run() {

        while (true) {
            System.out.println("请输入服务器要推送的消息/新闻(输入 exit 表示退出推送服务）：");
            String news = Utility.readString(100);
            if ("exit".equals(news)) {
                break;
            }
            //构建消息进行群发
            Message message = new Message();
            message.setSender("服务器");
            message.setContent(news);
            message.setMesType(MessageType.MESSAGE_TO_ALL_MES);
            message.setSendTime(new Date().toString());
            System.out.println("服务器推送消息给所有人说：" + news);

            //遍历所有的用户线程，得到socket，转发消息
            HashMap<String, ServerConnectClientThread> hm = ManageClientThreads.getHm();
            Iterator<String> iterator = hm.keySet().iterator();
            while (iterator.hasNext()) {
                String onlineUserId = iterator.next();
                try {
                    ObjectOutputStream oos = new ObjectOutputStream(hm.get(onlineUserId).getSocket().getOutputStream());
                    oos.writeObject(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
