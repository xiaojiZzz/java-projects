package hsp_java.event_;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// 演示小球通过键盘控制上下左右移动，从而讲解java的事件控制
public class BallMove extends JFrame{
    MyPanel mp = null;
    public static void main(String[] args) {
        BallMove ballMove = new BallMove();
    }

    public BallMove() {
        mp = new MyPanel();
        this.add(mp);
        this.setSize(400, 300);
        this.addKeyListener(mp); // 需要收到监听才行
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}

class MyPanel extends JPanel implements KeyListener {

    int x = 10;
    int y = 10;
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillOval(x, y, 20, 20);
    }

    // 有字符输出会被监听到，触发此方法
    @Override
    public void keyTyped(KeyEvent e) {

    }

    // 当某个键按下 此方法会被触发
    @Override
    public void keyPressed(KeyEvent e) {
//        System.out.println((char) e.getKeyCode() + "被按下");
        if (e.getKeyCode() == KeyEvent.VK_DOWN) { // KeyEvent.VK_DOWN 表示键盘上面向下的键
            y++;
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            y--;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            x--;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            x++;
        }
        this.repaint();
    }

    // 当某个键松开了，该方法会被触发
    @Override
    public void keyReleased(KeyEvent e) {

    }
}