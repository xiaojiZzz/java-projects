package hsp_java.hsp_projects.chatsystem.qqserver.service;

import hsp_java.hsp_projects.chatsystem.qqcommon.Message;
import hsp_java.hsp_projects.chatsystem.qqcommon.MessageType;
import hsp_java.hsp_projects.chatsystem.qqcommon.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

//服务端，监听端口 9999，等待客户连接，并保持通信
public class QQServer {

    private ServerSocket ss = null;
    //创建一个集合，存放多个用户，如果是这些用户登录，就认为是合法的
    //这里我们也可以使用ConcurrentHashMap，可以处理并发的集合，没有线程安全问题
    //HashMap没有处理线程安全，因此在多线程情况下是不安全的
    //ConcurrentHashMap 处理的线程安全，即线程同步处理，在多线程情况下是安全
    private static ConcurrentHashMap<String, User> validUsers = new ConcurrentHashMap<>();

    static { //在静态代码块中初始化 validUsers

        validUsers.put("100", new User("100", "123456"));
        validUsers.put("200", new User("200", "123456"));
        validUsers.put("300", new User("300", "123456"));
        validUsers.put("至尊宝", new User("至尊宝", "123456"));
        validUsers.put("紫霞仙子", new User("紫霞仙子", "123456"));
        validUsers.put("菩提老祖", new User("菩提老祖", "123456"));

    }

    public QQServer() {
        //注意：端口可以写进配置文件
        try {
            System.out.println("服务端在9999号端口监听...");
            //启动推送消息线程
            new Thread(new SendNewsToAllClients()).start();
            ss = new ServerSocket(9999);

            while (true) { //当和某个客户端连接后，继续监听
                Socket socket = ss.accept();

                //得到和socket关联的输入流
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                User u = (User) ois.readObject(); //读取客户端发送的用户对象

                //得到和socket关联的输出流
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

                //创建一个Message对象，准备回复客户端
                Message message = new Message();

                //验证
                if (checkUser(u.getUserId(), u.getPasswd())) { //登陆成功
                    message.setMesType(MessageType.MESSAGE_LOGIN_SUCCEED);
                    oos.writeObject(message);

                    //创建一个线程，和客户端保持通信,该对象需要持有socket对象
                    ServerConnectClientThread serverConnectClientThread =
                            new ServerConnectClientThread(socket, u.getUserId());
                    serverConnectClientThread.start();
                    //把该线程对象，放入到一个集合中，进行管理
                    ManageClientThreads.addClientThread(u.getUserId(), serverConnectClientThread);

                } else { //登陆失败
                    System.out.println("用户 id=" + u.getUserId() + " pwd=" + u.getPasswd() + " 验证失败");
                    message.setMesType(MessageType.MESSAGE_LOGIN_FAIL);
                    oos.writeObject(message);
                    //关闭socket；
                    socket.close();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //如果服务器退出了while，说明服务器端不在监听，因此关闭ServerSocket
            try {
                ss.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //验证用户是否有效的方法
    private boolean checkUser(String userId, String passwd) {

        User user = validUsers.get(userId);
        if (user == null) { //说明 userId 没有存在于 validUsers 的 key 中
            return false;
        }
        if (!(user.getPasswd().equals(passwd))) { //userId正确，但是密码错误
            return false;
        }
        return true;
    }

}
