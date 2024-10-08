package hsp_java.inetprograme.socket_;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

@SuppressWarnings({"all"})
//客户端，使用字符流
public class SocketTCP03Client {
    public static void main(String[] args) throws IOException {
        //思路
        //1.连接服务端〔ip，端口）
        //连接本机的9999号端口，如果连接成功 返回 Socket 对象
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        System.out.println("客户端socket=" + socket.getClass());

        //2.连接上后，生成Socket，得到 socket.getOutputStream()
        //得到和 socket 对象关联的输出流对象
        OutputStream outputStream = socket.getOutputStream();

        //3.通过输出流，写入数据到数据通道，使用字符流
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write("hello,server 字符流");
        bufferedWriter.newLine(); //插入一个换行符，表示写入的内容结束，注意！！！这里要求对方要使用 readLine() 读取
        bufferedWriter.flush(); //如果使用的字符流，需要手动刷新，否则数据不会写入数据通道

        //4.获取和socket相关联的输入流，读取数据并显示，使用字符流
        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String s = bufferedReader.readLine();
        System.out.println(s);

        //5.关闭流对象和socket，必须关闭!!!
        bufferedReader.close(); //关闭外层流，后打开的先关闭
        bufferedWriter.close(); //关闭外层流
        socket.close();
        System.out.println("客户端退出...");
    }
}
