package hsp_java.draw_;

import javax.swing.*;
import java.awt.*;

public class DrawCircle extends JFrame {

    private MyPanel mp = null;

    public static void main(String[] args) {
        new DrawCircle();
        System.out.println("程序结束运行");
    }

    public DrawCircle() {
        // 初始化面板
        mp = new MyPanel();
        this.add(mp);
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }
}


class MyPanel extends JPanel { // MyPanel 类似是画板

    @Override
    public void paint(Graphics g) { // g 类似是画笔
        super.paint(g); // 调用父类的方法完成初始化
        System.out.println("paint被调用");
        // 画一个圆
//        g.drawOval(10, 10, 100, 100);

        // 绘制不同的图形
//        g.drawLine(10, 10, 100, 100); // 画直线
//        g.drawRect(10, 10, 100, 100); // 画矩形
//        g.setColor(Color.BLUE);
//        g.fillRect(10, 10, 100, 100); // 填充矩形
//        g.setColor(Color.RED);
//        g.fillOval(10, 10, 100, 100); // 填充圆
        Image image = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("照片1.png")); // 画图片
        g.drawImage(image, 10, 10, 191, 198, this);
//        g.setColor(Color.BLUE);
//        g.setFont(new Font("楷体", Font.BOLD, 50));
//        g.drawString("北京你好", 100, 100); // 画字符串

    }
}