package hsp_java.reflection_;

@SuppressWarnings({"all"})
//演示得到 Class对象 的方法
public class GetClass_ {
    public static void main(String[] args) throws Exception{

        //1.Class.forName()
        String classAllPath = "hsp_java.reflection_.Car"; //通过读取配置文件获取
        Class cls1 = Class.forName(classAllPath);
        System.out.println(cls1); //class hsp_java.reflection_.Car

        //2.类名.class 用于参数传递：Constructor constructor = cls.getConstructor(String.class);
        Class cls2 = Car.class;
        System.out.println(cls2); //class hsp_java.reflection_.Car

        //3.对象.getclass() 用于已经有对象实例了
        Car car = new Car();
        Class cls3 = car.getClass();
        System.out.println(cls3); //class hsp_java.reflection_.Car

        //4.通过类加载器(4种)来获取到类的Class对象
        //(1)先得到类加载器
        ClassLoader classLoader = car.getClass().getClassLoader();
        //(2)通过类加载器得到Class对象
//        Class cls4 = classLoader.loadClass("hsp_java.reflection_.Cat"); //class hsp_java.reflection_.Cat
        Class cls4 = classLoader.loadClass(classAllPath);
        System.out.println(cls4); //class hsp_java.reflection_.Car

        //cls1, cls2, cls3, cls4, 是同一个Class对象
        System.out.println(cls1.hashCode());
        System.out.println(cls2.hashCode());
        System.out.println(cls3.hashCode());
        System.out.println(cls4.hashCode());
        
        //5.基本数据类型的Class对象
        Class<Integer> integerClass = int.class;
        Class<Character> characterClass = char.class;
        Class<Boolean> booleanClass = boolean.class;
        System.out.println(integerClass); //int 自动装箱和自动拆箱的过程

        //6.基本数据类型对应的包装类，可以通过.TYPE得到Class类对象
        Class<Integer> type1 = Integer.TYPE;
        Class<Character> type2 = Character.TYPE;
        Class<Boolean> type3 = Boolean.TYPE;
        System.out.println(type1);

        //int，Integer所产生的int的Class对象，是同一个
        System.out.println(integerClass.hashCode());
        System.out.println(type1.hashCode());

    }
}
