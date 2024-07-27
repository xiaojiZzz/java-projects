package hsp_java.hsp_projects.chatsystem.qqclient.service;

import hsp_java.hsp_projects.chatsystem.qqcommon.Message;
import hsp_java.hsp_projects.chatsystem.qqcommon.MessageType;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;

//该类提供和消息相关的服务
public class MessageClientService {

    /**
     * @param content  私聊的内容
     * @param senderId 发送者
     * @param getterId 接收者
     */
    //私聊
    public void sendMessageToOne(String content, String senderId, String getterId) {

        //构建message
        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_COMM_MES); //普通的聊天消息
        message.setSender(senderId);
        message.setGetter(getterId);
        message.setContent(content);
        message.setSendTime(new Date().toString()); //设置发送时间

        System.out.println(senderId + " 对 " + getterId + " 说 " + content);

        //将message发送给服务端
        try {
            ObjectOutputStream oos = new ObjectOutputStream(ManageClientConnectServerThread.
                    getClientConnectServerThread(senderId).getSocket().getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param content  群发的内容
     * @param senderId 发送者
     */
    //群聊
    public void sendMessageToAll(String content, String senderId) {
        //构建message
        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_TO_ALL_MES); //群发的聊天消息
        message.setSender(senderId);
        message.setContent(content);
        message.setSendTime(new Date().toString()); //设置发送时间

        System.out.println(senderId + " 对大家说 " + content);

        //将message发送给服务端
        try {
            ObjectOutputStream oos = new ObjectOutputStream(ManageClientConnectServerThread.
                    getClientConnectServerThread(senderId).getSocket().getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
