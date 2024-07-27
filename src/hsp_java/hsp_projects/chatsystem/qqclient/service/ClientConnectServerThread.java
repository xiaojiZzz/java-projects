package hsp_java.hsp_projects.chatsystem.qqclient.service;

import hsp_java.hsp_projects.chatsystem.qqcommon.Message;
import hsp_java.hsp_projects.chatsystem.qqcommon.MessageType;

import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

//客户端连接到服务器端的线程
public class ClientConnectServerThread extends Thread {

    //该线程需要持有Socket
    private Socket socket;

    public ClientConnectServerThread(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        //因为Thread需要在后合和服务器通信，因此我们while循环
        while (true) {

            try {
                System.out.println("客户端线程，等待读取服务端发送的消息");
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                //如果服务器没有发送消息，就会阻塞在这
                Message message = (Message) ois.readObject();

                //判断 Message 类型，做出相应的处理
                if (message.getMesType().equals(MessageType.MESSAGE_RET_ONLINE_FRIEND)) { //得到用户列表
                    //规定：在线用户用" "隔开
                    String[] onlineUsers = message.getContent().split(" ");
                    System.out.println("\n============当前在线用户列表============");
                    for (int i = 0; i < onlineUsers.length; i++) {
                        System.out.println("用户: " + onlineUsers[i]);
                    }
                } else if (message.getMesType().equals(MessageType.MESSAGE_COMM_MES)) { //普通的聊天消息
                    //把从服务器转发的消息，显示到控制台即可
                    System.out.println("\n" + message.getSender() + " 对 " + message.getGetter() + " 说 " +
                            message.getContent());
                } else if (message.getMesType().equals(MessageType.MESSAGE_TO_ALL_MES)) { //群发的聊天消息
                    //把从服务器转发的消息，显示到控制台即可
                    System.out.println("\n" + message.getSender() + " 对大家说 " + message.getContent());
                } else if (message.getMesType().equals(MessageType.MESSAGE_FILE_MES)) { //收到文件
                    System.out.println("\n" + message.getSender() + " 给 " + message.getGetter() + " 发文件："
                            + message.getSrc() + " 到我们电脑目录：" + message.getDest());

                    //取出message中的字节数组，通过文件输出流到磁盘
                    FileOutputStream fileOutputStream = new FileOutputStream(message.getDest());
                    fileOutputStream.write(message.getFileBytes());
                    fileOutputStream.close();
                    System.out.println("\n文件保存成功~");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
