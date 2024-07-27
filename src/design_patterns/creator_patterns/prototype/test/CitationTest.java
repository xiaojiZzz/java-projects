package design_patterns.creator_patterns.prototype.test;

//浅克隆
public class CitationTest {
    public static void main(String[] args) throws CloneNotSupportedException {

        //创建原型对象
        Citation citation = new Citation();
        //创建张三学生对象
        Student student = new Student();
        student.setName("张三");
        citation.setStu(student);

        //克隆奖状对象
        Citation citation1 = citation.clone();
        citation1.getStu().setName("李四");

        //设置名字
//        citation.setName("张三");
//        citation1.setName("李四");


        //调用show方法
        citation.show();
        citation1.show();
    }
}
