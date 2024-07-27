package hsp_java.inetprograme.socket_;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

@SuppressWarnings({"all"})
//服务器端，使用字节流
public class SocketTCP02Server {
    public static void main(String[] args) throws IOException {
        //思路
        //1.在本机的9999端口监听，等待连接
        //要求9999号端口没有被占用
        //细节：这个ServerSocket可以通过accept(）返回多个Socket[多个客户端连接服务器的并发]
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("服务器端在9999号端口监听，等待连接...");

        //2.当没有客户端连接9999端口时，程序会阻塞，等待连接
        //如果有客户端连接，则会返回Socket对象，程序继续
        Socket socket = serverSocket.accept();
        System.out.println("服务端socket=" + socket.getClass());

        //3.通过socket.getInputStream() 读取客户端写入到数据通道的数据，显示出来
        //如果客户端没有发送消息，将在这里阻塞
        InputStream inputStream = socket.getInputStream();

        //4.IO读取
        byte[] buf = new byte[1024];
        int readLen = 0;
        while ((readLen = inputStream.read(buf)) != -1) {
            System.out.println(new String(buf, 0, readLen));
        }

        //5.获取socket相关联的输出流
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("hello,client".getBytes());
        //表明已经写完
        socket.shutdownOutput();

        //6.关闭流和socket,serverSocket
        inputStream.close();
        outputStream.close();
        socket.close();
        serverSocket.close();
        System.out.println("服务端退出");
    }
}
