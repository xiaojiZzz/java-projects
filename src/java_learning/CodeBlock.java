package java_learning;


public class CodeBlock {
    public static void main(String[] args) {

        Movie movie = new Movie("xiaoji", 99);
        Movie.say();
        Movie movie2 = new Movie("xiaojixiao", 89);
    }
}

class Movie {
    private String name;
    private double price;

    public Movie(String name, double price) {
        System.out.println("构造器Movie(String name, double price)被调用");
        this.name = name;
        this.price = price;
    }
    { // static可以省略 存在时，如果再创建一个对象，不会再次调用此代码快，下面的语句就不会执行
        System.out.println("电影开始啦");
    }; // 分号可以省略
    static {
        System.out.println("看看显示次序");
    }
    public static void say() {
        System.out.println("hello");
    }
}

class Test_Block {
    public static void main(String[] args) {
        A a = new A();
    }
}

class A{
    private static int n1 = getN1();

    public A() {
        System.out.println("构造器被调用");
    }

    {
        System.out.println("加油");
    }
    private int n2 = getN2();

    static {
        System.out.println("静态代码块被调用");

    }

    //    private static int n1 = getN1();
    public static int getN1() {
        System.out.println("getN1被调用");
        return 100;
    }

    public int getN2() {
        System.out.println("getN2被调用");
        return 222;
    }
}
// 构造器最后被调用
// 静态初始化和静态代码块优先级一样，取决于定义顺序，之后执行普通代码块和普通初始化（两者优先级一样，处理顺序同静态）