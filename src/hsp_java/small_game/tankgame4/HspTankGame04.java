package hsp_java.small_game.tankgame4;

import javax.swing.*;

@SuppressWarnings({"all"})
public class HspTankGame04 extends JFrame {

    MyPanel mp = null;

    public static void main(String[] args) {

        HspTankGame04 hspTankGame02 = new HspTankGame04();


    }

    public HspTankGame04() {
        //将mp放入Thread 并启动
        mp = new MyPanel();
        Thread thread = new Thread(mp);
        thread.start();
        this.add(mp);
        this.setSize(1000, 750);
        this.addKeyListener(mp); //监听事件的发生
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
