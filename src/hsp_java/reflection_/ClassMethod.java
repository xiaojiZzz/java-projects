package hsp_java.reflection_;

import java.lang.reflect.Field;

@SuppressWarnings({"all"})
//演示 Class类 的常用方法
public class ClassMethod {
    public static void main(String[] args) throws Exception {
        String classAllPath = "hsp_java.reflection_.Car";
        //获取到 Car类 对应的 Class对象
        //<?>表示不确定的Java类型
        Class<?> cls = Class.forName(classAllPath);
        //输出cls
        System.out.println(cls);//显示 cls对象 是那个类的 Class对象
        System.out.println(cls.getClass());//输出cls的运行类型
        //得到包名
        System.out.println(cls.getPackage().getName());//包名
        //得到全类名
        System.out.println(cls.getName());
        //通过cls创建对象实例
        Car car = (Car) cls.getDeclaredConstructor().newInstance();
        System.out.println(car); //car.toString()
        //通过反射获取属性 brand
        Field brand = cls.getField("brand");
        System.out.println(brand.get(car)); //需要属性是 public
        //通过反射给属性赋值
        brand.set(car, "奔驰");
        System.out.println(car);
        System.out.println(brand.get(car));
        //遍历所有字段
        Field[] fields = cls.getFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }

    }
}
