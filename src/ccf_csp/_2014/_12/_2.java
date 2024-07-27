package ccf_csp._2014._12;

import java.util.Scanner;

public class _2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        if (n == 1) {
            System.out.println(scanner.nextInt());
            return;
        }
        int[][] matrix = new int[n][n];
        int[] res = new int[n * n];
        boolean[][] visit = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        int x = 0, y = 0;
        int index = 0;
        while (!(x == n - 1 && y == n - 1)) {
            if (y < n - 1 && !visit[x][y]) {
                res[index++] = matrix[x][y];
                visit[x][y] = true;
                y++;
            } else {
                res[index++] = matrix[x][y];
                visit[x][y] = true;
                x++;
            }
            while (!(x == n - 1 || y == 0) && !visit[x][y]) {
                res[index++] = matrix[x][y];
                visit[x][y] = true;
                y--;
                x++;
            }
            if (x < n - 1 && !visit[x][y]) {
                res[index++] = matrix[x][y];
                visit[x][y] = true;
                x++;
            } else {
                res[index++] = matrix[x][y];
                visit[x][y] = true;
                y++;
            }
            while (!(x == 0 || y == n - 1) && !visit[x][y]) {
                res[index++] = matrix[x][y];
                visit[x][y] = true;
                y++;
                x--;
            }
        }
        res[n * n - 1] = matrix[n - 1][n - 1];
        System.out.print(res[0]);
        for (int i = 1; i < n * n; i++) {
            System.out.print(" ");
            System.out.print(res[i]);
        }
    }
}

/*
public class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        int n;
        n = console.nextInt();
        int[][] a = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                a[i][j] = console.nextInt();
        //打印输出
        for (int i = 0; i < 2 * n - 1; i++) {//共2*n-1条斜线
            int s = i < n ? 0 : (i - n + 1);
            int e = i < n ? i : (n - 1);
            if (i % 2 == 0) {//第i条斜线编号是偶数，从左小打印到右上
                for (int j = s; j <= e; j++)
                    System.out.print(a[i - j][j] + " ");
            } else {//第i条斜线编号是奇数，从右上打印到左下
                for (int j = s; j <= e; j++)
                    System.out.print(a[j][i - j] + " ");
            }
        }
        console.close();
    }
}
*/

/*
import java.util.Scanner;

public class Main {
    static final int N = 510;
    static int n, cnt; // cnt统计已经遍历的点的个数
    static int[][] a = new int[N][N];
    static boolean[][] g = new boolean[N][N]; // 标记该点是否被遍历过
    static int[] dx = {0, 1, 1, -1};
    static int[] dy = {1, -1, 0, 1}; // 方向控制

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = scanner.nextInt();
            }
        }
        int i = 0, j = 0, t = 0; // t用来控制方向
        System.out.print(a[i][j] + " ");
        cnt++;
        g[i][j] = true;
        while (cnt < n * n) {
            int x = i + dx[t];
            int y = j + dy[t];
            if (x >= 0 && x < n && y >= 0 && y < n && !g[x][y]) {
                // 只有满足条件了，才更改i，j的值
                i = x;
                j = y;
                System.out.print(a[i][j] + " ");
                cnt++;
                g[i][j] = true;
                if (t == 0 || t == 2) {
                    t += 1; // 如果方向为向右或向下，改变方向
                }
            } else {
                t = (t + 1) % 4;
            }
        }
    }
}
 */