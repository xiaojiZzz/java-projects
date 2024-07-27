package hsp_java.reflection_;

import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;

@SuppressWarnings({"all"})
//反射问题的引入
public class ReflectionQuestion {
    public static void main(String[] args) throws Exception {

        //根据配置文件re.properties指定信息，创建Cat对象并调用方法hi
        //传统方法：new一个
        //使用properties类，可以读写配置文件
        Properties properties = new Properties();
        properties.load(new FileInputStream("/Users/jijunwei/JavaProjects/src/hsp_java/reflection_/re.properties"));
        String classfullpath = properties.get("classfullpath").toString();
        String methodName = properties.get("method").toString();
        System.out.println("classfullpath = " + classfullpath);
        System.out.println("method = " + methodName);

        //new classfullpath() 行不通
        //使用反射机制来解决
        //1.加载类
        Class cls = Class.forName(classfullpath); //Class<?> cls = Class.forName(classfullpath); <?>表示不确定的java类型
        //2.通过 cls 对象，得到加载类 hsp_java.reflection_.Cat 的实例
        Object o = cls.getDeclaredConstructor().newInstance();
        System.out.println("o的运行类型是 = " + o.getClass()); //运行类型
        //3.通过 cls 对象，得到加载类 hsp_java.reflection_.Cat 的 methodName(hi()方法） 的方法对象，即在反射中，可以把方法视为对象
        Method method = cls.getMethod(methodName);
        //4.通过 method 来调用方法：通过方法对象来实现调用方法
        method.invoke(o);

        //获取Cat类的name字段
        //getField() 不能得到私有的属性
//        Field name = cls.getField("name"); //不行
        Field ageField = cls.getField("age");
        System.out.println(ageField.get(o)); //反射是反过来用的，以前是对象.字段，现在是字段对象.get(对象)

        //获取Cat类的构造器
        Constructor constructor = cls.getConstructor(); //()中可以指定构造器参数类型，现在是无参构造器
        System.out.println(constructor);
        Constructor constructor1 = cls.getConstructor(String.class); //这里的 String.class 就是String类的Class对象
        System.out.println(constructor1);
    }
}
