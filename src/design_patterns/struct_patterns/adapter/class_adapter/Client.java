package design_patterns.struct_patterns.adapter.class_adapter;

public class Client {
    public static void main(String[] args) {
        //创建Computer对象
        Computer computer = new Computer();
        //读取SD卡中的数据
        String msg = computer.readSD(new SDCardImpl());
        System.out.println(msg);

        System.out.println("==========================");

        //使用该计算机读取TF卡中的数据，需要使用适配器
        String msg1 = computer.readSD(new SDAdapterTF());
        System.out.println(msg1);
    }
}
