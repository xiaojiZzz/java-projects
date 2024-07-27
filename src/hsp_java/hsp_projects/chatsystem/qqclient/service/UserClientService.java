package hsp_java.hsp_projects.chatsystem.qqclient.service;

import hsp_java.hsp_projects.chatsystem.qqcommon.Message;
import hsp_java.hsp_projects.chatsystem.qqcommon.MessageType;
import hsp_java.hsp_projects.chatsystem.qqcommon.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

//该类完成用户登陆和注册等
public class UserClientService {

    //因为我们可能在其他地方用使用 User 信息，因此作为成员属性
    private User u = new User();
    //因为我们可能在其他地方用使用 Socket 信息，因此作为成员属性
    private Socket socket;

    //根据 userId 和 pwd 到服务器验证该用户是否合法
    public boolean checkUser(String userId, String pwd) {

        boolean b = false;
        //创建User对象
        u.setUserId(userId);
        u.setPasswd(pwd);

        //连接到服务器。发送 u 对象
        try {
            socket = new Socket(InetAddress.getLocalHost(), 9999);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(u); //发送User对象

            //读取服务端回复的Message对象
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Message ms = (Message) ois.readObject();

            if (ms.getMesType().equals(MessageType.MESSAGE_LOGIN_SUCCEED)) { //登陆成功

                //创建一个和服务器保持通信的线程
                ClientConnectServerThread clientConnectServerThread = new ClientConnectServerThread(socket);
                //启动客户端线程
                clientConnectServerThread.start();
                //这里为了后面客户端的扩展，我们将线程放入到集合管理
                ManageClientConnectServerThread.addClientConnectServerThread(userId, clientConnectServerThread);
                b = true;

            } else { //登陆失败
                //如果登录失败，我们就不能启动和服务器通信的线程，关闭socket
                socket.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }

    //向服务器请求在线用户列表
    public void onlineFriendList() {

        //发送一个 Message，类型为 MESSAGE_GET_ONLINE_FRIEND
        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_GET_ONLINE_FRIEND);
        message.setSender(u.getUserId());

        //发送给服务器端
        try {
            //应该得到当前线程的 Socket 对应的 ObjectOutputScream 对象
            ObjectOutputStream oos = new ObjectOutputStream(ManageClientConnectServerThread.
                    getClientConnectServerThread(u.getUserId()).getSocket().getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logout() {
        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_CLIENT_EXIT);
        message.setSender(u.getUserId()); //指定用户id（可以不写）因为前面已经知道了id了

        //发送message
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(message);
            System.out.println(u.getUserId() + " 退出了系统");
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
