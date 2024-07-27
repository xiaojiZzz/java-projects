package hsp_java.inetprograme.udp;

import java.io.IOException;
import java.net.*;

@SuppressWarnings({"all"})
//发送端B
public class UDPSenderB {
    public static void main(String[] args) throws IOException {

        //1.创建DatagramSocket対象，准备在9998端口接收数据
        DatagramSocket socket = new DatagramSocket(9998);

        //2.将需要发送的数据，封装到DatagramPacket对象
        byte[] data = "hello 明天吃火锅".getBytes();
        //这里用 InetAddress.getLocalHost() 是因为都在一台机器中，不然用 InetAddress.getByName("ip地址")
        DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getLocalHost(), 9999);

        //3.发送数据
        socket.send(packet);

        //4.接收信息
        //构建一个DatagramPacket对象，准备接收数据
        //udp一个数据包最大64KB
        byte[] buf = new byte[1024];
        packet = new DatagramPacket(buf, buf.length);

        //调用接收方法，将网络传输的DatagramPacket对象填充到packst中
        //当有数据包发送到本机的9998端口时，就会按收到数据，如果没有数据包发送到本机的9998端口，就会阻寨等待。
        System.out.println("发送端B等待接收数据");
        socket.receive(packet);

        //可以把packet进行拆包，取出数据，并显示
        int length = packet.getLength(); //实际接收到的数据长度，不一定是1024
        data = packet.getData();
        String s = new String(data, 0, length);
        System.out.println(s);

        //5.关闭资源
        socket.close();
        System.out.println("发送端B退出");

    }
}
