package hsp_java.dynamicbinding_;

//动态绑定机制：1.当调用对象方法的时候，该方法会和该对象的内存地址/运行类型绑定
//2.当调用对象属性时，没有动态绑定机制，哪里声明，哪里使用。
public class DyBindDrive {
    public static void main(String[] args) {
        A a = new B();
//        a = null; 运行期报错 NullPointerException
        System.out.println(a.sum()); //40
        System.out.println(a.sum1()); //30
    }
}
//验证时：注释B的sum（）时，返回的get1（）是子类B的方法，尽管父类用this.get1（），也是同样的结果（this绑定的是子类）结果为 30，30
//注释B的sum1（）时，return    的i是父类的i，因为属性没有动态绑定机制，输出20，结果为 30，20