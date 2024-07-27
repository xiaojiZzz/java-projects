package hsp_java.innerclass_;

// 内部匿名类（接口）implements

/**
 * class InnerClass_Anonymous$1 implements IL {  //为运行类型
 *     @Override
 *     public void show() {
 *         System.out.println("你好呀");
 *     }
 * }
 */

public class InnerClass_Anonymous {
    public static void main(String[] args) {
        f1(new IL() {
            @Override
            public void show() {
                System.out.println("你好呀"); //这里修改输出内容不会像创建对象一样，影响其他的对象
            }
        });
    }

    public static void f1(IL il) {
        il.show();
    }
}


interface IL {
    void show();
}