package hsp_java.small_game.tankgame5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Vector;

@SuppressWarnings({"all"})
// 坦克大战的绘图区域
public class MyPanel extends JPanel implements KeyListener, Runnable { // 监听键盘事件
    //定义我的坦克
    HeroTank hero = null;
    //定义敌人坦克，放入到 Vector 集合内（考虑多线程问题）
    Vector<EnemyTank> enemyTanks = new Vector<>();
    //定义一个存放Node对象的Vector，用于恢复敌人坦克的坐标和方向
    Vector<Node> nodes = new Vector<>();
    //定义一个 Vector，用于存放炸弹
    //当子弹击中坦克时，就加入一个Bomb对象
    Vector<Bomb> bombs = new Vector<>();
    int enemyTankSize = 3;

    //定义三张炸弹图片，用于显示爆炸效果
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;

    public MyPanel(String key) {
        //先判断记录文件是否存在，如果存在，就正常执行，如果文件不存在，提示，只能开启新游戏，key="1"
        File file = new File(Recorder.getRecordFile());
        if (file.exists()) {
            nodes = Recorder.getNodesAndEnemyTankRec();
        }else {
            System.out.println("存档不存在，只能开启新游戏");
            key = "1";
        }
        //将MyPaneL对象的enemyTanks设置给Recorder的enemyTanks
        Recorder.setEnemyTanks(enemyTanks);
        hero = new HeroTank(500, 100); // 初始化自己的坦克
        hero.setSpeed(5);

        switch (key) {
            case "1": //开始新游戏
                //初始化敌人坦克
                for (int i = 0; i < enemyTankSize; i++) {
                    //创建一个敌人的坦克
                    EnemyTank enemyTank = new EnemyTank((100 * (i + 1)), 0);
                    //给敌人坦克设置方向
                    enemyTank.setDirect(2);
                    //启动坦克线程
                    new Thread(enemyTank).start();
                    //给敌人坦克加入一颗子弹
                    Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirect());
                    //将子弹放入敌人坦克的Vector成员里
                    enemyTank.shots.add(shot);
                    //启动shot
                    new Thread(shot).start();
                    enemyTanks.add(enemyTank);
                    //将 enemyTanks 设置给 enemyTank
                    enemyTank.setEnemyTanks(enemyTanks);
                }
                break;
            case "2": //继续上一把游戏
                //初始化敌人坦克
                for (int i = 0; i < nodes.size(); i++) {
                    Node node = nodes.get(i);
                    //创建一个敌人的坦克
                    EnemyTank enemyTank = new EnemyTank(node.getX(), node.getY());
                    //给敌人坦克设置方向
                    enemyTank.setDirect(node.getDirect());
                    //启动坦克线程
                    new Thread(enemyTank).start();
                    //给敌人坦克加入一颗子弹
                    Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirect());
                    //将子弹放入敌人坦克的Vector成员里
                    enemyTank.shots.add(shot);
                    //启动shot
                    new Thread(shot).start();
                    enemyTanks.add(enemyTank);
                    //将 enemyTanks 设置给 enemyTank
                    enemyTank.setEnemyTanks(enemyTanks);
                }
                break;
            default:
                System.out.println("你的输入有误，请重新输入（1/2）");
        }

        //初始化图片对象
        image1 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("bomb_1.png"));
        image2 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("bomb_2.png"));
        image3 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("bomb_3.png"));

    }

    //编写方法，显示我方击毁敌方坦克的信息
    public void showInfo(Graphics g) {

        //画出玩家的总成绩
        g.setColor(Color.BLACK);
        Font font = new Font("宋体", Font.BOLD, 25);
        g.setFont(font);

        g.drawString("您累积击毁敌方坦克：", 1020, 30);
        drawTank(1020, 60, g, 0, 0); //画出敌方坦克
        g.setColor(Color.BLACK); //这里需要重新设置成黑色
        g.drawString(Recorder.getAllEnemyTankNum() + "", 1080, 100);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);
        showInfo(g);

        //画出自己的坦克
        if (hero != null && hero.isLive) {
            drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 1);
        }

        //画出子弹
