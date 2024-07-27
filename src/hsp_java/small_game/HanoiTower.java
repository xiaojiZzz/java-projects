package hsp_java.small_game;

public class HanoiTower {
    public static void main(String[] args) {
        Tower tower = new Tower();
        tower.move(4, 'A', 'B', 'C');
    }
}

class Tower {
//    方法
//    num 表示要移动的个数，a，b，c 表示 A 塔，B 塔，C塔
    public void move(int num, char a, char b, char c) {
//        如果只有一个盘 num = 1
        if (num == 1) {
            System.out.println(a + "->" + c);
        } else {
//            如果有多个盘，可以当作是两个盘，最下面的和上面的所有盘
//            先移动上面所有的盘到 b，借助 c
            move(num - 1, a, c, b);
//            把最下面的盘移动到 c
            System.out.println(a + "->" + c);
//            再把 b 的所有盘移动到 c，借助 a
            move(num - 1, b, a, c);
        }
    }
}
