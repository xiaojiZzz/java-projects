package ccf_csp._2022._06;

import java.util.Scanner;

public class _2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), L = scanner.nextInt(), S = scanner.nextInt();
        int[][] trees = new int[n][2];
        int[][] map = new int[S + 1][S + 1];
        int[][] tmp;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            trees[i][0] = scanner.nextInt();
            trees[i][1] = scanner.nextInt();
        }
        for (int i = S; i >= 0; i--) {
            for (int j = 0; j < S + 1; j++) {
                map[i][j] = scanner.nextInt();
            }
        }
        for (int i = 0; i < n; i++) {
            int x = trees[i][0];
            int y = trees[i][1];
            tmp = build(S, L, x, y, n, trees);
            boolean flag = match(S, map, tmp);
            if (flag) {
                cnt++;
            }
        }
        System.out.print(cnt);
    }

    public static boolean match(int S, int[][] map, int[][] tmp) {
        for (int i = 0; i < S + 1; i++) {
            for (int j = 0; j < S + 1; j++) {
                if (map[i][j] != tmp[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int[][] build(int S, int L, int x, int y, int n, int[][] trees) {
        int[][] tmp = new int[S + 1][S + 1];
        tmp[0][0] = 1;
        if (x + S > L || y + S > L) {
            tmp[0][0] = -1;
            return tmp;
        }
        for (int i = 0; i < n; i++) {
            int X = trees[i][0];
            int Y = trees[i][1];
            if ((x <= X && x + S >= X) && (y <= Y && y + S >= Y)) {
                tmp[X - x][Y - y] = 1;
            }
        }
        return tmp;
    }
}
