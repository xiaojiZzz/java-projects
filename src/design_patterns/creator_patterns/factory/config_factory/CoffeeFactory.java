package design_patterns.creator_patterns.factory.config_factory;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

public class CoffeeFactory {

    //加载配置文件，获取配置文件中配置的全类名，并创建该类的对象进行存储
    //1.定义容器对象存储咖啡对象
    private static HashMap<String, Coffee> map = new HashMap<>();

    //2.加载配置文件，只需要加载一次
    static {
        //2.1创建Properties对象
        Properties p = new Properties();
        //2.2Co对象的load方法进行配置文件的加载
        try {
            p.load(new FileReader("src/design_patterns/creator_patterns/factory/bean.properties"));
            //从p集合中获取全类名并创建对象
            Set<Object> keys = p.keySet();
            for (Object key : keys) {
                String className = p.getProperty((String) key);
                Class<?> clazz = Class.forName(className);
                Coffee coffee = (Coffee) clazz.getConstructor().newInstance();
                //将名称和对象存储到容器中
                map.put((String) key, coffee);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Coffee createCoffee(String name) {
        return map.get(name);
    }
}
