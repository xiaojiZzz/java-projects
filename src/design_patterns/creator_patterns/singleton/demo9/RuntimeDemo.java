package design_patterns.creator_patterns.singleton.demo9;

import java.io.InputStream;

public class RuntimeDemo {
    public static void main(String[] args) throws Exception {
        //获取Runtime类的对象
        Runtime runtime = Runtime.getRuntime();
        //调用runtime的方法exec，参数要的是一个命令
        Process process = runtime.exec("ifconfig");
        //调用process对象的获取输入流的方法
        InputStream is = process.getInputStream();
        
        byte[] bytes = new byte[1024 * 1024 * 100];
        //读取数据
        int len = is.read(bytes); //返回读到的字节个数
        System.out.println(new String(bytes, 0, len, "GBK"));
    }
}
