package hsp_java.reflection_;

import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

//演示如何通过反射获取类的结构信息
public class ReflectionUtils {
    public static void main(String[] args) {


    }

    //第一组方法API
    @Test
    public void api_01() throws ClassNotFoundException {

        //得到Class对象
        Class<?> personCls = Class.forName("hsp_java.reflection_.Person");
        //getName：获取全类名
        System.out.println(personCls.getName()); //hsp_java.reflection_.Person
        //getSimpleName：获取简单类名
        System.out.println(personCls.getSimpleName()); //Person
        //getFields：获取所有public修饰的属性，包括本类以及父类的
        Field[] fields = personCls.getFields();
        for (Field field : fields) {
            System.out.println("本类以及父类的属性：" + field.getName());
        }
        //getDeclaredFields：获取本类的所有属性
        Field[] declaredFields = personCls.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println("本类的所有属性：" + declaredField.getName());
        }
        //getMethods：获取所有public修饰的方法，包括本类以及父类(A类和Object)的
        Method[] methods = personCls.getMethods();
        for (Method method : methods) {
            System.out.println("本类以及父类的方法：" + method.getName());
        }
        //getDeclaredMethods：获取本类的所有方法
        Method[] declaredMethods = personCls.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println("本类的所有方法：" + declaredMethod.getName());
        }
        //getConstructors：获取所有public修饰的构造器，包括本类但不包括父类
        Constructor<?>[] constructors = personCls.getConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println("本类的构造器：" + constructor.getName());
        }
        //getDeclaredConstructors：获取本类的所有构造器
        Constructor<?>[] declaredConstructors = personCls.getDeclaredConstructors();
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            System.out.println("本类的所有构造器：" + declaredConstructor.getName()); //只输出名字
        }
        //getPackage：以Package形式返回包信息
        System.out.println(personCls.getPackage()); //package hsp_java.reflection_
        //getSuperClass：以Class形式返回父类信息
        Class<?> superclass = personCls.getSuperclass();
        System.out.println("父类的Class对象：" + superclass); //class hsp_java.reflection_.A
        //getInterfaces：以Class[]形式返回接口信息
        Class<?>[] interfaces = personCls.getInterfaces();
        for (Class<?> anInterface : interfaces) {
            System.out.println("接口信息：" + anInterface);
        }
        //getAnnotations：以Annotation[]形式返回注解信息
        Annotation[] annotations = personCls.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println("注解信息：" + annotation);
        }

    }

    //第二组方法API
    @Test
    public void api_02() throws ClassNotFoundException {
        //得到Class对象
        Class<?> personCls = Class.forName("hsp_java.reflection_.Person");
        //getDeclaredFields：获取本类的所有属性以及修饰符值、该属性的类型
        //规定：默认修饰符是0，public是1，private是2，protected是4，static是8，final是16 如果有多个，就是值的相加
        Field[] declaredFields = personCls.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println("本类的所有属性：" + declaredField.getName() +
                    " 该属性的修饰符值 = " + declaredField.getModifiers() +
                    " 该属性的类型：" + declaredField.getType());
        }
    }

    //第三组方法API
    @Test
    public void api_03() throws ClassNotFoundException {
        //得到Class对象
        Class<?> personCls = Class.forName("hsp_java.reflection_.Person");
        //getDeclaredMethods：获取本类的所有方法
        //规定：默认修饰符是0，public是1，private是2，protected是4，static是8，final是16 如果有多个，就是值的相加
        Method[] declaredMethods = personCls.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println("本类的所有方法：" + declaredMethod.getName() +
                    " 该方法的访问修饰符值 = " + declaredMethod.getModifiers() +
                    " 该方法的返回类型" + declaredMethod.getReturnType());
            //输出当前这个方法的形参数组情况
            Class<?>[] parameterTypes = declaredMethod.getParameterTypes();
            for (Class<?> parameterType : parameterTypes) {
                System.out.println("该方法的形参类型 = " + parameterType);
            }
        }
    }

    //第四组方法API
    @Test
    public void api_04() throws ClassNotFoundException {
        //得到Class对象
        Class<?> personCls = Class.forName("hsp_java.reflection_.Person");
        //getDeclaredConstructors：获取本类的所有构造器
        Constructor<?>[] declaredConstructors = personCls.getDeclaredConstructors();
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            System.out.println("本类的所有构造器：" + declaredConstructor.getName()); //只输出名字
            //
            Class<?>[] parameterTypes = declaredConstructor.getParameterTypes();
            for (Class<?> parameterType : parameterTypes) {
                System.out.println("该构造器的形参类型 = " + parameterType);
            }
            System.out.println("---------------------------------");
        }
    }
}

interface IA {
}

interface IB {
}

class A {
    //父类属性
    public String hobby;

    //父类构造器
    public A() {
    }

    //父类方法
    public void hi() {
    }
}

@Deprecated
class Person extends A implements IA, IB {
    //属性
    public String name;
    protected static int age;
    String job;
    private double sal;

    //构造器
    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    private Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    //方法
    public void m1(String name, int age, double sal) {
    }

    protected String m2() {
        return null;
    }

    void m3() {
    }

    private void m4() {
    }
}
