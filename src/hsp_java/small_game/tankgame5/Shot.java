package hsp_java.small_game.tankgame5;

@SuppressWarnings({"all"})
//射击子弹
public class Shot implements Runnable {

    int x;
    int y;
    int direct = 0;
    int speed = 2;
    boolean isLive = true; //子弹是否还存活

    public Shot(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    @Override
    public void run() { //射击

        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //根据方向改变 x y
            switch (direct) {
                case 0: //向上
                    y -= speed;
                    break;
                case 1: //向右
                    x += speed;
                    break;
                case 2: //向下
                    y += speed;
                    break;
                case 3: //向左
                    x -= speed;
                    break;
            }
            //输出子弹的坐标
//            System.out.println("子弹x=" + x + " y=" + y);
            //当子弹到达屏幕边界时，子弹销毁，以及碰到坦克时候
            if (!(x >= 0 && x <= 1000 && y >= 0 && y <= 750 && isLive)) {
//                System.out.println("子弹线程退出");
                isLive = false;
                break;
            }
        }
    }
}
