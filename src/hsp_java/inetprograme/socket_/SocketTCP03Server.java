package hsp_java.inetprograme.socket_;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

@SuppressWarnings({"all"})
//服务器端，使用字符流
public class SocketTCP03Server {
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

        //4.IO读取，使用字符流
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String s = bufferedReader.readLine();
        System.out.println(s);

        //5.获取socket相关联的输出流
        OutputStream outputStream = socket.getOutputStream();
        //使用字符输出流来回复信息
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write("hello,client 字符流");
        bufferedWriter.newLine(); //插入一个换行符，表示写入的内容结束，注意！！！这里要求对方要使用 readLine() 读取
        bufferedWriter.flush(); ////如果使用的字符流，需要手动刷新，否则数据不会写入数据通道

        //6.关闭流和socket,serverSocket
        bufferedWriter.close(); //关闭外层流，后打开的先关闭
        bufferedReader.close(); //关闭外层流
        socket.close();
        serverSocket.close();
        System.out.println("服务端退出");
    }
}
