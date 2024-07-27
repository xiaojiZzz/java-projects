package hsp_java.small_game.tankgame5;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

@SuppressWarnings({"all"})
public class HspTankGame05 extends JFrame {

    MyPanel mp = null;
    static Scanner myScanner = new Scanner(System.in);

    public static void main(String[] args) {

        HspTankGame05 hspTankGame02 = new HspTankGame05();

    }

    public HspTankGame05() {
        System.out.println("请输入选择1：新游戏2：继续上局");
        String key = myScanner.next();
        //将mp放入Thread 并启动
        mp = new MyPanel(key);
        Thread thread = new Thread(mp);
        thread.start();
        this.add(mp);
        this.setSize(1300, 750);
        this.addKeyListener(mp); //监听事件的发生
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        //在JFrame 中增加相应关闭窗口的处理
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Recorder.keepRecord();
                System.exit(0);
            }
        });
    }
}
