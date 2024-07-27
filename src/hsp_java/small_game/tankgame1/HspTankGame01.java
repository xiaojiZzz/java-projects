package hsp_java.small_game.tankgame1;

import javax.swing.*;

public class HspTankGame01 extends JFrame {

    MyPanel mp = null;
    public static void main(String[] args) {

        HspTankGame01 hspTankGame01 = new HspTankGame01();

    }

    public HspTankGame01() {
        mp = new MyPanel();
        this.add(mp);
        this.setSize(1000, 750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
