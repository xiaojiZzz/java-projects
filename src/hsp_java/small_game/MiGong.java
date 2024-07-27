package hsp_java.small_game;

public class MiGong {
    public static void main(String[] args) {
//        先创建一个二维数组来表示迷宫模样 8*7
//        数组中 0 表示没有障碍物，1 表示障碍物
        int[][] map = new int[8][7];
//        将最上面和最下面一行全部设置为 1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
//        将最左边和最右边的都设置为 1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        map[3][1] = 1;
        map[3][2] = 1;
        map[2][2] = 1;

        System.out.println("======当前的迷宫情况=====");

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

//        使用findWay给老鼠找路
        T t1 = new T();
        boolean ok = t1.findWay(map, 1, 1);
        System.out.println(ok);

        System.out.println("======找路的情况如下=====");

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}

class T {
//    使用递归回溯的思想解决老鼠出迷宫
    public boolean findWay(int[][] map, int i, int j) {
//        findWay 方法就是专门来找出迷宫的路径，如果找到返回 true，反之返回 false，
//        map是迷宫，i，j，是在迷宫的位置，初始化的位置为（1，1），如果到（6，5）则出迷宫，
//        规定：0 表示可以走，1 表示障碍物，2 表示可以走(已经走过），3 表示走过但是是死路
//        当走到（6，5）为 2 时，表示找到出口，否则就继续找
//        迷宫找路的策略：下->右->上->左
        if (map[6][5] == 2) {
            return true;
        } else if (map[i][j] == 0) {//当前这个位置是 0 ，表示可以走
            map[i][j] = 2;
//            使用找路策略，来确定该位置是否可以走通
//            迷宫找路的策略：下->右->上->左
            if (findWay(map, i + 1, j)) {
                return true;
            } else if (findWay(map, i, j + 1)) {
                return true;
            } else if (findWay(map, i - 1, j)) {
                return true;
            } else if (findWay(map, i, j - 1)) {
                return true;
            } else {
                map[i][j] = 3;
                return false;
            }
        } else {
            return false;
        }
    }
}
