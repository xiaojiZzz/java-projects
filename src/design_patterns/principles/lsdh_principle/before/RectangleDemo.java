package design_patterns.principles.lsdh_principle.before;

//正方形不是长方形，违反了里氏代换原则
public class RectangleDemo {
    public static void main(String[] args) {

        //创建长方形对象
        Rectangle rectangle = new Rectangle();
        //设置长和宽
        rectangle.setWidth(10);
        rectangle.setLength(20);
        //调用resize方法进行扩宽
        resize(rectangle);
        printLengthAndWidth(rectangle);
        System.out.println("=======================");
        //创建正方形对象
        Square square = new Square();
        //设置长和宽
        rectangle.setLength(10);
        //调用resize方法进行扩宽
        resize(square);
        printLengthAndWidth(square);
    }

    //扩宽方法
    public static void resize(Rectangle rectangle) {
        //判断宽如果比长小，进行扩宽操作
        while (rectangle.getWidth() <= rectangle.getLength()) {
            rectangle.setWidth(rectangle.getWidth() + 1);
        }


    }

    //打印长和宽
    public static void printLengthAndWidth(Rectangle rectangle) {
        System.out.println(rectangle.getLength());
        System.out.println(rectangle.getWidth());
    }
}
