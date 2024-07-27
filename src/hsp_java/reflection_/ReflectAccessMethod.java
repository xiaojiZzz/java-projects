package hsp_java.reflection_;

import java.lang.reflect.Method;

//演示通过反射调用方法
public class ReflectAccessMethod{
    public static void main(String[] args) throws Exception{

        //1.得到Boss类对应的Class对象
        Class<?> bossCls = Class.forName("hsp_java.reflection_.Boss");
        //2.创建对象
        Object o = bossCls.getConstructor().newInstance();
        //3.调用public方法
        Method hi = bossCls.getDeclaredMethod("hi", String.class);
        hi.invoke(o, "xiaogu");
        //4.调用private static的方法
        Method say = bossCls.getDeclaredMethod("say", int.class, String.class, char.class);
        say.setAccessible(true);
//        Object o1 = say.invoke(o, 22, "xiaogu", '女');
        Object o1 = say.invoke(null, 22, "xiaogu", '女'); //static方法，返回值通通以Object接收，运行类型与方法中定义的返回类型一样
        System.out.println(o1);
        System.out.println(o1.getClass());
        //5.
    }
}

class Boss {
    public int age;
    private static String name;

    public Boss() {
    }

    private static String say(int n, String s, char c) {
        return n + " " + s + " " + c;
    }

    public void hi(String s) {
        System.out.println("hi " + s);
    }
}