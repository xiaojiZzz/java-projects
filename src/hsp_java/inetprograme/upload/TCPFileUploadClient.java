package hsp_java.inetprograme.upload;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;

@SuppressWarnings({"all"})
//客户端
public class TCPFileUploadClient {
    public static void main(String[] args) throws Exception {

        //1.客户端连接服务端，得到Socket对象
        Socket socket = new Socket(InetAddress.getLocalHost(), 8888);

        //2.创建读取磁盘文件的输入流
        String filePath = "/Users/jijunwei/Downloads/图片.jpg";
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath));

        //bytes就是文件对应的字节数组
        byte[] bytes = StreamUtils.streamToByteArray(bis);

        //3.通过socket获取到输出流，将bytes数据发送给服务端
        BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
        bos.write(bytes); //将文件对应的字节数组的内容，写入到数据通道
        bis.close();
        socket.shutdownOutput(); //设置了写入数据的结束标记

        //4.接收从服务端收到的消息
        InputStream inputStream = socket.getInputStream();
        //使用utils中的方法
        String s = StreamUtils.streamToString(inputStream);
        System.out.println(s);

        //4.关闭相关的数据流
        inputStream.close();
        bos.close();
        socket.close();

    }
}
