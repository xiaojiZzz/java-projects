package hsp_java.inetprograme.upload;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

@SuppressWarnings({"all"})
//服务端
public class TCPFileUploadServer {
    public static void main(String[] args) throws Exception {

        //1.服务端在本机监听 8888 端口号
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("服务端在 8888 端口监听...");

        //2.等待连接
        Socket socket = serverSocket.accept();

        //3.读取客户端写入的数据
        //通过Sockst得到输入流
        BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
        byte[] bytes = StreamUtils.streamToByteArray(bis);

        //4.将得到的 bytes 数组，写入到指定的路径，就得到一个文件了
        String destFilePath = "/Users/jijunwei/JavaProjects/src/hsp_java/inetprograme/upload/照片.jpg";
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destFilePath));
        bos.write(bytes);
        bos.close();

        //5.向客户端回复"收到图片"
        //通过 socket 获取到输出流（字符）
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        writer.write("收到图片");
        writer.flush(); //把内容刷新到数据通道
        socket.shutdownOutput(); //设置写入结束标记

        //6.关闭其他相关的流
        writer.close();
        bis.close();
        socket.close();
        serverSocket.close();
    }
}
