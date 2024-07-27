package java_learning.serializable_;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

//演示没有实现序列化的内容，可以加transient，在序列化的时候，跳过此部分
public class Pond implements Serializable {

    transient private Duck duck = new Duck(); //注意 没有实现序列化，要么实现Serializable接口，要么使用transient

    public static void main(String[] args) {
        Pond myPond = new Pond();

        try {
            FileOutputStream fs = new FileOutputStream("Pond.ser");
            ObjectOutputStream os = new ObjectOutputStream(fs);

            os.writeObject(myPond);
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Duck {
}