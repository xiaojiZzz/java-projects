package hsp_java.small_game.tankgame4;

import java.util.Vector;

@SuppressWarnings({"all"})
public class EnemyTank extends Tank implements Runnable {
    //在敌人坦克类，使用Vector，保存对个Shot
    Vector<Shot> shots = new Vector<>();
    boolean isLive = true;

    public EnemyTank(int x, int y) {
        super(x, y);
    }

    @Override
    public void run() {
        while (true) {

            //如果没有子弹了 就创建一颗子弹
            if (isLive && shots.size() < 10) {
                Shot s = null;

                //判断坦克的方向来创建子弹
                switch (getDirect()) {
                    case 0: //向上
                        s = new Shot(getX() + 20, getY(), 0);
                        break;
                    case 1: //向右
                        s = new Shot(getX() + 60, getY() + 20, 1);
                        break;
                    case 2: //向下
                        s = new Shot(getX() + 20, getY() + 60, 2);
                        break;
                    case 3: //向左
                        s = new Shot(getX(), getY() + 20, 3);
                        break;
                }
                shots.add(s);
                new Thread(s).start();
            }
            //根据坦克的方向继续移动
            switch (getDirect()) {
                case 0: //向上
                    //让坦克保持一个方向走30步
                    for (int i = 0; i < 30; i++) {
                        if (getY() > 0) { //防止坦克超出屏幕
                            moveUp();
                        }
                        //休眠50ms
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 1: //向右
                    for (int i = 0; i < 30; i++) {
                        if (getX() + 60 < 1000) {
                            moveRight();
                        }
                        //休眠50ms
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 2: //向下
                    for (int i = 0; i < 30; i++) {
                        if (getY() + 60 < 750) {
                            moveDown();
                        }
                        //休眠50ms
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 3: //向左
                    for (int i = 0; i < 30; i++) {
                        if (getX() > 0) {
                            moveLeft();
                        }
                        //休眠50ms
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }

            //然后随机改变坦克的方向 0-3
            setDirect((int) (Math.random() * 4));

            //考虑线程什么情况下结束
            if (!isLive) {
                break;
            }
        }
    }
}
