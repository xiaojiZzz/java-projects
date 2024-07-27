package hsp_java.single_mode;

/**
 * 顾名思义，他是一个懒汉，他不愿意动弹。什么时候需要吃饭了，他就什么时候开始想办法搞点食物。
 * 即懒汉式一开始不会实例化，什么时候用就什么时候new，才进行实例化。
 */
@SuppressWarnings({"all"})
// 单例模式：懒汉式
public class SingleMode02 {
    public static void main(String[] args) {
        Cat xiaogu = Cat.getInstance();
        System.out.println(xiaogu);
    }
}

// 希望在程序运行过程中只创建一个Cat对象
class Cat {
    private String name;
    private static Cat cat;


    // 步骤
    // 1.构造器私有化
    // 2.定义一个static属性对象
    // 3.提供一个public的static方法

    private Cat(String name) {
        this.name = name;
    }

    public static Cat getInstance() {
        if (cat == null) {
            cat = new Cat("gugu");
        }
        return cat;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                '}';
    }
}
