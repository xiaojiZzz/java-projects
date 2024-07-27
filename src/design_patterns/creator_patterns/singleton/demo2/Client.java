package design_patterns.creator_patterns.singleton.demo2;

public class Client {
    public static void main(String[] args) {
        //创建Singleton类的对象
        Singleton instance = Singleton.getInstance();
        Singleton instance1 = Singleton.getInstance();

        //判断是否是同一个对象
        System.out.println(instance == instance1);
    }
}
