package hsp_java.single_mode;

/**
 * 顾名思义，他是一个饿汉，他很勤快就怕自己饿着。他总是先把食物准备好，什么时候需要吃了，他随时拿来吃，不需要临时去搞食物。
 * 即饿汉式在一开始类加载的时候就已经实例化，并且创建单例对象，以后只管用即可。
 */
// 单例模式：饿汉式
public class SingleMode01 {
    public static void main(String[] args) {
        GirlFriend myWife = GirlFriend.getInstance();
        GirlFriend myWife2 = GirlFriend.getInstance();
        System.out.println(myWife);
        System.out.println(myWife2);
        System.out.println();
    }
}

class GirlFriend {

    private String name;
    private static GirlFriend gf = new GirlFriend("gugu"); // 注意为static

//    @Override
//    public String toString() {
//        return "GirlFriend{" +
//                "name='" + name + "'}";
//    }

    // 保证只能有一个女朋友
    // 步骤
    // 1.将构造器私有化
    // 2.在类的内部直接创建
    // 3.提供一个public方法，返回gf对象
    private GirlFriend(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static GirlFriend getInstance() {
        return gf;
    }
}