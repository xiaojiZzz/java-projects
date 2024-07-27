package design_patterns.struct_patterns.adapter.object_adapter;

public class Client {
    public static void main(String[] args) {
        //创建Computer对象
        Computer computer = new Computer();
        //读取SD卡中的数据
        String msg = computer.readSD(new SDCardImpl());
        System.out.println(msg);

        System.out.println("==========================");

        //使用该电脑读取TF卡中的数据
        SDAdapterTF sdAdapterTF = new SDAdapterTF(new TFCardImpl());
        String msg1 = computer.readSD(sdAdapterTF);
        System.out.println(msg1);
    }
}
