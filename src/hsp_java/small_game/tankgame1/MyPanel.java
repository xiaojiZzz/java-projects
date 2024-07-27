package hsp_java.small_game.tankgame1;

import javax.swing.*;
import java.awt.*;

// 坦克大战的绘图区域
public class MyPanel extends JPanel {
    // 定义我的坦克
    Hero hero = null;

    public MyPanel() {
        hero = new Hero(100, 100); // 初始化自己的坦克
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);
        drawTank(hero.getX(), hero.getY(), g, 0, 0);
    }

    // 编写方法画出坦克
    public void drawTank(int x, int y, Graphics g, int direct, int type) { // direct 表示上下左右移动，type 表示敌我类型

        switch (type) {
            case 0: // 我们的坦克
                g.setColor(Color.cyan);
                break;
            case 1: //敌人的坦克
                g.setColor(Color.yellow);
                break;
        }
        // 根据坦克的方向，来绘制坦克
        switch (direct) {
            case 0: // 向上时候的坦克
                // 坦克各部分，组成坦克
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 30, y, 10, 60, false);
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y + 30, x + 20, y);
                break;
            default:
                System.out.println("暂时没有处理");
        }
    }
}
