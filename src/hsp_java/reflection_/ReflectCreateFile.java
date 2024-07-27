package hsp_java.reflection_;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

//利用反射创建文件
public class ReflectCreateFile {
    public static void main(String[] args) throws Exception{

        Class<?> fileCls = Class.forName("java.io.File");
        Constructor<?>[] declaredConstructors = fileCls.getDeclaredConstructors();
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            System.out.println(declaredConstructor);
        }
        Constructor<?> declaredConstructor = fileCls.getDeclaredConstructor(String.class);
        String filePath = "/Users/jijunwei/Downloads/xiaoji";
        Object file = declaredConstructor.newInstance(filePath);
        Method createNewFile = fileCls.getMethod("createNewFile");
        createNewFile.invoke(file);
        System.out.println("创建文件成功");
    }
}
