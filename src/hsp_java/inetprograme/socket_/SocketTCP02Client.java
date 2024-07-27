package hsp_java.inetprograme.socket_;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

@SuppressWarnings({"all"})
//客户端,使用字节流
public class SocketTCP02Client {
    public static void main(String[] args) throws IOException {
        //思路
        //1.连接服务端〔ip，端口）
        //连接本机的9999号端口，如果连接成功 返回 Socket 对象
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        System.out.println("客户端socket=" + socket.getClass());

        //2.连接上后，生成Socket，得到 socket.getOutputStream()
        //得到和 socket 对象关联的输出流对象
        OutputStream outputStream = socket.getOutputStream();

        //3.通过输出流，写入数据到数据通道
        outputStream.write("hello,server".getBytes());
        //需要表明已经写完
        socket.shutdownOutput();

        //4.获取和socket相关联的输入流，读取数据并显示
        InputStream inputStream = socket.getInputStream();
        byte[] buf = new byte[1024];
        int readLen = 0;
        while ((readLen = inputStream.read(buf)) != -1) {
            System.out.println(new String(buf, 0, readLen));
        }

        //5.关闭流对象和socket，必须关闭!!!
        inputStream.close();
        outputStream.close();
        socket.close();
        System.out.println("客户端退出...");
    }
}
