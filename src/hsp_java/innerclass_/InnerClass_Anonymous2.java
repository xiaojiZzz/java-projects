package hsp_java.innerclass_;

/**
 * class InnerClass_Anonymous2$1 extends Gu {
 *     public void say() {
 *         System.out.println("你好呀");
 *     }
 * }
 */
// 内部匿名类（类）extends
public class InnerClass_Anonymous2 {
    public static void main(String[] args) {
        f1(new Gu() {
            public void say() {
                System.out.println("hello");
            }
        });
    }

    public static void f1(Gu gu) {
        gu.say();
    }
}

class Gu {
    public void say() {

    }
}