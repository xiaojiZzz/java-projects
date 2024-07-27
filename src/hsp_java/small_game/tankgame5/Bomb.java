package hsp_java.small_game.tankgame5;

public class Bomb {
    int x, y;
    int life = 9; //炸弹的生命周期 大的数表示显示爆炸范围大的图片 反之 小的图片
    boolean isLive = true; //是否还存活

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //减少生命值
    public void lifeDown() { //配合图片出现爆炸效果
        if (life > 0) {
            life--;
        } else {
            isLive = false;
        }
    }
}
