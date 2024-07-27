package hsp_java.inetprograme;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SuppressWarnings({"all"})
public class API_ {
    public static void main(String[] args) throws UnknownHostException {

        //1.获取本机的InetAddress 对象
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost);

        //2.根据指定主机名获取InetAddress对象
        InetAddress host1 = InetAddress.getByName("xiaoji.local");
        System.out.println(host1);

        //3.根据域名返国InetAddress对象，比如www.baidu.com对象
        InetAddress host2 = InetAddress.getByName("www.baidu.com");
        System.out.println(host2);

        //4.通过InetAddress对象，获取对应的地址
        String hostAddress = host2.getHostAddress();
        System.out.println(hostAddress);
        String hostAddress1 = host1.getHostAddress();
        System.out.println(hostAddress1);

        //5.通过InetAddiress对象，获取对应的主机名/域名
        String hostName = host2.getHostName();
        System.out.println(hostName);
        String hostName1 = host1.getHostName();
        System.out.println(hostName1);

    }
}
