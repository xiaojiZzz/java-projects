package design_patterns.struct_patterns.bridge;

public class Client {
    public static void main(String[] args) {

        //创建Mac系统对象
        OperatingSystem system = new Mac(new AviFile());
        //使用操作系统播放视频文件
        system.play("战狼3");
    }
}
