package hsp_java.reflection_;

import java.lang.reflect.Field;

//演示反射操作属性
public class ReflectAccessProperty {
    public static void main(String[] args) throws Exception {

        //1.得到Student类对应的Class对象
        Class<?> studentClass = Class.forName("hsp_java.reflection_.Student");
        //2.创建一个对象
        Object o = studentClass.getDeclaredConstructor().newInstance();
        //3.使用反射得到age属性对象
        Field age = studentClass.getField("age");
        age.set(o, 88); //通过反射来设置年龄字段
        System.out.println(o);
        System.out.println(age.get(o)); //直接返回年龄
        //4.使用反射操作static/private属性
        Field name = studentClass.getDeclaredField("name");
        name.setAccessible(true); //对私有属性使用暴破
//        name.set(o, "xiaoji");
        //对于static属性，对象可以使用null表示，因为static跟类相关，且所有对象所持有的static属性都是共有的，不需要制定对象
        name.set(null, "xiaoji");
        System.out.println(o);
        System.out.println(name.get(o));
        System.out.println(name.get(null));
    }
}

class Student {
    public int age;
    private static String name;

    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}