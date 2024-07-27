package ccf_csp._2016._04;

import java.util.Scanner;

public class _2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] table = new int[15][10];
        int[][] next = new int[4][4];
        //界面
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 10; j++) {
                table[i][j] = scanner.nextInt();
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                next[i][j] = scanner.nextInt();
            }
        }
        int col = scanner.nextInt(); //离界面左边的距离
        int pos = 0;
        boolean op = true;
        while (op) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if ((next[i][j] == 1 && i + pos >= 15) || (next[i][j] == 1 && table[i + pos][j + col - 1] == 1)) {
                        op = false;
                    }
                }
            }
            pos++;
        }
        pos -= 2;
        //去除最后行全是0的行数
        int row = 4;
        boolean isRow = false;
        for (int i = row - 1; i >= 0; i--) {
            for (int j = 0; j < 4; j++) {
                if (next[i][j] == 1) {
                    isRow = true;
                    break;
                }
            }
            if (isRow) {
                break;
            } else {
                row--;
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < 4; j++) {
                table[i + pos][j + col - 1] |= next[i][j];
            }
        }
        //输出
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(table[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}

/*
import java.util.Scanner;

public class Main {
    static final int MAXN = 20;
    static int[][] g = new int[MAXN][MAXN];
    static int[][] patt = new int[MAXN][MAXN];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        for (int i = 1; i <= 15; i++)
            for (int j = 1; j <= 10; j++)
                g[i][j] = scanner.nextInt();
        for (int i = 1; i <= 4; i++)
            for (int j = 1; j <= 4; j++)
                patt[i][j] = scanner.nextInt();
        int c = scanner.nextInt();
        c -= 1;
        int pos = 0;
        boolean op = true;
        while (op) {
            // 物体往下移动一个
            ++pos;
            // 判断是否下降到底或者碰到别的方格了
            for (int i = 1; i <= 4; i++)
                for (int j = 1; j <= 4; j++)
                    if ((patt[i][j] == 1 && i + pos > 15) || (patt[i][j] == 1 && g[i + pos][j + c] == 1))
                        op = false;
        }
        pos -= 1;
        // 更新网格
        for (int i = 1; i <= 4; i++)
            for (int j = 1; j <= 4; j++)
                g[i + pos][j + c] |= patt[i][j];
        // 输出答案
        for (int i = 1; i <= 15; i++) {
            for (int j = 1; j <= 10; j++) {
                System.out.print(g[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
*/