//        if (hero.shot != null && hero.shot.isLive == true) {
////            g.fill3DRect(hero.shot.x, hero.shot.y, 1, 1, false);
//            g.draw3DRect(hero.shot.x, hero.shot.y, 1, 1, false);
//        }
        for (int i = 0; i < hero.shots.size(); i++) {
            Shot shot = hero.shots.get(i);
            if (shot != null && shot.isLive == true) {
                g.draw3DRect(shot.x, shot.y, 1, 1, false);
            } else { //如果该shot子弹对象已经无效，就从集合里去除
                hero.shots.remove(shot);
            }
        }

        //如果bombs集合里有炸弹，就画出爆炸模样
        for (int i = 0; i < bombs.size(); i++) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Bomb bomb = bombs.get(i);
            //根据当前这个bomb对象的life值去画出相应的照片
            if (bomb.life > 6) {
                g.drawImage(image1, bomb.x, bomb.y, 60, 60, this);
            } else if (bomb.life > 3) {
                g.drawImage(image2, bomb.x, bomb.y, 60, 60, this);
            } else {
                g.drawImage(image3, bomb.x, bomb.y, 60, 60, this);
            }
            bomb.lifeDown();
            //如果bomb.life = 0 时，就把bomb对象移除
            if (bomb.life == 0) {
                bombs.remove(bomb);
            }
        }

        //画出敌人的坦克和子弹
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            //判断敌人坦克是否存活
            if (enemyTank.isLive) {
                drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 0);
                //画子弹
                for (int j = 0; j < enemyTank.shots.size(); j++) {
                    Shot shot = enemyTank.shots.get(j);
                    if (shot.isLive) {
                        g.draw3DRect(shot.x, shot.y, 1, 1, false);
                    } else {
                        enemyTank.shots.remove(shot);
                    }
                }
            }
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

    //如果我们的坦克发射多颗子弹，在判断子弹命中坦克前，就需要把子弹集合里的所有子弹都与坦克进行比较
    public void hitEnemyTank() {
        //遍历我们的子弹
        for (int j = 0; j < hero.shots.size(); j++) {
            Shot shot = hero.shots.get(j);
            //判断是否子弹是否击中坦克
            if (shot != null && shot.isLive) {
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    hitTank(shot, enemyTank);
                }
            }
        }
    }

    //编写方法，判断敌人的子弹是否命中我放的坦克
    public void hitHeroTank() {
        //遍历所有的敌人坦克
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            //遍历敌人坦克的所有子弹
            for (int j = 0; j < enemyTank.shots.size(); j++) {
                //取出子弹
                Shot shot = enemyTank.shots.get(j);
                //判断敌人子弹是否击中我方坦克
                if (hero.isLive && shot.isLive) {
                    hitTank(shot, hero);
                }
            }

        }
    }

    //编写方法，判断子弹是否命中坦克
    public void hitTank(Shot s, Tank tank) {
        //判断s击中坦克
        switch (tank.getDirect()) {
            case 0: //向上
            case 2: //向下
                if (s.x > tank.getX() && s.x < tank.getX() + 40 &&
                        s.y > tank.getY() && s.y < tank.getY() + 60) {
                    s.isLive = false;
                    tank.isLive = false;
                    //当子弹击中坦克，将坦克从集合里拿掉
                    enemyTanks.remove(tank);
                    //当我方击毁一个敌人坦克时，就对 allEnemyTankNum++
                    if (tank instanceof EnemyTank) { //tank是敌人坦克时
                        Recorder.addAllEnemyTankNum();
                    }
                    //创建Bomb对象，加入到bombs集合
                    Bomb bomb = new Bomb(tank.getX(), tank.getY());
                    bombs.add(bomb);
                }
                break;
            case 1: //向右
            case 3: //向左
                if (s.x > tank.getX() && s.x < tank.getX() + 60 &&
                        s.y > tank.getY() && s.y < tank.getY() + 40) {
                    s.isLive = false;
                    tank.isLive = false;
                    //当子弹击中坦克，将坦克从集合里拿掉
                    enemyTanks.remove(tank);
                    //当我方击毁一个敌人坦克时，就对 allEnemyTankNum++
                    if (tank instanceof EnemyTank) { //tank是敌人坦克时
                        Recorder.addAllEnemyTankNum();
                    }
                    //创建Bomb对象，加入到bombs集合
                    Bomb bomb = new Bomb(tank.getX(), tank.getY());
                    bombs.add(bomb);
                }
                break;
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
            if (hero.getY() > 0) {
                hero.moveUp();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_D) { //按下了 d 键
            hero.setDirect(1);
            if (hero.getX() + 60 < 1000) {
                hero.moveRight();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_S) { //按下了 s 键
            hero.setDirect(2);
            if (hero.getY() + 60 < 750) {
                hero.moveDown();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_A) { //按下了 a 键
            hero.setDirect(3);
            if (hero.getX() > 0) {
                hero.moveLeft();
            }
        }
        //如果用户按下J，则发射子弹
        if (e.getKeyCode() == KeyEvent.VK_J) {
            //判断hero的子弹是否销毁(发射一颗子弹)
//            if (hero.shot == null || !hero.shot.isLive) {
//                hero.shotEnemyTank();
//            }
            //发射多颗子弹
            hero.shotEnemyTank();
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() { //每隔100ms 重绘区域,刷新区域，子弹就移动了

        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //判断敌人坦克是否被命中
            hitEnemyTank();
            //判断我方坦克是否被命中
            hitHeroTank();
            this.repaint();
        }
    }
}
