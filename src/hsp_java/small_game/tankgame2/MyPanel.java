package hsp_java.small_game.tankgame2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

@SuppressWarnings({"all"})
// 坦克大战的绘图区域
public class MyPanel extends JPanel implements KeyListener { // 监听键盘事件
    //定义我的坦克
    HeroTank hero = null;
    //定义敌人坦克，放入到 Vector 集合内（考虑多线程问题）
    Vector<EnemyTank> enemyTanks = new Vector<>();
    int enemyTankSize = 3;

    public MyPanel() {
        hero = new HeroTank(100, 100); // 初始化自己的坦克
        hero.setSpeed(2);
        //初始化敌人坦克
        for (int i = 0; i < enemyTankSize; i++) {
            //创建一个敌人的坦克
            EnemyTank enemyTank = new EnemyTank((100 * (i + 1)), 0);
            //给敌人坦克设置方向
            enemyTank.setDirect(2);
            enemyTanks.add(enemyTank);
        }

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);
        //画出自己的坦克
        drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 1);
        //画出敌人的坦克
        for (int i = 0; i < enemyTankSize; i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 0);
        }
    }

    // 编写方法画出坦克
    public void drawTank(int x, int y, Graphics g, int direct, int type) { // direct 表示上下左右移动，type 表示敌我类型

        switch (type) {
            case 0: // 敌人的坦克
                g.setColor(Color.cyan);
                break;
            case 1: // 我们的坦克
                g.setColor(Color.yellow);
                break;
        }
        // 根据坦克的方向，来绘制不同的坦克
        switch (direct) {
            case 0: // 向上时候的坦克
                // 坦克各部分，组成坦克
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 30, y, 10, 60, false);
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y + 30, x + 20, y);
                break;
            case 1: // 向右时候的坦克
                // 坦克各部分，组成坦克
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 30, y + 20, x + 60, y + 20);
                break;
            case 2: // 向下时候的坦克
                // 坦克各部分，组成坦克
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 30, y, 10, 60, false);
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y + 30, x + 20, y + 60);
                break;
            case 3: // 向做时候的坦克
                // 坦克各部分，组成坦克
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 30, y + 20, x, y + 20);
                break;
            default:
                System.out.println("暂时没有处理");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    // 处理 wdsa 键盘的输入
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) { //按下了 w 键
            hero.setDirect(0);
            hero.moveUp();
        } else if (e.getKeyCode() == KeyEvent.VK_D) { //按下了 d 键
            hero.setDirect(1);
            hero.moveRight();
        } else if (e.getKeyCode() == KeyEvent.VK_S) { //按下了 s 键
            hero.setDirect(2);
            hero.moveDown();
        } else if (e.getKeyCode() == KeyEvent.VK_A) { //按下了 a 键
            hero.setDirect(3);
            hero.moveLeft();
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
