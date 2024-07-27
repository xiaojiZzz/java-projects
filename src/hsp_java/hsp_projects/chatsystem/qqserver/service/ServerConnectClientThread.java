package hsp_java.hsp_projects.chatsystem.qqserver.service;

import hsp_java.hsp_projects.chatsystem.qqcommon.Message;
import hsp_java.hsp_projects.chatsystem.qqcommon.MessageType;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;

//该类的对象和客户端保持通信
public class ServerConnectClientThread extends Thread {

    //该线程需要持有Socket
    private Socket socket;
    //连接到服务器的用户id
    private String userId;

    public ServerConnectClientThread(Socket socket, String userId) {
        this.socket = socket;
        this.userId = userId;
    }

    public Socket getSocket() {
        return socket;
    }

    @Override
    public void run() { //可以发送和接收消息

        while (true) {
            try {
                System.out.println("服务端和客户端 " + userId + " 保持通信，读取数据...");
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                //如果服务器没有发送消息，就会阻塞在这
                Message message = (Message) ois.readObject();

                //根据 Message 的类型，做相应的处理
                if (message.getMesType().equals(MessageType.MESSAGE_GET_ONLINE_FRIEND)) { //返回用户列表
                    System.out.println(message.getSender() + " 要在线用户列表");
                    String onlineUser = ManageClientThreads.getOnlineUser();

                    //返回message
                    //先构建一个Message对象，返回给客户端
                    Message message2 = new Message();
                    message2.setMesType(MessageType.MESSAGE_RET_ONLINE_FRIEND);
                    message2.setContent(onlineUser);
                    message2.setGetter(message.getSender());

                    //发送给客户端
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(message2);

                } else if (message.getMesType().equals(MessageType.MESSAGE_CLIENT_EXIT)) { //客户端要退出程序
                    System.out.println(message.getSender() + " 退出程序");
                    //从线程集移除
                    ManageClientThreads.removeServerConnectClientThread(message.getSender());
                    socket.close(); //关闭连接
                    //退出线程
                    break;
                } else if (message.getMesType().equals(MessageType.MESSAGE_COMM_MES)) { //收到普通的聊天消息
                    //根据message获取getterId，然后在得到对应先线程
                    ServerConnectClientThread serverConnectClientThread =
                            ManageClientThreads.getServerConnectClientThread(message.getGetter());
                    //得到对应socket的对象输出流，将message对象转发给指定的容户端
                    ObjectOutputStream oos = new ObjectOutputStream(serverConnectClientThread.getSocket().
                            getOutputStream());
                    oos.writeObject(message); //转发消息，如果用户离线，可以把消息保存到数据库
                } else if (message.getMesType().equals(MessageType.MESSAGE_TO_ALL_MES)) {
                    //需要遍历管理线程的集合，把所有的线程的socket得到，然后把message进行转发即可
                    HashMap<String, ServerConnectClientThread> hm = ManageClientThreads.getHm();

                    Iterator<String> iterator = hm.keySet().iterator();
                    while (iterator.hasNext()) {
                        //取出在线用户id
                        String onlineUserId = iterator.next();
                        if (!onlineUserId.equals(message.getSender())) { //排除群发消息的用户

                            //进行message转发
                            ObjectOutputStream oos = new ObjectOutputStream(hm.get(onlineUserId).
                                    getSocket().getOutputStream());
                            oos.writeObject(message);

                        }
                    }
                } else if (message.getMesType().equals(MessageType.MESSAGE_FILE_MES)) {
                    ObjectOutputStream oos = new ObjectOutputStream(ManageClientThreads.
                            getServerConnectClientThread(message.getGetter()).getSocket().getOutputStream());
                    oos.writeObject(message);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
