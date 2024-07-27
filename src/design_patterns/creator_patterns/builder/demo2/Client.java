package design_patterns.creator_patterns.builder.demo2;

public class Client {
    public static void main(String[] args) {

        //创建手机对象
        Phone phone = new Phone.Builder()
                .cpu("Intel")
                .screen("三星")
                .memory("金士顿")
                .mainboard("华硕")
                .build();

        System.out.println(phone);
    }
}
