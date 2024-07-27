package hsp_java.small_game.tankgame5;

import java.util.Vector;

@SuppressWarnings({"all"})
public class EnemyTank extends Tank implements Runnable {
    //在敌人坦克类，使用Vector，保存对个Shot
    Vector<Shot> shots = new Vector<>();
    //增加成员，EnemyTank 可以得到敌人坦克的 Vector
    Vector<EnemyTank> enemyTanks = new Vector<>();
    boolean isLive = true;

    public EnemyTank(int x, int y) {
        super(x, y);
    }

    //这里提供方法，可以将MyPanel 的成员 Vector<EnemyTank> enemyTanks = new Vector<>() 设置到这里
    public void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }

    //编写方法，判断敌人坦克是否重叠，或者碰撞
    public boolean isTouchEnemyTank() {

        switch (this.getDirect()) {
            case 0: //向上
                //让当前的坦克和其他所有敌人坦克进行比较
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    //不和自己比较
                    if (enemyTank != this) {
                        //如果敌人坦克是上下方向
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            //当前坦克的左上角与其他坦克进行比较
                            if (this.getX() >= enemyTank.getX() && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY() && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                            //当前坦克的右上角与其他坦克进行比较
                            if (this.getX() + 40 >= enemyTank.getX() && this.getX() + 40 <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY() && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                        }
                        //如果敌人坦克是右左方向
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            //当前坦克的左上角与其他坦克进行比较
                            if (this.getX() >= enemyTank.getX() && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY() && this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }
                            //当前坦克的右上角与其他坦克进行比较
                            if (this.getX() + 40 >= enemyTank.getX() && this.getX() + 40 <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY() && this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 1: //向右
                //让当前的坦克和其他所有敌人坦克进行比较
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    //不和自己比较
                    if (enemyTank != this) {
                        //如果敌人坦克是上下方向
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            //当前坦克的右上角与其他坦克进行比较
                            if (this.getX() + 60 >= enemyTank.getX() && this.getX() + 60 <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY() && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                            //当前坦克的右下角与其他坦克进行比较
                            if (this.getX() + 60 >= enemyTank.getX() && this.getX() + 60 <= enemyTank.getX() + 40
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 60) {
                                return true;
                            }
                        }
                        //如果敌人坦克是右左方向
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            //当前坦克的右上角与其他坦克进行比较
                            if (this.getX() + 60 >= enemyTank.getX() && this.getX() + 60 <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY() && this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }
                            //当前坦克的右下角与其他坦克进行比较
                            if (this.getX() + 60 >= enemyTank.getX() && this.getX() + 60 <= enemyTank.getX() + 60
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 2: //向下
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    //不和自己比较
                    if (enemyTank != this) {
                        //如果敌人坦克是上下方向
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            //当前坦克的左下角与其他坦克进行比较
                            if (this.getX() >= enemyTank.getX() && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 60) {
                                return true;
                            }
                            //当前坦克的右下角与其他坦克进行比较
                            if (this.getX() + 40 >= enemyTank.getX() && this.getX() + 40 <= enemyTank.getX() + 40
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 60) {
                                return true;
                            }
                        }
                        //如果敌人坦克是右左方向
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            //当前坦克的坐下角与其他坦克进行比较
                            if (this.getX() >= enemyTank.getX() && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 40) {
                                return true;
                            }
                            //当前坦克的右下角与其他坦克进行比较
                            if (this.getX() + 40 >= enemyTank.getX() && this.getX() + 40 <= enemyTank.getX() + 60
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 3: //向左
                //让当前的坦克和其他所有敌人坦克进行比较
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    //不和自己比较
                    if (enemyTank != this) {
                        //如果敌人坦克是上下方向
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            //当前坦克的左上角与其他坦克进行比较
                            if (this.getX() >= enemyTank.getX() && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY() && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                            //当前坦克的左下角与其他坦克进行比较
                            if (this.getX() >= enemyTank.getX() && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 60) {
                                return true;
                            }
                        }
                        //如果敌人坦克是右左方向
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            //当前坦克的左上角与其他坦克进行比较
                            if (this.getX() >= enemyTank.getX() && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY() && this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }
                            //当前坦克的左下角与其他坦克进行比较
                            if (this.getX() >= enemyTank.getX() && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
        }
        return false;

    }

    @Override
    public void run() {
        while (true) {

            //如果没有子弹了 就创建一颗子弹
            if (isLive && shots.size() < 5) {
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
                        if (getY() > 0 && !isTouchEnemyTank()) { //防止坦克超出屏幕以及敌人坦克间的碰撞
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
                        if (getX() + 60 < 1000 && !isTouchEnemyTank()) {
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
                        if (getY() + 60 < 750 && !isTouchEnemyTank()) {
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
                        if (getX() > 0 && !isTouchEnemyTank()) {
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
