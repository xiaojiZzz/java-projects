package hsp_java.small_game.tankgame5;

import java.util.Vector;

@SuppressWarnings({"all"})
// 自己的坦克
public class HeroTank extends Tank {

    Shot shot = null;
    //可以发射多颗子弹
    Vector<Shot> shots = new Vector<>();

    public HeroTank(int x, int y) {
        super(x, y);
    }

    //射击
    public void shotEnemyTank() {

        //在面板上只能有五颗子弹
        if (shots.size() == 5) {
            return;
        }

        //创建Shot对象,根据Hero对象的位置和方向创建
        switch (getDirect()) { //Hero的方向
            case 0: //向上
                shot = new Shot(getX() + 20, getY(), 0);
                break;
            case 1: //向右
                shot = new Shot(getX() + 60, getY() + 20, 1);
                break;
            case 2: //向下
                shot = new Shot(getX() + 20, getY() + 60, 2);
                break;
            case 3: //向左
                shot = new Shot(getX(), getY() + 20, 3);
                break;
        }

        //把新建的子弹加入到Vector中
        shots.add(shot);
        //启动子弹线程
        new Thread(shot).start();
    }
}
