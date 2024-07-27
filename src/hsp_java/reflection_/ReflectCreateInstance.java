package hsp_java.reflection_;

import java.lang.reflect.Constructor;

//利用反射创建对象
public class ReflectCreateInstance {
    public static void main(String[] args) throws Exception {

        //1.先获取到User类的Class对象
        Class<?> userClass = Class.forName("hsp_java.reflection_.User");
        //2.通过public的无参构造器创建实例
        Object o = userClass.getDeclaredConstructor().newInstance();
        System.out.println(o);
        //3.通过public的有参构造器创建实例    注：getDeclaredConstructor可以制定所有的构造器，而getConstructor只能制定public的
        Constructor<?> constructor = userClass.getConstructor(String.class);
        Object o1 = constructor.newInstance("xiaogu");
        System.out.println(o1);
        //4.通过非public的有参构造器创建实例 可以返回构造器，但是不能运行，必须使用暴破的方式：setAccessible(true)
        Constructor<?> declaredConstructor = userClass.getDeclaredConstructor(int.class, String.class);
        declaredConstructor.setAccessible(true); //暴破（暴力破解）：使用反射可以访问私有的构造器/方法/属性，反射面前都是纸老虎
        Object xiaogu = declaredConstructor.newInstance(22, "xiaogu");
        System.out.println(xiaogu);
    }
}

class User {
    private int age = 23;
    private String name = "xiaoji";

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    private User(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}