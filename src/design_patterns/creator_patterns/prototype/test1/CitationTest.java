package design_patterns.creator_patterns.prototype.test1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

//深克隆
public class CitationTest {
    public static void main(String[] args) throws Exception {

        //创建原型对象
        Citation citation = new Citation();
        //创建张三学生对象
        Student student = new Student();
        student.setName("张三");
        citation.setStu(student);

        //序列化，创建对象输出流对象
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("/Users/jijunwei/Downloads/b.txt"));
        //写对象
        oos.writeObject(citation);
        //释放资源
        oos.close();

        //创建对象输入流对象
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("/Users/jijunwei/Downloads/b.txt"));
        //读取对象
        Citation citation1 = (Citation) ois.readObject();
        //释放资源
        ois.close();

        Student stu = citation1.getStu();
        stu.setName("李四");

        citation.show();
        citation1.show();
    }
}
